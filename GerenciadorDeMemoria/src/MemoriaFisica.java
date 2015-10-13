
public class MemoriaFisica {
	private int numBytesPorPagina;
	private Quadro[] quadros;
	private int tamanho; 
	public MemoriaFisica(int tamanho, int numBytesPorPagina){
		this.tamanho = tamanho;
		this.numBytesPorPagina = numBytesPorPagina;
		quadros = new Quadro[tamanho/numBytesPorPagina];
		for(int i = 0; i < tamanho/numBytesPorPagina; i++){
			quadros[i] = new Quadro();
		}
	}
	

    void setPaginaMemoriaVirtual(int quadroMemoriaFisica, int posicaoMemoriaVirtual){
    	int paginaMemoriaVirtual = posicaoMemoriaVirtual/numBytesPorPagina;
    	quadros[quadroMemoriaFisica].setPaginaMemoriaVirtual(paginaMemoriaVirtual);
    }

    int getPagina(int quadro){
    	return quadros[quadro].getPagina();
    }
    
    int getQuadroLivreNaMemoria(){
    	for(int i=0; i < quadros.length; i++){
    		if(!quadros[i].quadroEstaOcupado()){
    			return i;
    		}
    	}
    	return -1;
    }


	void removeProcesso(int[] quadrosParaRemover) {
		for( int i = 0; i < quadros.length; i++){
			quadros[quadrosParaRemover[i]].setQuadroOcupado(false);

		}
	}

	
}


class Quadro {
	private boolean quadroOcupado;
	private int paginaMemoriaVirtual;
	
	
	Quadro(){
		quadroOcupado = false;
	}
	public int getPagina(){
		return paginaMemoriaVirtual;
	}
	
	public void setPaginaMemoriaVirtual(int paginaMemoriaVirtual){
		this.paginaMemoriaVirtual = paginaMemoriaVirtual;
	}

	public void setQuadroOcupado(boolean quadroOcupado) {
		this.quadroOcupado = quadroOcupado;
	}
	
	public boolean quadroEstaOcupado() {
		return quadroOcupado;
	}
	
}