package labBClimateMonitoringClient;

import java.io.*;

public class InterestedArea {
	
	private static final long serialVersionUID = 1;
	
	private double latitudine;
	private double longitudine;
	public InterestedArea() {
		super();
		latitudine = 0.0;
		longitudine = 0.0;
	}
	public InterestedArea(double latitudine, double longitudine) {
		super();
		this.latitudine = latitudine;
		this.longitudine = longitudine;
	}
	public double getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(double latitudine) {
		this.latitudine = latitudine;
	}
	public double getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(double longitudine) {
		this.longitudine = longitudine;
	}
	
	
	
	

}
