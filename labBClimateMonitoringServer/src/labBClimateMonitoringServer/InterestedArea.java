package labBClimateMonitoringServer;

import java.io.Serializable;
/**
 * Serializable class that models a geographical area of interest
 */
public class InterestedArea implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int geo_ID;
	private double lat;
	private double lon;
	private String name;
	private String state;
	private String countryCode;
	/**
	 * Empty constructor
	 */
	public InterestedArea() {
		this.geo_ID = 0;
		this.lat = 0.0;
		this.lon = 0.0;
		this.name = "";
		this.state = "";
		this.countryCode = "";
	}

	/**
	 * Constructor with parameters:
	 * @param geo_ID DB key identifying the area
	 * @param lat latitude of the area
	 * @param lon longitude of the area
	 * @param name name of the area
	 * @param state state the area is in
	 * @param countryCode code of the country the area is in
	 */
	public InterestedArea(int geo_ID, double lat, double lon, String name, String state,
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