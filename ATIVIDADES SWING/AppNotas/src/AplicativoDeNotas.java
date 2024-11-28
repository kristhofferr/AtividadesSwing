import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AplicativoDeNotas {

    private JFrame frame;
    private JTextField notaField;
    private JTextArea notasArea;
    private JLabel resultadoLabel;
    private ArrayList<Double> notas;

    public AplicativoDeNotas() {
        // Inicializa a lista de notas
        notas = new ArrayList<>();

        // Cria o JFrame
        frame = new JFrame("Aplicativo de Notas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // Campo de texto para inserir a nota
        notaField = new JTextField();
        notaField.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(notaField, BorderLayout.NORTH);  // Adiciona o campo de texto na parte superior

        // Área de texto para exibir as notas
        notasArea = new JTextArea();
        notasArea.setFont(new Font("Arial", Font.PLAIN, 18));
        notasArea.setEditable(false);  // Não permitir edição direta no JTextArea
        JScrollPane scrollPane = new JScrollPane(notasArea);  // Cria um JScrollPane para permitir rolagem
        frame.add(scrollPane, BorderLayout.CENTER);  // Adiciona o JScrollPane no centro

        // Painel para os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());  // Organiza os botões horizontalmente

        // Botão "Adicionar Nota"
        JButton adicionarNotaBtn = new JButton("Adicionar Nota");
        adicionarNotaBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        painelBotoes.add(adicionarNotaBtn);

        // Botão "Calcular Média"
        JButton calcularMediaBtn = new JButton("Calcular Média");
        calcularMediaBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        painelBotoes.add(calcularMediaBtn);

        frame.add(painelBotoes, BorderLayout.SOUTH);  // Adiciona o painel de botões na parte inferior

        // Label para exibir a média e status de aprovação
        resultadoLabel = new JLabel("Média: N/A - Status: N/A");
        resultadoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        frame.add(resultadoLabel, BorderLayout.PAGE_END);  // Exibe a média e o status no final

        // Ação para o botão "Adicionar Nota"
        adicionarNotaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Tenta converter a entrada para um número
                    double nota = Double.parseDouble(notaField.getText());
                    notas.add(nota);
                    notasArea.append("Nota: " + nota + "\n");  // Exibe a nota na área de texto
                    notaField.setText("");  // Limpa o campo de texto após adicionar a nota
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira um número válido para a nota.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação para o botão "Calcular Média"
        calcularMediaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (notas.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, adicione ao menos uma nota antes de calcular.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double soma = 0;
                for (Double nota : notas) {
                    soma += nota;
                }

                double media = soma / notas.size();
                String status = media >= 7.0 ? "Aprovado" : "Reprovado";
                resultadoLabel.setText("Média: " + media + " - Status: " + status);  // Exibe a média e o status
            }
        });

        // Exibe a janela
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AplicativoDeNotas();  // Cria e inicia a aplicação
    }
}
