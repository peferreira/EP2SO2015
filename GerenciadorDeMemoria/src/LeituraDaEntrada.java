import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class LeituraDaEntrada {
	private int t0[] = {1, 2, 3, 4, 5 ,6 ,7};
	private int tf[] = {15 , 3, 20 ,6 ,14 , 9 ,16};
	private String nome[] = {"proc1", "proc2", "proc3", "proc4", "proc5", "proc6", "proc7"};
	private int b[] = {10, 10, 30, 20, 10 ,30 ,20};
	private int numProcessos;
	private int total, virtual;
	
	
	
	public int getTotal() {
		return total;
	}

	public int getVirtual() {
		return virtual;
	}

	public LeituraDaEntrada(){
		numProcessos = 0;
		/*virtual = 64;
		total = 32;*/
	}
	
	/*List<Processo> criarListaDeProcessos(){
		List<Processo> listaDeProcessos = new LinkedList<Processo>();
		for(int i = 0; i < t0.length; i++){
			listaDeProcessos.add(new Processo(t0[i], nome[i], tf[i], b[i],numProcessos));
			numProcessos++;
		}
		return listaDeProcessos;
	}*/
	
	/*le entrada e retorna lista de processos*/
	List<Processo> criarListaDeProcessos(String nomeArquivo){
		String arquivo = nomeArquivo;
		int numProcessos = 0;
		BufferedReader br = null;
		List<Processo> listaDeProcessos = new LinkedList<Processo>();
		try {

			String linhaAtual;
     		br = new BufferedReader(new FileReader("src/"+ arquivo));
     		
     		linhaAtual = br.readLine();
     		String[] linha = linhaAtual.split(" ");
     		
     		total = Integer.parseInt(linha[0]);
     		virtual = Integer.parseInt(linha[1]);
     		     		
			while ((linhaAtual = br.readLine()) != null) {
				
				System.out.println(linhaAtual);
				linha = linhaAtual.split(" ");
				
				t0[numProcessos] = Integer.parseInt(linha[0]);
				nome[numProcessos] = linha[1];
				tf[numProcessos] = Integer.parseInt(linha[2]);
				b[numProcessos] = Integer.parseInt(linha[3]);

				int p,t;
				p = t = 0;
				Processo proc = new Processo(t0[numProcessos], nome[numProcessos], tf[numProcessos], b[numProcessos], numProcessos);
		
				for(int i = 4; i < linha.length; i++){
					if(i%2 == 0){
						p = Integer.parseInt(linha[i]);
					}
					else{
						t = Integer.parseInt(linha[i]);
						AcessoDaMemoria acessoDaMemoria = new AcessoDaMemoria(p, t, nome[numProcessos]); 
						proc.filaDeAcessosDaMemoria.add(acessoDaMemoria);
						
					}
				}
				listaDeProcessos.add(proc);
				numProcessos++;
			}
			return listaDeProcessos;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	
		
		return null;
	
	}
	
	
	
}
