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
public class PecaOsBean {

	private Integer quantidade;
	private PecaBean peca;
	private OsBean os;
	
	/**
	 * @return Returns the os.
	 */
	public OsBean getOs() {
		return os;
	}
	/**
	 * @param os The os to set.
	 */
	public void setOs(OsBean os) {
		this.os = os;
	}
	/**
	 * @return Returns the peca.
	 */
	public PecaBean getPeca() {
		return peca;
	}
	/**
	 * @param peca The peca to set.
	 */
	public void setPeca(PecaBean peca) {
		this.peca = peca;
	}
	/**
	 * @return Returns the quantidade.
	 */
	public Integer getQuantidade() {
		return quantidade;
	}
	/**
	 * @param quantidade The quantidade to set.
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
