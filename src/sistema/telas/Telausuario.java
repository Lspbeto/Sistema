package sistema.telas;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import sistema.cod.ConexaoBD;

public class Telausuario extends javax.swing.JInternalFrame {

    // Variáveis para conexão com o banco de dados
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public Telausuario() {
        initComponents();
        conexao = ConexaoBD.getConnection(); // Conecta ao banco de dados
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel("ID:");
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel("Nome:");
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel("Login:");
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel("Senha:");
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel("Perfil:");
        jComboBox1 = new javax.swing.JComboBox<>(new String[] { "Admin", "Operador" });
        jLabel6 = new javax.swing.JLabel("Telefone:");
        jTextField4 = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton("Salvar");
        btnAlterar = new javax.swing.JButton("Alterar");
        btnExcluir = new javax.swing.JButton("Excluir");
        btnLimpar = new javax.swing.JButton("Limpar");
        btnPesquisar = new javax.swing.JButton("Pesquisar");

        setClosable(true);
        setTitle("Cadastro de Usuário");

        // Ação do botão Salvar
        btnSalvar.addActionListener(e -> adicionarUsuario());

        // Ação do botão Alterar
        btnAlterar.addActionListener(e -> alterarUsuario());

        // Ação do botão Excluir
        btnExcluir.addActionListener(e -> excluirUsuario());

        // Ação do botão Limpar
        btnLimpar.addActionListener(e -> limparCampos());

        // Ação do botão Pesquisar
        btnPesquisar.addActionListener(e -> pesquisarUsuario());

        // Layout da interface
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpar)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisar)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnAlterar)
                    .addComponent(btnExcluir)
                    .addComponent(btnLimpar)
                    .addComponent(btnPesquisar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    // Método para adicionar um usuário
    private void adicionarUsuario() {
        String nome = jTextField2.getText();
        String login = jTextField3.getText();
        String senha = new String(jPasswordField1.getPassword());
        String perfil = (String) jComboBox1.getSelectedItem();
        String telefone = jTextField4.getText();

        if (nome.isEmpty() || login.isEmpty() || senha.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO tbusuario (nome, login, senha, perfil, telefone) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, login);
            pst.setString(3, senha);
            pst.setString(4, perfil);
            pst.setString(5, telefone);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    // Método para alterar um usuário
    private void alterarUsuario() {
        String id = jTextField1.getText();
        String nome = jTextField2.getText();
        String login = jTextField3.getText();
        String senha = new String(jPasswordField1.getPassword());
        String perfil = (String) jComboBox1.getSelectedItem();
        String telefone = jTextField4.getText();

        if (id.isEmpty() || nome.isEmpty() || login.isEmpty() || senha.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "UPDATE tbusuario SET nome=?, login=?, senha=?, perfil=?, telefone=? WHERE id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, login);
            pst.setString(3, senha);
            pst.setString(4, perfil);
            pst.setString(5, telefone);
            pst.setString(6, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar usuário: " + e.getMessage());
        }
    }

    // Método para excluir um usuário
    private void excluirUsuario() {
        String id = jTextField1.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o ID do usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "DELETE FROM tbusuario WHERE id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir usuário: " + e.getMessage());
        }
    }

    // Método para pesquisar um usuário pelo ID
    private void pesquisarUsuario() {
        String id = jTextField1.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o ID do usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM tbusuario WHERE id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                jTextField2.setText(rs.getString("nome"));
                jTextField3.setText(rs.getString("login"));
                jPasswordField1.setText(rs.getString("senha"));
                jComboBox1.setSelectedItem(rs.getString("perfil"));
                jTextField4.setText(rs.getString("telefone")); // Corrigido para "telefone"
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar usuário: " + e.getMessage());
        }
    }

    // Método para limpar os campos
    private void limparCampos() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jPasswordField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jTextField4.setText("");
    }

    // Variáveis de componentes
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
}