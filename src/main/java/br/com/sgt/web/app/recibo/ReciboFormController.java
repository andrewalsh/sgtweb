package br.com.sgt.web.app.recibo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.Terreiro;
import br.com.sgt.entities.UltimoPagamentoDaTarifa;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.pattern.builder.ValorAutorizadoBuilder;
import br.com.sgt.pattern.observer.recibo.ImprimirRecibo;
import br.com.sgt.repository.filtro.FiltroSocio;
import br.com.sgt.repository.filtro.FiltroUltimoPagamento;
import br.com.sgt.repository.filtro.FiltroValorAutorizado;
import br.com.sgt.service.api.ReciboService;
import br.com.sgt.service.api.SocioService;
import br.com.sgt.service.api.TerreiroService;
import br.com.sgt.service.api.UltimoPagamentoService;
import br.com.sgt.service.api.ValorAutorizadoService;

@Named("reciboFormController")
@ViewScoped
public class ReciboFormController implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Recibo recibo;
	
	private ValorAutorizado valorAutorizado;
	
	private UltimoPagamentoDaTarifa ultimoPagamentoDaTarifa = new UltimoPagamentoDaTarifa();
	
	private Long idSocio;
	
	private String nomeSocio;
	
	private boolean exibeFormUltimoPagamento = false;
	
	@Inject
	private ValorAutorizadoService valorAutorizadoService;
	
	@Inject
	private ReciboService reciboService;
	
	@Inject
	private SocioService socioService;
	
	@Inject
	private UltimoPagamentoService ultimoPagamentoService;
	
	@Inject
	private TerreiroService terreiroService;
	
	@Inject
	private ImprimirRecibo imprimirRecibo;
	
	private FiltroValorAutorizado filtroValorAutorizado = new FiltroValorAutorizado();
	
	private List<ValorAutorizado> valoresAutorizados = new ArrayList<ValorAutorizado>();
	
	private List<SocioDTO> sociosDTO = new ArrayList<>();
	
	
	
	@PostConstruct
	public void init() {
		valorAutorizado = new ValorAutorizadoBuilder().gerar();
		inicializaRecibo();
		try {
			popularSociosDTO();
			valoresAutorizados = valorAutorizadoService.listarPorFiltro(filtroValorAutorizado);
			recibo.setTerreiro(terreiroService.buscarTerreiro());
		} catch (RuntimeException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Ocorreu um erro: "+e.getMessage());
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		}
	}
	
	public void onRowDblClckSelect(SelectEvent event) {
		valorAutorizado = (ValorAutorizado)event.getObject();
		recibo.setValorAutorizado(valorAutorizado);
		ultimoPagamento(valorAutorizado);
		RequestContext.getCurrentInstance().update("formRecibo");
	}
	
	public void onRowSocioDblClckSelect(SelectEvent event) {
		SocioDTO dto = (SocioDTO)event.getObject();
		filtroValorAutorizado.setIdSocio(dto.getIdSocio());
		try {
			valoresAutorizados = valorAutorizadoService.listarPorFiltro(filtroValorAutorizado);
			RequestContext.getCurrentInstance().update("formValorAutorizado");
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void gerarRecibo() {
		try {
			reciboService.salvar(recibo);
			notificarSucesso("Operação realizada com sucesso!");
	        exibeFormUltimoPagamento = false;
	        init();
	        
	        RequestContext.getCurrentInstance().update("frmToolbar");
	        RequestContext.getCurrentInstance().update("formValorAutorizado");
	        RequestContext.getCurrentInstance().update("formRecibo");
	        
		} catch (RuntimeException e) {
			notificarErro(e.getMessage());
		}
	}

	public void getSocioDTO(String query) {
		List<SocioDTO> sociosFiltrados = new ArrayList<>();
		FiltroSocio filtro = new FiltroSocio();
		
		if (query != null && !query.equals("") && query.length() > 2) {
			filtro.setNome(query);
			sociosFiltrados = socioService.buscarPorFiltro(filtro);
			sociosDTO = sociosFiltrados;
		} else if(query.equals("")){
			notificarErro("Digite pelo menos 3 caracteres para pesquisar");
		}else {
			sociosFiltrados = null;
			notificarErro("Não existem Sócios com este nome! Verifique o nome digitado.");
		}
	}
	
	public void listarValoresAutorizadosPorSocio() {
		try {
			valoresAutorizados = valorAutorizadoService.listarPorFiltro(filtroValorAutorizado);
		} catch (Exception e) {
			notificarErro(e.getMessage());
		}
	}
	
	public void imprimir() {
		imprimirRecibo.executa(recibo);
	}
	
	private void ultimoPagamento(ValorAutorizado valorAutorizado) {
		try {
			recibo.setUltimoPagamento(ultimoPagamentoService.
					buscarPorFiltro(new FiltroUltimoPagamento(valorAutorizado.getIdValorAutorizado())));
			renderizarFormUltimoPagamento();
			RequestContext.getCurrentInstance().update("formUltimoPagamento");
		} catch (RuntimeException e) {
			notificarErro(e.getMessage());
		}
	}
	
	private void popularSociosDTO() {
		FiltroSocio filtro = new FiltroSocio();
		try {
			sociosDTO = socioService.buscarPorFiltro(filtro);
		} catch (RuntimeException e) {
			
		}
	}
	
	private void notificarSucesso(String sucesso) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,null, sucesso);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
	}
	
	private void notificarErro(String erro) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
				"Ocorreu um erro. "+erro);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
	}
	
	private void renderizarFormUltimoPagamento() {
		if(Objects.nonNull(recibo.getUltimoPagamento().getIdUltimoPagamento()))
			exibeFormUltimoPagamento = true;
		else
			exibeFormUltimoPagamento = false;
			
	}
	
	private void inicializaRecibo() {
		if(Objects.isNull(recibo)) {
			recibo = new Recibo();
			recibo.setDataPagamento(new Date());
			recibo.setTerreiro(new Terreiro());
		}
	}
	
	public List<ValorAutorizado> getValoresAutorizados() {
		return valoresAutorizados;
	}

	public List<SocioDTO> getSociosDTO() {
		return sociosDTO;
	}

	public void setSociosDTO(List<SocioDTO> sociosDTO) {
		this.sociosDTO = sociosDTO;
	}

	public Recibo getRecibo() {
		if(Objects.isNull(recibo)) {
			recibo.setValorAutorizado(new ValorAutorizado());
			recibo.setTerreiro(new Terreiro());
		}
		return recibo;
	}

	public void setRecibo(Recibo recibo) {
		this.recibo = recibo;
	}

	public ValorAutorizado getValorAutorizado() {
		return valorAutorizado;
	}

	public void setValorAutorizado(ValorAutorizado valorAutorizado) {
		this.valorAutorizado = valorAutorizado;
	}

	public Long getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(Long idSocio) {
		this.idSocio = idSocio;
	}

	public String getNomeSocio() {
		return nomeSocio;
	}

	public void setNomeSocio(String nomeSocio) {
		this.nomeSocio = nomeSocio;
	}

	public FiltroValorAutorizado getFiltroValorAutorizado() {
		return filtroValorAutorizado;
	}

	public void setFiltroValorAutorizado(FiltroValorAutorizado filtroValorAutorizado) {
		this.filtroValorAutorizado = filtroValorAutorizado;
	}

	public UltimoPagamentoDaTarifa getUltimoPagamentoDaTarifa() {
		return ultimoPagamentoDaTarifa;
	}

	public void setUltimoPagamentoDaTarifa(UltimoPagamentoDaTarifa ultimoPagamentoDaTarifa) {
		this.ultimoPagamentoDaTarifa = ultimoPagamentoDaTarifa;
	}

	public boolean isExibeFormUltimoPagamento() {
		return exibeFormUltimoPagamento;
	}
}
