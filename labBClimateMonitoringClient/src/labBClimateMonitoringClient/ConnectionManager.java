package labBClimateMonitoringClient;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ConnectionManager {
	private static ConnectionManager instance = null; 
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket client = null;
	private ConnectionManager() {
		int port = 6066;
		InetAddress address = null;
		
		try {
			address = InetAddress.getByName("127.0.0.1"); //serve per cambiare l'indirizzo se il server non è sulla stessa macchina
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Connecting to " + address.toString() + " on port " + port);
		    client = new Socket(address, port);
		    System.out.println("Connected to " + client.getRemoteSocketAddress());
		    out = new ObjectOutputStream(client.getOutputStream());
		    in = new ObjectInputStream(client.getInputStream());

		    while (true) {
		    	in.readObject();
		    }
		    
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	public static synchronized ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

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