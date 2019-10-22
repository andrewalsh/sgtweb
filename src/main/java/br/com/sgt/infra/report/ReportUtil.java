package br.com.sgt.infra.report;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static String relatorio;
	
	private static List<?> fonte;
	
	public ReportUtil(String relatorio, List<?> fonte) {
		ReportUtil.relatorio = relatorio;
		ReportUtil.fonte = fonte;
	}

	public static byte[] criarRelatorio(){
		byte[] bytes = null;
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fonte);
			Map<String, Object> parametros = new HashMap<>();
			
			bytes = JasperRunManager.runReportToPdf(relatorio, parametros, dataSource);
		} catch (JRException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar o arquivo para impressão! ERRO: [ "+e.getMessage()+" ]");
			
		}
		return bytes;
	}
}
