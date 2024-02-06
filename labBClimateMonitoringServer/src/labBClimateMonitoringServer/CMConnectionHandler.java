package labBClimateMonitoringServer;

import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.*;

/**
 * Class extending Thread to handle the connection to the clients.
 * Uses a Socket and input / output streams
 * @author Roberto Alfonso Bernasconi
 * @author Andrea Magliocca
 */

public class CMConnectionHandler extends Thread{
	private Socket clientSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private DBManager dbM;
	
	/**
	 * Constructor. Creates a new CMConnectionHandler object and invokes run()
	 * @param socket socket to use
	 * @param dbm2 database to use
	 */
	public CMConnectionHandler(Socket socket, DBManager dbm2) {
		this.clientSocket = socket;
		this.dbM = dbm2;
	}
	/**
	 * Takes String commands from the client in order to execute relevant operations
	 * @override overrides the Thread run() method
	 */
	public void run() {
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());			
			String input;
			boolean result = false;			
			
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
						out.writeObject("Logged in");
					} else {
						out.writeObject("Failed");
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
						ArrayList <InterestedArea> resultQuery = searchAreaName(name);
						out.writeObject(resultQuery);
					}else {
						double latitude = in.readDouble();
						double longitude = in.readDouble();
						ArrayList <InterestedArea> resultQuery = searchArea(latitude, longitude);
						out.writeObject(resultQuery);
					}
					
					break;
				case "viewArea" :
					String name = (String) in.readObject();
					ArrayList<ClimateParameters> climateParam = ViewArea(name);
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
	
	private ArrayList<ClimateParameters> ViewArea(String name) {
		
		ArrayList <ClimateParameters> cpArray = new ArrayList <ClimateParameters>();
		ArrayList <InterestedArea> inA = searchAreaName(name);
		for(int j = 0; j < inA.size(); j++) {
		String query = "SELECT * FROM parametriClimatici WHERE InterestedArea = "+inA.get(j).getGeo_ID();
		ResultSet result = dbM.queryDB(query);
		ClimateParameters cp = new ClimateParameters();
		try {
			while(result.next()) {
				cp = new ClimateParameters();
				cp.setNameCM((MonitoringCentre) result.getObject(1));
				cp.setClimateCategory((String) result.getObject(4));
				cp.setDate(result.getObject(3).toString());
				cp.setExplanation((String) result.getObject(5));
				cp.setNotes((String) result.getObject(7));
				cp.setScore(Integer.parseInt(result.getObject(6).toString()));
				cp.setInterestedArea(inA.get(j));
				cpArray.add(cp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return cpArray;
	}

	private ArrayList <InterestedArea> searchArea(double latitude, double longitude) {
		
		String query = "SELECT * FROM InterestedArea WHERE latitudine = "+latitude+" AND Longitudine = "+longitude+"";
		ResultSet result = dbM.queryDB(query);
		return interestedAreaSearched(result);
	}
	
	private ArrayList <InterestedArea> interestedAreaSearched(ResultSet result) {
		ArrayList <InterestedArea> inArray = new ArrayList<InterestedArea>();
		InterestedArea inA;
		try {
			while(result.isAfterLast()) {
				inA = new InterestedArea();
				inA.setGeo_ID(result.getInt("Geo_ID"));
				inA.setLat(result.getDouble("Latitudine"));
				inA.setLon(result.getInt("Longitudine"));
				inA.setName(result.getString("Denominazione"));
				inA.setState(result.getString("Stato"));
				inA.setCountryCode(result.getString("CountryCode"));
				inArray.add(inA);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return inArray;
	}

	private ArrayList <InterestedArea> searchAreaName(String name) {
		
		String query = "SELECT * FROM InterestedArea WHERE Denominazione = "+name+"";
		ResultSet result = dbM.queryDB(query);
		return interestedAreaSearched(result);
	}

	private String login(String userid, String pwd) {
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
				e.printStackTrace();
			}
		}
		return user;
	}

	private boolean registerArea(InterestedArea intArea) {
		String query = "INSERT INTO InterestedArea (Geo_id, Latitudine, Longitudine, Denominazione, Stato, CountryCode) "
				+ "VALUES ("+intArea.getGeo_ID()+", "+intArea.getLa()+", "+intArea.getLon()+", "+intArea.getName()+", "+intArea.getState()+
				", "+intArea.getCountryCode()+")";
		boolean result = dbM.updateDB(query);
		return result;
	}

	private boolean insertMonitoringCentre(MonitoringCentre mc) {
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
			e.printStackTrace();
		}
		query = "INSERT INTO indirizzoCentri (name, id) VALUES ("+mc.getCentreName()+", "+idA+")";
		result = dbM.updateDB(query);
		return result;
	}

	private boolean insertClimateParameters(ClimateParameters cp) {
		String query = "INSERT INTO parametriClimatici (nameCM, interestedArea, dataRilevazione, climateCategory, explanation, score, notes) "
				+ "VALUES ("+cp.getNameCM()+", "+cp.getInterestedArea()+", "+cp.getDate()+", "+cp.getClimateCategory()+", "+
		cp.getExplanation()+", "+cp.getScore()+", "+cp.getNotes()+")";
		boolean result = dbM.updateDB(query);
		return result;
	}

	private boolean registerUser(Registered user) {
		String query = "INSERT INTO operatoriRegistrati (CF, name, surname, mail, userid, password, nameCM) "
				+ "VALUES ("+user.getSsn()+", "+user.getName()+", "+user.getSurname()+", "+user.getMail()+", "+user.getUser()+
				", "+user.getPwd()+", "+user.getCentreName()+")";
		boolean result = dbM.updateDB(query);
		return result;
	}
}
