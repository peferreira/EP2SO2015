import java.util.LinkedList;
import java.util.Queue;


public class FirstInFirstOut extends Paginacao {
	
	private Queue<Integer> filaDeQuadros; 
	

	FirstInFirstOut() {
		
		filaDeQuadros = new LinkedList<Integer>();
	}
	
	public int selecionaQuadroParaSair(){
		
		return filaDeQuadros.poll();
	} 
	
	void rotinaPaginacao(int quadroMemoria, int t) {
		if (!filaDeQuadros.contains(quadroMemoria)) {
			filaDeQuadros.add(quadroMemoria);
		}
	}
}
