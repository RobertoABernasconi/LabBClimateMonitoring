package labBClimateMonitoringServer;

import java.net.*;
import java.io.*;
/**
 * CMServer class that awaits connections and launches CMConnectionHandler threads for each successful connection
 * @author Roberto Alfonso Bernasconi
 * @author Andrea Magliocca
 */
public class CMServer {
	private ServerSocket serverSocket;
	/**
	 * Creates a CMServer object.
	 * @param port port used by the client and server sockets
	 * @param dbm DBManager object that handles DB operations
	 */
	public CMServer(int port, DBManager dbm) {
		try {
			serverSocket = new ServerSocket(port);
			while(true) {
				System.out.println("Awaiting Connection");
	    		new CMConnectionHandler(serverSocket.accept(), dbm).start();
	    		System.out.println("Connection Successful!");
				}
			} catch (IOException e) {
				System.out.println("IO Exception in CMServer");
				}
	}
}
