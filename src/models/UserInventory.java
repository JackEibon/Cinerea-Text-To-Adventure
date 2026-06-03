package models;

public class UserInventory {

	private int idInventory;
	private int idUser;
	private int idItem;
	private int quantity;
	
	private String nickname;
	private String itemName;

	public UserInventory() {

	}

	public UserInventory(int idUser, int idItem, int quantity) {
		this.idUser = idUser;
		this.idItem = idItem;
		this.quantity = quantity;
	}

	public UserInventory(int idInventory, int idUser, int idItem, int quantity) {
		this.idInventory = idInventory;
		this.idUser = idUser;
		this.idItem = idItem;
		this.quantity = quantity;
	}

	public int getIdInventory() {
		return idInventory;
	}

	public void setIdInventory(int idInventory) {
		this.idInventory = idInventory;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}