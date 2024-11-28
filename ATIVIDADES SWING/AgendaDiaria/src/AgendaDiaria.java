import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendaDiaria extends JFrame {

    // Componentes da interface
    private JTextField tfCompromisso;
    private JSpinner spDataHora;
    private JButton btnAdicionar, btnRemover;
    private JTable tabela;
    private DefaultTableModel model;

    // Construtor da janela
    public AgendaDiaria() {
        setTitle("Agenda Diária");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel superior com o campo de texto para compromisso e o Spinner para data/hora
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout());

        tfCompromisso = new JTextField(20);
        spDataHora = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spDataHora, "dd/MM/yyyy HH:mm");
        spDataHora.setEditor(dateEditor);

        panelTop.add(new JLabel("Compromisso:"));
        panelTop.add(tfCompromisso);
        panelTop.add(new JLabel("Data e Hora:"));
        panelTop.add(spDataHora);

        // Botões para adicionar e remover compromissos
        btnAdicionar = new JButton("Adicionar Compromisso");
        btnRemover = new JButton("Remover Compromisso");

        panelTop.add(btnAdicionar);
        panelTop.add(btnRemover);

        // Modelo e JTable para exibir os compromissos
        model = new DefaultTableModel(new Object[]{"Data/Hora", "Compromisso"}, 0);
        tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adicionando o painel superior e a tabela à janela
        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Ação do botão "Adicionar Compromisso"
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCompromisso();
            }
        });

        // Ação do botão "Remover Compromisso"
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerCompromisso();
            }
        });
    }

    // Método para adicionar um compromisso
    private void adicionarCompromisso() {
        String compromisso = tfCompromisso.getText().trim();
        Date dataHora = (Date) spDataHora.getValue();

        // Verificar se o compromisso foi inserido
        if (compromisso.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira uma descrição para o compromisso.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Formatar a data/hora para exibição
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataHoraStr = sdf.format(dataHora);

        // Adiciona o compromisso na tabela
        model.addRow(new Object[]{dataHoraStr, compromisso});

        // Limpar o campo de texto
        tfCompromisso.setText("");
    }

    // Método para remover um compromisso
    private void removerCompromisso() {
        int selectedRow = tabela.getSelectedRow();

        // Verifica se uma linha foi selecionada
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um compromisso para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método principal para executar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AgendaDiaria().setVisible(true);
            }
        });
    }
}

