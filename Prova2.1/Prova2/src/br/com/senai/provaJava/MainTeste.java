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
		//Inst�ncias
		Locale locale = new Locale("pt", "BR");
		Scanner scNum = new Scanner(System.in);
		Scanner scSala = new Scanner(System.in);
		Scanner scText = new Scanner(System.in);
		ListaFun bancoDados = new ListaFun();

		//Mem�rias
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

		//Valida��o
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

		//In�cio do sistema
		System.out.println("Bem vindo ao banco de dados dos funcion�rios");
		do {

			System.out.println("Escolha a oper��o desejada:"); 
			System.out.println("1 . Cadastrar Funcion�rio"); 
			System.out.println("2 . Alterar Funcion�rio"); 
			System.out.println("3 . Excluir Funcion�rio"); 
			System.out.println("4 . Listar todos os Funcion�rios");
			System.out.println("5. Atualizar Salario de Todos os Funcion�rios");
			System.out.println("6. Listar pagamento l�quido de Todos os Funcion�rios");
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
							System.out.println("CPF V�lido");
							break;
						}else{
							contadorCPF++;
							System.out.println("CPF Inv�lido, tent�tivas: " + contadorCPF + " de " + " 3");
						}
					} catch (Exception e) {
						e.printStackTrace();
						
					}finally{
						if(contadorCPF == 3){
							System.err.println("Voc� ultrapassou o limite, tente mais tarde");
							return;
						}
					}
				} while (contadorCPF != 3);
				System.out.println("Digite a data de nascimento");
				dtNasci = LocalDate.parse(scText.nextLine(), data);
				dtRegistro = LocalDateTime.now();

				int contadorStatus = 0;
				do {
					System.out.println("Voc� est� Empregado ou Desempregado?");
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
							System.err.println("Status s� podem ser Empregado ou Desempregado \n" + "Status Inv�lido, tent�tivas: " 
									+ contadorStatus + " de " + " 3");
						}

					if(contadorStatus == 3)
						return;

				} while (true);
				System.out.println("Digite seu sal�rio");
				salario = scSala.nextDouble();
				Funcionario funcionario = new Funcionario(nome, cpf, dtNasci, dtRegistro, 
						status, salario);
				
				bancoDados.adicionar(funcionario);
				break;

			case 2:
				System.out.println("O que voc� deseja alterar? \n"
						+ "[1] Nome \n"
						+ "[2] CPF \n"
						+ "[3] Sal�rio \n"
						+ "[4] Status \n");

				opcao = scNum.nextInt();

				switch (opcao) {
				case 1:
					System.out.println("Informe um novo nome");
					String nomeAux = scText.nextLine();
					System.out.println("Informe a posi��o do vetor que deseja alterar");
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
								System.out.println("CPF V�lido");
								break;
							}else{
								contadorCPF2++;
								System.out.println("CPF Inv�lido, tent�tivas: " + contadorCPF2 + " de " + " 3");
							}
						} catch (Exception e) {
							e.printStackTrace();
							
						}finally{
							if(contadorCPF2 == 3){
								System.err.println("Voc� ultrapassou o limite, tente mais tarde");
								return;
							}
						}
					} while (contadorCPF2 != 3);
					System.out.println("Informe a posi��o do vetor que deseja alterar");
					int pos1 = scNum.nextInt();

					bancoDados.alterarFuncionario(pos1, novoCPF2);
					break;

				case 3:
					System.out.println("Informe um novo sal�rio");
					double novoSal = scSala.nextDouble();
					System.out.println("Informe a posi��o do vetor que deseja alterar");
					int pos2 = scNum.nextInt();

					bancoDados.alterarFuncionario(pos2, novoSal);
					break;

				case 4:
					System.out.println("Informe um novo Status");
					String statusMod = scText.nextLine();
					System.out.println("Informe a posi��o do vetor que deseja alterar");
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
							System.err.println("Dados inv�lidos, tente mais tarde");
							return;
						}

				default:
					System.err.println("Dados inseridos inv�lidos");
					break;
				}
				break;

			case 3:
				System.out.println("Informe a posi��o do funcion�rio");
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
