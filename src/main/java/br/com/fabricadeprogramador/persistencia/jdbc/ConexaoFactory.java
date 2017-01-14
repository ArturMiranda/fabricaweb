package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

public class ConexaoFactory {

	public static Connection getConnection() {
		try {
			//return DriverManager.getConnection("jdbc:mysql://localhost:3306/fabricaweb","root", "");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/fabricaweb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
