package br.com.sgt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {
	
	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	
	public T adiciona(T t) {
		T persistencia;
		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// abre transacao
		em.getTransaction().begin();

		// persiste o objeto
		persistencia = em.merge(t);

		// commita a transacao
		em.getTransaction().commit();

		// fecha a entity manager
		em.close();
		
		return persistencia;
	}
	
	
	public T atualizar(T t) {
		T persistencia;
		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// abre transacao
		em.getTransaction().begin();

		// persiste o objeto
		persistencia = em.merge(t);

		// commita a transacao
		em.getTransaction().commit();

		// fecha a entity manager
		em.close();
		
		return persistencia;
	}
	
	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.remove(em.merge(t));

		em.getTransaction().commit();
		em.close();
	}
	
	public List<T> buscarPorFiltro(Filtro<T> filtro) {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		em.close();
		return lista;
	}

}
