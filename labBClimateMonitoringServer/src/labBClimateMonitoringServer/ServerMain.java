package labBClimateMonitoringServer;

import java.io.*;
import java.util.Scanner;

public class ServerMain {

	public static void main(String[] args) {
		final int port = 6066;
		Scanner scanner = null;
		DBManager dbm = null;
		try {
			scanner = new Scanner(System.in);
			
			System.out.println("Insert DB username: ");
			String user = scanner.nextLine();
			System.out.println("Insert DB password: ");
			String pw = scanner.nextLine();
			System.out.println("Insert DB host name (format example:localhost:8080/ClimateMonitoring): ");
			String host = scanner.nextLine();
			dbm = new DBManager(host, user, pw);
		} catch (Exception e) {
			System.out.println("Error connecting to the DB");
		} finally {
			scanner.close();
		}
		
		try {
			CMServer server = new CMServer(port, dbm);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
