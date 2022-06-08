package br.com.senai.provaJava;

public class ListaFun {
	private GravacaoTxt arq_txt = new GravacaoTxt();
	private int posicao;
	private Funcionario op[];

	public ListaFun() {
		op = new Funcionario[4];
	}

	public ListaFun(int tamanho) {
		op = new Funcionario[tamanho];
	}

	private String formatarLista() {
		String descriOp = "";
		for (int i = 0; i < posicao; i++) {
			Funcionario opAux = op[i];

			descriOp += opAux.getNome() + ";" + opAux.getCpf() + ";" + opAux.getDtNasci() + ";"
					+ opAux.getDtRegistro() + ";" + opAux.getStatus() + ";" +  opAux.getSalario() + "\r";
		}

		return descriOp;
	}

	public void adicionar(Funcionario operador) {
		if (posicao == op.length) {
			Funcionario opAux[] = new Funcionario[op.length * 2];

			for (int i = 0; i < posicao; i++) {
				opAux[i] = op[i];
			}

			op = opAux;
		}

		op[posicao] = operador;
		posicao++;
		arq_txt.gravarArq(formatarLista());

	}

	public boolean alterarFuncionario(int pos, double salario) {
		if(pos < this.posicao) {
			op[pos].setSalario(salario);
			arq_txt.gravarArq(formatarLista());
			return true;
		}

		return false;

	}

	public boolean alterarFuncionario(int pos, String cpf) {
		if(pos < this.posicao) {
			op[pos].setCpf(cpf);
			arq_txt.gravarArq(formatarLista());
			return true;
		}

		return false;

	}

	public boolean alterarFuncionario(String nome, int pos) {
		if(pos < this.posicao) {
			op[pos].setNome(nome);
			arq_txt.gravarArq(formatarLista());
			return true;
		}

		return false;

	}

	public boolean alterarFuncionario(int pos, Status status ) {
		if(pos < this.posicao) {
			op[pos].setStatus(status);
			arq_txt.gravarArq(formatarLista());
			return true;
		}

		return false;

	}

	public boolean excluir(int pos) {
		if (this.posicao > pos) {
			for (int i = 0; i < posicao - 1; i++) {
				op[posicao] = op[posicao + 1];
			}
			op[posicao] = null;
			posicao--;
			arq_txt.gravarArq(formatarLista());
			return true;
		}else{
			return false;
		}
	}
	
	public void imprimirFuncionarios() {
		for (int i = 0; i < posicao; i++) {
			System.out.println(op[i].toString(op[i])); 
		}
	}
	
	public void imprimirSalaLiquiFun() {
		for (int i = 0; i < posicao; i++) {
			double salario = op[i].getSalario() - Calculadora.impostoRenda(op[i]);
			System.out.println(op[i].getNome() + " " + salario);
		}
	}
	
	public void atualizarSala() {
		for (int i = 0; i < posicao; i++) {
			double salario = op[i].getSalario() + Calculadora.novoSalario(op[i]);
			op[i].setSalario(salario);
		}
		
		arq_txt.gravarArq(formatarLista());
	}
	
	public int tamanhoOp() {
		return posicao;
	}

}
