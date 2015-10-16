import java.util.List;

public class FirstFit extends Gerenciador{
	
	

	public FirstFit(List<Processo> processos, int virtual, int total){
		inicializaBlocosLivres(virtual, total);
		this.processos = processos;
		memoriaLivre = virtual;
	}
	
	boolean alocarMemoriaProcesso(Processo novoProcesso){
		//System.out.println("***Alocando memoria com FIRST FIT***");
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

}
