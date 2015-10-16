import java.util.LinkedList;
import java.util.Queue;


public class SecondChancePage extends Paginacao {
	
	private Queue<Integer> filaDeQuadros;
	private boolean[] bitR;
	private int numQuadros;
	SecondChancePage(int numQuadros) {
		/*System.out.println("***SECOND CHANGE PAGE***");*/
		bitR = new boolean[numQuadros];
		filaDeQuadros = new LinkedList<Integer>();
		this.numQuadros = numQuadros;
	}
	
	public int selecionaQuadroParaSair(){
		
		int segundaChance;
		while (!filaDeQuadros.isEmpty()) {
			
			if (bitR[filaDeQuadros.peek()] == false)
				return filaDeQuadros.poll();
			else {
				segundaChance = filaDeQuadros.poll();
				filaDeQuadros.add(segundaChance);
				bitR[segundaChance] = false;
			    /*System.out.println("ganhou segunda chance!");*/

			}
		}
		/*System.out.println("ERRO: nao tinha ngm na fila de quadros, ja deveriam existir quadros livres");*/
		return -1;
	} 
	
	void rotinaPaginacao(int quadroMemoria, int t) {
		if (!filaDeQuadros.contains(quadroMemoria)) {
			filaDeQuadros.add(quadroMemoria);
		}
		else
			bitR[quadroMemoria] = true;
		/*System.out.println("acesso ao quadro " + quadroMemoria + " no tempo " + t);*/
	}
}
