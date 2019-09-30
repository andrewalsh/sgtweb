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
import br.com.sgt.entities.Socio;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.repository.api.ValorAutorizadoRepository;
import br.com.sgt.repository.filtro.FiltroValorAutorizado;

public class ValorAutorizadoDAO implements ValorAutorizadoRepository, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	private DAO<ValorAutorizado> dao;
	

	public ValorAutorizadoDAO() {
		
	}

	@PostConstruct
	void init() {
		dao = new DAO<ValorAutorizado>(em, ValorAutorizado.class);
	}
	
	public ValorAutorizado salavar(ValorAutorizado valorAutorizado) {
		try {
			return dao.adiciona(valorAutorizado);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public ValorAutorizado atualizar(ValorAutorizado valorAutorizado) {
		try {
			return dao.atualizar(valorAutorizado);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public ValorAutorizado buscaPorId(Long id) {
		ValorAutorizado toReturn = new ValorAutorizado();
		try {
			toReturn = dao.buscaPorId(id);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public List<ValorAutorizado> buscarPorFiltro(FiltroValorAutorizado filtroValorAutorizado) {
		List<ValorAutorizado> lista = new ArrayList<>();
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ValorAutorizado> query = em.getCriteriaBuilder().createQuery(ValorAutorizado.class);
			Root<ValorAutorizado> root = query.from(ValorAutorizado.class);
			
			query.where(whereClausule(filtroValorAutorizado, root, cb)
					.toArray(new Predicate[0]));
			
			lista = em.createQuery(query).getResultList();
			
		} catch (RuntimeException e) {
			throw e;
		}
		
		return lista;
	}

	public void excluir(ValorAutorizado valorAutorizado) {
		// TODO Auto-generated method stub

	}
	
	private List<Predicate> whereClausule(FiltroValorAutorizado filtro, Root<ValorAutorizado> root, CriteriaBuilder cb){
		List<Predicate> predicates = new ArrayList<>();
		
		if(Objects.nonNull(filtro.getIdSocio())) {
			Path<Long> idSocioPath = root.<Socio>get("socio").<Long>get("idSocio");
			predicates.add(cb.equal(idSocioPath, filtro.getIdSocio()));
		}
		
		return predicates;
	}

}
