package br.com.sgt.pattern.observer.recibo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

import br.com.sgt.entities.Recibo;
import br.com.sgt.infra.report.ReportUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ImprimirRecibo implements Serializable, AcaoAposGerarRecibo{

	private static final long serialVersionUID = 1L;
	
	/*@Inject
	private ReciboService reciboService;*/
	
	private void imprimir() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/sgt/relatorioServlet");
	}

	@Override
	public void executa(Recibo recibo) {
		
		List<Recibo> fonteDeDados = new ArrayList<>();
		fonteDeDados.add(recibo);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
		String realpath = sc.getRealPath("/WEB-INF/reports/Blank_A4.jasper");
		new ReportUtil(realpath, fonteDeDados);
		//byte[] bytes = ReportUtil.criarRelatorio();
		try {
			imprimir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		
		
		
		
	}
	
	
}
