package br.com.sgt.web.app.recibo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.UltimoPagamentoDaTarifa;
import br.com.sgt.entities.Usuario;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.entities.dto.UsuarioDTO;
import br.com.sgt.pattern.builder.ReciboBuilder;
import br.com.sgt.pattern.builder.ValorAutorizadoBuilder;
import br.com.sgt.repository.filtro.FiltroSocio;
import br.com.sgt.repository.filtro.FiltroUltimoPagamento;
import br.com.sgt.repository.filtro.FiltroValorAutorizado;
import br.com.sgt.service.api.ReciboService;
import br.com.sgt.service.api.SocioService;
import br.com.sgt.service.api.UltimoPagamentoService;
import br.com.sgt.service.api.UsuarioService;
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
	
	@Inject
	private ValorAutorizadoService valorAutorizadoService;
	
	@Inject
	private ReciboService reciboService;
	
	@Inject
	private SocioService socioService;
	
	@Inject
	private UltimoPagamentoService ultimoPagamentoService;
	
	@Inject
	UsuarioService usuarioService;
	
	private FiltroValorAutorizado filtroValorAutorizado = new FiltroValorAutorizado();
	
	private List<ValorAutorizado> valoresAutorizados = new ArrayList<ValorAutorizado>();
	
	private List<SocioDTO> sociosDTO = new ArrayList<>();
	
	
	
	@PostConstruct
	public void init() {
		valorAutorizado = new ValorAutorizadoBuilder().gerar();
		recibo = new ReciboBuilder().gerar();
		try {
			popularSociosDTO();
			valoresAutorizados = valorAutorizadoService.listarPorFiltro(filtroValorAutorizado);
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
			Recibo toReturn = new ReciboBuilder().gerar();
			toReturn = reciboService.salvar(recibo, ultimoPagamentoDaTarifa);
			reciboService.enviarEmail(toReturn);
			notificarSucesso("Opera��o realizada com sucesso!");
	        
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
			notificarErro("N�o existem S�cios com este nome! Verifique o nome digitado.");
		}
	}
	
	public void listarValoresAutorizadosPorSocio() {
		try {
			valoresAutorizados = valorAutorizadoService.listarPorFiltro(filtroValorAutorizado);
		} catch (Exception e) {
			notificarErro(e.getMessage());
		}
	}
	
	private void ultimoPagamento(ValorAutorizado valorAutorizado) {
		try {
			ultimoPagamentoDaTarifa = ultimoPagamentoService.
					buscarPorFiltro(new FiltroUltimoPagamento(valorAutorizado.getIdValorAutorizado()));
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
}
