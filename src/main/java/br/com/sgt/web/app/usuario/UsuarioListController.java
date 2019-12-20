package br.com.sgt.web.app.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sgt.entities.dto.UsuarioDTO;
import br.com.sgt.repository.filtro.FiltroUsuario;
import br.com.sgt.service.api.UsuarioService;


@Named("usuarioListController")
@ViewScoped
public class UsuarioListController implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private static final String ATIVO = "S";
	
	private static final String INATIVO = "N";
	
	
	
	@Inject
	private UsuarioService service;
	
	private List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
	
	private FiltroUsuario filtroUsuario = new FiltroUsuario();
	
	
	@PostConstruct
	public void init() {
		filtroUsuario.setAtivo(ATIVO);
		usuarios = service.listarUsuarios(filtroUsuario);
	}
	
	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}

}
