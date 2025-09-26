import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.util.Base64;

public class Main {
   private static KeyPair gerarChavesAssimetricas() throws Exception {
       KeyPairGenerator objGerador = KeyPairGenerator.getInstance("RSA");
       objGerador.initialize(1024);
       return objGerador.generateKeyPair();
   }

   private static String encriptarRSA(KeyPair parDeChaves, byte[] chaveSimetrica) throws Exception {
       Cipher objCifra = Cipher.getInstance("RSA");
       objCifra.init(Cipher.ENCRYPT_MODE, parDeChaves.getPublic());
       byte[] temp = objCifra.doFinal(chaveSimetrica);
       return Base64.getEncoder().encodeToString(temp);
   }

   private static byte[] decriptarRSA(KeyPair parDeChaves, String criptograma) throws Exception {
       Cipher objCifra = Cipher.getInstance("RSA");
       objCifra.init(Cipher.DECRYPT_MODE, parDeChaves.getPrivate());
       return objCifra.doFinal(Base64.getDecoder().decode(criptograma));
   }

   private static String encriptarAES(byte[] chaveSimetrica, String texto) throws Exception {
       Cipher objCifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
       Key objChave = new SecretKeySpec(chaveSimetrica, "AES");
       IvParameterSpec objIv = new IvParameterSpec("0123456789101112".getBytes());
       objCifra.init(Cipher.ENCRYPT_MODE, objChave, objIv);
       byte[] temp = objCifra.doFinal(texto.getBytes("UTF-8"));
       return Base64.getEncoder().encodeToString(temp);
   }

   private static String decriptarAES(byte[] chaveSimetrica, String criptograma) throws Exception {
       Cipher objCifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
       Key objChave = new SecretKeySpec(chaveSimetrica, "AES");
       IvParameterSpec objIv = new IvParameterSpec("0123456789101112".getBytes());
       objCifra.init(Cipher.DECRYPT_MODE, objChave, objIv);
       byte[] temp = objCifra.doFinal(Base64.getDecoder().decode(criptograma));
       return new String(temp, "UTF-8");
   }

   private static byte[] calcularHash(byte[] chaveSimetrica) throws Exception {
       MessageDigest objHash = MessageDigest.getInstance("SHA-256");
       return objHash.digest(chaveSimetrica);
   }

   public static void main(String[] args) {
       try {
           KeyPair parDeChaves = gerarChavesAssimetricas();

           BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

           System.out.print("Digite o texto: ");
           String texto = leitor.readLine();

           byte[] chaveSimetrica = new byte[100];
           for (int i = 0; i < chaveSimetrica.length; i++) {
               chaveSimetrica[i] = ((byte) (256 * Math.random()));
           }

           System.out.println(encriptarRSA(parDeChaves, chaveSimetrica));
           System.out.println(encriptarAES(calcularHash(chaveSimetrica), texto));

           System.out.print("Digite o criptografia da chave: ");
           chaveSimetrica = decriptarRSA(parDeChaves, leitor.readLine());


           System.out.print("Digite o criptografia do texto: ");
           System.out.println(decriptarAES(calcularHash(chaveSimetrica), leitor.readLine()));
       } catch (Exception erro) {
           System.out.println(erro);
       }
   }
}
