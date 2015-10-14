
public class MemoriaVirtual {
	private int numBytesPorPagina;
	private Pagina[] paginas;
	private int tamanho; 
	
	public MemoriaVirtual(int tamanho, int numBytesPorPagina){
		this.numBytesPorPagina = numBytesPorPagina;
		this.tamanho = tamanho;
		paginas = new Pagina[tamanho/numBytesPorPagina];
		for(int i = 0; i < tamanho/numBytesPorPagina; i++){
			paginas[i] = new Pagina();
		}
	}
	


    /*recebe o endereço da memoria virtual e devolve o respectivo endereco na mem fisica*/
	int getQuadroDaMemoriaFisica(int enderecoPaginaMemoriaVirtual){
		int enderecoMemFisica;
		int paginaMemVirtual = enderecoPaginaMemoriaVirtual / numBytesPorPagina;
		Pagina p = paginas[paginaMemVirtual];
		if(p.estaNaMemoriaFisica())
			return p.getQuadro();
		else
			return -1;
	}

	/*define um valor de endereço para memoria fisica*/
	void setQuadroDaMemoriaFisica(int posicaoMemoriaVirtual, int quadro){
		int paginaMemVirtual = posicaoMemoriaVirtual / numBytesPorPagina;
		Pagina p = paginas[paginaMemVirtual];
		if(!p.estaNaMemoriaFisica()){
			p.setQuadro(quadro);
			p.setEstaNaMemoriaFisica(true);
		}
		else
			System.out.println("ja estava na memoria fisica!!!!");
	}
	
	void removeQuadroDaMemoriaFisica(int paginaMemVirtual){
		Pagina p = paginas[paginaMemVirtual];
		if(p.estaNaMemoriaFisica()){
			p.setEstaNaMemoriaFisica(false);
		}
		else
			System.out.println("ERRO: esta tentando remover uma pagina que nao estava na memoria fisica!!!!");
	}
	
	boolean processoEstaNaMemoriaFisica(Processo p){
		int paginaMemVirtual = p.getPosInicialMemoriaVirtual() / numBytesPorPagina;
		return paginas[paginaMemVirtual].estaNaMemoriaFisica(); 
	}



	int[] removeProcesso(Processo p) {
		
		int paginaMemVirtual = p.getPosInicialMemoriaVirtual() / numBytesPorPagina;
		int quadros[] = new int[p.getNumPaginas()];
		for(int i = paginaMemVirtual; i < p.getNumPaginas() + paginaMemVirtual; i++){
			if(paginas[i].estaNaMemoriaFisica()){
				paginas[i].setEstaNaMemoriaFisica(false);
				quadros[i - paginaMemVirtual] = paginas[i].getQuadro();
				System.out.println("xxxxxxxxxxxxx");
			}
			else
				System.out.println("Tenta remover quadro " + quadros[i - paginaMemVirtual] + ": mas nao esta na memoria fisica");
		}
		return quadros;
	}
}

class Pagina{
	int quadro;
	boolean estaNaMemoriaFisica;
	
	public Pagina(){
		estaNaMemoriaFisica = false;
	}
	
	public int getQuadro() {
		return quadro;
	}

	public void setQuadro(int quadro) {
		this.quadro = quadro;
	}

	public boolean estaNaMemoriaFisica() {
		return estaNaMemoriaFisica;
	}

	public void setEstaNaMemoriaFisica(boolean estaNaMemoriaFisica) {
		this.estaNaMemoriaFisica = estaNaMemoriaFisica;
	}

	public Pagina(int tamanho, int posicao){
		estaNaMemoriaFisica = false;
		
	}
	
}