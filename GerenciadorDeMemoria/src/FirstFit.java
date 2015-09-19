import java.util.List;


public class FirstFit {
	
	List<BlocoLivre> blocosLivres;
	List<Processo> processos;

	public FirstFit(List<Processo> processos, int virtual, int total){
		inicializaBlocosLivres(virtual, total);
	}
	
	void inicializaBlocosLivres(int virtual, int total){
		blocosLivres.add(new BlocoLivre(0,virtual)); /*virtual ou total ??!*/
	}
	
	boolean alocarMemoriaProcesso(Processo novoProcesso){
		for(BlocoLivre bloco: blocosLivres){
			if(bloco.getTamanho() >= novoProcesso.getB()){
				novoProcesso.setPosInicialMemoriaVirtual(bloco.getInicio());
				particionaBlocoLivre(bloco, novoProcesso.getB());
				return true;
			}
		}
		return false;
	}
	
	void liberaMemoriaProcesso(Processo processoSaindo){
		BlocoLivre novoBlocoLivre = new BlocoLivre(processoSaindo.getPosInicialMemoriaVirtual(),
								                   processoSaindo.getB());
		for(BlocoLivre bloco: blocosLivres){
			if(novoBlocoLivre.getInicio() < bloco.getInicio()){
				blocosLivres.add(blocosLivres.indexOf(bloco), novoBlocoLivre);
				break;
			}
		}
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

	
	
}
