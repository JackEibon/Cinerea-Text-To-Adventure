package models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Style;

public class Item {
	private int id;
	private String name, description;
	private List<String> tags = new ArrayList<>();
	

	public Item(int iD, String name, String description,List<String> tags ) {
		this.id = iD;
		this.name = name;
		this.description = description;
		this.tags=tags;
	}

	public Item(int iD, String name, String description,String taglist ) {
		this.id = iD;
		this.name = name;
		this.description = description;
		String[] tagGroup = taglist.split(",");
		for (String ta : tagGroup) {
			if (ta.isBlank() || ta.equals("")||ta.equals(",")) continue;
			else this.tags.add(ta);
			}
	}
	
	public Item(String name, String description,String taglist ) {
		this.name = name;
		this.description = description;
		String[] tagGroup = taglist.split(",");
		for (String ta : tagGroup) {
			if (ta.isBlank() || ta.equals("")||ta.equals(",")) continue;
			else this.tags.add(ta);
			}
	}
	
	public boolean addTag(String tag) {
		return this.tags.add(tag);
	}
	
	public boolean replaceTag(String replaced,String newTag) {
		if(this.tags.remove(replaced)) 
			return this.tags.add(newTag);
		else return false;
	}
	public boolean removeTag(String tag) {
		return this.tags.remove(tag) ;
	}


	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public void setTags(String taglist) {
		String[] tagGroup = taglist.split(",");
		List<String> aux = null;
		for (String ta : tagGroup) {
			if (ta.isBlank() || ta.equals("")||ta.equals(",")) continue;
			else {aux.add(ta);}
			}
		this.tags= aux;
	}

	public List<String> getTags() {
		return tags;
	}
	public String getTagList() {
		String x="";
		for (String ta : this.tags) {
			x+=ta;
			x+=",";
			}
		return x;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}



/*Old Item Model
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
}*/