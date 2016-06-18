package br.usjt.bd2.sgbd.to;
import java.util.Date;

import br.usjt.bd2.sgbd.xml.TransferenciaCobolItem;

/*
 * Created on Sep 25, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class TesteTO extends TransferenciaCobolItem {
	private static final long serialVersionUID = 2291300209744543737L;
	private String nome;
	private Double altura;
	private Date dataNascimento;
	private Integer idade;
		
	/**
	 * @return Returns the altura.
	 */
	public Double getAltura() {
		return altura;
	}
	/**
	 * @param altura The altura to set.
	 */
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	/**
	 * @return Returns the dataNascimento.
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}
	/**
	 * @param dataNascimento The dataNascimento to set.
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	/**
	 * @return Returns the idade.
	 */
	public Integer getIdade() {
		return idade;
	}
	/**
	 * @param idade The idade to set.
	 */
	public void setIdade(Integer idade) {
		this.idade = idade;
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
}
