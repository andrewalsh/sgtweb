package br.com.sgt.repository.impl;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.sgt.dao.DAO;
import br.com.sgt.entities.Terreiro;
import br.com.sgt.entities.dto.SocioDTO;
import br.com.sgt.repository.api.TerreiroRepository;

public class TerreiroDAO implements Serializable, TerreiroRepository{

	
	private static final long serialVersionUID = 1L;

	private DAO<Terreiro> dao;
	
	@Inject
	private EntityManager em;
	
	public TerreiroDAO() {
		
	}
	
	@PostConstruct
	void init() {
		dao = new DAO<Terreiro>(em, Terreiro.class);
	}
	
	@Override
	public Terreiro buscarTerreiro() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Terreiro> cq = em.getCriteriaBuilder().createQuery(Terreiro.class);
		Root<Terreiro> root = cq.from(Terreiro.class);
		cq.where(cb.equal(root.<Long>get("idTerreiro"), 1L));
		return em.createQuery(cq).getSingleResult();
	}

}
