import br.com.sgt.entities.TipoTarifa;
import br.com.sgt.service.impl.TipoTarifaBoundary;

public class Teste {

	public static void main(String[] args) {
		TipoTarifa tipoTarifa = new TipoTarifa();
		
		tipoTarifa.setIdTerreiro(1);
		tipoTarifa.setNomeTipoTarifa("Teste 2");
		
		TipoTarifaBoundary boundary = new TipoTarifaBoundary();
		
		try {
			boundary.salavar(tipoTarifa);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
	}

}
