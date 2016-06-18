package br.usjt.bd2.sgbd.to;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.usjt.bd2.sgbd.xml.TransferenciaCobolItem;

public class ClienteTO extends TransferenciaCobolItem {
	private static final long serialVersionUID = 4837187743111650157L;
	private String nome;
	private Integer codigo;
	private String telefone;
	private Date dataUltimoContato;
	
	private List<String> listaEquipamentoId = new ArrayList<String>() ;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getDataUltimoContato() {
		return dataUltimoContato;
	}

	public void setDataUltimoContato(Date dataUltimoContato) {
		this.dataUltimoContato = dataUltimoContato;
	}

	public List<String> getListaEquipamentoId() {
		return listaEquipamentoId;
	}

	public void setListaEquipamentoId(List<String> listaEquipamentoBean) {
		this.listaEquipamentoId = listaEquipamentoBean;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void addEquipamentoId(String equipamentoId){
		this.getListaEquipamentoId().add(equipamentoId);
	}
	
}
