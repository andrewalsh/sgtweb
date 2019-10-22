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
	
	private static final String path = "/WEB-INF/reports/";
	
	private static List<?> fonte;
	
	private static String relatorio;
	
	public ReportUtil(List<?> fonte, String relatorio) {
		ReportUtil.fonte = fonte;
		ReportUtil.relatorio = path + relatorio;
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
