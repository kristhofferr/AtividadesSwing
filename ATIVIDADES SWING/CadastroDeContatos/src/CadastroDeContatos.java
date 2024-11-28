import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroDeContatos extends JFrame {

    // Componentes da interface
    private JTextField tfNome, tfTelefone, tfEmail;
    private JButton btnAdicionar, btnRemover;
    private JList<String> listaContatos;
    private DefaultListModel<String> listaModel;
    private List<Contato> contatos;

    // Construtor da janela
    public CadastroDeContatos() {
        setTitle("Cadastro de Contatos");
        setSize(400, 300);
        setLocationRelativeTo(null);  // Para centralizar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Inicializando a lista de contatos
        contatos = new ArrayList<>();
        listaModel = new DefaultListModel<>();

        // Campos de entrada
        tfNome = new JTextField(15);
        tfTelefone = new JTextField(15);
        tfEmail = new JTextField(15);

        // Botões
        btnAdicionar = new JButton("Adicionar Contato");
        btnRemover = new JButton("Remover Contato");

        // Lista para exibir os contatos
        listaContatos = new JList<>(listaModel);
        listaContatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Adicionando os componentes na janela
        add(new JLabel("Nome:"));
        add(tfNome);
        add(new JLabel("Telefone:"));
        add(tfTelefone);
        add(new JLabel("E-mail:"));
        add(tfEmail);
        add(btnAdicionar);
        add(btnRemover);
        add(new JScrollPane(listaContatos));  // Para permitir rolagem na lista

        // Ações dos botões
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarContato();
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerContato();
            }
        });
    }

    // Método para adicionar um contato
    private void adicionarContato() {
        String nome = tfNome.getText().trim();
        String telefone = tfTelefone.getText().trim();
        String email = tfEmail.getText().trim();

        // Validando se os campos estão preenchidos
        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criando o novo contato e adicionando à lista
        Contato contato = new Contato(nome, telefone, email);
        contatos.add(contato);
        listaModel.addElement(contato.toString());

        // Limpar campos
        tfNome.setText("");
        tfTelefone.setText("");
        tfEmail.setText("");
    }

    // Método para remover o contato selecionado
    private void removerContato() {
        int selectedIndex = listaContatos.getSelectedIndex();

        if (selectedIndex != -1) {
            // Removendo o contato da lista
            contatos.remove(selectedIndex);
            listaModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um contato para remover!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Classe Contato
    private class Contato {
        private String nome;
        private String telefone;
        private String email;

        public Contato(String nome, String telefone, String email) {
            this.nome = nome;
            this.telefone = telefone;
            this.email = email;
        }

        @Override
        public String toString() {
            return nome + " - " + telefone + " - " + email;
        }
    }

    // Método principal para executar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroDeContatos().setVisible(true);
            }
        });
    }
}

