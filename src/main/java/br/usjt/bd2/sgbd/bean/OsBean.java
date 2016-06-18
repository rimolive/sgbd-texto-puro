/*
 * Created on 20/09/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.usjt.bd2.sgbd.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author roliveira
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OsBean {

	private Integer numero;
	private Date data;
	private BigDecimal valorTotal;
	
	private List<ServicoBean> listaServico = new ArrayList<ServicoBean>(); 
	private List<PecaOsBean> listaPecaOs = new ArrayList<PecaOsBean>();
	private EquipamentoBean equipamento;
		
	/**
	 * @return Returns the equipamento.
	 */
	public EquipamentoBean getEquipamento() {
		return equipamento;
	}
	/**
	 * @param equipamento The equipamento to set.
	 */
	public void setEquipamento(EquipamentoBean equipamento) {
		this.equipamento = equipamento;
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
	/**
	 * @return Returns the data.
	 */
	public Date getData() {
		return data;
	}
	/**
	 * @param data The data to set.
	 */
	public void setData(Date data) {
		this.data = data;
	}
	/**
	 * @return Returns the listaPecaOs.
	 */
	public List<PecaOsBean> getListaPecaOs() {
		return listaPecaOs;
	}
	/**
	 * @param listaPecaOs The listaPecaOs to set.
	 */
	public void setListaPecaOs(List<PecaOsBean> listaPecaOs) {
		this.listaPecaOs = listaPecaOs;
	}
	/**
	 * @return Returns the listaServico.
	 */
	public List<ServicoBean> getListaServico() {
		return listaServico;
	}
	/**
	 * @param listaServico The listaServico to set.
	 */
	public void setListaServico(List<ServicoBean> listaServico) {
		this.listaServico = listaServico;
	}
	/**
	 * @return Returns the numero.
	 */
	public Integer getNumero() {
		return numero;
	}
	/**
	 * @param numero The numero to set.
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public void addServico(ServicoBean servico){
		getListaServico().add(servico);
	}
	
	public void addPecaOs(PecaOsBean pecaOs) {
		getListaPecaOs().add(pecaOs);
	}
	
}
