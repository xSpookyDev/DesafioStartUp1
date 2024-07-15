package cl.praxis.models.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Db {
	
	
	private static Connection connect =null;
	
	
	private  Db() {
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/startup", "postgres", "djdream3");
			System.out.println(connect.isValid(50000) ? "Conectado a la BD" : "Conexion a la base de datos MALA");
			
		} catch (ClassNotFoundException | SQLException  c) {
			System.out.println("Error al conectar la bd"+ c.getMessage());
			// TODO: handle exception
		}
		
	}
	
	public static Connection getConnect() {
		if(connect == null) {
			new Db();
		}
		return connect;
	}
}
