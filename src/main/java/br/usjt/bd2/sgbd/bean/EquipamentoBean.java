/*
 * Created on 20/09/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.usjt.bd2.sgbd.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipamentoBean {

	private String codigo;
	private String descricao;
	private Date dataGarantia;
	
	private ClienteBean cliente;
	private List<OsBean> listaOs = new ArrayList<OsBean>();
	
	public ClienteBean getCliente() {
		return cliente;
	}
	
	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Date getDataGarantia() {
		return dataGarantia;
	}
	
	public void setDataGarantia(Date dataGarantia) {
		this.dataGarantia = dataGarantia;
	}
		
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void addOS(OsBean os){
		this.getListaOs().add(os);
	}
	
	public List<OsBean> getListaOs() {
		return listaOs;
	}

	public void setListaOs(List<OsBean> listaOs) {
		this.listaOs = listaOs;
	}
}
