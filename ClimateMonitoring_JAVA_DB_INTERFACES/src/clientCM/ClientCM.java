package clientCM;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.util.Scanner;

public class ClientCM {
	
	//FUNZIONI NECESSARIE IN CLIENTCM
	/*
	 * NO LOGIN
	 * 1- CERCAAREAGEOGRAFICA
	 * 2- VISUALIZZAAREAGEOGRAFICA
	 * 3- REGISTRAZIONE
	 * LOGIN NECESSARIO
	 * 4- REGISTRACENTROAREE
	 * 5- INSERISCIPARAMETRICLIMATICI
	 * */
	
	private Socket s;
	//private BufferedReader br;
	//private PrintWriter pw;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	/*private String nome;
	private String cognome;
	private String cf;
	private String mail;
	private String user;
	private String pwd;
	private String nomeCentro; */
	private Registrato r;
	public ClientCM() {
		super();
		this.s = null;
		//this.br = null;
		//this.pw = null;
		/*this.nome = "";
		this.cognome = "";
		cf = "";
		this.mail = "";
		this.user = "";
		this.pwd = "";
		this.nomeCentro = "";*/
		r = new Registrato();
	}
	public ClientCM(String nome, String cognome, String cF, String mail, String user, String pwd, 
			        String nomeCentro) 
	{
		super();
		try {
			InetAddress addr = InetAddress.getByName(null);
			s = new Socket(addr, 8883);
			//br = new BufferedReader(new InputStreamReader(s.getInputStream())); //Stream di input
			//pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*this.nome = nome;
		this.cognome = cognome;
		cf = cF;
		this.mail = mail;
		this.user = user;
		this.pwd = pwd;
		this.nomeCentro = nomeCentro; */
	}
	
	public ResultSet cercaAreaGeografica() {
		String nomeArea;
		System.out.println("INSERISCI NOME AREA DA RICERCARE");
		nomeArea = new Scanner(System.in).nextLine(); //temp
		//pw.print(nomeArea);
		ResultSet result = null;
		try {
			oos.writeObject(nomeArea);
			result = (ResultSet) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			//Gestisce errore in caso di classe inesistente oppure in caso di errore di invio dati socket
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultSet visualizzaAreaGeografica() {
		String nomeArea;
		System.out.println("INSERISCI COORDINATE");
		nomeArea = new Scanner(System.in).nextLine(); //temp
		//pw.print(nomeArea);
		ResultSet result = null;
		try {
			oos.writeObject(nomeArea);
			result = (ResultSet) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			//Gestisce errore in caso di classe inesistente oppure in caso di errore di invio dati socket
			e.printStackTrace();
		}
		return result;
	}
	
	public void registrazione() {
		Scanner in = new Scanner(System.in);
		System.out.println("INSERISCI NOME");
		String nome = in.nextLine();
		System.out.println("INSERISCI COGNOME");
		String cognome = in.nextLine();
		System.out.println("INSERISCI CODICE FISCALE");
		String cf = in.nextLine();
		System.out.println("INSERISCI MAIL");
		String mail = in.nextLine();
		System.out.println("INSERISCI USERID");
		String user = in.nextLine();
		System.out.println("INSERISCI PASSWORD");
		String pwd = in.nextLine();
		System.out.println("INSERISCI CENTRO MONITORAGGIO DI APPARTENENZA");
		String nomeCentro = in.nextLine();
		r = new Registrato(nome, cognome, cf, mail, user, pwd, nomeCentro);
		try {
			//oos.writeInt(2); CODICE PER GESTIRE LE FUNZIONI LATO SERVER
			oos.writeObject(r);
		} catch (IOException e) {
			// ERRORE CHE PUO' SORGERE IN CASO NON SIA SERIALIZZABILE
			e.printStackTrace();
		}
	}
	
	//FUNZIONE LOGIN/LOGOUT
}
