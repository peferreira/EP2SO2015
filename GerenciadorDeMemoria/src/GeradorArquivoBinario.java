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
	
	void escreveProcessoMemoriaVirtual(byte[] bytesVirtual, Processo p) {
		
		for (int i = p.getPosInicialMemoriaVirtual(); i < p.getPosInicialMemoriaVirtual() + p.getB(); i++) {
			
			bytesVirtual[i] = converteIntParaByte(p.getPid());
		}
	}
	
	void escreveArquivosBinarios(Memoria mem, List<Processo> processos) {
		
		String filePath = "src/binario";
		File file = new File(filePath);
		FileOutputStream fos = null;
		int tamanhoMemVirtual = mem.getMv().getTamanho();
		byte[] bytesVirtual = new byte[tamanhoMemVirtual];
		
		/* inicializa memoria virtual com "-1" */
		for (int i = 0; i < tamanhoMemVirtual; i++) {
			bytesVirtual[i] = 127;
		}
		
		for (Processo p: processos) {
			
			if (p.getPosInicialMemoriaVirtual() >= 0) {
				System.out.println("proc " + p.getPid() + " inicio " + p.getPosInicialMemoriaVirtual() + " tamanho " + p.getB());
				escreveProcessoMemoriaVirtual(bytesVirtual,  p);
			}
		}
		
		try {
			fos = new FileOutputStream(file);
			fos.write(bytesVirtual);
			fos.close();

		} catch (Exception e1) {
			e1.printStackTrace();
			}
		
		leArquivoBinario(bytesVirtual);
		
	}
	
	void leArquivoBinario(byte[] bytes) {
		
		String filePath = "src/binario";
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
