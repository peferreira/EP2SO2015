
public class AcessoDaMemoria {
	private int p;
	private int t;
	private String nomeProc;
	
	public int getP() {
		return p;
	}
	public int getT() {
		return t;
	}
	
	public AcessoDaMemoria(int p, int t, String nomeProc){
		this.p = p;
		this.t = t;
		this.nomeProc = nomeProc;
	}
	public String getNomeProc() {
		return nomeProc;
	}
}
