package br.com.eicon.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConfig {
	public static Connection getConnection() throws SQLException,ClassNotFoundException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/Eicon","root","macacofr");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
	}
}
