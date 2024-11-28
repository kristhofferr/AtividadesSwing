import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendaDiariaa extends JFrame {

    // Componentes da interface
    private JPanel Jpanel1;           // Painel principal (JPanel1)
    private JTextField txtComp;        // Campo de texto para o compromisso
    private JSpinner spnHora;          // Spinner para a data e hora
    private JButton btnAdicionar;      // Botão para adicionar compromisso
    private JButton btnRemover;        // Botão para remover compromisso
    private JTable TblCom;             // Tabela para exibir compromissos
    private DefaultTableModel model;   // Modelo da tabela

    // Construtor da janela
    public AgendaDiariaa() {
        setTitle("Agenda Diária");
        setSize(500, 400);
        setLocationRelativeTo(null);  // Centraliza a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel Jpanel1: agrupando componentes
        Jpanel1 = new JPanel();
        Jpanel1.setLayout(new FlowLayout());  // Usando FlowLayout para disposição dos componentes

        // Inicializando os componentes
        txtComp = new JTextField(20);  // Campo de texto para o compromisso
        spnHora = new JSpinner(new SpinnerDateModel());  // Spinner para data/hora
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnHora, "dd/MM/yyyy HH:mm");
        spnHora.setEditor(dateEditor);

        // Adicionando os componentes ao Jpanel1
        Jpanel1.add(new JLabel("Compromisso:"));
        Jpanel1.add(txtComp);
        Jpanel1.add(new JLabel("Data e Hora:"));
        Jpanel1.add(spnHora);

        // Botões para adicionar e remover compromissos
        btnAdicionar = new JButton("Adicionar Compromisso");
        btnRemover = new JButton("Remover Compromisso");

        // Adicionando botões ao painel
        Jpanel1.add(btnAdicionar);
        Jpanel1.add(btnRemover);

        // Modelo e JTable para exibir os compromissos
        model = new DefaultTableModel(new Object[]{"Data/Hora", "Compromisso"}, 0);
        TblCom = new JTable(model);  // Tabela para exibir os compromissos
        JScrollPane scrollPane = new JScrollPane(TblCom);

        // Adicionando o Jpanel1 e a tabela à janela principal
        add(Jpanel1, BorderLayout.NORTH);
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
        String compromisso = txtComp.getText().trim();  // Usa txtComp para o compromisso
        Date dataHora = (Date) spnHora.getValue();  // Usa spnHora para a data e hora

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
        txtComp.setText("");
    }

    // Método para remover um compromisso
    private void removerCompromisso() {
        int selectedRow = TblCom.getSelectedRow();  // Usa TblCom para obter a linha selecionada

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
                new AgendaDiariaa().setVisible(true);  // Exibe a janela
            }
        });
    }
}
