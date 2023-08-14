package labBClimateMonitoringServer;

import java.net.*;
import java.io.*;

public class CMServer extends Thread {
	private ServerSocket serverSocket;
	private int port;
	
	CMServer(int port) throws IOException {
		this.port = port;
		this.run();
	}
	
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			while(true) {
				System.out.println("Attendo connessione");
	    		new CMConnectionHandler(serverSocket.accept()).start();
	    		System.out.println("Connessione effettuata");
				}
			} catch (IOException e) {
				e.printStackTrace();
				}
	}
}