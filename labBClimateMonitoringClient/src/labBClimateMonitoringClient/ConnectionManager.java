package labBClimateMonitoringClient;

import java.net.*;
import java.util.ArrayList;
import java.io.*;
/**
 * Class implementing the Singleton design pattern that handles all client-server communication.
 * @author Roberto Alfonso Bernasconi
 * @author Andrea Magliocca
 */
public class ConnectionManager {
	private static ConnectionManager instance = null; 
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket client = null;
	
	private ConnectionManager() {
		int port = 6066;
		InetAddress address = null;
		
		try {
			address = InetAddress.getByName("127.0.0.1"); //serve per cambiare l'indirizzo se il server non e' sulla stessa macchina
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Connecting to " + address.toString() + " on port " + port);
		    client = new Socket(address, port);
		    System.out.println("Connected to " + client.getRemoteSocketAddress());
		    out = new ObjectOutputStream(client.getOutputStream());
		    in = new ObjectInputStream(client.getInputStream());
		    
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	/**
	 * Singleton design pattern method to ensure there is only one ConnectionManager object at a time
	 * @return returns the ConnectionManager instance
	 */
	public static synchronized ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}
	/**
	 * Method to send a MonitoringCentre object to the server
	 * @param centre the MonitoringCentre object to be sent
	 * @return true if the method executed successfully, false if it raised an exception
	 */
	public boolean registerMonitoringCentre(MonitoringCentre centre) {
		try {
			instance.out.writeObject("registerMonitoringCentre");
			instance.out.writeObject(centre);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Method to send a InterestedArea object to the server
	 * @param result the InterestedArea object to be sent
	 * @return true if the method executed successfully, false if it raised an exception
	 */
	public boolean registerArea(InterestedArea result) {
		try {
			instance.out.writeObject("registerArea");
			instance.out.writeObject(result);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Method to attempt to log in the user to the registered application functions.
	 * @param user username to use
	 * @param pw password to use
	 * @return true if the method executed successfully, false if it raised an exception
	 */
	public boolean userLogin(String user, String pw) {
		try {
			instance.out.writeObject("login");
			instance.out.writeObject(user);
			instance.out.writeObject(pw);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			if(instance.in.readObject() == "Logged in") {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Method to send a ClimateParameters object to the server
	 * @param parameters the ClimateParameters object to be sent
	 * @return true if the method executed successfully, false if it raised an exception
	 */
	public boolean addParameters(ClimateParameters parameters) {
		try {
			instance.out.writeObject("insertClimateParameters");
			instance.out.writeObject(parameters);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	/**
	 * Method to send a Registered object to the server in order to register a new user
	 * @param registeredObject the Registered object to be sent
	 * @return true if the method executed successfully, false if it raised an exception
	 */
	public boolean registerUser(Registered registeredObject) {
		try {
			instance.out.writeObject("registerUser");
			instance.out.writeObject(registeredObject);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	/**
	 * Method to search for the name of an area
	 * @param name the name to search, the server will look for similar names in the DB
	 * @return an ArrayList containing all the matching InterestedArea objects found
	 */
	public ArrayList <InterestedArea> searchArea(String name) {
			ArrayList <InterestedArea> result = null;
			try {
				instance.out.writeObject("searchArea");
				instance.out.writeBoolean(true);
				instance.out.writeObject(name);
				result = (ArrayList <InterestedArea>) in.readObject();
			} catch (ClassNotFoundException | IOException e) {
				ErrorWindow.start("Couldn't process request");
			}
			return result;
	}
	/**
	 * Method to search for areas matching a certain latitude and longitude
	 * @param lat the latitude to search for
	 * @param lon the longitude to search for
	 * @return an ArrayList containing all the matching InterestedArea objects found
	 */	
	public ArrayList <InterestedArea> searchArea(Double lat, Double lon) {
		ArrayList <InterestedArea> result = null;
		try {
			instance.out.writeObject("searchArea");
			instance.out.writeBoolean(false);
			instance.out.writeDouble(lat);
			instance.out.writeDouble(lon);
			result = (ArrayList <InterestedArea>) in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			ErrorWindow.start("Couldn't process request");
		}
		return result;
	}
	/**
	 * Method to retrieve all the ClimateParameters pertaining an area of interest
	 * @param name the name of the area to look for
	 * @return an ArrayList containing the ClimateParameters to be shown to the user
	 */
	public ArrayList <ClimateParameters> viewArea(String name) {
		ArrayList <ClimateParameters> result = null;
		try {
			instance.out.writeObject("viewArea");
			instance.out.writeObject(name);
			result = (ArrayList <ClimateParameters>) in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			ErrorWindow.start("Couldn't process request");
		}
		return result;
	}
}