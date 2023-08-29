package serverCM;

import java.io.Serializable;

public class CoordinateMonitoraggio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int geo_ID;
	private double latitudine;
	private double longitudine;
	private String denominazione;
	private String stato;
	private String countryCode;
	
	public CoordinateMonitoraggio() {
		this.geo_ID = 0;
		this.latitudine = 0.0;
		this.longitudine = 0.0;
		this.denominazione = "";
		this.stato = "";
		this.countryCode = "";
	}

	public CoordinateMonitoraggio(int geo_ID, double latitudine, double longitudine, String denominazione, String stato,
			String countryCode) {
		this.geo_ID = geo_ID;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.denominazione = denominazione;
		this.stato = stato;
		this.countryCode = countryCode;
	}

	public synchronized int getGeo_ID() {
		return geo_ID;
	}

	public synchronized void setGeo_ID(int geo_ID) {
		this.geo_ID = geo_ID;
	}

	public synchronized double getLatitudine() {
		return latitudine;
	}

	public synchronized void setLatitudine(double latitudine) {
		this.latitudine = latitudine;
	}

	public synchronized double getLongitudine() {
		return longitudine;
	}

	public synchronized void setLongitudine(double longitudine) {
		this.longitudine = longitudine;
	}

	public synchronized String getDenominazione() {
		return denominazione;
	}

	public synchronized void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public synchronized String getStato() {
		return stato;
	}

	public synchronized void setStato(String stato) {
		this.stato = stato;
	}

	public synchronized String getCountryCode() {
		return countryCode;
	}

	public synchronized void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
	
	

}
