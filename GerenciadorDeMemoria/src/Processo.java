import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Processo implements Comparable<Processo>{
	String nome;
	private int t0;
	private int tf;
	private int tfRelativo;
	private int b;
	private int pid;
	private int posInicialMemoriaVirtual;
	private int numPaginas;
	
	

	Queue<AcessoDaMemoria> filaDeAcessosDaMemoria;
	
	public Processo(int t0, String nome, int tf, int b, int pid){
		this.t0 = t0;
		this.nome = nome;
		this.tf = tf;
		this.b = b;
		this.pid = pid;
		posInicialMemoriaVirtual = -1;
		this.numPaginas = b/16;
		filaDeAcessosDaMemoria = new LinkedList<AcessoDaMemoria>();
	}

	
	
	public void setTfRelativo(int tempoDeInicio){
		this.tfRelativo = tempoDeInicio + tf - t0;
	}
	
	public int getTfRelativo(){
		return tfRelativo;
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

	public Queue<AcessoDaMemoria> getFilaDeAcessosDaMemoria() {
		return filaDeAcessosDaMemoria;
	}
	
	public int getPosInicialMemoriaVirtual() {
		return posInicialMemoriaVirtual;
	}

	public void setPosInicialMemoriaVirtual(int posInicialMemoriaVirtual) {
		this.posInicialMemoriaVirtual = posInicialMemoriaVirtual;
	}

	public int calculaPosicaoFinal(){
		return posInicialMemoriaVirtual+b-1;
	}
	
	@Override
	public int compareTo(Processo p) {
		if(this.getPosInicialMemoriaVirtual() < p.getPosInicialMemoriaVirtual()){
			return -1;
		}else{
			return 1;
		}
		
	}



	public int getNumPaginas() {
		return numPaginas;
	}



	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
	
	
}
