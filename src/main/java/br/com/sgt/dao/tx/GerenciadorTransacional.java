package br.com.sgt.dao.tx;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorTransacional {

	@Inject
	EntityManager manager;
	
	@AroundInvoke
	public Object executa(InvocationContext context) {
			try {
				manager.getTransaction().begin();
				
				Object result = context.proceed();
				
				manager.getTransaction().commit();
				
				return result;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
	}
}
