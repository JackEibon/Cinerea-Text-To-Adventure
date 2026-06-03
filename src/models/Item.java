package models;

public class Item {
	
	private int idItem;
	private String itemName;
	private String itemType;
	private String description;
	
	public Item() {
		
	}
	
	public Item(String itemName, String itemType, String description) {
		this.itemName = itemName;
		this.itemType = itemType;
		this.description = description;
	}
	
	public Item(int idItem, String itemName, String itemType, String description) {
		this.idItem = idItem;
		this.itemName = itemName;
		this.itemType = itemType;
		this.description = description;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}