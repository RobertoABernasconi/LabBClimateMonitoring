package labBClimateMonitoringClient;

import java.net.*;
import java.io.*;

public class ConnectionManager {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	public ConnectionManager(int port) {
		InetAddress address = null;
		try {
			address = InetAddress.getByName("127.0.0.1"); //serve per cambiare l'indirizzo se il server non è sulla stessa macchina
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Connecting to " + address.toString() + " on port " + port);
		    Socket client = new Socket(address, port);
		    System.out.println("Just connected to " + client.getRemoteSocketAddress());
		    out = new ObjectOutputStream(client.getOutputStream());
		    out.writeObject("Hello");
		    in = new ObjectInputStream(client.getInputStream());
		    System.out.println("Server says " + in.readObject().toString());
		    client.close();
		    
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
}