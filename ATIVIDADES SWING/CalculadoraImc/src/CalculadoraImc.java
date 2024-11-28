import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraImc extends JFrame {

    // Componentes da interface
    private JPanel jPanel;
    private JTextField txtPeso;
    private JTextField txtAltura;
    private JButton btnCal;
    private JLabel lblRes;

    // Construtor da janela
    public CalculadoraImc() {
        setTitle("Calculadora de IMC");
        setSize(300, 200);
        setLocationRelativeTo(null); // Centralizar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10)); // Layout para organizar os componentes

        // Inicializando os componentes
        txtPeso = new JTextField(10);
        txtAltura = new JTextField(10);
        btnCal = new JButton("Calcular IMC");
        lblRes= new JLabel("Informe o peso e altura.");

        // Adicionando os componentes na janela
        add(new JLabel("Peso (kg):"));
        add(txtPeso);
        add(new JLabel("Altura (m):"));
        add(txtAltura);
        add(new JLabel()); // Espaço vazio para alinhamento
        add(btnCal);
        add(new JLabel("Resultado:"));
        add(lblRes);

        // Ação do botão "Calcular IMC"
        btnCal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });
    }

    // Método para calcular o IMC
    private void calcularIMC() {
        try {
            // Captura o peso e altura inseridos pelo usuário
            String pesoStr = txtPeso.getText().trim();
            String alturaStr = txtAltura.getText().trim();

            // Verifica se os campos não estão vazios
            if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha ambos os campos.",
                        "Entrada inválida", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Converte as entradas para números
            double peso = Double.parseDouble(pesoStr);
            double altura = Double.parseDouble(alturaStr);

            // Validação de entrada
            if (peso <= 0 || altura <= 0) {
                JOptionPane.showMessageDialog(this, "Peso e altura devem ser valores positivos.",
                        "Entrada inválida", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calculando o IMC
            double imc = peso / (altura * altura);

            // Determinando a categoria
            String categoria;
            if (imc < 18.5) {
                categoria = "Baixo peso";
            } else if (imc < 24.9) {
                categoria = "Peso normal";
            } else if (imc < 29.9) {
                categoria = "Sobrepeso";
            } else {
                categoria = "Obesidade";
            }

            // Exibindo o resultado
            lblRes.setText(String.format("IMC: %.2f - %s", imc, categoria));

        } catch (NumberFormatException e) {
            // Caso a entrada não seja válida
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para peso e altura.",
                    "Entrada inválida", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método principal para executar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraImc().setVisible(true));
    }
}
