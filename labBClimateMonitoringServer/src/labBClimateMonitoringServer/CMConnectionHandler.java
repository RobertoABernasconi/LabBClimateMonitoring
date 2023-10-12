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
				input = in.readObject().toString();
				switch(input) {
				case "registerUser" : //nome cognome CF email userid password centromonitoraggio
					input = in.readObject().toString(); //TODO riceve oggetto serializable
					registerUser(input);
					break;
				case "login" : 
					//funzione login variabile "loggato" booleana
					break;
				case "registerArea" :
					//funzione registerArea oggetto come per registerUser
					break;
				case "searchArea" :
					//funzione searchArea
					break;
				case "registerMonitoringCentre" : //TODO oggetto
					//funzione registerMonitoringCentre	
					break;
				case "insertClimateParameters" : //TODO oggetto come per registerUser
					//funzione insertClimateParameters
					break;
				default :
					out.writeObject("input non valido"); //messaggio di errore per il client
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("ClassNotFoundException o IOException");
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				System.out.println("IOException chiudendo il socket del server.");
			}
		}
		
	}
	
	private void registerUser(String input) { //TODO oggetto RegisteredUser
		
	}
}
