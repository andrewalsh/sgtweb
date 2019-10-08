package br.com.sgt.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.sgt.dao.DAO;
import br.com.sgt.entities.Pessoa;
import br.com.sgt.entities.Usuario;
import br.com.sgt.entities.dto.UsuarioDTO;
import br.com.sgt.repository.api.UsuarioRepository;
import br.com.sgt.repository.filtro.FiltroUsuario;

public class UsuarioDAO implements UsuarioRepository {
	
	@Inject
	private EntityManager em;
	
	@SuppressWarnings("unused")
	private DAO<Usuario> dao;
	
	public UsuarioDAO() {
		
	}
	
	@PostConstruct
	void init(){
		dao = new DAO<Usuario>(em, Usuario.class);
	}

	public Usuario salavar(Usuario usuario) {
		
		return null;
	}

	public Usuario atualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UsuarioDTO> buscarPorFiltro(FiltroUsuario filtroUsuario) {
		List<UsuarioDTO> usuarios = new ArrayList<>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<UsuarioDTO> query = em.getCriteriaBuilder().createQuery(UsuarioDTO.class);
			Root<Usuario> root = query.from(Usuario.class);
			
			query.select(cb.construct(UsuarioDTO.class, 
					root.<Long>get("idUsuario"),
					root.<Pessoa>get("pessoa").<String>get("nome"),
					root.<Pessoa>get("pessoa").<String>get("cpf"),
					root.<Integer>get("idTerreiro")));
			
			query.where(whereClausule(filtroUsuario, root, cb)
					.toArray(new Predicate[0]));
			
			usuarios = em.createQuery(query).getResultList();
		} catch (RuntimeException e) {
			throw e;
		}
		return usuarios;
	}

	
	public UsuarioDTO login(FiltroUsuario filtroUsuario) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<UsuarioDTO> query = em.getCriteriaBuilder().createQuery(UsuarioDTO.class);
			Root<Usuario> root = query.from(Usuario.class);
			
			query.select(cb.construct(UsuarioDTO.class, 
					root.<Long>get("idUsuario"),
					root.<Pessoa>get("pessoa").<String>get("nome"),
					root.<Pessoa>get("pessoa").<String>get("cpf"),
					root.<Integer>get("idTerreiro")));
			
			query.where(whereClausule(filtroUsuario, root, cb)
					.toArray(new Predicate[0]));
			Object obj = em.createQuery(query).getSingleResult();
			
			return new UsuarioDTO(Long.valueOf(obj.toString()), obj.toString(), obj.toString(), Integer.valueOf(obj.toString()));
		} catch (RuntimeException e) {
			throw e;
		}
	}

	
	public void excluir(Usuario usuario) {
		// TODO Auto-generated method stub

	}
	
	private List<Predicate> whereClausule(FiltroUsuario filtro, Root<Usuario> root, CriteriaBuilder cb){
		List<Predicate> predicates = new ArrayList<>();
		
		if(Objects.nonNull(filtro.getLogin()) && !filtro.getLogin().isEmpty()) {
			Path<String> loginPath = root.<Pessoa>get("pessoa").<String>get("cpf");
			predicates.add(cb.equal(loginPath, filtro.getLogin()));
		}
		
		if(Objects.nonNull(filtro.getSenha()) && !filtro.getSenha().isEmpty()) {
			Path<String> senhaPath = root.<String>get("senha");
			predicates.add(cb.equal(senhaPath, filtro.getSenha()));
		}
		
		return predicates;
	}

}
