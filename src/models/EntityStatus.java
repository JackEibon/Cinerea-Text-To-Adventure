package models;

public class EntityStatus {
	
	private int idEntityStatus;
	private Integer idUser; 
	private Integer idNpc;
	private int idStatusEffect;
	
	public EntityStatus() {
		
	}
	
	public EntityStatus(Integer idUser, Integer idNpc, int idStatusEffect) {
		this.idUser = idUser;
		this.idNpc = idNpc;
		this.idStatusEffect = idStatusEffect;
	}
	
	public EntityStatus(int idEntityStatus, Integer idUser, Integer idNpc, int idStatusEffect) {
		this.idEntityStatus = idEntityStatus;
		this.idUser = idUser;
		this.idNpc = idNpc;
		this.idStatusEffect = idStatusEffect;
	}

	public int getIdEntityStatus() {
		return idEntityStatus;
	}

	public void setIdEntityStatus(int idEntityStatus) {
		this.idEntityStatus = idEntityStatus;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdNpc() {
		return idNpc;
	}

	public void setIdNpc(Integer idNpc) {
		this.idNpc = idNpc;
	}

	public int getIdStatusEffect() {
		return idStatusEffect;
	}

	public void setIdStatusEffect(int idStatusEffect) {
		this.idStatusEffect = idStatusEffect;
	}
}