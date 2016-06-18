/*
 * Created on 18/10/2005
 * */
package br.usjt.bd2.sgbd.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa um registro do arquivo xml que descreve o layout de um
 * arquivo externo
 * 
 * @author ricardocury
 */
public class LayoutArquivoExterno implements Serializable {
	private static final long serialVersionUID = -2535150755738310841L;
	private String tipoClasse;
	private String codigoRegistro;
	private Collection<Campo> campos = new ArrayList<Campo>();
	private String codigoRegistroPai;
	private String nomeAtributoRegistroPai;
	private Map<String,String> mapCampoFormato = new HashMap<String,String>();

	/**
	 * @return
	 */
	public String getCodigoRegistroPai() {
		return codigoRegistroPai;
	}

	/**
	 * @param codigoRegistroPai
	 */
	public void setCodigoRegistroPai(String codigoRegistroPai) {
		this.codigoRegistroPai = codigoRegistroPai;
	}

	/**
	 * @return
	 */
	public String getNomeAtributoRegistroPai() {
		return nomeAtributoRegistroPai;
	}

	/**
	 * @param nomeAtributoRegistroPai
	 */
	public void setNomeAtributoRegistroPai(String nomeAtributoRegistroPai) {
		this.nomeAtributoRegistroPai = nomeAtributoRegistroPai;
	}

	/**
	 * @return
	 */
	public String getTipoClasse() {
		return tipoClasse;
	}

	/**
	 * @param nomeClasse
	 */
	public void setTipoClasse(String nomeClasse) {
		this.tipoClasse = nomeClasse;
	}

	/**
	 * @return
	 */
	public String getCodigoRegistro() {
		return codigoRegistro;
	}

	/**
	 * @param codigoRegistro
	 */
	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	/**
	 * @return
	 */
	public Collection<Campo> getCampos() {
		return campos;
	}

	/**
	 * @param nome
	 * @param tamanho
	 * @param codigoRegistroAninhado
	 * @param ocorrencia
	 */
	public void addCampo(String nome, int tamanho, String codigoRegistroAninhado,
			int ocorrencia) {
		Campo campo = new Campo();
		campo.setNome(nome);
		campo.setTamanho(tamanho);
		campo.setCodRegistroAninhado(codigoRegistroAninhado);
		campo.setOcorrencia(ocorrencia);
		getCampos().add(campo);
	}

	/**
	 * @param nomeCampo
	 * @param formato
	 */
	public void putFormato(String nomeCampo, String formato) {
		mapCampoFormato.put(nomeCampo, formato);
	}

	/**
	 * @return
	 */
	public Map getMapCampoFormato() {
		return mapCampoFormato;
	}

	/**
	 * @author ricardocury
	 * 
	 */
	class Campo implements Serializable {
		private static final long serialVersionUID = -3767030420492675992L;
		private String nome;
		private int tamanho;
		private String codRegistroAninhado;
		private int ocorrencia;

		/**
		 * @return
		 */
		public String getNome() {
			return nome;
		}

		/**
		 * @param nome
		 */
		public void setNome(String nome) {
			this.nome = nome;
		}

		/**
		 * @return
		 */
		public int getTamanho() {
			return tamanho;
		}

		/**
		 * @param tamanho
		 */
		public void setTamanho(int tamanho) {
			this.tamanho = tamanho;
		}

		/**
		 * @return
		 */
		public String getCodRegistroAninhado() {
			return codRegistroAninhado;
		}

		/**
		 * @param codRegistroAninhado
		 */
		public void setCodRegistroAninhado(String codRegistroAninhado) {
			this.codRegistroAninhado = codRegistroAninhado;
		}

		/**
		 * @return
		 */
		public int getOcorrencia() {
			return ocorrencia;
		}

		/**
		 * @param ocorrencia
		 */
		public void setOcorrencia(int ocorrencia) {
			this.ocorrencia = ocorrencia;
		}
	}

}
