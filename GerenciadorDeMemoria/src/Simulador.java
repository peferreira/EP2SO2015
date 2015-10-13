import java.util.List;


public class Simulador {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LeituraDaEntrada le = new LeituraDaEntrada();
		List<Processo> listaDeProc = le.criarListaDeProcessos();
		for (Processo p: listaDeProc){
			System.out.println("nome:" +p.nome+ " | pos t0:" + p.getT0());
		}
		System.out.println("mem virtual:" +le.getVirtual());
		System.out.println("mem total:" +le.getTotal());
		
		
		Gerenciador ff = new FirstFit(listaDeProc, le.getVirtual(), le.getTotal());
		
		/*Gerenciador nf = new NextFit(listaDeProc, le.getVirtual(), le.getTotal());*/
		Memoria mem = new Memoria(le.getTotal(), le.getVirtual());
		NotRecentlyUsedPage nrup = new NotRecentlyUsedPage(le.getTotal()/16);
		
		ff.executar(mem, nrup);
		
		
	}

}
