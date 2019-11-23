package br.com.sgt.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sgt.entities.dto.UsuarioDTO;


@Table(name="T_RECIBO")
@Entity
public class Recibo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_RECIBO")
	private Long idRecibo;
	
	@Column(name="ANO_BASE", nullable=false)
	private int anoBase;
	
	@Column(name="MES_BASE", nullable=false)
	private int mesBase;
	
	@Column(name="DATA_PAGAMENTO", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	
	@Column(name="FORMA_DE_PAGAMENTO", nullable=false)
	private String formaPagamento;
	
	@Column(name="NUM_RECIBO", unique=true, nullable=false)
	private String numeroRecibo;
	
	@Column(name="VALOR", nullable=false, scale=3, precision=2)
	private BigDecimal valorRecibo;
	
	@Column(name="RECIBO_ESTORNADO", nullable=false)
	private String estornado;
	
	@ManyToOne
	@JoinColumn(name="ID_TERREIRO", nullable=false)
	private Terreiro terreiro;
	
	@ManyToOne
	@JoinColumn(name="ID_VALOR_AUTORIZADO", nullable=false)
	private ValorAutorizado valorAutorizado;
	
	@Transient
	private UltimoPagamentoDaTarifa ultimoPagamento;
	
	@Transient
	private UsuarioDTO usuarioDTO;
	
	
	public Recibo() {
		setAnoBase(anoCorrente());
		setMesBase(mesCorrente());
		setNumeroRecibo(gerarNumeroRecibo());
	}

	public Long getIdRecibo() {
		return idRecibo;
	}
	public void setIdRecibo(Long idRecibo) {
		this.idRecibo = idRecibo;
	}
	public int getAnoBase() {
		return anoBase;
	}
	public void setAnoBase(int anoBase) {
		this.anoBase = anoBase;
	}
	public int getMesBase() {
		return mesBase;
	}
	public void setMesBase(int mesBase) {
		this.mesBase = mesBase;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public BigDecimal getValorRecibo() {
		return valorRecibo;
	}
	public void setValorRecibo(BigDecimal valorRecibo) {
		this.valorRecibo = valorRecibo;
	}
	public Terreiro getIdTerreiro() {
		return terreiro;
	}
	public void setTerreiro(Terreiro terreiro) {
		this.terreiro = terreiro;
	}
	public ValorAutorizado getValorAutorizado() {
		return valorAutorizado;
	}
	public void setValorAutorizado(ValorAutorizado valorAutorizado) {
		this.valorAutorizado = valorAutorizado;
	}	
	public Terreiro getTerreiro() {
		return terreiro;
	}
	public UltimoPagamentoDaTarifa getUltimoPagamento() {
		return ultimoPagamento;
	}
	public void setUltimoPagamento(UltimoPagamentoDaTarifa ultimoPagamento) {
		this.ultimoPagamento = ultimoPagamento;
	}
	public String getEstornado() {
		return estornado;
	}
	public void setEstornado(String estornado) {
		this.estornado = estornado;
	}
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRecibo == null) ? 0 : idRecibo.hashCode());
		result = prime * result + ((numeroRecibo == null) ? 0 : numeroRecibo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recibo other = (Recibo) obj;
		if (idRecibo == null) {
			if (other.idRecibo != null)
				return false;
		} else if (!idRecibo.equals(other.idRecibo))
			return false;
		if (numeroRecibo == null) {
			if (other.numeroRecibo != null)
				return false;
		} else if (!numeroRecibo.equals(other.numeroRecibo))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recibo [idRecibo=");
		builder.append(idRecibo);
		builder.append(", anoBase=");
		builder.append(anoBase);
		builder.append(", mesBase=");
		builder.append(mesBase);
		builder.append(", dataPagamento=");
		builder.append(dataPagamento);
		builder.append(", formaPagamento=");
		builder.append(formaPagamento);
		builder.append(", numeroRecibo=");
		builder.append(numeroRecibo);
		builder.append(", valorRecibo=");
		builder.append(valorRecibo);
		builder.append(", idTerreiro=");
		builder.append(terreiro.getIdTerreiro());
		builder.append(", valorAutorizado=");
		builder.append(valorAutorizado);
		builder.append("]");
		return builder.toString();
	}
	
	public List<String> getListFormasPagamento() {
		return Arrays.asList("Dinheiro", "Cartao de Credito", "TED", "Cartao de Debito", "Deposito", "Cheque");
	}
	
	private int mesCorrente() {
		Calendar calendar = Calendar.getInstance();
		int mes = calendar.get(Calendar.MONTH) + 1;
		return mes;
	}

	private int anoCorrente() {
		Calendar calendar = Calendar.getInstance();
		int ano = calendar.get(Calendar.YEAR);
		return ano;
	}
	
	private String gerarNumeroRecibo() {
		Locale locale = new Locale("pt", "BR");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("yyMMddHHmmss", locale);
		return formatador.format(calendar.getTime());
	}
}
