/*
 * Created on 20/09/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.usjt.bd2.sgbd.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author roliveira
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PecaBean {

	private String codigo;
	private String descricao;
	private BigDecimal precoUnitario;
	
	private List<PecaOsBean> pecaOS = new ArrayList<PecaOsBean>();
	
	/**
	 * @return Returns the pecaOS.
	 */
	public List<PecaOsBean> getPecaOS() {
		return pecaOS;
	}
	/**
	 * @param pecaOS The pecaOS to set.
	 */
	public void setPecaOS(List<PecaOsBean> pecaOS) {
		this.pecaOS = pecaOS;
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
	
	public void addPecaOs(PecaOsBean pecaOs){
		getPecaOS().add(pecaOs);
	}
	
}
