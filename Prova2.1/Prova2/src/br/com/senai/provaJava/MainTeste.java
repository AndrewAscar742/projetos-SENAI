package br.com.senai.provaJava;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainTeste {


	public static void main(String[] args) {
		//Instâncias
		Locale locale = new Locale("pt", "BR");
		Scanner scNum = new Scanner(System.in);
		Scanner scSala = new Scanner(System.in);
		Scanner scText = new Scanner(System.in);
		ListaFun bancoDados = new ListaFun();

		//Memórias
		String nome, cpf;
		double salario = 0;
		int opcao;
		Status status = null;
		boolean validar = true;

		//LocalDate
		LocalDateTime dtRegistro;
		LocalDate dtNasci;
		DateTimeFormatter data = DateTimeFormatter.ofPattern("dd/MM/yyyy", locale);

		Path p = Paths.get("C://temp//Banco.txt");

		//Validação
		if(Files.exists(p)){
			String vet[];
			List<String> bancoDeDados = new ArrayList<String>();
			Funcionario funAux;
			bancoDeDados = GravacaoTxt.lerArq();

			for (String bancoAux : bancoDeDados) {
				vet = bancoAux.split(";");
				funAux = new Funcionario(vet[0], vet[1], LocalDate.parse(vet[2]), LocalDateTime.parse(vet[3]), Status.pasearString(vet[4]), 
						Double.parseDouble(vet[5]));
				bancoDados.adicionar(funAux);

			}
		}

		//Início do sistema
		System.out.println("Bem vindo ao banco de dados dos funcionários");
		do {

			System.out.println("Escolha a operção desejada:"); 
			System.out.println("1 . Cadastrar Funcionário"); 
			System.out.println("2 . Alterar Funcionário"); 
			System.out.println("3 . Excluir Funcionário"); 
			System.out.println("4 . Listar todos os Funcionários");
			System.out.println("5. Atualizar Salario de Todos os Funcionários");
			System.out.println("6. Listar pagamento líquido de Todos os Funcionários");
			System.out.println("9 . Sair do Sistema"); 
			opcao = scNum.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("Digite o seu nome completo");
				nome = scText.nextLine();

				int contadorCPF = 0;
				do {
					System.out.println("Digite o seu CPF");
					cpf = scText.nextLine();

					try {
						if (ValidarCPF.validarCPF(cpf) == 0) {
							System.out.println("CPF Válido");
							break;
						}else{
							contadorCPF++;
							System.out.println("CPF Inválido, tentátivas: " + contadorCPF + " de " + " 3");
						}
					} catch (Exception e) {
						e.printStackTrace();
						
					}finally{
						if(contadorCPF == 3){
							System.err.println("Você ultrapassou o limite, tente mais tarde");
							return;
						}
					}
				} while (contadorCPF != 3);
				System.out.println("Digite a data de nascimento");
				dtNasci = LocalDate.parse(scText.nextLine(), data);
				dtRegistro = LocalDateTime.now();

				int contadorStatus = 0;
				do {
					System.out.println("Você está Empregado ou Desempregado?");
					String text = scText.nextLine();
					
					if (Status.ATIVO.status.equalsIgnoreCase(text)) {
						status = Status.ATIVO;
						break;
					}else
						if(Status.INATIVO.status.equalsIgnoreCase(text)) {
							status = Status.INATIVO;
							break;
						}else{
							contadorStatus++;
							System.err.println("Status só podem ser Empregado ou Desempregado \n" + "Status Inválido, tentátivas: " 
									+ contadorStatus + " de " + " 3");
						}

					if(contadorStatus == 3)
						return;

				} while (true);
				System.out.println("Digite seu salário");
				salario = scSala.nextDouble();
				Funcionario funcionario = new Funcionario(nome, cpf, dtNasci, dtRegistro, 
						status, salario);
				
				bancoDados.adicionar(funcionario);
				break;

			case 2:
				System.out.println("O que você deseja alterar? \n"
						+ "[1] Nome \n"
						+ "[2] CPF \n"
						+ "[3] Salário \n"
						+ "[4] Status \n");

				opcao = scNum.nextInt();

				switch (opcao) {
				case 1:
					System.out.println("Informe um novo nome");
					String nomeAux = scText.nextLine();
					System.out.println("Informe a posição do vetor que deseja alterar");
					int pos = scNum.nextInt();

					bancoDados.alterarFuncionario(nomeAux, pos);
					break;

				case 2:
					int contadorCPF2 = 0;
					String novoCPF2 = "";
					
					do {
						System.out.println("Informe o novo CPF");
						novoCPF2 = scText.nextLine();

						try {
							if (ValidarCPF.validarCPF(novoCPF2) == 0) {
								System.out.println("CPF Válido");
								break;
							}else{
								contadorCPF2++;
								System.out.println("CPF Inválido, tentátivas: " + contadorCPF2 + " de " + " 3");
							}
						} catch (Exception e) {
							e.printStackTrace();
							
						}finally{
							if(contadorCPF2 == 3){
								System.err.println("Você ultrapassou o limite, tente mais tarde");
								return;
							}
						}
					} while (contadorCPF2 != 3);
					System.out.println("Informe a posição do vetor que deseja alterar");
					int pos1 = scNum.nextInt();

					bancoDados.alterarFuncionario(pos1, novoCPF2);
					break;

				case 3:
					System.out.println("Informe um novo salário");
					double novoSal = scSala.nextDouble();
					System.out.println("Informe a posição do vetor que deseja alterar");
					int pos2 = scNum.nextInt();

					bancoDados.alterarFuncionario(pos2, novoSal);
					break;

				case 4:
					System.out.println("Informe um novo Status");
					String statusMod = scText.nextLine();
					System.out.println("Informe a posição do vetor que deseja alterar");
					int pos3 = scNum.nextInt();

					if (Status.ATIVO.status.equalsIgnoreCase(statusMod)) {
						status = Status.ATIVO;
						bancoDados.alterarFuncionario(pos3, status);
						break;
					}else
						if(Status.INATIVO.status.equalsIgnoreCase(statusMod)) {
							status = Status.INATIVO;
							break;
						}else{
							System.err.println("Dados inválidos, tente mais tarde");
							return;
						}

				default:
					System.err.println("Dados inseridos inválidos");
					break;
				}
				break;

			case 3:
				System.out.println("Informe a posição do funcionário");
				int pos = scNum.nextInt();

				bancoDados.excluir(pos);
				break;
			case 4:
				bancoDados.imprimirFuncionarios();
				break;

			case 5:
				bancoDados.atualizarSala();
				break;

			case 6:
				bancoDados.imprimirSalaLiquiFun();
				break;

			case 9:
				break;

			default:
				break;
			}
		} while (opcao != 9);
	}

}
