import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    private static final BigInteger p = new BigInteger("102031405123416071809152453627382938465749676859789");
    private static final BigInteger g = new BigInteger("1234567890123456789012345");

    public static void main(String[] args) {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
        BigInteger minhaChaveSecreta = null;
        BigInteger minhaChavePublica = null;
        BigInteger chaveSecretaDoComunicante = null;
        BigInteger nossaChaveCompartilhada= null;

        try {
            System.out.print("Escolha a sua chave secreta: ");
            minhaChaveSecreta = new BigInteger(leitor.readLine());

            minhaChavePublica = g.modPow(minhaChaveSecreta, p);
            System.out.println("Minha chave publica: " + minhaChavePublica);

            System.out.print("Digite a chave publica do comunicante:");
            chaveSecretaDoComunicante = new BigInteger(leitor.readLine());

            nossaChaveCompartilhada = chaveSecretaDoComunicante.modPow(minhaChaveSecreta, p);
            System.out.println("Nossa chave compartilhada: " + nossaChaveCompartilhada);
        } catch (Exception erro) {
            System.out.println(erro);
        }
    }
}