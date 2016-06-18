package br.usjt.bd2.sgbd.to;

import br.usjt.bd2.sgbd.xml.TransferenciaCobolItem;


/**
 * @author roliveira
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServicoTO extends TransferenciaCobolItem {
	private static final long serialVersionUID = -7440966896953370399L;
	private Integer numeroOS;
	private Integer seqItem;
	private String descricao;
	
	public Integer getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(Integer numeroOS) {
		this.numeroOS = numeroOS;
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
	 * @return Returns the seqItem.
	 */
	public Integer getSeqItem() {
		return seqItem;
	}
	/**
	 * @param seqItem The seqItem to set.
	 */
	public void setSeqItem(Integer seqItem) {
		this.seqItem = seqItem;
	}
}
