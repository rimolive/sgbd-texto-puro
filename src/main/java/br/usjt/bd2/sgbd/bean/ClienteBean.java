package br.usjt.bd2.sgbd.bean;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author roliveira
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ClienteBean {

	private String nome;
	private Integer codigo;
	private String telefone;
	private Date dataUltimoContato;
	
	private List<EquipamentoBean> listaEquipamentoBean = new ArrayList<EquipamentoBean>() ;
	
	/**
	 * @return Returns the codigo.
	 */
	public Integer getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Returns the dataUltimoContato.
	 */
	public Date getDataUltimoContato() {
		return dataUltimoContato;
	}
	/**
	 * @param dataUltimoContato The dataUltimoContato to set.
	 */
	public void setDataUltimoContato(Date dataUltimoContato) {
		this.dataUltimoContato = dataUltimoContato;
	}
	/**
	 * @return Returns the listaEquipamentoBean.
	 */
	public List<EquipamentoBean> getListaEquipamentoBean() {
		return listaEquipamentoBean;
	}
	/**
	 * @param listaEquipamentoBean The listaEquipamentoBean to set.
	 */
	public void setListaEquipamentoBean(List<EquipamentoBean> listaEquipamentoBean) {
		this.listaEquipamentoBean = listaEquipamentoBean;
	}
	/**
	 * @return Returns the nome.
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome The nome to set.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return Returns the telefone.
	 */
	public String getTelefone() {
		return telefone;
	}
	/**
	 * @param telefone The telefone to set.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void addEquipamentoBean(EquipamentoBean equipamento){
		getListaEquipamentoBean().add(equipamento);
	}
	
}
