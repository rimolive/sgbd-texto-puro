package br.usjt.bd2.sgbd;

public class ResultadoEquipamento implements Comparable {
	
	private String descricaoPeca;
	private Integer numeroOS;
	private String numSerieEquip;
	private String descricaoEquipamento;

	public String getDescricaoEquipamento() {
		return descricaoEquipamento;
	}

	public void setDescricaoEquipamento(String descricaoEquipamento) {
		this.descricaoEquipamento = descricaoEquipamento;
	}

	public String getDescricaoPeca() {
		return descricaoPeca;
	}

	public void setDescricaoPeca(String descricaoPeca) {
		this.descricaoPeca = descricaoPeca;
	}

	public Integer getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(Integer numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getNumSerieEquip() {
		return numSerieEquip;
	}

	public void setNumSerieEquip(String numSerieEquip) {
		this.numSerieEquip = numSerieEquip;
	}

	public int compareTo(Object o) {
		ResultadoEquipamento resultadoEquipamento = (ResultadoEquipamento) o;
		String texto1 = this.getNumSerieEquip() + this.getNumeroOS();
		String texto2 = resultadoEquipamento.getNumSerieEquip() + resultadoEquipamento.getNumeroOS();
		return texto1.compareTo(texto2);
	}
	
	public String toString() {
		return this.getDescricaoPeca() + " - " + this.getNumeroOS() + " - " + this.getNumSerieEquip() + " - " + this.getDescricaoEquipamento(); 		
	}
}
