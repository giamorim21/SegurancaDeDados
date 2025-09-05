import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.security.Key;
import java.util.Base64;

public class Main {
    private static String encriptar(String texto, String chave) throws Exception {
        String criptograma = "";

        // declara o tipo de cifra
        Cipher objCifra = Cipher.getInstance("DES");
        // expande a chave
        Key objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "DES");
        // inicializa a cifra dizendo oque queremos fazer
        objCifra.init(Cipher.ENCRYPT_MODE, objChave);
        // criptografia propriamente dita
        byte[] temp = objCifra.doFinal(texto.getBytes("UTF-8"));
        // codificação em base64
        return Base64.getEncoder().encodeToString(temp);
    }

    private static String decriptar(String criptograma, String chave) throws Exception {

        // declara o tipo de cifra
        Cipher objCifra = Cipher.getInstance("DES");
        // expande a chave
        Key objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "DES");
        // inicializa a cifra dizendo oque queremos fazer
        objCifra.init(Cipher.DECRYPT_MODE, objChave);
        // decriptografia propriamente dita
        byte[] temp = objCifra.doFinal(Base64.getDecoder().decode(criptograma));
        // codificação em base64
        return new String(temp, "UTF-8");
    }

    public static void main(String[] args) {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
        String texto = "";
        String chave = "";
        String criptograma = "";

        try {
            System.out.print("Digite um texto: ");
            texto = leitor.readLine();
            System.out.print("Digite um chave: ");
            chave = leitor.readLine();

            criptograma = encriptar(texto, chave);
            System.out.println("Texto encriptado: " + criptograma);
            System.out.print(decriptar(criptograma, chave));

        } catch (Exception erro) {
            System.out.println(erro);
        }
    }
}