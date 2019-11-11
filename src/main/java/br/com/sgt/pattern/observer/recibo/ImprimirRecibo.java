package br.com.sgt.pattern.observer.recibo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import br.com.sgt.entities.Recibo;
import br.com.sgt.entities.dto.ReciboDTO;
import br.com.sgt.infra.report.ReportUtil;

public class ImprimirRecibo implements Serializable, AcaoAposGerarRecibo{

	private static final long serialVersionUID = 1L;
	
	private void imprimir() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/sgt/relatorioServlet");
		
	}

	@Override
	public void executa(Recibo recibo) {
		
		List<ReciboDTO> fonteDeDados = new ArrayList<>();
		fonteDeDados.add(populaReciboDTO(recibo));
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
		String realpath = sc.getRealPath("/WEB-INF/reports/Recibo.jasper");
		new ReportUtil(realpath, fonteDeDados);
		try {
			imprimir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		
		
		
		
	}

	private ReciboDTO populaReciboDTO(Recibo recibo) {
		ReciboDTO dto = new ReciboDTO();
		dto.setValorPago(recibo.getValorAutorizado().getValorLiquido());
		dto.setAnoBase(recibo.getAnoBase());
		dto.setDataPagamento(recibo.getDataPagamento());
		dto.setFormaPagamento(recibo.getFormaPagamento());
		dto.setMesBase(recibo.getMesBase());
		dto.setNumeroRecibo(recibo.getNumeroRecibo());
		dto.setSocioCpf(recibo.getValorAutorizado().getSocio().getPessoa().getCpf());
		dto.setSocioNome(recibo.getValorAutorizado().getSocio().getPessoa().getNome());
		dto.setTarifaDescricao(recibo.getValorAutorizado().getTarifa().getNomeTarifa());
		dto.setTerreiroBairro(recibo.getTerreiro().getBairro());
		dto.setTerreiroCep(recibo.getTerreiro().getCep());
		dto.setTerreiroCidade(recibo.getTerreiro().getCidade());
		dto.setTerreiroEmail(recibo.getTerreiro().getEmail());
		dto.setTerreiroEndereco(recibo.getTerreiro().getEndereco());
		dto.setTerreiroNome(recibo.getTerreiro().getNome());
		dto.setTerreiroSite(recibo.getTerreiro().getSite());
		dto.setTerreiroTelefone(recibo.getTerreiro().getTelefone());
		dto.setTerreiroUf(recibo.getTerreiro().getUf());
		return dto;
	}
	
	
}
