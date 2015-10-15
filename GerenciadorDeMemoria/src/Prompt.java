import java.io.BufferedReader;
import java.util.Scanner;

public class Prompt {
	
	BufferedReader br;
	Scanner sc;
	String arquivoEntrada;
	int algEspacoLivre;
	int algPaginacao;
	int intervalo;
	
	Prompt() {
		br = null;
		sc = new Scanner(System.in);
	}
	
	boolean leComandos() {
		
		String comando = "";
		System.out.print("[ep2]:");

		while (!comando.equals("sai")) {
			comando = sc.next();
			if (comando.equals("carrega")) {
				arquivoEntrada = sc.next();
				/*System.out.println("Carrega arquivo " + arquivoEntrada);*/
			}
			else if (comando.equals("espaco")) {
				algEspacoLivre = Integer.parseInt(sc.next());
				/*System.out.println("Executa com algoritmo de gerenciamento de espaco livre numero " + algEspacoLivre);*/
			}
			else if (comando.equals("substitui")) {
				algPaginacao = Integer.parseInt(sc.next());
				/*System.out.println("Executa com algoritmo de paginacao numero " + algPaginacao);*/
			}
			else if (comando.equals("executa")) {
				intervalo = Integer.parseInt(sc.next());
				return true;
				/*System.out.println("Executa simulador e imprime estado das memorias de " + intervalo + 
						" em " + intervalo + " segundos");*/
			}
			
			if (!comando.equals("sai")){
				System.out.print("[ep2]:");
			}
		
		}
		return false;
	}
	
	String getArquivoEntrada() {
		return this.arquivoEntrada;
	}
	
	int getAlgEspacoLivre() {
		return this.algEspacoLivre;
	}
	
	int getAlgPaginacao() {
		return this.algPaginacao;
	}
	
	int getIntervalo() {
		return this.intervalo;
	}
	/*
	public static void main(String []args) {
		Prompt p = new Prompt();
		p.leComandos();
		System.out.println("Deve voltar agora para o shell do terminal");
	} */


}
