package sp.senai.model;

public class Boneca {
	private String nome;
	private String indicacaoParaUso;
	private String empresa;

	public Boneca(String nome, String indicacaoParaUso, String empresa) {
		this.nome = nome;
		this.indicacaoParaUso = indicacaoParaUso;
		this.empresa = empresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndicacaoParaUso() {
		return indicacaoParaUso;
	}

	public void setIndicacaoParaUso(String indicacaoParaUso) {
		this.indicacaoParaUso = indicacaoParaUso;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	@Override
	public String toString() {
		return this.nome + " " + this.indicacaoParaUso + " " + this.empresa + "\n";
	}
}
