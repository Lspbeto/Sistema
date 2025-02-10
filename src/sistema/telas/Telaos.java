package sistema.telas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import sistema.cod.ConexaoBD;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Telaos extends JInternalFrame {
    // Componentes do formulário
    private JComboBox<String> comboItens;
    private JTextField txtQuantidade, txtIdCliente, txtNomeCliente;
    private JTextArea txtAreaPedido;
    private JButton btnAdicionar, btnCalcularTotal, btnLimpar, btnBuscarCliente;
    private double totalPedido = 0.0;

    // Itens do menu
    private String[] itensMenu = {"Hambúrguer", "Pizza", "Batata Frita", "Refrigerante", "Suco"};
    private double[] precos = {10.0, 25.0, 8.0, 5.0, 7.0};

    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;

    public Telaos() {
        // Configurações do JInternalFrame
        setTitle("Pedido Lanchonete");
        setSize(600, 400);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        // Conectar ao banco de dados
        conexao = ConexaoBD.getConnection();

        getContentPane().setLayout(new BorderLayout());

        // Painel para entrada de dados
        JPanel painelEntrada = new JPanel(new GridLayout(5, 2));  // Alterado para 5 linhas no GridLayout
        painelEntrada.add(new JLabel("ID Cliente:"));
        txtIdCliente = new JTextField();
        painelEntrada.add(txtIdCliente);

        painelEntrada.add(new JLabel("Nome Cliente:"));
        txtNomeCliente = new JTextField();
        txtNomeCliente.setEditable(false); // Nome será preenchido automaticamente após consulta
        painelEntrada.add(txtNomeCliente);

        painelEntrada.add(new JLabel("Item:"));
        comboItens = new JComboBox<>(itensMenu);
        painelEntrada.add(comboItens);

        painelEntrada.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        painelEntrada.add(txtQuantidade);

        btnAdicionar = new JButton("Adicionar ao Pedido");
        painelEntrada.add(btnAdicionar);

        btnCalcularTotal = new JButton("Calcular Total");
        painelEntrada.add(btnCalcularTotal);

        btnBuscarCliente = new JButton("Buscar Cliente");
        painelEntrada.add(btnBuscarCliente);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);

        // Área de texto para exibir o pedido
        txtAreaPedido = new JTextArea();
        txtAreaPedido.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaPedido);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Botão para limpar o pedido
        btnLimpar = new JButton("Limpar Pedido");
        getContentPane().add(btnLimpar, BorderLayout.SOUTH);

        // Adicionar listeners aos botões
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItem();
            }
        });

        btnCalcularTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotal();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparPedido();
            }
        });

        btnBuscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });
    }

    // Método para adicionar um item ao pedido
    private void adicionarItem() {
        try {
            int indiceItem = comboItens.getSelectedIndex();
            int quantidade = 0;

            // Verificar se a quantidade é válida
            if (txtQuantidade.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a quantidade!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            quantidade = Integer.parseInt(txtQuantidade.getText());

            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double precoItem = precos[indiceItem];
            double subtotal = precoItem * quantidade;

            txtAreaPedido.append(itensMenu[indiceItem] + " x " + quantidade + " = R$ " + subtotal + "\n");
            totalPedido += subtotal;

            txtQuantidade.setText(""); // Limpa o campo de quantidade
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

 // Método para calcular o total do pedido e salvar em arquivo .txt
    private void calcularTotal() {
        DecimalFormat df = new DecimalFormat("#.00");

        // Definir a data e hora atual
        String dataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

        // Nome do arquivo (incluir ID do cliente ou outro identificador)
        String nomeArquivo = "pedido_" + txtIdCliente.getText() + ".txt";

        // Definir o caminho completo para salvar o arquivo (diretório C:\Temp\ws-eclipse\Sis\Sistema\src)
        String caminhoArquivo = "C:\\Temp\\ws-eclipse\\Sis\\Sistema\\src\\" + nomeArquivo;

        // Verificar se o diretório existe, caso contrário, criar
        File diretorio = new File("C:\\Temp\\ws-eclipse\\Sis\\Sistema\\src");
        if (!diretorio.exists()) {
            diretorio.mkdirs();  // Cria os diretórios, se não existirem
        }

        // Verificar se o arquivo já existe
        File arquivo = new File(caminhoArquivo);
        if (arquivo.exists()) {
            int resposta = JOptionPane.showConfirmDialog(this, "Arquivo já existe. Deseja sobrescrevê-lo?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.NO_OPTION) {
                return; // Não faz nada se o usuário não quiser sobrescrever
            }
        }

        // Criar o conteúdo do pedido
        StringBuilder conteudoPedido = new StringBuilder();
        conteudoPedido.append("Pedido Lanchonete\n");
        conteudoPedido.append("Cliente: ").append(txtNomeCliente.getText()).append("\n");
        conteudoPedido.append("Data/Hora: ").append(dataHora).append("\n");
        conteudoPedido.append("Itens:\n");

        // Adicionar itens ao conteúdo
        String[] linhas = txtAreaPedido.getText().split("\n");
        for (String linha : linhas) {
            conteudoPedido.append(linha).append("\n");
        }

        // Adicionar o total
        conteudoPedido.append("\nTotal: R$ ").append(df.format(totalPedido));

        // Exibir o conteúdo do pedido na área de texto
        txtAreaPedido.setText(conteudoPedido.toString());

        // Criar e escrever no arquivo .txt
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            writer.write(conteudoPedido.toString());
            JOptionPane.showMessageDialog(this, "Pedido salvo em " + caminhoArquivo, "Arquivo Gerado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpar o pedido
    private void limparPedido() {
        txtAreaPedido.setText("");
        totalPedido = 0.0;
    }

    // Método para buscar o cliente no banco de dados
    private void buscarCliente() {
        String idCliente = txtIdCliente.getText();

        if (idCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o ID do cliente!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT nomecli FROM tbcliente WHERE idcli=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idCliente);
            rs = pst.executeQuery();

            if (rs.next()) {
                String nomeCliente = rs.getString("nomecli");
                txtNomeCliente.setText(nomeCliente);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                txtNomeCliente.setText(""); // Limpa o campo de nome
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

