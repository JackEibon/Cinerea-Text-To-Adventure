package models;

public class Config {

	private int idConfig;
	private int idUser;
	private String settingKey;
	private String settingValue;
	
	private String nickname;

	public Config() {

	}

	public Config(int idUser, String settingKey, String settingValue) {
		this.idUser = idUser;
		this.settingKey = settingKey;
		this.settingValue = settingValue;
	}

	public Config(int idConfig, int idUser, String settingKey, String settingValue) {
		this.idConfig = idConfig;
		this.idUser = idUser;
		this.settingKey = settingKey;
		this.settingValue = settingValue;
	}

	public int getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(int idConfig) {
		this.idConfig = idConfig;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getSettingKey() {
		return settingKey;
	}

	public void setSettingKey(String settingKey) {
		this.settingKey = settingKey;
	}

	public String getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}