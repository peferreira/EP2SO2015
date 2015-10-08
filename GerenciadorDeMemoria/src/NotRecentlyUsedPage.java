
public class NotRecentlyUsedPage {
	private boolean[] bitR;
	private int numQuadros;
	public NotRecentlyUsedPage(int numQuadros){
		bitR = new boolean[numQuadros];
		this.numQuadros = numQuadros;
	}
	
	void setBitRTrue(int quadro){
		bitR[quadro] = true;
	}
	
	void resetBitR(){
		for(int i = 0; i < numQuadros; i++){
			bitR[i] = false;
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
}
