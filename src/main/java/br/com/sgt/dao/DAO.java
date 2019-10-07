package br.com.sgt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {
	
	private final Class<T> classe;
	private EntityManager em;

	public DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}
	
	
	public T adiciona(T t) {
		T persistencia;

		persistencia = em.merge(t);

		return persistencia;
	}
	
	
	public T atualizar(T t) {
		T persistencia;

		persistencia = em.merge(t);

		return persistencia;
	}
	
	public void remove(T t) {

		em.remove(em.merge(t));

	}
	
	
	public T buscaPorId(Long id) {
		T instancia = em.find(classe, id);

		return instancia;
	}
	
	@SuppressWarnings("unused")
	public List<T> buscarPorFiltro() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		return lista;
	}

}
