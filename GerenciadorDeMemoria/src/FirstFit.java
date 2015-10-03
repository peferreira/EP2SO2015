import java.util.LinkedList;
import java.util.List;
import java.math.*;

public class FirstFit extends Gerenciador{
	
	

	public FirstFit(List<Processo> processos, int virtual, int total){
		inicializaBlocosLivres(virtual, total);
		this.processos = processos;
		memoriaLivre = virtual;
	}
	
	void inicializaBlocosLivres(int virtual, int total){
		blocosLivres = new LinkedList<BlocoLivre>();
		blocosLivres.add(new BlocoLivre(0,virtual)); /*virtual ou total ??!*/
	}
	
	boolean alocarMemoriaProcesso(Processo novoProcesso){
		for(BlocoLivre bloco: blocosLivres){
			if(bloco.getTamanho() >= novoProcesso.getB()){
				novoProcesso.setPosInicialMemoriaVirtual(bloco.getInicio());
				particionaBlocoLivre(bloco, novoProcesso.getB());
				decrementaMemLivre(novoProcesso.getB());
				return true;
			}
		}
		return false;
	}
	
	void liberaMemoriaProcesso(Processo processoSaindo){
		BlocoLivre novoBlocoLivre = new BlocoLivre(processoSaindo.getPosInicialMemoriaVirtual(),
								                   processoSaindo.getB());
		boolean uniuBlocos = false;
		int posicaoDeEntrada = 0;
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
	}
	/*junta o bloco da posicao de entrada -1 com o bloco da posicao de entrada*/
	private void aumentaBlocoEsq(int posicaoBlocoEsq, int tamanho){
		BlocoLivre esq = blocosLivres.get(posicaoBlocoEsq);
		esq.setTamanho(esq.getTamanho() + tamanho); 
	}
	
	private void aumentaBlocoDir(int posicaoDeEntrada, int tamanho){
		BlocoLivre pos = blocosLivres.get(posicaoDeEntrada);
		pos.setTamanho(pos.getTamanho() + tamanho);
		pos.setInicio(pos.getInicio() - tamanho);
	}
	
	
	
	
	void desfragmentaMemoriaProcesso(){
		
	}
	
	private void particionaBlocoLivre(BlocoLivre bloco, int b) {
		bloco.setInicio(bloco.getInicio() + b);
		bloco.setTamanho(bloco.getTamanho() - b);
		if(bloco.getTamanho() == 0){
			
			blocosLivres.remove(bloco);
		}
		else if(bloco.getTamanho() < 0){
			System.out.println("bloco com tamanho menor que zero!!!!");
		}
			
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
					alocarMemoriaProcesso(p);
				}
				/*posicao maior ou igual a 0 processo esta na memoria*/
				if (tempoAtual >= p.getTf() && p.getPosInicialMemoriaVirtual() >= 0){
					liberaMemoriaProcesso(p);
					processos.remove(p);
					break;/*evitar concurrent modification na lista*/
				}
			}
			
			if(tempoAtual - tempoPassado > 1){
				System.out.println(tempoAtual);
				tempoPassado = tempoAtual;
				imprimeProcessosNaMemoria();
				imprimeBlocosLivres();
				System.out.println("memoria livre:"+ memoriaLivre);
			}
			imprimeProcessos();

			
		}
		imprimeProcessos();
		imprimeProcessosNaMemoria();
		imprimeBlocosLivres();
		System.out.println("memoria livre:"+ memoriaLivre);
	}
	
	
	private void imprimeProcessos() {
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
