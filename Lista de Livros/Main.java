import java.util.*;

public class Main {

	public static void main(String[] args) {
		// Inst�ncia
		Scanner leia = new Scanner(System.in);
		Scanner leiaTxt = new Scanner(System.in);

		// Inst�ncia das classes
		ListaLivro lista = new ListaLivro();

		Livro l;

		int opcao = 0;
		do {

			System.out.println("Qual opera��o voc� deseja realizar? (Digite: \n [1] Novo Cadastro \n "
					+ "[2] Alterar Cadastro \n " + "[3] Excluir Cadastro \n " + "[9] Sair do Sistema.");

			opcao = leia.nextInt();
			switch (opcao) {
			case 1:
				l = new Livro();
				
				System.out.println("Escreva o ISBN do Livro");
				l.setISBN(leiaTxt.nextLine());

				System.out.println("Escreva o Titulo do livro");
				l.setTitulo(leiaTxt.nextLine());

				System.out.println("Escreva o nome do Autor");
				l.setAutor(leiaTxt.nextLine());

				System.out.println("Escreva a quantidade de p�ginas do Livro");
				l.setQtdpags(leia.nextInt());
				
				lista.adicionar(l);

				break;
			case 2:

				System.out.println("Escolha o Livro pelo Titulo para substituir para um novo titulo");

				int pos = leia.nextInt();
				l = lista.buscar(pos);
				l.setTitulo(leiaTxt.nextLine());

				lista.alterarLivro(pos, l);

				break;
			case 3:
				System.out.println("Escolha a posi��o do Livro que deseja excluir");

				int posExcluir = leia.nextInt();
				lista.excluir(posExcluir);

				break;
			case 9:
				break;
			default:
				System.err.println("N�mero Inv�lido");
			}
		} while (opcao != 9);

	}
}
