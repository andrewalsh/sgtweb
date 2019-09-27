package br.com.sgt.infra.impl;

import java.io.IOException;

import javax.faces.context.FacesContext;

import br.com.sgt.infra.api.ListInterface;
import br.com.sgt.web.PagesNavigation;

public class ListController implements ListInterface{
	
	private String prefixo;

	@Override
	public void navigation(PagesNavigation navigation) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(prefixo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
