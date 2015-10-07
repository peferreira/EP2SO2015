import java.util.List;


public class NextFit extends Gerenciador {
	
	int IndiceUltimoBlocoAlocado;
	public NextFit(List<Processo> processos, int virtual, int total){
		inicializaBlocosLivres(virtual, total);
		this.processos = processos;
		memoriaLivre = virtual;
		IndiceUltimoBlocoAlocado = 0;
	}
	
	
	public void compactarMemoriaVirtual(){
		super.compactarMemoriaVirtual();
		IndiceUltimoBlocoAlocado = 0;
	}
	
	public void executar(){
		long tempoInicial = System.nanoTime();
		long tempoAtual;
		long tempoPassado;
		tempoPassado = tempoAtual = 0;
		while(!processos.isEmpty()){
			tempoAtual = (long) ((System.nanoTime() - tempoInicial)/ 1e9);
			
				
			for(Processo p: processos){/*posicao menor que zero: o processo nao esta na memoria*/
				if (tempoAtual >= p.getT0() && p.getPosInicialMemoriaVirtual() < 0) {
					if(memoriaLivre >= p.getB()){
						if(alocarMemoriaProcesso(p)){
							//System.out.println("alocando memoria p/"+ p.nome + "  tempo atual:" + tempoAtual);
							p.setTfRelativo((int)tempoAtual);
						}
					
						else{
							System.out.println("antes de compactar:");
							imprimeProcessos();
							compactarMemoriaVirtual();
							System.out.println("depois de compactar");
							imprimeProcessos();
							if(alocarMemoriaProcesso(p)){
								//System.out.println("alocando memoria p/"+ p.nome + "  tempo atual:" + tempoAtual);
								p.setTfRelativo((int)tempoAtual);
							}
						}
						/*else
							//System.out.println("ERRO: DEVERIA TER ALOCADO POIS JA FEZ COMPACTACAO");*/
						
					}
				}
				/*posicao maior ou igual a 0 processo esta na memoria*/
				if (p.getPosInicialMemoriaVirtual() >= 0 && tempoAtual >= p.getTfRelativo()  ){
					liberaMemoriaProcesso(p);
					processos.remove(p);
					System.out.println("removi o processo" + p.nome + "   tempo atual:"+ tempoAtual+ "       tf relativo:"+ p.getTfRelativo());
					break;/*evitar concurrent modification na lista*/
				}
			}
			
			if(tempoAtual - tempoPassado > 0){
				System.out.println(tempoAtual);
				tempoPassado = tempoAtual;
				imprimeProcessosNaMemoria();
				imprimeBlocosLivres();
				System.out.println("memoria livre:"+ memoriaLivre);
			}

			
		}
		System.out.println("situacao final da memoria:");
		imprimeProcessos();
		imprimeProcessosNaMemoria();
		imprimeBlocosLivres();
		System.out.println("memoria livre:"+ memoriaLivre);
	}
	
	/*alocação de memoria do next fit*/
	
	boolean alocarMemoriaProcesso(Processo novoProcesso){
		System.out.println("aloca memoria Next Fit");

		BlocoLivre bloco;
		int indiceBlocoInicial = IndiceUltimoBlocoAlocado;
		for(int i = indiceBlocoInicial; i < blocosLivres.size() ; i++){
			bloco = blocosLivres.get(i);
			if(bloco.getTamanho() >= novoProcesso.getB()){
				novoProcesso.setPosInicialMemoriaVirtual(bloco.getInicio());
				particionaBlocoLivre(bloco, novoProcesso.getB());
				decrementaMemLivre(novoProcesso.getB());
				IndiceUltimoBlocoAlocado = i;
				return true;
			}
		}
		
		for(int i = 0; i < indiceBlocoInicial ; i++){
			bloco = blocosLivres.get(i);
			if(bloco.getTamanho() >= novoProcesso.getB()){
				novoProcesso.setPosInicialMemoriaVirtual(bloco.getInicio());
				particionaBlocoLivre(bloco, novoProcesso.getB());
				decrementaMemLivre(novoProcesso.getB());
				IndiceUltimoBlocoAlocado = i;
				return true;
			}
		}
		return false;
	}
	
}
