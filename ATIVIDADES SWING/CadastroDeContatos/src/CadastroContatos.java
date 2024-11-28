import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroContatos extends JFrame {

    // Componentes da interface
    private JPanel txtPaine;
    private JTextField txtNome, txtTel, txtEmail;
    private JButton btnAdd, btnRemov;
    private JList<String> listCont;
    private DefaultListModel<String> listaModel;
    private List<Contato> contatos;

    // Construtor da janela
    public CadastroContatos() {
        setTitle("Cadastro de Contatos");
        setSize(400, 300);
        setLocationRelativeTo(null);  // Para centralizar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Inicializando a lista de contatos
        contatos = new ArrayList<>();
        listaModel = new DefaultListModel<>();

        // Inicializando os componentes
        txtNome = new JTextField(15);
        txtTel = new JTextField(15);
        txtEmail = new JTextField(15);

        btnAdd = new JButton("Adicionar Contato");
        btnRemov = new JButton("Remover Contato");

        // Lista para exibir os contatos
        listCont = new JList<>(listaModel);
        listCont.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Adicionando os componentes na janela
        add(new JLabel("Nome:"));
        add(txtNome);
        add(new JLabel("Telefone:"));
        add(txtTel);
        add(new JLabel("E-mail:"));
        add(txtEmail);
        add(btnAdd);
        add(btnRemov);
        add(new JScrollPane(listCont));  // Para permitir rolagem na lista

        // Ações dos botões
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarContato();
            }
        });

        btnRemov.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerContato();
            }
        });
    }

    // Método para adicionar um contato
    private void adicionarContato() {
        String nome = txtNome.getText().trim();
        String telefone = txtTel.getText().trim();
        String email = txtEmail.getText().trim();

        // Validando se os campos estão preenchidos
        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criando o novo contato e adicionando à lista
        Contato contato = new Contato(nome, telefone, email);
        contatos.add(contato);
        listaModel.addElement(contato.toString());  // Atualizando a lista no JList

        // Limpar campos
        txtNome.setText("");
        txtTel.setText("");
        txtEmail.setText("");
    }

    // Método para remover o contato selecionado
    private void removerContato() {
        int selectedIndex = listCont.getSelectedIndex();

        if (selectedIndex != -1) {
            // Removendo o contato da lista
            contatos.remove(selectedIndex);
            listaModel.remove(selectedIndex);  // Atualizando a lista no JList
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
                new CadastroContatos().setVisible(true);  // Corrigido o nome da classe
            }
        });
    }
}
