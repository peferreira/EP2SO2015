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
	
	
	/*alocação de memoria do next fit*/
	
	boolean alocarMemoriaProcesso(Processo novoProcesso){
		System.out.println("***Alocando memoria com NEXT FIT***");

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
