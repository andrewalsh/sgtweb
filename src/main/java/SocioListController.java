import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.sgt.entities.Socio;
import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.repository.filtro.FiltroSocio;
import br.com.sgt.service.api.SocioService;


@Named("sociosController")
@RequestScoped
public class SocioListController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private FiltroSocio filtro = new FiltroSocio();
	
	private List<SocioDTO> socios = new ArrayList<>();
	
	
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
        @SuppressWarnings("unused")
		SocioDTO socio = (SocioDTO)event.getObject();
    }
 
    public void onRowUnselect(UnselectEvent event) {
        
    }


	public List<SocioDTO> getSocios() {
		return socios;
	}


	public void setSocios(List<SocioDTO> socios) {
		this.socios = socios;
	}
	
	

}
