package serverCM;

import java.sql.*;

public class ServerCM {
	
	//Classe Server che si interfaccia con il DB
	
	Connection conn;
	Statement stat;
	
	private String host;
	
	private String protocol;
	private String resource;
	private String url;
	
	private String username;
	private String pwd;
	
	public ServerCM() {
		//Inizializzazione Variabili di connessione
		
		host = "";
		
		username="";
		pwd = "";
	}
	
	public ServerCM(String host, String username, String pwd) {
		
		this.host = host;
		this.username = username;
		this.pwd = pwd;
		
		protocol = "jdbc:postgresql" + "://";
		resource = "ClimateMonitoring";
		url = protocol + host + resource;
		
		connectionDatabase();
	}
	
	private void connectionDatabase() {
		//funzione per l'instaurazione della connessione al DB
		try {
			conn = DriverManager.getConnection(url, username, pwd);
			stat = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized boolean updateDB(String query) {
		//Funzione per eventuali update a livello DB, come insert, update o Delete
		try {
			stat.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public synchronized ResultSet queryDB(String query) {
		//Funzione per esecuzione Query quali SELECT
		try {
			return stat.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
