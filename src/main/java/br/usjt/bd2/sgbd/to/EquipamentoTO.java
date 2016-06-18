package br.usjt.bd2.sgbd.to;


import java.util.Date;

import br.usjt.bd2.sgbd.xml.TransferenciaCobolItem;

public class EquipamentoTO extends TransferenciaCobolItem {
	private static final long serialVersionUID = -6267043878140334354L;
	private String codigo;
	private String descricao;
	private Date dataGarantia;
	private Integer clienteCodigo;
	
	/**
	 * @return Returns the cliente.
	 */
	public Integer getClienteCodigo() {
		return clienteCodigo;
	}
	/**
	 * @param cliente The cliente to set.
	 */
	public void setClienteCodigo(Integer clienteCodigo) {
		this.clienteCodigo = clienteCodigo;
	}
	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Returns the dataGarantia.
	 */
	public Date getDataGarantia() {
		return dataGarantia;
	}
	/**
	 * @param dataGarantia The dataGarantia to set.
	 */
	public void setDataGarantia(Date dataGarantia) {
		this.dataGarantia = dataGarantia;
	}
	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
