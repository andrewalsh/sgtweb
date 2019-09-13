package br.com.sgt.web.app.socio;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.sgt.entities.Socio;
import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.infra.api.ListInterface;
import br.com.sgt.repository.filtro.FiltroSocio;
import br.com.sgt.service.api.SocioService;
import br.com.sgt.web.PagesNavigation;


@Named("sociosListController")
@RequestScoped
public class SocioListController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private FiltroSocio filtro = new FiltroSocio();
	
	private List<SocioDTO> socios = new ArrayList<>();
	
	SocioDTO socio;
	
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
 
    public void onRowUnselect(UnselectEvent event) {
    }
    
    public void navega() {
		try {
			//FacesContext.getCurrentInstance().getExternalContext().redirect("/sgt/pages/socios_form.xhtml");
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect("socios_form.xhtml");
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

}
