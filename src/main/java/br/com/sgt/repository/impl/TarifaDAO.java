package br.com.sgt.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.sgt.dao.DAO;
import br.com.sgt.entities.Tarifa;
import br.com.sgt.repository.api.TarifaRepository;
import br.com.sgt.repository.filtro.FiltroTarifa;

public class TarifaDAO implements TarifaRepository, Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	private DAO<Tarifa> dao;
	
	
	public TarifaDAO() {
		
	}
	
	@PostConstruct
	void init() {
		dao = new DAO<Tarifa>(em, Tarifa.class);
	}

	public Tarifa salavar(Tarifa tarifa) {
		Tarifa toReturn = new Tarifa();
		try {
			toReturn = dao.adiciona(tarifa);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public Tarifa atualizar(Tarifa tarifa) {
		Tarifa toReturn = new Tarifa();
		try {
			toReturn = dao.atualizar(tarifa);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}
	
	public Tarifa buscaPorId(Long id) {
		Tarifa toReturn = new Tarifa();
		try {
			toReturn = dao.buscaPorId(id);
		} catch (RuntimeException e) {
			throw e;
		}
		return toReturn;
	}

	public List<Tarifa> buscarPorFiltro(FiltroTarifa filtroTarifa) {
		List<Tarifa> lista = new ArrayList<>();
		
		try {
			CriteriaQuery<Tarifa> query = em.getCriteriaBuilder().createQuery(Tarifa.class);
			Root<Tarifa> root = query.from(Tarifa.class);
			
			query.select(root);
			
			
			lista = em.createQuery(query).getResultList();
			
		} catch (RuntimeException e) {
			throw e;
		}
		
		return lista;
	}

	public void excluir(Tarifa tarifa) {
		// TODO Auto-generated method stub
		
	}
	
	

}
