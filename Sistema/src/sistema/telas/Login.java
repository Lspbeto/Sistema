package sistema.telas;


import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import sistema.cod.ConexaoBD;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtusuario;
    private JPasswordField txtsenha;
    private JLabel btnconfirmar;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void logar() {
        String sql = "select * from tbusuario where login = ? and senha = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtusuario.getText());
            pst.setString(2, new String(txtsenha.getPassword()));
            rs = pst.executeQuery();

            if (rs.next()) {
                String perfil = rs.getString(6);
                if (perfil.equals("Admin")) {
                    Telaprincipal principal = new Telaprincipal();
                    principal.setVisible(true);
                    Telaprincipal.menrel.setEnabled(true);
                    Telaprincipal.mencadusu.setEnabled(true);
                    Telaprincipal.btnusuario.setText(rs.getString(2));
                    this.dispose();
                } else {
                    Telaprincipal principal = new Telaprincipal();
                    principal.setVisible(true);
                    Telaprincipal.btnusuario.setText(rs.getString(2));
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválida(s).");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar logar: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        conexao = ConexaoBD.getConnection();
        System.out.println(conexao);

        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Temp\\ws-eclipse\\lanc\\lanc\\src\\lanc\\icones\\fast-food_605573.png"));
        setTitle("Lanche");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel btnusuario = new JLabel("Usuário");
        btnusuario.setBounds(50, 50, 100, 20);
        contentPane.add(btnusuario);

        txtusuario = new JTextField();
        txtusuario.setBounds(150, 50, 200, 20);
        contentPane.add(txtusuario);
        txtusuario.setColumns(10);

        JLabel btnsenha = new JLabel("Senha");
        btnsenha.setBounds(50, 100, 100, 20);
        contentPane.add(btnsenha);

        txtsenha = new JPasswordField();
        txtsenha.setBounds(150, 100, 200, 20);
        contentPane.add(txtsenha);
        txtsenha.setColumns(10);

        JButton btnConectar = new JButton("Conectar");
        btnConectar.setBounds(150, 150, 100, 30);
        contentPane.add(btnConectar);

        btnconfirmar = new JLabel("");
        btnconfirmar.setBounds(50, 200, 300, 20);
        contentPane.add(btnconfirmar);

        if (conexao != null) {
            btnconfirmar.setText("Banco de dados conectado");
        } else {
            btnconfirmar.setText("Banco de dados não conectado");
        }

        btnConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtusuario.getText().isEmpty() || txtsenha.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                } else {
                    logar();
                }
            }
        });
    }
}