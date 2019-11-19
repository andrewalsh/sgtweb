package br.com.sgt.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.sgt.dao.DAO;
import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.Socio;
import br.com.sgt.entities.ValorAutorizado;
import br.com.sgt.repository.api.ReciboRepository;
import br.com.sgt.repository.filtro.FiltroRecibo;

public class ReciboDAO implements ReciboRepository, Serializable {
	
	private static final long serialVersionUID = 1L;

	private DAO<Recibo> dao;
	
	@Inject
	private EntityManager em;
	
	public ReciboDAO() {
		
	}
	
	@PostConstruct
	void init() {
		dao = new DAO<Recibo>(em, Recibo.class);
	}

	public Recibo salavar(Recibo recibo) {
		Recibo toReturn = new Recibo();
		try {
			toReturn = dao.adiciona(recibo);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public Recibo atualizar(Recibo recibo) {
		Recibo toReturn = new Recibo();
		try {
			toReturn = dao.atualizar(recibo);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}
	
	public void excluir(Recibo recibo) {
		try {
			dao.remove(recibo);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	@Override
	public Recibo ultimoPagamentoDaTarifa(Long idValorAutorizado) {
		String queryMaxIdRecibo = "select max(idRecibo)from Recibo r where r.valorAutorizado.idValorAutorizado=:idValorAutorizado";
		Recibo recibo = new Recibo();
		FiltroRecibo filtro = new FiltroRecibo();
		
		
		try {
			Query queryMax = em.createQuery(queryMaxIdRecibo).setParameter("idValorAutorizado", idValorAutorizado);
			
			Long ultimoPagamento = (Long) queryMax.getSingleResult();
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Recibo> query = em.getCriteriaBuilder().createQuery(Recibo.class);
			Root<Recibo> root = query.from(Recibo.class);
			
			filtro.setIdRecibo(ultimoPagamento);
			
			query.where(whereClausule(filtro, root, cb)
					.toArray(new Predicate[0]));
			
			recibo = em.createQuery(query).getSingleResult();
			
			return recibo;
		} catch (RuntimeException e) {
			throw new RuntimeException("Ocorreu um erro ao buscar o último pagamento "+e.getMessage());
		}
	}

	public List<Recibo> buscarPorFiltro(FiltroRecibo filtroRecibo) {
		List<Recibo> recibos = new ArrayList<>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Recibo> query = em.getCriteriaBuilder().createQuery(Recibo.class);
			Root<Recibo> root = query.from(Recibo.class);
			
			query.where(whereClausule(filtroRecibo, root, cb)
					.toArray(new Predicate[0]));
			
			recibos = em.createQuery(query).getResultList();
		} catch (RuntimeException e) {
			throw e;
		}
		return recibos;
	}
	
	private List<Predicate> whereClausule(FiltroRecibo filtro, Root<Recibo> root, CriteriaBuilder cb){
		List<Predicate> predicates = new ArrayList<>();
		
		if(Objects.nonNull(filtro.getIdSocio())) {
			Path<Long> idSocioPath = root.<ValorAutorizado>get("valorAutorizado").<Socio>get("socio").<Long>get("idSocio");
			predicates.add(cb.equal(idSocioPath, filtro.getIdSocio()));
		}
		
		if(Objects.nonNull(filtro.getNumeroRecibo()) && !filtro.getNumeroRecibo().isEmpty()) {
			Path<String> numeroReciboPath = root.<String>get("numeroRecibo");
			predicates.add(cb.equal(numeroReciboPath, filtro.getNumeroRecibo()));
		}
		
		if(Objects.nonNull(filtro.getAnoBase())) {
			Path<Integer> anoBasePath = root.<Integer>get("anoBase");
			predicates.add(cb.equal(anoBasePath, filtro.getAnoBase()));
		}
		
		if(Objects.nonNull(filtro.getMesBase())) {
			Path<Integer> mesBasePath = root.<Integer>get("mesBase");
			predicates.add(cb.equal(mesBasePath, filtro.getMesBase()));
		}
		
		if(Objects.nonNull(filtro.getIdRecibo())) {
			Path<Integer> reciboPath = root.<Integer>get("idRecibo");
			predicates.add(cb.equal(reciboPath, filtro.getIdRecibo()));
		}
		
		return predicates;
	}

}
