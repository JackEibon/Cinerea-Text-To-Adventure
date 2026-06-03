package models;

public class Status {
	
	private int idStatus;
	private String effectName;
	private int duration;
	
	public Status() {
		
	}
	
	public Status(String effectName, int duration) {
		this.effectName = effectName;
		this.duration = duration;
	}
	
	public Status(int idStatus, String effectName, int duration) {
		this.idStatus = idStatus;
		this.effectName = effectName;
		this.duration = duration;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getEffectName() {
		return effectName;
	}

	public void setEffectName(String effectName) {
		this.effectName = effectName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
