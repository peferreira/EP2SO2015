import java.util.List;


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
	
	
}
