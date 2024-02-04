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
	}
	
	public boolean connectionDatabase() {
		//funzione per l'instaurazione della connessione al DB
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, username, pwd);
			if(conn != null) {
				System.out.println("Connection successfully");
				stat = conn.createStatement();
				return true;
			}else {
				System.out.println("Connection failed, wrong credential");
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find class "+e.getMessage());
		}
		return false;
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
