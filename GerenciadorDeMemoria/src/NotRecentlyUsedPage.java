
public class NotRecentlyUsedPage extends Paginacao {
	private boolean[] bitR;
	private int numQuadros;
	private int ultimoLimpaBitR;
	private final int intervaloLimpaBitR = 5;
	public NotRecentlyUsedPage(int numQuadros){
		System.out.println("***NOT RECENTLY USED PAGE***");
		bitR = new boolean[numQuadros];
		this.numQuadros = numQuadros;
		this.ultimoLimpaBitR = 0;
		
	}
	
	void setBitRTrue(int quadro, int tempoChegada){
		System.out.println("acessou o quadro: "+ quadro +" no tempo: " + tempoChegada );
		limpaBitR(tempoChegada);
		bitR[quadro] = true;
	}
	
	void limpaBitR(int momentoDoAcesso){
		int novoUltimoLimpaBitR = ((momentoDoAcesso/intervaloLimpaBitR)*intervaloLimpaBitR);
		if(ultimoLimpaBitR < novoUltimoLimpaBitR){
			for(int i = 0; i < numQuadros; i++){
				bitR[i] = false;
			}
			ultimoLimpaBitR = novoUltimoLimpaBitR;
		}
		
	}
	
	public int selecionaQuadroParaSair(){
		for(int i = 0; i < numQuadros; i++){
			if(!bitR[i]){
				return i;
			}
		}
		return 0;
	}


	void rotinaPaginacao(int quadroMemoria, int t) {
		setBitRTrue(quadroMemoria,t);
		
	}
}
