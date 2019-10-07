package br.com.sgt.web.app.tarifa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.sgt.entities.Tarifa;
import br.com.sgt.repository.filtro.FiltroTarifa;
import br.com.sgt.service.api.TarifaService;

@Named("tarifaFormController")
@ViewScoped
public class TarifaFormController implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Tarifa tarifa;
	
	private List<Tarifa> tarifasCadastradas = new ArrayList<Tarifa>();
	
	@Inject
	private TarifaService tarifaService;
	
	
	@PostConstruct
	public void init() {
		try {
			FiltroTarifa filtroTarifa = new FiltroTarifa();
			tarifasCadastradas = tarifaService.buscarPorFiltro(filtroTarifa);
		} catch (RuntimeException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Ocorreu um erro: "+e.getMessage());
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		}
	}
	
	public void onRowDblClckSelect(SelectEvent event) {
		tarifa = (Tarifa)event.getObject();
	}
	
	public void salvar() {
		try {
			tarifaService.atualizar(tarifa);
			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,null, "Operação realizada com sucesso!");
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		} catch (RuntimeException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Ocorreu um erro: "+e.getMessage());
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		}
	}

	public Tarifa getTarifa() {
		if(Objects.isNull(tarifa)) {
			tarifa = new Tarifa();
		}
		return tarifa;
	}
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
	public List<Tarifa> getTarifasCadastradas() {
		return tarifasCadastradas;
	}
	public void setTarifasCadastradas(List<Tarifa> tarifasCadastradas) {
		this.tarifasCadastradas = tarifasCadastradas;
	}
}
