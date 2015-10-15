import java.util.List;


public class Simulador {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LeituraDaEntrada le = new LeituraDaEntrada();
		Gerenciador ger = null;
		Paginacao pag = null;
		Prompt prompt = new Prompt();
		Memoria mem;

		
		while(prompt.leComandos()){
			
			List<Processo> listaDeProc = le.criarListaDeProcessos(prompt.getArquivoEntrada());
			for (Processo p: listaDeProc){
				System.out.println("nome:" +p.nome+ " | pos t0:" + p.getT0());
			}
			System.out.println("mem virtual:" +le.getVirtual());
			System.out.println("mem total:" +le.getTotal());
			
			int espaco, paginacao, intervalo;
			espaco = prompt.getAlgEspacoLivre();
			paginacao = prompt.getAlgPaginacao();
			intervalo = prompt.getIntervalo();
			
			mem = new Memoria(le.getTotal(), le.getVirtual());
			
			switch (espaco) {
				case 1:
					ger = new FirstFit(listaDeProc, le.getVirtual(), le.getTotal());
					break;
				case 2:
					ger = new NextFit(listaDeProc, le.getVirtual(), le.getTotal());
					break;
				case 3:
					break;
				default:
					break;
			}
			
			switch (paginacao) {
				case 1:
					pag = new NotRecentlyUsedPage(le.getTotal()/16);
					break;
				case 2:
					pag = new FirstInFirstOut();
					break;
				case 3:
					pag = new SecondChancePage(le.getTotal()/16);
					break;
				case 4:
					break;
				default:
					break;
			}
			
			
			
			/*Gerenciador nf = new NextFit(listaDeProc, le.getVirtual(), le.getTotal());*/
			/*NotRecentlyUsedPage nrup = new NotRecentlyUsedPage(le.getTotal()/16);*/
			/*Paginacao fifo = new FirstInFirstOut();*/
			/*Paginacao scp = new SecondChancePage(le.getTotal()/16);*/
	
			ger.executar(mem, pag);
		}
		
	}

}
