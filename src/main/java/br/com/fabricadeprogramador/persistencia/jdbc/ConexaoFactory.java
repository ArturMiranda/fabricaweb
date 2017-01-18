package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnection() {
		try {
			//return DriverManager.getConnection("jdbc:mysql://localhost:3306/fabricaweb","root", "");
			Class.forName("com.mysql.cj.jdbc.Driver");/*Forcando o carregamento do driver*/
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/fabricaweb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
