package br.usjt.bd2.sgbd.to;


import java.math.BigDecimal;

import br.usjt.bd2.sgbd.xml.TransferenciaCobolItem;

public class PecaTO extends TransferenciaCobolItem {
	private static final long serialVersionUID = 596009224188356960L;
	private String codigo;
	private String descricao;
	private BigDecimal precoUnitario;

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
	/**
	 * @return Returns the precoUnitario.
	 */
	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}
	/**
	 * @param precoUnitario The precoUnitario to set.
	 */
	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
}
