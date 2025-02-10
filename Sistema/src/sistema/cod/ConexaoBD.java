package sistema.cod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	// Método para estabelecer a conexão com o banco de dados
	public static Connection getConnection() {
		Connection connection = null;
		try {
			// Carregar o driver JDBC
			Class.forName("com.mysql.cj.jdbc.Driver");

			// URL de conexão com o banco de dados
			String url = "jdbc:mysql://localhost:3306/sistema";
			String user = "root";
			String password = "123456";

			// Estabelecer a conexão
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexão estabelecida com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
		}
		return connection;	
	}

	// Método para fechar a conexão com o banco de dados
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Conexão fechada com sucesso!");
			} catch (SQLException e) {
				System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Estabelecer a conexão com o banco de dados
		Connection connection = ConexaoBD.getConnection();

		// Aqui você pode executar operações no banco de dados, como consultas,
		// inserções, etc.

		// Fechar a conexão com o banco de dados
		ConexaoBD.closeConnection(connection);
	}

}
