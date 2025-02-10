package sistema.telas;

import java.awt.EventQueue;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


public class Telaprincipal extends javax.swing.JFrame {

    public Telaprincipal() {
        initComponents();
    }

    private void initComponents() {
        Desktop = new javax.swing.JDesktopPane();
        btnusuario = new java.awt.Label();
        btndata = new java.awt.Label();
        Menu = new javax.swing.JMenuBar();
        mencad = new javax.swing.JMenu();
        mencadcli = new javax.swing.JMenuItem();
        mencados = new javax.swing.JMenuItem();
        mencadusu = new javax.swing.JMenuItem();
        menrel = new javax.swing.JMenu();
        menrelser = new javax.swing.JMenuItem();
        menaju = new javax.swing.JMenu();
        menajusob = new javax.swing.JMenuItem();
        menopc = new javax.swing.JMenu();
        menopcsai = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Desktop.setLayout(new java.awt.BorderLayout());

        btnusuario.setFont(new java.awt.Font("Dialog", 1, 18));
        btnusuario.setText("Usuário");

        btndata.setFont(new java.awt.Font("Dialog", 1, 18));
        btndata.setText("Data");

        mencad.setText("Cadastro");

        mencadcli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        mencadcli.setText("Cliente");
        mencadcli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirTelaCliente();
            }
        });
        mencad.add(mencadcli);

        mencados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        mencados.setText("OS");
        mencados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirTelaos();
            }
        });
        mencad.add(mencados);

        mencadusu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        mencadusu.setText("Usuário");
        mencadusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirTelaUsuario();
            }
        });
        mencad.add(mencadusu);

        Menu.add(mencad);

        menrel.setText("Relatório");
        menrel.setEnabled(false);

        menrelser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        menrelser.setText("Serviço");
        menrel.add(menrelser);

        Menu.add(menrel);

        menaju.setText("Ajuda");

        menajusob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        menajusob.setText("Sobre");
        menajusob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menajusobActionPerformed(evt);
            }
        });
        menaju.add(menajusob);

        Menu.add(menaju);

        menopc.setText("Opção");

        menopcsai.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menopcsai.setText("Sair");
        menopcsai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menopcsaiActionPerformed(evt);
            }
        });
        menopc.add(menopcsai);

        Menu.add(menopc);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btndata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(400, Short.MAX_VALUE))
        );

        pack();
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        btndata.setText(formatador.format(data));
    }

    private void menopcsaiActionPerformed(java.awt.event.ActionEvent evt) {
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void menajusobActionPerformed(java.awt.event.ActionEvent evt) {
        Telasobre sobre = new Telasobre();
         sobre.setVisible(true);
    }

    private void abrirTelaUsuario() {
        Telausuario telaUsuario = new Telausuario();
        Desktop.add(telaUsuario);
        telaUsuario.setVisible(true);
    }

    private void abrirTelaCliente() {
        Telacliente telaCliente = new Telacliente();
        Desktop.add(telaCliente);
        telaCliente.setVisible(true);
    }
    private void abrirTelaos() {
        Telaos telaos = new Telaos();
        Desktop.add(telaos);
        telaos.setVisible(true);
        
        telaos.setLocation(
                (Desktop.getWidth() - telaos.getWidth()) / 2,
                (Desktop.getHeight() - telaos.getHeight()) / 2
            );
        
        
        
        
        
        
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Telaprincipal().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuBar Menu;
    private java.awt.Label btndata;
    public static java.awt.Label btnusuario;
    private javax.swing.JMenu menaju;
    private javax.swing.JMenuItem menajusob;
    private javax.swing.JMenu mencad;
    private javax.swing.JMenuItem mencadcli;
    private javax.swing.JMenuItem mencados;
    public static javax.swing.JMenuItem mencadusu;
    private javax.swing.JMenu menopc;
    private javax.swing.JMenuItem menopcsai;
    public static javax.swing.JMenu menrel;
    private javax.swing.JMenuItem menrelser;
}