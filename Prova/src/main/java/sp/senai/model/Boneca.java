package sp.senai.model;

public class Boneca {
	private int idBoneca;
	private String nome;
	private String empresa;

	public Boneca(int idBoneca, String nome, String empresa) {
		this.idBoneca = idBoneca;
		this.nome = nome;
		this.empresa = empresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdBoneca() {
		return idBoneca;
	}

	public void setIdBoneca(int idBoneca) {
		this.idBoneca = idBoneca;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return this.idBoneca + " " + this.nome + " " + this.empresa + "\n";
	}
}
