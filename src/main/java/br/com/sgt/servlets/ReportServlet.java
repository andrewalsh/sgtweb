package br.com.sgt.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String caminho = getServletContext().getRealPath("/WEB-INF/reports/Blank_A4.jasper");
		byte[] bytes = ReportUtil.criarRelatorio();
		
		if(bytes != null && bytes.length > 0){
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
