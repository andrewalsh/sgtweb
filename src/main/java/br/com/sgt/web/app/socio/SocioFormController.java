package br.com.sgt.web.app.socio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.sgt.entities.Pessoa;
import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.Socio;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.helper.MessageHelper;
import br.com.sgt.pattern.observer.recibo.ImprimirRecibo;
import br.com.sgt.repository.filtro.FiltroRecibo;
import br.com.sgt.repository.filtro.FiltroTarifa;
import br.com.sgt.service.api.ReciboService;
import br.com.sgt.service.api.SocioService;
import br.com.sgt.service.api.TarifaService;
import br.com.sgt.service.api.ValorAutorizadoService;

@Named("socioFormController")
@ViewScoped
public class SocioFormController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private SocioDTO dto = new SocioDTO();
	
	private Socio socio;
	
	private Tarifa tarifa = new Tarifa();
	
	private ValorAutorizado va;
	
	private List<Tarifa> tarifas = new ArrayList<>();
	
	private List<ValorAutorizado> valoresAutorizados = new ArrayList<>();
	
	private List<Recibo> recibosDoSocio = new ArrayList<>();
	
	private FiltroTarifa filtroTarifa = new FiltroTarifa();
	
	private BigDecimal valorTarifa = BigDecimal.ZERO;
	
	private BigDecimal descontoOuAcrescimo = BigDecimal.ZERO;
	
	private Long idTarifa;
	
	private String action;
	
	@Inject
	private SocioService socioService;
	
	@Inject
	private TarifaService tarifaService;
	
	@Inject
	private ValorAutorizadoService valorAutorizadoService;
	
	@Inject
	private ReciboService reciboService;
	
	@Inject
	private MessageHelper helper;
	
	@Inject
	private ImprimirRecibo imprimirRecibo;
	
	@PostConstruct
	public void init() {
		try {
			tarifas = tarifaService.buscarPorFiltro(filtroTarifa);
		} catch (RuntimeException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Ocorreu um erro ao tentar carregar a lista de Tarifas: "+e.getMessage());
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		}
		va = valorAutorizadoService.valorAutorizadoBuilder();
		//getParams();
	}
	
	
	public void salvar() {
		try {
			socio = socioService.salvar(socio);
			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,null, "Operação realizada com sucesso!");
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
	        
		} catch (RuntimeException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Ocorreu um erro : "+e.getMessage());
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		}
	}
	
	public void convertIdTarifa() {
		try {
			tarifa = tarifaService.buscarPorId(idTarifa);
			va.setTarifa(tarifa);
		} catch (RuntimeException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Ocorreu um erro ao carregar a Tarifa selecionada: "+e.getMessage());
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		}
	}
	
	public void getParams(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String,String> parametro = externalContext.getRequestParameterMap();
		String valor = parametro.get("dto");
		popularSocio(valor);
    }
	
	public void addValorAutorizado() {
		
	}
	
	
	public void abirPopUpAlteracaoDeValor() {
		action = "NOVO";
		RequestContext.getCurrentInstance().update("formPopUpNovo");
		RequestContext.getCurrentInstance().execute("PF('popUpValorAutorizadoNovo').show()");
	}
	
	public void vincularAutorizacaoDeValor() {
		
	}
	
	public void popularSocio() {
		
	}
	
	public void onRowDblClckSelect(SelectEvent event) {
		action = "EDITAR";
		va = (ValorAutorizado)event.getObject();
		RequestContext.getCurrentInstance().update("formPopUpEditar");
		RequestContext.getCurrentInstance().execute("PF('popUpValorAutorizadoEditar').show()");
	}
	
	public void onRowReciboDblClckSelect(SelectEvent event) {
		Recibo recibo = new Recibo();
		recibo = (Recibo) event.getObject();
		imprimirRecibo.executa(recibo);
	}
	
	public void salvarValorAutorizado() {
		try {
			va.setSocio(socio);
			valorAutorizadoService.salvar(va);
			helper.notificacaoSucesso("Operação realizada com sucesso.");
			listarValoresAutorizadosPorSocio();
		} catch (RuntimeException e) {
			helper.notificarErro(e.getMessage());
		}
	}
	
	public void atribuiValorLiquidoValorAutorizado() {
		va.setValorLiquido(tarifa.getValor());
	}
	
	public void novo() {
		this.socio = new Socio();
		this.socio.setPessoa(new Pessoa());
		va = valorAutorizadoService.valorAutorizadoBuilder();
	}
	
	
	private void popularSocio(String id) {
		Long idSocio = null;
		try {
			idSocio = Long.valueOf(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(Objects.nonNull(idSocio)) {
			try {
				socio = socioService.buscarPorId(idSocio);
				listarValoresAutorizadosPorSocio();
				listarRecibosPorSocio();
			} catch (RuntimeException e) {
				helper.notificarErro(e.getMessage());
			}
		}
	}
	
	
	private void listarValoresAutorizadosPorSocio() {
		try {
			valoresAutorizados = valorAutorizadoService.listarPorSocio(socio);
		} catch (RuntimeException e) {
			helper.notificarErro(e.getMessage());
		}
	}
	
	private void listarRecibosPorSocio() {
		FiltroRecibo filtroRecibo = new FiltroRecibo();
		try {
			filtroRecibo.setIdSocio(socio.getIdSocio());
			filtroRecibo.setAnoBase(anoCorrente());
			recibosDoSocio = reciboService.listar(filtroRecibo);
		} catch (RuntimeException e) {
			helper.notificarErro(e.getMessage());
		}
	}
	
	/*private void notificarErro(String erro) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
				"Ocorreu um erro : "+erro);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
	}
	
	private void notificacaoSucesso(String sucesso) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, null, sucesso);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
	}*/
	
	private int anoCorrente() {
		Calendar calendar = Calendar.getInstance();
		int ano = calendar.get(Calendar.YEAR);
		return ano;
	}
	
	/*
	 * GETTERS AND SETTERS 
	 * */
	
	public Socio getSocio() {
		if(Objects.isNull(socio)) {
			socio = new Socio();
			socio.setPessoa(new Pessoa());
		}
		return socio;
	}
	
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	
	public SocioDTO getDto() {
		return dto;
	}
	
	public void setDto(SocioDTO dto) {
		this.dto = dto;
	}

	public List<Tarifa> getTarifas() {
		return tarifas;
	}
	
	public void setTarifas(List<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}
	
	public List<ValorAutorizado> getValoresAutorizados() {
		return valoresAutorizados;
	}
	
	public void setValoresAutorizados(List<ValorAutorizado> valoresAutorizados) {
		this.valoresAutorizados = valoresAutorizados;
	}
	
	public List<Recibo> getRecibosDoSocio() {
		return recibosDoSocio;
	}
	
	public BigDecimal getValorTarifa() {
		return valorTarifa;
	}
	
	public void setValorTarifa(BigDecimal valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
	
	public void setIdTarifa(Long idTarifa) {
		this.idTarifa = idTarifa;
	}

	public Long getIdTarifa() {
		return idTarifa;
	}
	
	public BigDecimal getDescontoOuAcrescimo() {
		return descontoOuAcrescimo;
	}
	
	public void setDescontoOuAcrescimo(BigDecimal descontoOuAcrescimo) {
		this.descontoOuAcrescimo = descontoOuAcrescimo;
	}

	public ValorAutorizado getVa() {
		return va;
	}

	public void setVa(ValorAutorizado va) {
		this.va = va;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
