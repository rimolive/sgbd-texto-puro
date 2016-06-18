package br.usjt.bd2.sgbd.to;


import java.math.BigDecimal;
import java.util.Date;

import br.usjt.bd2.sgbd.xml.TransferenciaCobolItem;

public class OsTO extends TransferenciaCobolItem {
	private static final long serialVersionUID = 6358926938710978666L;
	private Integer numero;
	private Date data;
	private BigDecimal valorTotal;
	private String equipamentoCodigo;
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEquipamentoCodigo() {
		return equipamentoCodigo;
	}

	
	
	public void setEquipamentoCodigo(String equipamentoCodigo) {
		this.equipamentoCodigo = equipamentoCodigo;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	
	
	
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
