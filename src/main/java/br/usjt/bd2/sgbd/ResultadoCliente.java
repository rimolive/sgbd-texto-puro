/*
 * Created on 20/09/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.usjt.bd2.sgbd;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author roliveira
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResultadoCliente implements Comparable {

	private String nomeCliente;
	private String descEquip;
	private Integer numeroOs;
	private Date dataOs;
	private BigDecimal valorTotal;

	/**
	 * @return Returns the dataOs.
	 */
	public Date getDataOs() {
		return dataOs;
	}
	/**
	 * @param dataOs The dataOs to set.
	 */
	public void setDataOs(Date dataOs) {
		this.dataOs = dataOs;
	}
	/**
	 * @return Returns the descEquip.
	 */
	public String getDescEquip() {
		return descEquip;
	}
	/**
	 * @param descEquip The descEquip to set.
	 */
	public void setDescEquip(String descEquip) {
		this.descEquip = descEquip;
	}
	/**
	 * @return Returns the nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente The nomeCliente to set.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/**
	 * @return Returns the numeroOs.
	 */
	public Integer getNumeroOs() {
		return numeroOs;
	}
	/**
	 * @param numeroOs The numeroOs to set.
	 */
	public void setNumeroOs(Integer numeroOs) {
		this.numeroOs = numeroOs;
	}
	/**
	 * @return Returns the valorTotal.
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	/**
	 * @param valorTotal The valorTotal to set.
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public int compareTo(Object o) {
		ResultadoCliente rescultadoCliente = (ResultadoCliente)o;
		String texto1 = this.getDescEquip() + this.getNumeroOs().toString();
		String texto2 = rescultadoCliente.getDescEquip() + rescultadoCliente.getNumeroOs().toString();
		return texto1.compareTo(texto2);
	}
	
	public String toString() {
		return getNomeCliente() + " - " + getDescEquip() + " - " + getNumeroOs() + " - " + getDataOs() + " - " + getValorTotal();
	}
	
}
