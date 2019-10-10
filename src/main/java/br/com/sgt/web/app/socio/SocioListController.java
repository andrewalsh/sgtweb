package br.com.sgt.web.app.socio;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.repository.filtro.FiltroSocio;
import br.com.sgt.service.api.SocioService;


@Named("sociosListController")
@RequestScoped
public class SocioListController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private FiltroSocio filtro = new FiltroSocio();
	
	private List<SocioDTO> socios = new ArrayList<>();
	
	private List<SocioDTO> sociosFiltrados = new ArrayList<>();
	
	private SocioDTO socio = new SocioDTO();
	
	@Inject
	SocioService socioService;
	

	@PostConstruct
	public void init() {
		try {
			socios = socioService.buscarPorFiltro(filtro);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
	}
	
	
	public void onRowSelect(SelectEvent event) {
		socio = (SocioDTO)event.getObject();
		System.out.println(socio);
    }
	
	public void onRowDblClckSelect(SelectEvent event) {
		socio = (SocioDTO)event.getObject();
		try {
			//FacesContext.getCurrentInstance().getExternalContext().redirect("/sgt/pages/socios_form.xhtml");
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect("socios_form.xhtml?dto="+getSocio().getIdSocio());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void filtrarListaPorNome() {
		try {
			socios = socioService.buscarPorFiltro(filtro);
			//RequestContext.getCurrentInstance().update("tblSocios");
		} catch (RuntimeException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Ocorreu um erro : "+e.getMessage());
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		}
	}
 
    public void onRowUnselect(UnselectEvent event) {
    }
    
    public void navega() {
		try {
			//FacesContext.getCurrentInstance().getExternalContext().redirect("/sgt/pages/socios_form.xhtml");
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect("socios_form.xhtml?dto="+getSocio());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<SocioDTO> getSocios() {
		return socios;
	}
	public void setSocios(List<SocioDTO> socios) {
		this.socios = socios;
	}
	public List<SocioDTO> getSociosFiltrados() {
		return sociosFiltrados;
	}
	public void setSociosFiltrados(List<SocioDTO> sociosFiltrados) {
		this.sociosFiltrados = sociosFiltrados;
	}
	public SocioDTO getSocio() {
		return socio;
	}
	public FiltroSocio getFiltro() {
		return filtro;
	}
	public void setFiltro(FiltroSocio filtro) {
		this.filtro = filtro;
	}
}
