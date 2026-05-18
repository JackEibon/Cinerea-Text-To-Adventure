package models;

public class User {
	private int id;
	
	private String nickname;
	private String email;
	private String gem;
	private String weapon;
	private String elements;
	private String password;
	private String role;
	
	public User() {
		
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User(String nickname, String email, String gem, String weapon, String elements, String role) {
		this.nickname = nickname;
		this.email = email;
		this.gem = gem;
		this.weapon = weapon;
		this.elements = elements;
		this.role = role;
	}
	
	public User(int id, String nickname, String email, String gem, String weapon, String elements, String role) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.gem = gem;
		this.weapon = weapon;
		this.elements = elements;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}
	

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGem() {
		return gem;
	}

	public void setGem(String gem) {
		this.gem = gem;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	
	public String getElements() {
		return elements;
	}

	public void setElements(String elements) {
		this.elements = elements;
	}
	
	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Nickname: " + nickname + 
				"\nEmail: " + email + 
				"\nGem: " + gem + 
				"\nWeapon: " + weapon + 
				"\nelements:\n" + elements;
	}
	
	/*public String toCsv() {
		return nickname + "," +
				email + "," +
				gem + "," +
				weapon + "," +
				elements;
	}
	
	public static User fromCsv(String userData) {
		String data[] = userData.split(",");
		String nickname = data[0];
		String email = data[1];
		String gem = data[2];
		String weapon = data[3];
		String elements = data[4];
		
		return new User(nickname, email, gem, weapon, elements);
	}*/
	
}