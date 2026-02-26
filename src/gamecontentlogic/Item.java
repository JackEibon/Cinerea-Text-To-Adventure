package gamecontentlogic;
/*
 * Author: Eibon
 * added in class of 24/2/2026 at 0955 from New Cinerea Netbeans Project
 * Resume: This is meant to handle most if not all Item Logic
 * status: To be modded for new project expectations
 * */
import java.util.List;
import java.util.Map;

public class Item implements Target{
    private int id;
    private String name;
    private String type;
    private String description;
    private List<String> tags;
    private Map<String, Integer> effects;
    private CraftingRecipe crafting;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Map getEffects() {
        return effects;
    }

    public CraftingRecipe getCrafting() {
        return crafting;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEffects(Map effects) {
        this.effects = effects;
    }

    public void setCrafting(CraftingRecipe crafting) {
        this.crafting = crafting;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
    
    public Boolean hasTag(String saidtag) {
        return tags.contains(saidtag);
    }
    @Override
    public void applyEffect(String effect) {
        System.out.println("Item " + name + " is affected by " + effect);}  
}
class CraftingRecipe {
    private String ingredient,process,result;

    public String getIngredient() {
        return ingredient;
    }

    public String getResult() {
        return result;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
    
}
