package labBClimateMonitoringServer;

import java.sql.*;

public class DBManager {
	
	//Classe Server che si interfaccia con il DB
	
	Connection conn;
	Statement stat;
	
	private String protocol;
	private String url;
	private String username;
	private String pwd;
	
	public DBManager() {
		//Inizializzazione Variabili di connessione
	
		username="";
		pwd = "";
	}
	
	public DBManager(String host, String username, String pwd) {
		this.username = username;
		this.pwd = pwd;
		
		protocol = "jdbc:postgresql://";
		url = protocol + host;
		
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
