package br.com.sgt.web.enumereted;

public enum NavigationEnun {

	NOVO(0),
	LISTAR(1),
	EDITAR(2),
	CONSULTAR(3);
	
	private final int navigation;

	NavigationEnun(int navigation) {
		this.navigation = navigation;
	}
	
	public int getNavigation() {
		return navigation;
	}
	
}
