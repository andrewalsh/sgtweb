package br.com.sgt.web.app.socio;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.sgt.entities.Pessoa;
import br.com.sgt.entities.Socio;

@Named("socioFormController")
@RequestScoped
public class SocioFormController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Socio socio;
	
	
	@PostConstruct
	public void init() {
		socioBuilder();
	}
	
	
	
	
	public Socio getSocio() {
		if(Objects.isNull(socio)) {
			socioBuilder();
		}
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}


	private void socioBuilder() {
		socio = new Socio();
		socio.setPessoa(new Pessoa());
	}

}
