package br.com.sgt.servlets;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sgt.entities.dto.ReciboDTO;
import br.com.sgt.infra.report.ReportUtil;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/relatorioServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		//String caminho = getServletContext().getRealPath("/WEB-INF/reports/Recibo.jasper");
		try {
			byte[] bytes = ReportUtil.criarRelatorio();
			/*StringBuilder titulo = new StringBuilder();
			gerarTituloRecibo(ReportUtil.getRelatorio());*/
			
			if(bytes != null && bytes.length > 0){
				//response.setContentType("application/pdf");
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition", "attachment;filename=\""+ gerarTituloRecibo(ReportUtil.getRelatorio()) + ".pdf\";");
				response.setContentLength(bytes.length);
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();
			}
			//FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String gerarTituloRecibo(List<?> resultList){
		StringBuilder titulo = new StringBuilder();
		
		if(resultList != null && resultList.size() > 0){
			ReciboDTO dto = new ReciboDTO();
			dto = (ReciboDTO) resultList.get(0);
			titulo.append("Recibo - ");
			titulo.append(dto.getNumeroRecibo());
		}
		
		return titulo.toString();
	}

}
