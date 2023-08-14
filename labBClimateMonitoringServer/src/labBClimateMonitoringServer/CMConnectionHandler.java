package labBClimateMonitoringServer;

import java.net.*;
import java.io.*;

public class CMConnectionHandler extends Thread{
	private Socket clientSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public CMConnectionHandler(Socket socket) {
		this.clientSocket = socket;
	}
	
	public void run() {
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());			
			String input;
			
			//todo gestione username, password e host per connettere al DB, se non sono valide, annulla la connessione
			
			
			while(true) {
				input = (String) in.readObject();
				switch(input) {
				case "registerUser" :
					//funzione register
					break;
				case "login" : 
					//funzione login
					break;
				case "registerArea" :
					//funzione registerArea
					break;
				case "searchArea" :
					//funzione searchArea
					break;
				case "registerMonitoringCentre" :
					//funzione registerMonitoringCentre
					break;
				case "insertClimateParameters" :
					//funzione insertClimateParameters
					break;
				default :
					out.writeObject("input non valido"); //messaggio di errore per il client
					
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}