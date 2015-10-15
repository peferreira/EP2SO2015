import java.util.LinkedList;
import java.util.Queue;


public class FirstInFirstOut extends Paginacao {
	
	private Queue<Integer> filaDeQuadros; 
	

	FirstInFirstOut() {
		
		System.out.println("***FIRST IN FIRST OUT***s");
		filaDeQuadros = new LinkedList<Integer>();
	}
	
	public int selecionaQuadroParaSair(){
		
		return filaDeQuadros.poll();
	} 
	
	void rotinaPaginacao(int quadroMemoria, int t) {
		if (!filaDeQuadros.contains(quadroMemoria)) {
			filaDeQuadros.add(quadroMemoria);
		}
		System.out.println("acesso ao quadro " + quadroMemoria + " no tempo " + t);
	}
}
