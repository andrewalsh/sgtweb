package br.com.sgt.web.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.sgt.entities.Tarifa;
import br.com.sgt.repository.impl.TarifaDAO;
import br.com.sgt.service.api.TarifaService;

@FacesConverter("tarifaConverter")
public class TarifaConverter implements Converter, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TarifaService tarifaService;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			TarifaDAO dao = new TarifaDAO();
			Tarifa tarifa = dao.buscaPorId(codigo);
			return tarifa;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		try {
			Tarifa tarifa = (Tarifa)object;
			Long codigo = tarifa.getIdTarifa();
			return codigo.toString();
		} catch (Exception e) {
			return null;
		}
	}

}
