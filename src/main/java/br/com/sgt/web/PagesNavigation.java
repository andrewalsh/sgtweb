package br.com.sgt.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;

import br.com.sgt.web.enumereted.NavigationEnun;

public class PagesNavigation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private NavigationEnun navigationEnun;
	private String url;

	public PagesNavigation(NavigationEnun navigationEnun, String url) {
		this.navigationEnun = navigationEnun;
		this.url = url;
	}

	public NavigationEnun getNavigationEnun() {
		return navigationEnun;
	}

	public String getUrl() {
		return url;
	}
	
	public void navega() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
