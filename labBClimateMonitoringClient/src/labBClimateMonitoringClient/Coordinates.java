package labBClimateMonitoringClient;

import java.io.Serializable;

public class Coordinates implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int geo_ID;
	private double lat;
	private double lon;
	private String name;
	private String state;
	private String countryCode;
	
	public Coordinates() {
		this.geo_ID = 0;
		this.lat = 0.0;
		this.lon = 0.0;
		this.name = "";
		this.state = "";
		this.countryCode = "";
	}

	public Coordinates(int geo_ID, double lat, double lon, String name, String state,
			String countryCode) {
		this.geo_ID = geo_ID;
		this.lat = lat;
		this.lon = lon;
		this.name = name;
		this.state = state;
		this.countryCode = countryCode;
	}

	public synchronized int getGeo_ID() {
		return geo_ID;
	}

	public synchronized void setGeo_ID(int geo_ID) {
		this.geo_ID = geo_ID;
	}

	public synchronized double getLa() {
		return lat;
	}

	public synchronized void setLat(double lat) {
		this.lat = lat;
	}

	public synchronized double getLon() {
		return lon;
	}

	public synchronized void setLon(double lon) {
		this.lon = lon;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized String getState() {
		return state;
	}

	public synchronized void setState(String state) {
		this.state = state;
	}

	public synchronized String getCountryCode() {
		return countryCode;
	}

	public synchronized void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
	
	

}
