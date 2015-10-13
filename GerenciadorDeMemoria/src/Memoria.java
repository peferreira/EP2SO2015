
public class Memoria {
	final private int numBytesPorPagina = 16;
	MemoriaFisica mf;
	MemoriaVirtual mv;
	
	public Memoria(int tamanhoFisica, int tamanhoVirtual){
		mf = new MemoriaFisica(tamanhoFisica, numBytesPorPagina);
		mv = new MemoriaVirtual(tamanhoVirtual, numBytesPorPagina);
		
	}
	
	int calculaPaginaDaPosicao(int posicao){
		return posicao/numBytesPorPagina;
	}
	
	public int acessarMemoria(AcessoDaMemoria acessoDaMemoria, NotRecentlyUsedPage nrup){
		int quadroMemoria;
		int posicaoMemoriaFisica;
		quadroMemoria = mv.getQuadroDaMemoriaFisica(acessoDaMemoria.getP());
		
		if(quadroMemoria == -1){/*se nao esta na memoria fisica*/
			quadroMemoria = mf.getQuadroLivreNaMemoria();
			if(mf.getQuadroLivreNaMemoria() == -1){/*se nao existe quadro livre na fisica*/
				//nrup.paginacao(quadroMemoria, acessoDaMemoria.getT());
				
				quadroMemoria = nrup.selecionaQuadroParaSair();/*define o quadro da memoria com paginacao*/
				mv.removeQuadroDaMemoriaFisica(mf.getPagina(quadroMemoria));
				mv.setQuadroDaMemoriaFisica(acessoDaMemoria.getP(), quadroMemoria);
				
				mf.setPaginaMemoriaVirtual(quadroMemoria, acessoDaMemoria.getP());
			}
			else{
				mv.setQuadroDaMemoriaFisica(acessoDaMemoria.getP(), quadroMemoria);
				mf.setPaginaMemoriaVirtual(quadroMemoria, acessoDaMemoria.getP());
			}
			
		}
		nrup.rotinaPaginacao(quadroMemoria, acessoDaMemoria.getT());
		posicaoMemoriaFisica = acessoDaMemoria.getP()% numBytesPorPagina + quadroMemoria*numBytesPorPagina;
		return posicaoMemoriaFisica;
		
	}

	public void liberaMemoria(Processo p) {
		
		if(mv.processoEstaNaMemoriaFisica(p)){
			mf.removeProcesso(p.getNumPaginas(),mv.getQuadroDaMemoriaFisica( p.getPosInicialMemoriaVirtual()));
		}
		mv.removeProcesso(p);

	}

	
	
}
