public class ListaLivro {

	private Livro[] vet;
	private int pos = 0;

	public ListaLivro(int tamanho) {
		vet = new Livro[tamanho];
	}

	public ListaLivro() {
		vet = new Livro[3];
	}

	public void adicionar(Livro livro) {
		if (pos == vet.length) {
			Livro vetAux[] = new Livro[vet.length * 2];
			for (int i = 0; i < vetAux.length; i++) {
				vetAux[i] = vet[i];
			}
			vet = vetAux;
		}
		vet[pos] = livro;
		pos++;
	}

	public void alterarLivro(int pos, Livro livro) {
		vet[pos] = livro;
	}

	public void excluir(int num) {
		for (int i = num; i < pos; i++) {
			vet[i] = vet[i + 1];
		}
		vet[pos - 1] = null;
		pos--;
	}

	public Livro buscar(int posicao) {
		if (posicao < this.pos && posicao > -1)
			return vet[posicao];

		return null;
	}

}
