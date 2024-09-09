import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CriptografiaDES {
	public static void main (String[] args) {
		//declaração variaveis
		BufferedReader leitor = new BufferedReader(
								new InputStreamReader(
								System.in));
		String texto = "";
		String chave = "";
		String criptograma = "";
		
		try {
			//Processo de encriptacao
			System.out.print("Digite o Texto: ");
			texto = leitor.readLine();
			
			System.out.print("Digite a Chave: ");
			chave = leitor.readLine();
			
			criptograma = encriptar(texto, chave);
			
			System.out.println(criptograma);
			
			//processo de decriptacao
			
			System.out.print("Digite o Criptograma: ");
			criptograma = leitor.readLine();
			
			System.out.print("Digite a Chave: ");
			chave = leitor.readLine();
			
			texto = decriptar(criptograma, chave);
			
			System.out.println(texto);
			
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}

	private static String encriptar(String texto, String chave) throws Exception {
		String criptograma = "";
		
		SecretKeySpec objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "DES");
		Cipher objCifra = Cipher.getInstance("DES/ECB/PKCS5Padding");
		objCifra.init(Cipher.ENCRYPT_MODE, objChave);
		
		criptograma = java.util.Base64.getMimeEncoder().encodeToString(
				objCifra.doFinal(texto.getBytes("UTF-8")));
				
		return criptograma;
	}
	private static String decriptar(String criptograma, String chave) throws Exception {
		String texto = "";
		
		SecretKeySpec objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "DES");
		Cipher objCifra = Cipher.getInstance("DES/ECB/PKCS5Padding");
		objCifra.init(Cipher.DECRYPT_MODE, objChave);
		
		texto = new String(objCifra.doFinal(Base64.getDecoder().decode(criptograma)));
				
		return texto;
	}
}