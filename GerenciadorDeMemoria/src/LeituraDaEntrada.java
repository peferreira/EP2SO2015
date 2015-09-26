import java.util.LinkedList;
import java.util.List;


public class LeituraDaEntrada {
	private int t0[] = {1, 2, 3, 4, 5 ,6 ,7};
	private int tf[] = {15 , 25, 20 ,13 ,14 , 15 ,16};
	private String nome[] = {"proc1", "proc2", "proc3", "proc4", "proc5", "proc6", "proc7"};
	private int b[] = {10, 20, 30, 40, 50 ,60 ,70};
	private int numProcessos;
	private int total, virtual;
	
	
	
	public int getTotal() {
		return total;
	}

	public int getVirtual() {
		return virtual;
	}

	public LeituraDaEntrada(){
		numProcessos = 0;
		virtual = 250;
		total = 120;
	}
	
	List<Processo> criarListaDeProcessos(){
		List<Processo> listaDeProcessos = new LinkedList<Processo>();
		for(int i = 0; i < t0.length; i++){
			listaDeProcessos.add(new Processo(t0[i], nome[i], tf[i], b[i],numProcessos));
			numProcessos++;
		}
		return listaDeProcessos;
	}
}
