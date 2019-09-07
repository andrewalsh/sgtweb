package br.com.sgt.repository.impl;

import java.io.Serializable;
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
import br.com.sgt.entities.Socio;
import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.repository.api.SocioRepository;
import br.com.sgt.repository.filtro.FiltroSocio;

public class SocioDAO implements SocioRepository, Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Socio> dao;

	
	public SocioDAO() {
		
	}
	
	@PostConstruct
	public void init() {
		dao = new DAO<Socio>(em, Socio.class);
	}

	public Socio salvar(Socio socio) {
		Socio toReturn = new Socio();
		try {
			toReturn = dao.adiciona(socio);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public Socio atualizar(Socio socio) {
		Socio toReturn = new Socio();
		try {
			toReturn = dao.atualizar(socio);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public Socio buscarPorId(Long id) {
		Socio toReturn = new Socio();
		try {
			toReturn = dao.buscaPorId(id);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public List<SocioDTO> buscarPorFiltro(FiltroSocio filtroSocio) {
		List<SocioDTO> lista = new ArrayList<>();
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<SocioDTO> query = em.getCriteriaBuilder().createQuery(SocioDTO.class);
			Root<Socio> root = query.from(Socio.class);
			
			query.select(cb.construct(SocioDTO.class, 
					root.<Long>get("idSocio"),
					root.<Pessoa>get("pessoa").<String>get("nome"),
					root.<String>get("tipoSocio"),
					root.<Pessoa>get("pessoa").<String>get("telefoneCelular1"),
					root.<Pessoa>get("pessoa").<String>get("telefoneResidencial"))
					);
			
			query.where(whereClausule(filtroSocio, root, cb)
					.toArray(new Predicate[0]));
			
			lista = em.createQuery(query).getResultList();
			
		} catch (RuntimeException e) {
			throw e;
		}
		
		return lista;
	}
	
	
	public void excluir(Socio socio) {
		// TODO Auto-generated method stub
		
	}

	
	private List<Predicate> whereClausule(FiltroSocio filtro, Root<Socio> root, CriteriaBuilder cb){
		List<Predicate> predicates = new ArrayList<>();
		
		if(Objects.nonNull(filtro.getNome()) && !filtro.getNome().isEmpty()) {
			Path<String> nomePath = root.<Pessoa>get("pessoa").<String>get("nome");
			predicates.add(cb.like(nomePath, "%"+filtro.getNome()+"%"));
		}
		
		if(Objects.nonNull(filtro.getCpf()) && !filtro.getCpf().isEmpty()) {
			Path<String> cpfPath = root.<Pessoa>get("pessoa").<String>get("cpf");
			predicates.add(cb.equal(cpfPath, filtro.getCpf()));
		}
		
		return predicates;
	}
}
