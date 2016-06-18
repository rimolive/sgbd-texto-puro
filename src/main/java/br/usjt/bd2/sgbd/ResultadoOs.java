package br.usjt.bd2.sgbd;

import java.util.Date;

public class ResultadoOs {
	private Integer numeroOs;
	private Date dataOs;
	private String nomeCliente;
	private String codEquip;
	private String descrEquip;
	
	public String getCodEquip() {
		return codEquip;
	}
	public void setCodEquip(String codEquip) {
		this.codEquip = codEquip;
	}
	public Date getDataOs() {
		return dataOs;
	}
	public void setDataOs(Date dataOs) {
		this.dataOs = dataOs;
	}
	public String getDescrEquip() {
		return descrEquip;
	}
	public void setDescrEquip(String descrEquip) {
		this.descrEquip = descrEquip;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public Integer getNumeroOs() {
		return numeroOs;
	}
	public void setNumeroOs(Integer numeroOs) {
		this.numeroOs = numeroOs;
	}

}
