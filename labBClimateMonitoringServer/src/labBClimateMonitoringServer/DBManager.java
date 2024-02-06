package labBClimateMonitoringServer;

import java.sql.*;

/**
 * Class that handles all DB operations.
 */
public class DBManager {
	
	private Connection conn;
	private Statement stat;
	private String protocol;
	private String url;
	private String username;
	private String pwd;
	
	/**
	 * Empty constructor
	 */
	public DBManager() {
		username="";
		pwd = "";
	}
	
	/**
	 * Creates a DBManager object with the information inserted at server startup
	 * @param host DB Host
	 * @param username DB Username to use
	 * @param pwd DB Password to use
	 */
	public DBManager(String host, String username, String pwd) {
		this.username = username;
		this.pwd = pwd;
		
		protocol = "jdbc:postgresql://";
		url = protocol + host;
	}
	
	/**
	 * Establishes connection to the DB. Creates statement.
	 * @return true if the execution was successful, false if it failed
	 */
	public boolean connectionDatabase() {
		try {
			//Class.forName("org.postgresql.Driver");
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
		} /*catch (ClassNotFoundException e) {
			System.out.println("Cannot find class "+e.getMessage());
		}
		return false;*/
	}
	
	/**
	 * Method to use INSERT, UPDATE, DELETE commands
	 * @param query Query for the DB
	 * @return true if the execution was successful, false if it failed
	 */
	public synchronized boolean updateDB(String query) {
		try {
			stat.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Method to use SELECT queries
	 * @param query
	 * @return ResultSet containing the results of the query
	 */
	public synchronized ResultSet queryDB(String query) {
		try {
			return stat.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
