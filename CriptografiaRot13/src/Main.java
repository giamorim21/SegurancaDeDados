import java.util.Scanner;

public class Main {
    public static String encriptar(String texto) {
        String cifra = "";

        for (int i = 0; i < texto.length(); i++) {
            int letraOriginal = texto.charAt(i);
            int letraCifrada = (letraOriginal + 13);
            if (letraCifrada > 122) {
                letraCifrada = (letraCifrada - 26);
            }
            cifra += ((char) letraCifrada);
        }
        return cifra;
    }

    public static void main(String[] args) {
        //Declaração de Variáveis
        Scanner leitor = new Scanner(System.in);
        String texto = "";
        String cifra = "";

        //Entrada de dados
        System.out.println("Digite um texto: ");
        texto = leitor.nextLine();

        leitor.close();

        //Processamento
        cifra = encriptar(texto);
        texto = encriptar(cifra);

        //saída de dados
        System.out.println(cifra);
        System.out.println(texto);

    }
}