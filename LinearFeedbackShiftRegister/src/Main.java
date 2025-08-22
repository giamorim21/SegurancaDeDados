import java.io.BufferedReader;
import java.io.InputStreamReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static void inicializar(int[] registrador) throws Exception {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
        String chave = "";

        System.out.print("Digite uma chave de 4 letras: ");
        chave = leitor.readLine();

        for (int i = 0; i < 4; i++) {
            //pega cada uma das letras
            int letra = chave.charAt(i);
            //tranforma as letras em binário
            String binario = Integer.toBinaryString(letra);
            int tamanho = binario.length();
            // fazer o alinhamento do binário para sempre ter 8 bits de tamanho
            for (int j = 0; j < (8 - tamanho); j++) {
                binario = "0" + binario;
            }
            for (int j = 0; j < 8; j++) {
                registrador[(i * 8) + j] = Integer.parseInt(binario.substring(j, j + 1));
            }
        }
    }

    private static int girar(int[] registrador, int tipo) {
        int retorno = registrador[0];
        int xor = 0;

        if (tipo == 0) {
            xor = (registrador[31] ^ registrador[6] ^ registrador[4] ^ registrador[2] ^ registrador[1] ^ registrador[0]);
        } else {
            xor = (registrador[31] ^ registrador[6] ^ registrador[5] ^ registrador[1]);
        }

        for (int i = 0; i < 31; i++) {
            registrador[i] = (registrador[i + 1]);
        }
        registrador[31] = xor;

        return retorno;
    }

    public static void main(String[] args) {
        try{
            int[] cabeca = new int[32];
            int[] gerador0 = new int[32];
            int[] gerador1 = new int[32];

            inicializar(cabeca);
            inicializar(gerador0);
            inicializar(gerador1);

            String acumulador = "";
            // 100 bytes = 100 letras = 800 bits
            for (int i = 0; i < 800000; i++) {
                int bit0 = 0;
                int bit1 = 0;

                if (girar(cabeca, 0) == 0) {
                    bit0 = girar(gerador0, 0);
                    bit1 = gerador1[0];
                } else {
                    bit0 = gerador0[0];
                    bit1 = girar(gerador1, 1);
                }

                acumulador += (bit0 ^ bit1);
                if (acumulador.length() == 8) {
                    System.out.print((char) Integer.parseInt(acumulador, 2));
                    acumulador = "";
                }
            }

        } catch (Exception erro) {
            System.out.print(erro);
        }
    }
}