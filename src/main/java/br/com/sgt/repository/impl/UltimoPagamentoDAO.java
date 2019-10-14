package br.com.sgt.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.sgt.dao.DAO;
import br.com.sgt.entities.UltimoPagamentoDaTarifa;
import br.com.sgt.repository.api.UltimoPagamentoRepository;
import br.com.sgt.repository.filtro.FiltroUltimoPagamento;

public class UltimoPagamentoDAO implements UltimoPagamentoRepository, Serializable{

	
	private static final long serialVersionUID = 1L;

	
	private DAO<UltimoPagamentoDaTarifa> dao;
	
	@Inject
	private EntityManager em;
	
	public UltimoPagamentoDAO() {
		
	}
	
	@PostConstruct
	void init() {
		dao = new DAO<UltimoPagamentoDaTarifa>(em, UltimoPagamentoDaTarifa.class);
	}
	
	@Override
	public UltimoPagamentoDaTarifa salvar(UltimoPagamentoDaTarifa ultimoPagamento) {
		try {
			return dao.adiciona(ultimoPagamento);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public UltimoPagamentoDaTarifa atualizar(UltimoPagamentoDaTarifa ultimoPagamento) {
		try {
			return dao.atualizar(ultimoPagamento);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	@Override
	public UltimoPagamentoDaTarifa buscarPorFiltro(FiltroUltimoPagamento filtroUltimoPagamento) {
		UltimoPagamentoDaTarifa ultimoPagamento = new UltimoPagamentoDaTarifa();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<UltimoPagamentoDaTarifa> query = em.getCriteriaBuilder().createQuery(UltimoPagamentoDaTarifa.class);
			Root<UltimoPagamentoDaTarifa> root = query.from(UltimoPagamentoDaTarifa.class);
			
			query.where(whereClausule(filtroUltimoPagamento, root, cb)
					.toArray(new Predicate[0]));
			
			ultimoPagamento = em.createQuery(query).getSingleResult();
			
		} catch (RuntimeException e) {
			if(e instanceof NoResultException ){
				return ultimoPagamento;
			}
			else
				throw e;
		}
		return ultimoPagamento;
	}
	
	private List<Predicate> whereClausule(FiltroUltimoPagamento filtro, Root<UltimoPagamentoDaTarifa> root, CriteriaBuilder cb){
		List<Predicate> predicates = new ArrayList<>();
		
		if(Objects.nonNull(filtro.getIdValorAutorizado())) {
			Path<Long> idValorAutorizadoPath = root.<Long>get("idValorAutorizado");
			predicates.add(cb.equal(idValorAutorizadoPath, filtro.getIdValorAutorizado()));
		}
		
		return predicates;
	}


}
