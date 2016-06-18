/*
 * Created on 17/10/2005
 * */
package br.usjt.bd2.sgbd.xml;

import java.io.Serializable;

/**
 * @author ricardocury
 */
public abstract class TransferenciaBean implements Serializable {

	private long linhaNoArquivo;
	
	/**
	 * @return
	 */
	public long getLinhaNoArquivo() {
		return linhaNoArquivo;
	}
	/**
	 * @param linhaNoArquivo
	 */
	public void setLinhaNoArquivo(long linhaNoArquivo) {
		this.linhaNoArquivo = linhaNoArquivo;
	}
}
