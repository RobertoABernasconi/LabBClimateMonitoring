package labBClimateMonitoringClient;

import java.io.Serializable;

public class ClimateParameters implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private MonitoringCentre nameCM;
	private Coordinates interestedArea;
	private String date;
	private String climateCategory;
	private String explanation;
	private int score;
	private String notes;
	
	public ClimateParameters() {
		super();
		this.nameCM = null;
		this.interestedArea = null;
		this.date = "";
		this.climateCategory = "";
		this.explanation = "";
		this.score = 0;
		this.notes = "";
	}
	
	public ClimateParameters(MonitoringCentre nameCM, Coordinates interestedArea, String date,
			String climateCategory, String explanation, int score, String notes) {
		super();
		this.nameCM = nameCM;
		this.interestedArea = interestedArea;
		this.date = date;
		this.climateCategory = climateCategory;
		this.explanation = explanation;
		this.score = score;
		this.notes = notes;
	}

	public synchronized MonitoringCentre getNameCM() {
		return nameCM;
	}

	public synchronized void setNameCM(MonitoringCentre nameCM) {
		this.nameCM = nameCM;
	}

	public synchronized Coordinates getInterestedArea() {
		return interestedArea;
	}

	public synchronized void setInterestedArea(Coordinates interestedArea) {
		this.interestedArea = interestedArea;
	}

	public synchronized String getDate() {
		return date;
	}

	public synchronized void setDate(String date) {
		this.date = date;
	}

	public synchronized String getClimateCategory() {
		return climateCategory;
	}

	public synchronized void setClimateCategory(String climateCategory) {
		this.climateCategory = climateCategory;
	}

	public synchronized String getExplanation() {
		return explanation;
	}

	public synchronized void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public synchronized int getScore() {
		return score;
	}

	public synchronized void setScore(int score) {
		this.score = score;
	}

	public synchronized String getNotes() {
		return notes;
	}

	public synchronized void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
	
}
