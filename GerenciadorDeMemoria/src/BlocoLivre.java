
public class BlocoLivre {
	
	private int inicio;
	private int tamanho;
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public BlocoLivre(int inicio, int tamanho){
		this.inicio = inicio;
		this.tamanho = tamanho;
	}
	public int getInicio() {
		return inicio;
	}
	public int getTamanho() {
		return tamanho;
	}
}
