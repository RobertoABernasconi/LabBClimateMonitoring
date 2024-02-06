package labBClimateMonitoringServer;

import java.io.Serializable;
/**
 * Class that models a registered user
 */
public class Registered implements Serializable{
	
	private static final long serialVersionUID = 1;
	
	private String name;
	private String surname;
	private String ssn;
	private String mail;
	private String user;
	private String pwd;
	private String centreName;
	private boolean loggedIn;
	
	/**
	 * Empty constructor
	 */
	public Registered()
	{
		name = "";
		surname = "";
		ssn = "";
		mail = "";
		user = "";
		pwd = "";
		centreName = "";
		loggedIn = false;
	}
	/**
	 * Constructor with parameters:
	 * @param name Name of the user
	 * @param surname Surname of the user
	 * @param ssn Social security number of the user
	 * @param mail E-Mail address of the user
	 * @param user User name chosen by the user
	 * @param pwd Password chosen or inputted by the user
	 * @param centreName Name of the centre that the user is registered to
	 */
	public Registered(String name, String surname, String ssn, String mail, String user, String pwd, String centreName) {
		super();
		this.name = name;
		this.surname = surname;
		this.ssn = ssn;
		this.mail = mail;
		this.user = user;
		this.pwd = pwd;
		this.centreName = centreName;
		loggedIn = true;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized String getSurname() {
		return surname;
	}

	public synchronized void setSurname(String cognome) {
		this.surname = cognome;
	}

	public synchronized String getSsn() {
		return ssn;
	}

	public synchronized void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public synchronized String getMail() {
		return mail;
	}

	public synchronized void setMail(String mail) {
		this.mail = mail;
	}

	public synchronized String getUser() {
		return user;
	}

	public synchronized void setUser(String user) {
		this.user = user;
	}

	public synchronized String getPwd() {
		return pwd;
	}

	public synchronized void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public synchronized String getCentreName() {
		return centreName;
	}

	public synchronized void setCentreName(String centreName) {
		this.centreName = centreName;
	}

	public synchronized boolean isLoggedIn() {
		return loggedIn;
	}

	public synchronized void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
