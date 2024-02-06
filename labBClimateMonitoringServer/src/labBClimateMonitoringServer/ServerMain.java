package labBClimateMonitoringServer;

import java.util.Scanner;

public class ServerMain {

	public static void main(String[] args) {
		final int port = 6066;
		Scanner scanner = new Scanner(System.in);
		DBManager dbm = null;
		boolean correct = false;
		try {
			while(!correct) {
			
				dbm = new DBManager();
				System.out.println("Insert DB username: ");
				String user = scanner.nextLine();
				System.out.println("Insert DB password: ");
				String pw = scanner.nextLine();
				System.out.println("Insert DB host name (format example:localhost:5433/ClimateMonitoring): ");
				String host = scanner.nextLine();
				dbm = new DBManager(host, user, pw);
				correct = dbm.connectionDatabase();
			}
		} catch (Exception e) {
			System.out.println("Error connecting to the DB");
		} finally {
			scanner.close();
		}
			CMServer server = new CMServer(port, dbm);
	}
}
