package clientCM;

import java.io.Serializable;

public class CentriMonitoraggio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nomeCentro;
	private int AreaInteresse;
	private String via;
	private int civico;
	private int cap;
	private String comune;
	private String provincia;
	
	public CentriMonitoraggio() {
		this.nomeCentro = "";
		AreaInteresse = 0;
		this.via = "";
		this.civico = 0;
		this.cap = 0;
		this.comune = "";
		this.provincia = "";
	}

	public CentriMonitoraggio(String nomeCentro, int areaInteresse, String via, int civico, int cap, String comune,
			String provincia) {
		this.nomeCentro = nomeCentro;
		AreaInteresse = areaInteresse;
		this.via = via;
		this.civico = civico;
		this.cap = cap;
		this.comune = comune;
		this.provincia = provincia;
	}

	public synchronized String getNomeCentro() {
		return nomeCentro;
	}

	public synchronized void setNomeCentro(String nomeCentro) {
		this.nomeCentro = nomeCentro;
	}

	public synchronized int getAreaInteresse() {
		return AreaInteresse;
	}

	public synchronized void setAreaInteresse(int areaInteresse) {
		AreaInteresse = areaInteresse;
	}

	public synchronized String getVia() {
		return via;
	}

	public synchronized void setVia(String via) {
		this.via = via;
	}

	public synchronized int getCivico() {
		return civico;
	}

	public synchronized void setCivico(int civico) {
		this.civico = civico;
	}

	public synchronized int getCap() {
		return cap;
	}

	public synchronized void setCap(int cap) {
		this.cap = cap;
	}

	public synchronized String getComune() {
		return comune;
	}

	public synchronized void setComune(String comune) {
		this.comune = comune;
	}

	public synchronized String getProvincia() {
		return provincia;
	}

	public synchronized void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	
	
	
}
