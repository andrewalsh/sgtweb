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

import org.primefaces.component.toolbar.ToolbarRenderer;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.pattern.builder.ReciboBuilder;
import br.com.sgt.pattern.builder.ValorAutorizadoBuilder;
import br.com.sgt.repository.filtro.FiltroValorAutorizado;
import br.com.sgt.service.api.ReciboService;
import br.com.sgt.service.api.ValorAutorizadoService;

@Named("reciboFormController")
@ViewScoped
public class ReciboFormController implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Recibo recibo;
	
	private ValorAutorizado valorAutorizado;
	
	@Inject
	private ValorAutorizadoService valorAutorizadoService;
	
	@Inject
	private ReciboService reciboService;
	
	private FiltroValorAutorizado filtroValorAutorizado = new FiltroValorAutorizado();
	
	private List<ValorAutorizado> valoresAutorizados = new ArrayList<ValorAutorizado>();
	
	
	@PostConstruct
	public void init() {
		valorAutorizado = new ValorAutorizadoBuilder().gerar();
		recibo = new ReciboBuilder().gerar();
		try {
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

	public void gerarRecibo() {
		try {
			Recibo toReturn = new ReciboBuilder().gerar();
			toReturn = reciboService.salvar(recibo);
			reciboService.enviarEmail(toReturn);
			
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

	
	public List<ValorAutorizado> getValoresAutorizados() {
		return valoresAutorizados;
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
}
