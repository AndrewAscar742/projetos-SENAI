package br.com.senai.provaJava;

public class Calculadora {
	
	public static double novoSalario(Funcionario f) {
		
		if (f.tempServiMonths() < 36) {
			return f.getSalario() * 0.1f;
		} else {
			if (f.tempServiMonths() >= 60) {
				return f.getSalario() * 0.5f;
			} else {
				return f.getSalario() * 0.3f;
			}
		}
	}
	
	public static double impostoRenda(Funcionario f) {
		if (f.getSalario() < 2000) {
			return f.getSalario() * 0.10f;
		}else{
			if (f.getSalario() >= 5000) {
				return f.getSalario() * 0.20f;
			}else{
				return f.getSalario() * 0.15f;
			}
		}
	}
}
