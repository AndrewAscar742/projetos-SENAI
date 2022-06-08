package br.com.senai.provaJava;

public enum Status {
	ATIVO("Empregado"), INATIVO("Desempregado");
	
	protected String status = "";
	
	private Status(String status) {
		this.status = status;
	}
	
	public static Status pasearString(String text) {
		if("ATIVO".equals(text))
			return Status.ATIVO;
		else if ("INATIVO".equals(text))
			return Status.INATIVO;
		else
			throw new IllegalArgumentException("Dados inválidos no banco.txt");
	}
}
