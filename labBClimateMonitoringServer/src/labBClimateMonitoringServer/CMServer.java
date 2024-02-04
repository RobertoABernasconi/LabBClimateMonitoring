package labBClimateMonitoringServer;

import java.net.*;
import java.io.*;

public class CMServer {
	private ServerSocket serverSocket;
	
	CMServer(int port, DBManager dbm) throws IOException {
		try {
			serverSocket = new ServerSocket(port);
			while(true) {
				System.out.println("Attendo connessione");
	    		new CMConnectionHandler(serverSocket.accept(), dbm).start();
	    		System.out.println("Connessione effettuata");
				}
			} catch (IOException e) {
				System.out.println("errore IO in CMServer");
				}
	}
}
