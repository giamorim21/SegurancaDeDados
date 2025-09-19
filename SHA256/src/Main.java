import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame{

    //propriedades da classe
    private static final long serialVersionUID = 1L;

    private JTextArea txtTexto = new JTextArea();
    private JScrollPane jspTexto = new JScrollPane(txtTexto);
    private JTextField txtResumo = new JTextField();
    private JButton btnCalcular = new JButton("Calcular");

    //metodo principal de execução da janela
    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    //metodo construtor da classe
    public Main() {
        //configurando a janela
        setTitle("Calculo do resumo unidirecianl SHA-256");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        //configurando a area do texto
        jspTexto.setBounds(10, 10, 465, 280);
        add(jspTexto);
        txtTexto.setLineWrap(true);

        //configurando a caixa de resumo
        txtResumo.setBounds(10, 300, 465, 20);
        add(txtResumo);

        //configurando o botao e calcular
        btnCalcular.setBounds(200, 330, 100, 20);
        add(btnCalcular);
        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    txtResumo.setText(SHA256.calcular(txtTexto.getText()));
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro);
                }
            }
        });
    }
}

