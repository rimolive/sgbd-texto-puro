/*
 * Created on 20/09/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.usjt.bd2.sgbd.bean;

/**
 * @author roliveira
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServicoBean {

	private Integer seqItem;
	private String descricao;
	private OsBean osBean;
	
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
	public OsBean getOsBean() {
		return osBean;
	}
	public void setOsBean(OsBean osBean) {
		this.osBean = osBean;
	}
}
