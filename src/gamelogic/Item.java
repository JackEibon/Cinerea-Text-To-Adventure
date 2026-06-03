package gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Item {

	private int id;
    private String fullName,name, description, codename;
    private List<String> tags=new ArrayList<>();
    /*
     * fullName is displayed name if nessesary, i dont think we`ll reach those needs but
     * "Silver Sword" etc.
     * name is name of the item, nessesary for nouns lexicon-
     * 
     * */
    
    public Item(int iD, String name, String fullname, String description) {
    	this.id=iD;this.name=name;this.description=description;this.fullName=fullname;}
    
    public Item(int iD, String name, String fullname, String coden, String description,List<String> characteristics) {
    	this.id=iD;this.name=name;this.description=description;this.fullName=fullname;this.codename=coden; 
    	this.tags=characteristics;}
    
    public Item(String name, String description, String fullName) {
    	this.name=name;this.description=description;this.fullName=fullName;}
    
    public Item(String name) {this.name=name;this.description="";}

    public Item(String name,String description) {this.name=name;this.description=description;}
    public void setCodename(String p) {this.codename=p;}
    

    public String getCodename() {
		return codename;
	}

	public void setTags(List<String> tags) {this.tags = tags;}

	public List<String> getTags(){return tags;}
    public String getName() {return name;}
    public void setName(String name) {this.name=name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description=description;}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getFullName() {return fullName;}
	public void setFullName(String fullName) {this.fullName = fullName;}
}


/*Old Item Class*/

/*
 * 
 * package cinerea;

import java.util.Map;

public class Item {
    private int id;
    private String name;
    private String type;
    private String description;
    private Map<String, Integer> effects;
    private CraftingRecipe crafting;

    public int getId() {
        return id;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}

/* Old Item Class */

/*
 * 
 * package cinerea;
 * 
 * import java.util.Map;
 * 
 * public class Item { private int id; private String name; private String type;
 * private String description; private Map<String, Integer> effects; private
 * CraftingRecipe crafting;
 * 
 * public int getId() { return id; }
 * 
 * public String getName() { return name; }
 * 
 * public String getType() { return type; }
 * 
 * public String getDescription() { return description; }
 * 
 * public Map getEffects() { return effects; }
 * 
 * public CraftingRecipe getCrafting() { return crafting; }
 * 
 * public void setId(int id) { this.id = id; }
 * 
 * public void setName(String name) { this.name = name; }
 * 
 * public void setType(String type) { this.type = type; }
 * 
 * public void setDescription(String description) { this.description =
 * description; }
 * 
 * public void setEffects(Map effects) { this.effects = effects; }
 * 
 * public void setCrafting(CraftingRecipe crafting) { this.crafting = crafting;
 * }
 * 
 * }
 * 
 * class CraftingRecipe { private String ingredient,process,result;
 * 
 * public String getIngredient() { return ingredient; }
 * 
 * public String getResult() { return result; }
 * 
 * public void setIngredient(String ingredient) { this.ingredient = ingredient;
 * }
 * 
 * public void setResult(String result) { this.result = result; }
 * 
 * public String getProcess() { return process; }
 * 
 * public void setProcess(String process) { this.process = process; }
 * 
 * }
 * 
 * 
 * 
 */
