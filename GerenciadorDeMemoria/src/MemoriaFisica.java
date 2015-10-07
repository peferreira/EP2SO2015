
public class MemoriaFisica {
	
	private final int numBytesPorPagina = 16;
	private Quadro[] quadros;
	private int tamanho; 
	public MemoriaFisica(int tamanho){
		this.tamanho = tamanho;
		quadros = new Quadro[tamanho/numBytesPorPagina];
	}
	

    void setPaginaMemoriaVirtual(int quadroMemoriaFisica, int paginaMemoriaVirtual){
    	quadros[quadroMemoriaFisica].setPaginaMemoriaVirtual(paginaMemoriaVirtual);
    }

    
    int getQuadroLivreNaMemoria(){
    	for(int i=0; i < quadros.length; i++){
    		if(!quadros[i].quadroEstaOcupado()){
    			return i;
    		}
    	}
    	return -1;
    }

	
}


class Quadro {
	private boolean quadroOcupado;
	private int paginaMemoriaVirtual;
	
	
	Quadro(){
		quadroOcupado = false;
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