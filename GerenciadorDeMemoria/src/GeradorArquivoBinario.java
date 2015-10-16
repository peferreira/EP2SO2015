import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;


public class GeradorArquivoBinario {

	
	
	GeradorArquivoBinario() {
		
	}

	byte converteIntParaByte(int n) {
		return (byte) (n - 128);
	}
	
	void inicializaMemorias(Memoria mem, byte[] bytesVirtual, byte[] bytesFisica) {
		
		int tamanhoMemVirtual, tamanhoMemFisica;
		
		tamanhoMemVirtual = mem.getMv().getTamanho();
		tamanhoMemFisica = mem.getMf().getTamanho();
		
		/* inicializa memoria virtual com "-1" */
		for (int i = 0; i < tamanhoMemVirtual; i++) {
			bytesVirtual[i] = 127;
		}
		
		/* inicializa memoria fisica com "-1" */
		for (int i = 0; i < tamanhoMemFisica; i++) {
			bytesFisica[i] = 127;
		}
	}
	
	void escreveProcessoMemoriaVirtual(byte[] bytesVirtual, Processo p) {
		
		for (int i = p.getPosInicialMemoriaVirtual(); i < p.getPosInicialMemoriaVirtual() + p.getB(); i++) {
			bytesVirtual[i] = converteIntParaByte(p.getPid());
		}
	}
	
	void escreveProcessoMemoriaFisicaANTIGO(Memoria mem, byte[] bytesFisica, Processo p) {

		int quadroMemFisica;
		
		for (int i = p.getPosInicialMemoriaVirtual(); i < p.getPosInicialMemoriaVirtual() + p.getB(); i++) {
			if (i%16 == 0) {

				quadroMemFisica = mem.getMv().getQuadroDaMemoriaFisica(i);
				if (quadroMemFisica != -1) { /* se quadro = -1 a pagina procurada nao esta na memoria fisica*/
					System.out.println("QUADRO NA MEMORIA FISICA");
					for (int j = 0; j < 16; j++)
						bytesFisica[quadroMemFisica*16 + j] = converteIntParaByte(p.getPid());
				}
			}
		}
	}
	
	void escreveProcessoMemoriaFisica(Memoria mem, byte[] bytesFisica, Processo p) {

		int quadroMemFisica;
		int numPaginasDoProcesso = p.getNumPaginas();
		int pagina = 0;
		int posicoesUltimaPagina = p.getB()%16;
		
		for (int i = p.getPosInicialMemoriaVirtual(); i < p.getPosInicialMemoriaVirtual() + p.getB(); i++) {
			if (i%16 == 0) {
				
				pagina++;
				quadroMemFisica = mem.getMv().getQuadroDaMemoriaFisica(i);
				if (quadroMemFisica != -1) { /* se quadro = -1 a pagina procurada nao esta na memoria fisica*/
					if (pagina == numPaginasDoProcesso && posicoesUltimaPagina != 0) { /* ultima pagina do processo */
						for (int j = 0; j < posicoesUltimaPagina; j++)
							bytesFisica[quadroMemFisica*16 + j] = converteIntParaByte(p.getPid());
					}
					else {
						for (int j = 0; j < 16; j++) {
							bytesFisica[quadroMemFisica*16 + j] = converteIntParaByte(p.getPid());
						}
					}
				}
			}
		}
	}

	
	void escreveArquivosBinarios(Memoria mem, List<Processo> processos) {
		
		String filePathVir = "src/ep2.vir";
		String filePathMem = "src/ep2.mem";

		File ep2vir = new File(filePathVir);
		File ep2mem = new File(filePathMem);

		FileOutputStream fos = null;
		int tamanhoMemVirtual = mem.getMv().getTamanho();
		int tamanhoMemFisica = mem.getMf().getTamanho();
		byte[] bytesVirtual = new byte[tamanhoMemVirtual];
		byte[] bytesFisica = new byte[tamanhoMemFisica];
		
		
		inicializaMemorias(mem, bytesVirtual, bytesFisica);

		
		for (Processo p: processos) {
			
			if (p.getPosInicialMemoriaVirtual() >= 0) {
				/*System.out.println("proc " + p.getPid() + " inicio " + p.getPosInicialMemoriaVirtual() + " tamanho " + p.getB());*/
				escreveProcessoMemoriaVirtual(bytesVirtual,  p);
				escreveProcessoMemoriaFisica(mem, bytesFisica, p);
			}
		}
		
		try {
			fos = new FileOutputStream(ep2vir);
			fos.write(bytesVirtual);
			fos.close();

		} catch (Exception e1) {
			e1.printStackTrace();
			}
		
		try {
			fos = new FileOutputStream(ep2mem);
			fos.write(bytesFisica);
			fos.close();

		} catch (Exception e1) {
			e1.printStackTrace();
			}
		
		System.out.println("Memoria virtual:");
		leArquivoBinario(bytesVirtual,filePathVir);
		System.out.println("Memoria fisica:");
		leArquivoBinario(bytesFisica,filePathMem);
	}
	
	void leArquivoBinario(byte[] bytes, String filePath) {
		
		File file = new File(filePath);
		FileOutputStream fos = null;
		FileInputStream fis = null;
		
		try{
			byte[] fileContent = new byte[(int)file.length()];
			fis = new FileInputStream(file);
			fis.read(fileContent);
			fis.close();
			
			for(int i = 0; i < bytes.length; i++) {
				
				if (i%16 == 0) {
					System.out.println();
				}
				System.out.print(bytes[i]+" ");

			}
			System.out.println();
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/*public static void main (String []args) {
		String filePath = "src/binario";
		File file = new File(filePath);
		byte[] data = new byte[100];
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bs = null;
		data[0] = -1;
		data[1] = 10;
		data[5] = -7;

		try {
			fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try{
			byte[] fileContent = new byte[(int)file.length()];
			fis = new FileInputStream(file);
			fis.read(fileContent);
			fis.close();
			//System.out.println(fileContent);
			System.out.println(fileContent[5]);

			
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}

	}*/
}
