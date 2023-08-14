package labBClimateMonitoringServer;

import java.io.*;

public class ServerMain {

	public static void main(String[] args) {
		final int port = 6066;
		
		//DBManager manager = new DBManager();
		
		try {
			CMServer server = new CMServer(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
