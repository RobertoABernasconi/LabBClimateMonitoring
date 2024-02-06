package labBClimateMonitoringServer;

import java.io.Serializable;

/**
 * Serializable lass that models a monitoring centre
 * @author Roberto Alfonso Bernasconi
 * @author Andrea Magliocca
 */

public class MonitoringCentre implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String centreName;
	private int area;
	private String address;
	private int streetNumber;
	private int postalCode;
	private String city;
	private String province;
	
	/**
	 * Empty constructor
	 */
	public MonitoringCentre() {
		this.centreName = "";
		this.area = 0;
		this.address = "";
		this.streetNumber = 0;
		this.postalCode = 0;
		this.city = "";
		this.province = "";
	}

	/**
	 * Contructor with parameters:
	 * @param centreName Name of the centre
	 * @param area Number corresponding to the area ID
	 * @param address Address with no street number
	 * @param streetNumber Street number of the address
	 * @param postalCode Postal code of the centre
	 * @param city City the centre is in
	 * @param province Province the centre is in
	 */
	public MonitoringCentre(String centreName, int area, String address, int streetNumber, int postalCode, String city,
			String province) {
		this.centreName = centreName;
		this.area = area;
		this.address = address;
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.province = province;
	}

	public synchronized String getCentreName() {
		return centreName;
	}

	public synchronized void setCentreName(String nomeCentro) {
		this.centreName = nomeCentro;
	}

	public synchronized int getArea() {
		return this.area;
	}

	public synchronized void setArea(int area) {
		this.area = area;
	}

	public synchronized String getAddress() {
		return address;
	}

	public synchronized void setAddress(String address) {
		this.address = address;
	}

	public synchronized int getStreetNumber() {
		return streetNumber;
	}

	public synchronized void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public synchronized int getPostalCode() {
		return postalCode;
	}

	public synchronized void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public synchronized String getCity() {
		return city;
	}

	public synchronized void setCity(String city) {
		this.city = city;
	}

	public synchronized String getProvince() {
		return province;
	}

	public synchronized void setProvince(String province) {
		this.province = province;
	}
}
