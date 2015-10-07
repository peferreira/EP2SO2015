
public class MemoriaVirtual {
	private final int numBytesPorPagina = 16;
	private Pagina[] paginas;
	private int tamanho; 
	
	public MemoriaVirtual(int tamanho){
		this.tamanho = tamanho;
		paginas = new Pagina[tamanho/numBytesPorPagina];
	}
	


    /*recebe o endereço da memoria virtual e devolve o respectivo endereco na mem fisica*/
	int getEnderecoPaginaDaMemoriaFisica(int enderecoPaginaMemoriaVirtual){
		int enderecoMemFisica;
		int paginaMemVirtual = enderecoPaginaMemoriaVirtual / numBytesPorPagina;
		Pagina p = paginas[paginaMemVirtual];
		if(p.estaNaMemoriaFisica())
			return p.getEnderecoNaMemoriaFisica();
		else
			return -1;
	}

	/*define um valor de endereço para memoria fisica*/
	void setEnderecoPaginaDaMemoriaFisica(int enderecoPaginaMemoriaVirtual, int enderecoPaginaNaMemoriaFisica){
		int paginaMemVirtual = enderecoPaginaMemoriaVirtual / numBytesPorPagina;
		Pagina p = paginas[paginaMemVirtual];
		if(!p.estaNaMemoriaFisica()){
			p.setEnderecoNaMemoriaFisica(enderecoPaginaNaMemoriaFisica);
			p.setEstaNaMemoriaFisica(true);
		}
	}
	
	void removePaginaDaMemoriaFisica(int enderecoPaginaMemorialVirtual){
		int paginaMemVirtual = enderecoPaginaMemorialVirtual / numBytesPorPagina;
		Pagina p = paginas[paginaMemVirtual];
		if(p.estaNaMemoriaFisica()){
			p.setEstaNaMemoriaFisica(false);
		}
		else
			System.out.println("esta tentando remover uma pagina que nao estava na memoria fisica!!!!");
	}

}

class Pagina{
	int enderecoNaMemoriaFisica;
	boolean estaNaMemoriaFisica;
	
	public Pagina(){
	
	}
	
	public int getEnderecoNaMemoriaFisica() {
		return enderecoNaMemoriaFisica;
	}

	public void setEnderecoNaMemoriaFisica(int enderecoNaMemoriaFisica) {
		this.enderecoNaMemoriaFisica = enderecoNaMemoriaFisica;
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