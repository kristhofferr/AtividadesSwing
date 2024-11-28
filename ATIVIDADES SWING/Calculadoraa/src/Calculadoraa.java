import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadoraa {
    private JPanel jPanel1;
    private JButton btn7;
    private JButton btn9;
    private JButton btn8;
    private JButton btnDiv;
    private JButton btnMult;
    private JButton btn6;
    private JButton btn5;
    private JButton btn4;
    private JButton btnSub;
    private JButton btn3;
    private JButton btn2;
    private JButton btn1;
    private JButton btnAdi;
    private JButton btnIgual;
    private JButton btnC;
    private JButton btn0;
    private JTextField txtRes;

    // Variáveis para a lógica da calculadora
    private double valor1 = 0;
    private double valor2 = 0;
    private String operacao = "";

    public Calculadoraa() {
        // Adicionando um ActionListener genérico para todos os botões numéricos
        ActionListener numListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                txtRes.setText(txtRes.getText() + source.getText());
            }
        };

        // Adicionando ActionListener aos botões numéricos
        btn0.addActionListener(numListener);
        btn1.addActionListener(numListener);
        btn2.addActionListener(numListener);
        btn3.addActionListener(numListener);
        btn4.addActionListener(numListener);
        btn5.addActionListener(numListener);
        btn6.addActionListener(numListener);
        btn7.addActionListener(numListener);
        btn8.addActionListener(numListener);
        btn9.addActionListener(numListener);

        // Adicionando ActionListener aos botões de operação
        btnAdi.addActionListener(e -> {
            operacao = "+";
            valor1 = Double.parseDouble(txtRes.getText());
            txtRes.setText("");
        });

        btnSub.addActionListener(e -> {
            operacao = "-";
            valor1 = Double.parseDouble(txtRes.getText());
            txtRes.setText("");
        });

        btnMult.addActionListener(e -> {
            operacao = "*";
            valor1 = Double.parseDouble(txtRes.getText());
            txtRes.setText("");
        });

        btnDiv.addActionListener(e -> {
            operacao = "/";
            valor1 = Double.parseDouble(txtRes.getText());
            txtRes.setText("");
        });

        // Botão "=" para calcular o resultado
        btnIgual.addActionListener(e -> {
            valor2 = Double.parseDouble(txtRes.getText());
            double resultado = 0;

            switch (operacao) {
                case "+" -> resultado = valor1 + valor2;
                case "-" -> resultado = valor1 - valor2;
                case "*" -> resultado = valor1 * valor2;
                case "/" -> {
                    if (valor2 != 0) {
                        resultado = valor1 / valor2;
                    } else {
                        JOptionPane.showMessageDialog(null, "Divisão por zero não é permitida.");
                        txtRes.setText("");
                        return;
                    }
                }
            }

            txtRes.setText(String.valueOf(resultado));
            valor1 = resultado; // Permite continuar a operação com o resultado
        });

        // Botão "C" para limpar
        btnC.addActionListener(e -> txtRes.setText(""));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");
        frame.setContentPane(new Calculadoraa().jPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
