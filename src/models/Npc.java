package models;

public class Npc {
	
	private int idNpc;
	private String npcName;
	private String npcRole;
	private String location;
	
	public Npc() {
		
	}
	
	public Npc(String npcName, String npcRole, String location) {
		this.npcName = npcName;
		this.npcRole = npcRole;
		this.location = location;
	}
	
	public Npc(int idNpc, String npcName, String npcRole, String location) {
		this.idNpc = idNpc;
		this.npcName = npcName;
		this.npcRole = npcRole;
		this.location = location;
	}

	public int getIdNpc() {
		return idNpc;
	}

	public void setIdNpc(int idNpc) {
		this.idNpc = idNpc;
	}

	public String getNpcName() {
		return npcName;
	}

	public void setNpcName(String npcName) {
		this.npcName = npcName;
	}

	public String getNpcRole() {
		return npcRole;
	}

	public void setNpcRole(String npcRole) {
		this.npcRole = npcRole;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}