import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Gerenciador {
	List<BlocoLivre> blocosLivres;
	List<Processo> processos;
	int memoriaLivre;
	
	void incrementaMemLivre(int incremento){
		memoriaLivre += incremento;
	}
	
	void decrementaMemLivre(int decremento){
		memoriaLivre -= decremento;
	}
	
	
	void inicializaBlocosLivres(int virtual, int total){
		blocosLivres = new LinkedList<BlocoLivre>();
		blocosLivres.add(new BlocoLivre(0,virtual)); /*virtual ou total ??!*/
	}
	boolean alocarMemoriaProcesso(Processo novoProcesso){return true;}

	
	public void executar(Memoria mem, NotRecentlyUsedPage nrup){
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
			
			paginacao(mem, nrup, tempoAtual);
			
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
    
	void paginacao(Memoria mem, NotRecentlyUsedPage nrup, long tempoAtual){
		AcessoDaMemoria acessoDaMemoria;
		Queue<AcessoDaMemoria> filaDeAcessoMemoria;
		for(Processo p: processos){
			filaDeAcessoMemoria = p.getFilaDeAcessosDaMemoria();
			if(!filaDeAcessoMemoria.isEmpty() && filaDeAcessoMemoria.element().getT() >= tempoAtual){
				acessoDaMemoria = p.getFilaDeAcessosDaMemoria().poll();
				mem.acessarMemoria(acessoDaMemoria, nrup);
			}
		}
	}
	
	void liberaMemoriaProcesso(Processo processoSaindo){
		BlocoLivre novoBlocoLivre = new BlocoLivre(processoSaindo.getPosInicialMemoriaVirtual(),
								                   processoSaindo.getB());
		boolean uniuBlocos = false;
		int posicaoDeEntrada = -1;
		incrementaMemLivre(processoSaindo.getB());
		for(BlocoLivre bloco: blocosLivres){
			if(novoBlocoLivre.getInicio() < bloco.getInicio()){
				posicaoDeEntrada = blocosLivres.indexOf(bloco);
				System.out.println("posicao entrada:" + posicaoDeEntrada);
				if(posicaoDeEntrada > 0 ){
					if(novoBlocoLivre.getInicio() == blocosLivres.get(posicaoDeEntrada-1).calculaPosicaoFinal() + 1 ){
						aumentaBlocoEsq(posicaoDeEntrada-1, novoBlocoLivre.getTamanho());
						uniuBlocos = true;
						System.out.println("uniubloco");
					}
				}
				if(posicaoDeEntrada < blocosLivres.size() ){
					if(novoBlocoLivre.calculaPosicaoFinal() == blocosLivres.get(posicaoDeEntrada).getInicio() - 1 ){
						if(uniuBlocos == true){
							aumentaBlocoEsq(posicaoDeEntrada-1, blocosLivres.get(posicaoDeEntrada).getTamanho());
							blocosLivres.remove(posicaoDeEntrada);
						}
						else{
							aumentaBlocoDir(posicaoDeEntrada, novoBlocoLivre.getTamanho());
							uniuBlocos = true;
						}
						System.out.println("uniubloco2!");
					}
				}
				if(uniuBlocos == false)
					blocosLivres.add(posicaoDeEntrada, novoBlocoLivre);
				break;
			}

		}
		if(posicaoDeEntrada == -1)
			blocosLivres.add(novoBlocoLivre);

		
	}
	
	
	
	
	
	/*junta o bloco da posicao de entrada -1 com o bloco da posicao de entrada*/
	void aumentaBlocoEsq(int posicaoBlocoEsq, int tamanho){
		BlocoLivre esq = blocosLivres.get(posicaoBlocoEsq);
		esq.setTamanho(esq.getTamanho() + tamanho); 
	}
	
	void aumentaBlocoDir(int posicaoDeEntrada, int tamanho){
		BlocoLivre pos = blocosLivres.get(posicaoDeEntrada);
		pos.setTamanho(pos.getTamanho() + tamanho);
		pos.setInicio(pos.getInicio() - tamanho);
	}
	
	
	void particionaBlocoLivre(BlocoLivre bloco, int b) {
		bloco.setInicio(bloco.getInicio() + b);
		bloco.setTamanho(bloco.getTamanho() - b);
		if(bloco.getTamanho() == 0){
			
			blocosLivres.remove(bloco);
		}
		else if(bloco.getTamanho() < 0){
			System.out.println("bloco com tamanho menor que zero!!!!");
		}
			
	}
	
	
	void compactarMemoriaVirtual() {
		Collections.sort(processos);
		int novaPosMemoria = 0;
		for(Processo p: processos){
			if(p.getPosInicialMemoriaVirtual() >= 0){
				p.setPosInicialMemoriaVirtual(novaPosMemoria);
				novaPosMemoria = p.calculaPosicaoFinal() + 1;
			}
		}
		blocosLivres.clear();
		blocosLivres.add(new BlocoLivre(novaPosMemoria, memoriaLivre));
	}

	void imprimeProcessos() {
		for (Processo p: processos){
			System.out.println("nome:" +p.nome+ " | pos memoria:" + p.getPosInicialMemoriaVirtual());
			
		}
		
	}

	void imprimeProcessosNaMemoria(){
		for (Processo p: processos){
			if(p.getPosInicialMemoriaVirtual() >= 0 ){
				System.out.println("nome:" +p.nome+ " | pos memoria:" + p.getPosInicialMemoriaVirtual());
			}
		}
	}
	
	void imprimeBlocosLivres(){
		for (BlocoLivre b: blocosLivres){
			System.out.println("inicio:" + b.getInicio() + "     final:"+ b.calculaPosicaoFinal() + "     tamanho:"+ b.getTamanho());
		}
	}
	
	
	
	
}
