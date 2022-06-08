package br.com.senai.provaJava;

import java.time.LocalDate;

public abstract class Pessoa {
	private String nome;
	private String cpf;
	private LocalDate dtNasci;
	
	public Pessoa(String nome, String cpf, LocalDate dtNasci) {
		this.nome = nome;
		this.cpf = cpf;
		this.dtNasci = dtNasci;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDtNasci() {
		return dtNasci;
	}

	public void setDtNasci(LocalDate dtNasci) {
		this.dtNasci = dtNasci;
	}

	public String toString(Funcionario obj) {
		Funcionario objFun = (Funcionario) obj;
		return "Nome: " + objFun.getNome() + " CPF: [" + objFun.getCpf() + "] Salário: ";
	}
}
