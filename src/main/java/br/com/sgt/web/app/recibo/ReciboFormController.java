package br.com.sgt.web.app.recibo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.pattern.builder.ReciboBuilder;
import br.com.sgt.pattern.builder.ValorAutorizadoBuilder;
import br.com.sgt.repository.filtro.FiltroSocio;
import br.com.sgt.repository.filtro.FiltroValorAutorizado;
import br.com.sgt.service.api.ReciboService;
import br.com.sgt.service.api.SocioService;
import br.com.sgt.service.api.ValorAutorizadoService;

@Named("reciboFormController")
@ViewScoped
public class ReciboFormController implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Recibo recibo;
	
	private ValorAutorizado valorAutorizado;
	
	private Long idSocio;
	
	private String nomeSocio;
	
	@Inject
	private ValorAutorizadoService valorAutorizadoService;
	
	@Inject
	private ReciboService reciboService;
	
	@Inject
	private SocioService socioService;
	
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
		RequestContext.getCurrentInstance().update("formRecibo");
		//RequestContext.getCurrentInstance().execute("PF('popUpValorAutorizadoEditar').show()");
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
		//recibo.setValorAutorizado(valorAutorizado);
		//RequestContext.getCurrentInstance().update("formRecibo");
		//RequestContext.getCurrentInstance().execute("PF('popUpValorAutorizadoEditar').show()");
	}

	public void gerarRecibo() {
		try {
			Recibo toReturn = new ReciboBuilder().gerar();
			toReturn = reciboService.salvar(recibo);
			reciboService.enviarEmail(toReturn);
			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,null, "Operação realizada com sucesso!");
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
	        
	        init();
	        
	        RequestContext.getCurrentInstance().update("frmToolbar");
	        RequestContext.getCurrentInstance().update("formValorAutorizado");
	        RequestContext.getCurrentInstance().update("formRecibo");
		} catch (RuntimeException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Ocorreu um erro : "+e.getMessage());
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
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
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Digite pelo menos 3 caracteres para pesquisar");
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		}else {
			sociosFiltrados = null;
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Não existem Sócios com este nome! Verifique o nome digitado.");
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		}
	}
	
	private void popularSociosDTO() {
		FiltroSocio filtro = new FiltroSocio();
		try {
			sociosDTO = socioService.buscarPorFiltro(filtro);
		} catch (RuntimeException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Ocorreu um erro ao carregar a lista de Sócios. "+e.getMessage());
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
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
}
