package br.com.eicon.teste;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.eicon.config.*;

public class TestaConexao {
	public static void main(String [] args) throws SQLException, ClassNotFoundException {
        Connection connection = BDConfig.getConnection();
        System.out.println("Conexão aberta");
        connection.close();
    }
}
