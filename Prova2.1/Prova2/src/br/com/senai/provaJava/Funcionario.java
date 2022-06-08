package br.com.senai.provaJava;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Funcionario extends Pessoa {
	ListaFun listinha = new ListaFun();
	
	private LocalDateTime dtRegistro;
	private Status status;
	private double salario;

	public Funcionario(String nome, String cpf, LocalDate dtNasci, LocalDateTime dtRegistro, Status status, double salario) {
		super(nome, cpf, dtNasci);
		this.dtRegistro = dtRegistro;
		this.status = status;
		this.salario = salario;
	}


	public LocalDateTime getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(LocalDateTime dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int tempServiMonths() {
		Period periodo = Period.between(LocalDate.now(), this.dtRegistro.toLocalDate());
		int months = periodo.getMonths();
		return months;
	}


	@Override
	public String toString(Funcionario obj) {
		return super.toString(obj) + obj.getSalario();
	}
}
