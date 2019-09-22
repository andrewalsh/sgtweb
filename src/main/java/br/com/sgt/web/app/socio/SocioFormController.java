package br.com.sgt.web.app.socio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import br.com.sgt.entities.Pessoa;
import br.com.sgt.entities.Socio;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.repository.filtro.FiltroTarifa;
import br.com.sgt.service.api.SocioService;
import br.com.sgt.service.api.TarifaService;

@Named("socioFormController")
@ViewScoped
public class SocioFormController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private SocioDTO dto = new SocioDTO();
	
	private Socio socio;
	
	private Tarifa tarifa = new Tarifa();
	
	private List<Tarifa> tarifas = new ArrayList<>();
	
	private FiltroTarifa filtroTarifa = new FiltroTarifa();
	
	private BigDecimal valorTarifa = BigDecimal.ZERO;
	
	private BigDecimal descontoOuAcrescimo = BigDecimal.ZERO;
	
	private Long idTarifa;
	
	@Inject
	private SocioService socioService;
	
	@Inject
	private TarifaService tarifaService;
	
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
	}
	
	
	public void salvar() {
		try {
			socioService.salvar(socio);
			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,null, "Sócio cadastrado com sucesso!");
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
	        
		} catch (RuntimeException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Ocorreu um erro : "+e.getMessage());
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		}
	}
	
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


	public Long getIdTarifa() {
		return idTarifa;
	}
	
	public BigDecimal getDescontoOuAcrescimo() {
		return descontoOuAcrescimo;
	}
	public void setDescontoOuAcrescimo(BigDecimal descontoOuAcrescimo) {
		this.descontoOuAcrescimo = descontoOuAcrescimo;
	}


	public void setIdTarifa(Long idTarifa) {
		this.idTarifa = idTarifa;
		try {
			tarifa = tarifaService.buscarPorId(idTarifa);
		} catch (RuntimeException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Ocorreu um erro ao carregar a Tarifa selecionada: "+e.getMessage());
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        facesContext.addMessage(null, facesMessage);
		}
	}
	
	
	public void abirPopUpAlteracaoDeValor() {
		
	}
	
	public void vincularAutorizacaoDeValor() {
		socioService.associarValorAutorizadoParaCadastro(tarifa, socio);
	}
	
	public void popularSocio() {
		
	}
	
	public void getParams(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String,String> parametro = externalContext.getRequestParameterMap();
		String valor = parametro.get("dto");
		popularSocio(valor);
    }
	
	private void popularSocio(String id) {
		if(Objects.nonNull(id)) {
			try {
				socio = socioService.buscarPorId(Long.valueOf(id));
				System.out.println(socio);
			} catch (RuntimeException e) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
						"Ocorreu um erro ao carregar os dados do sócio selecionado: "+e.getMessage());
		        FacesContext facesContext = FacesContext.getCurrentInstance();
		        facesContext.addMessage(null, facesMessage);
			}
		}
	}
	
}
