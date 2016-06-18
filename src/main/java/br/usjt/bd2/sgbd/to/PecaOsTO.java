package br.usjt.bd2.sgbd.to;

import br.usjt.bd2.sgbd.xml.TransferenciaCobolItem;


public class PecaOsTO extends TransferenciaCobolItem {
	private static final long serialVersionUID = -6604806085557037884L;
	private Integer quantidade;
	private String codigoPeca;
	private Integer numeroOS;
	
	public String getCodigoPeca() {
		return codigoPeca;
	}
	public void setCodigoPeca(String codigoPeca) {
		this.codigoPeca = codigoPeca;
	}
	public Integer getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(Integer numeroOS) {
		this.numeroOS = numeroOS;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
