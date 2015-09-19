import java.util.List;


public class Processo {
	String nome;
	double t0;
	double tf;
	private int b;
	private int pid;
	private int posInicialMemoriaVirtual;
	
	
	

	List<AcessoDaMemoria> listaDeAcessosDaMemoria;
	
	public Processo(double t0, String nome, double tf, int b, int pid){
		this.t0 = t0;
		this.nome = nome;
		this.tf = tf;
		this.b = b;
		this.pid = pid;
	}

	public String getNome() {
		return nome;
	}

	public double getT0() {
		return t0;
	}

	public double getTf() {
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
