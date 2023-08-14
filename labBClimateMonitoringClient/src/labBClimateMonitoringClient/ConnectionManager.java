package labBClimateMonitoringClient;

import java.net.*;
import java.io.*;

public class ConnectionManager {
	public ConnectionManager(int port) {
		InetAddress address = null;
		try {
			address = InetAddress.getByName("127.0.0.1"); //serve per cambiare l'indirizzo se il server non è sulla stessa macchina
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Connecting to " + "127.0.0.1" + " on port " + port);
		    Socket client = new Socket(address, port);
		    /*System.out.println("Just connected to " + client.getRemoteSocketAddress());
		    OutputStream outToServer = client.getOutputStream();
		    DataOutputStream out = new DataOutputStream(outToServer);
		    out.writeUTF("Hello from " + client.getLocalSocketAddress());
		    InputStream inFromServer = client.getInputStream();
		    DataInputStream in = new DataInputStream(inFromServer);
		    System.out.println("Server says " + in.readUTF());*/
		    client.close();
		    
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}