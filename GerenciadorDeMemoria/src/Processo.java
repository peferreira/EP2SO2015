import java.util.List;


public class Processo {
	String nome;
	private int t0;
	private int tf;
	private int b;
	private int pid;
	private int posInicialMemoriaVirtual;
	
	
	

	List<AcessoDaMemoria> listaDeAcessosDaMemoria;
	
	public Processo(int t0, String nome, int tf, int b, int pid){
		this.t0 = t0;
		this.nome = nome;
		this.tf = tf;
		this.b = b;
		this.pid = pid;
		posInicialMemoriaVirtual = -1;
	}

	public String getNome() {
		return nome;
	}

	public int getT0() {
		return t0;
	}

	public int getTf() {
		return tf;
	}

	public int getB() {
		return b;
	}

	public int getPid() {
		return pid;
	}

	public List<AcessoDaMemoria> getListaDeAcessosDaMemoria() {
		return listaDeAcessosDaMemoria;
	}
	
	public int getPosInicialMemoriaVirtual() {
		return posInicialMemoriaVirtual;
	}

	public void setPosInicialMemoriaVirtual(int posInicialMemoriaVirtual) {
		this.posInicialMemoriaVirtual = posInicialMemoriaVirtual;
	}
	
}
