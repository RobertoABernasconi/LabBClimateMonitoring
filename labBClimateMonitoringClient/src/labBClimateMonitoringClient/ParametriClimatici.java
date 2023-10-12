package labBClimateMonitoringClient;

import java.io.Serializable;

public class ParametriClimatici implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private CentriMonitoraggio nameCM;
	private CoordinateMonitoraggio interestedArea;
	private String dataRilevazione;
	private String climateCategory;
	private String explanation;
	private int score;
	private String notes;
	
	public ParametriClimatici() {
		super();
		this.nameCM = null;
		this.interestedArea = null;
		this.dataRilevazione = "";
		this.climateCategory = "";
		this.explanation = "";
		this.score = 0;
		this.notes = "";
	}
	
	public ParametriClimatici(CentriMonitoraggio nameCM, CoordinateMonitoraggio interestedArea, String dataRilevazione,
			String climateCategory, String explanation, int score, String notes) {
		super();
		this.nameCM = nameCM;
		this.interestedArea = interestedArea;
		this.dataRilevazione = dataRilevazione;
		this.climateCategory = climateCategory;
		this.explanation = explanation;
		this.score = score;
		this.notes = notes;
	}

	public synchronized CentriMonitoraggio getNameCM() {
		return nameCM;
	}

	public synchronized void setNameCM(CentriMonitoraggio nameCM) {
		this.nameCM = nameCM;
	}

	public synchronized CoordinateMonitoraggio getInterestedArea() {
		return interestedArea;
	}

	public synchronized void setInterestedArea(CoordinateMonitoraggio interestedArea) {
		this.interestedArea = interestedArea;
	}

	public synchronized String getDataRilevazione() {
		return dataRilevazione;
	}

	public synchronized void setDataRilevazione(String dataRilevazione) {
		this.dataRilevazione = dataRilevazione;
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
