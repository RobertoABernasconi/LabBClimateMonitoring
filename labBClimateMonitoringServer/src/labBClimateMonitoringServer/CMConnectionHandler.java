package labBClimateMonitoringServer;

import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;

public class CMConnectionHandler extends Thread{
	private Socket clientSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private DBManager dbM;
	
	public CMConnectionHandler(Socket socket) {
		this.clientSocket = socket;
	}
	
	public void run() {
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());			
			String input;
			boolean result = false;
			//todo gestione username, password e host per connettere al DB, se non sono valide, annulla la connessione
			
			
			while(true) {
				input = in.readObject().toString();
				switch(input) {
				case "registerUser" : //nome cognome CF email userid password centromonitoraggio
					Registered user = (Registered) in.readObject(); //TODO riceve oggetto serializable
					registerUser(user);
					break;
				case "login" : 
					//funzione login variabile "loggato" booleana
					String userid = (String) in.readObject();
					String pwd = (String) in.readObject();
					String userRegistered = login(userid, pwd);
					if(!userRegistered.isEmpty()) {
						//Reindirizzato alla pagina iniziale con funzioni sbloccate
					}
					break;
				case "registerArea" :
					//funzione registerArea oggetto come per registerUser
					InterestedArea intArea = (InterestedArea) in.readObject();
					result = registerArea(intArea);
					out.writeBoolean(result);
					break;
				case "searchArea" :
					//funzione searchArea
					boolean nameSearch = in.readBoolean();
					if(nameSearch) {
						String name = (String) in.readObject();
						ResultSet resultQuery = searchAreaName(name);
						out.writeObject(resultQuery);
					}else {
						String latitude = (String) in.readObject();
						String longitude = (String) in.readObject();
						ResultSet resultQuery = searchArea(latitude, longitude);
						out.writeObject(resultQuery);
					}
					
					break;
				case "viewArea" :
					
					InterestedArea inA = (InterestedArea) in.readObject();
					ClimateParameters[] climateParam = ViewArea(inA);
					out.writeObject(climateParam);
					break;
				case "registerMonitoringCentre" : //TODO oggetto
					//funzione registerMonitoringCentre	
					MonitoringCentre mc = (MonitoringCentre) in.readObject();
					result = insertMonitoringCentre(mc);
					out.writeBoolean(result);
					break;
				case "insertClimateParameters" : //TODO oggetto come per registerUser
					//funzione insertClimateParameters
					ClimateParameters cp = (ClimateParameters) in.readObject();
					result = insertClimateParameters(cp);
					out.writeBoolean(result);
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
	
	private ClimateParameters[] ViewArea(InterestedArea inA) {
		
		ClimateParameters[] cp = null;
		String query = "SELECT * FROM parametriClimatici WHERE InterestedArea = "+inA.getGeo_ID();
		ResultSet result = dbM.queryDB(query);
		int i = 0;
		try {
			while(result.next()) {
				cp[i].setNameCM((MonitoringCentre) result.getObject(1));
				cp[i].setClimateCategory((String) result.getObject(4));
				cp[i].setDate(result.getObject(3).toString());
				cp[i].setExplanation((String) result.getObject(5));
				cp[i].setNotes((String) result.getObject(7));
				cp[i].setScore(Integer.parseInt(result.getObject(6).toString()));
				cp[i].setInterestedArea(inA);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cp;
	}

	private ResultSet searchArea(String latitude, String longitude) {
		
		String query = "SELECT * FROM InterestedArea WHERE latitudine = "+latitude+" AND Longitudine = "+longitude+"";
		ResultSet result = dbM.queryDB(query);
		return result;
	}
	
private ResultSet searchAreaName(String name) {
		
		String query = "SELECT * FROM InterestedArea WHERE Denominazione = "+name+"";
		ResultSet result = dbM.queryDB(query);
		return result;
	}

	private String login(String userid, String pwd) {
		// TODO Auto-generated method stub
		String user = "";
		String query = "SELECT name, surname FROM operatoriRegistrati WHERE userid = "+userid+" AND password = "+pwd+"";
		ResultSet resultQuery = dbM.queryDB(query);
		if(resultQuery.equals(null)) {
			System.out.println("ERRORE! UTENTE NON LOGGATO");
		}else {
			try {
				String name = (String) resultQuery.getObject("name");
				String surname = (String) resultQuery.getObject("surname");
				user = name + " "+surname;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	private boolean registerArea(InterestedArea intArea) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO InterestedArea (Geo_id, Latitudine, Longitudine, Denominazione, Stato, CountryCode) "
				+ "VALUES ("+intArea.getGeo_ID()+", "+intArea.getLa()+", "+intArea.getLon()+", "+intArea.getName()+", "+intArea.getState()+
				", "+intArea.getCountryCode()+")";
		boolean result = dbM.updateDB(query);
		return result;
	}

	private boolean insertMonitoringCentre(MonitoringCentre mc) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO centriMonitoraggio (nome, areaInteresse) VALUES ("+mc.getCentreName()+", "+mc.getArea()+")";
		boolean result = dbM.updateDB(query);
		query = "INSERT INTO address (via, civico, cap, comune, provincia) VALUES ("+mc.getAddress()+", "+mc.getStreetNumber()+", "+mc.getPostalCode()+""
				+ mc.getCity()+", "+mc.getProvince()+")";
		result = dbM.updateDB(query);
		String selectIDAddress = "SELECT id_A from Address WHERE via = "+mc.getAddress()+"  AND civico = "+mc.getStreetNumber()+
				" AND cap = "+mc.getPostalCode()+" AND comune = "+mc.getCity()+" AND provincia = "+mc.getProvince()+"";
		ResultSet res = dbM.queryDB(selectIDAddress);
		int idA = 0;
		try {
			idA = res.getInt("id_A");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = "INSERT INTO indirizzoCentri (name, id) VALUES ("+mc.getCentreName()+", "+idA+")";
		result = dbM.updateDB(query);
		return result;
	}

	private boolean insertClimateParameters(ClimateParameters cp) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO parametriClimatici (nameCM, interestedArea, dataRilevazione, climateCategory, explanation, score, notes) "
				+ "VALUES ("+cp.getNameCM()+", "+cp.getInterestedArea()+", "+cp.getDate()+", "+cp.getClimateCategory()+", "+
		cp.getExplanation()+", "+cp.getScore()+", "+cp.getNotes()+")";
		boolean result = dbM.updateDB(query);
		return result;
	}

	private boolean registerUser(Registered user) { //TODO oggetto RegisteredUser
		String query = "INSERT INTO operatoriRegistrati (CF, name, surname, mail, userid, password, nameCM) "
				+ "VALUES ("+user.getSsn()+", "+user.getName()+", "+user.getSurname()+", "+user.getMail()+", "+user.getUser()+
				", "+user.getPwd()+", "+user.getCentreName()+")";
		boolean result = dbM.updateDB(query);
		return result;
	}
}
