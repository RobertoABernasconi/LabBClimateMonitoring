package clientCM;

import java.io.Serializable;

public class Registrato implements Serializable{
	
	private static final long serialVersionUID = 1;
	
	private String nome;
	private String cognome;
	private String cf;
	private String mail;
	private String user;
	private String pwd;
	private String nomeCentro;
	private boolean loggato;
	
	public Registrato()
	{
		nome = "";
		cognome = "";
		cf = "";
		mail = "";
		user = "";
		pwd = "";
		nomeCentro = "";
		loggato = false;
	}

	public Registrato(String nome, String cognome, String cf, String mail, String user, String pwd, String nomeCentro) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.mail = mail;
		this.user = user;
		this.pwd = pwd;
		this.nomeCentro = nomeCentro;
		loggato = true;
	}

	public synchronized String getNome() {
		return nome;
	}

	public synchronized void setNome(String nome) {
		this.nome = nome;
	}

	public synchronized String getCognome() {
		return cognome;
	}

	public synchronized void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public synchronized String getCf() {
		return cf;
	}

	public synchronized void setCf(String cf) {
		this.cf = cf;
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

	public synchronized String getNomeCentro() {
		return nomeCentro;
	}

	public synchronized void setNomeCentro(String nomeCentro) {
		this.nomeCentro = nomeCentro;
	}

	public synchronized boolean isLoggato() {
		return loggato;
	}

	public synchronized void setLoggato(boolean loggato) {
		this.loggato = loggato;
	}
	
	
	
	

}
