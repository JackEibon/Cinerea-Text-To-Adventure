package gamelogic;

import java.util.ArrayList;
import gamelogic.Item;
import java.util.List;

public class CharSheet {

	private String name, location;
	private boolean alive = true, player = false, resting = false, blessed = false, enchanted = false, retreating = false;
	private int charId, health = 100, maxHealth = 100, energy = 100, maxEnergy = 100, recovery = 5, aim = 0, dodge = 0, tired = 0, bleed = 0, aroma = 0;
	private String description = "";
	private List<Item> inventory = new ArrayList<>();
	private List<String> statusEffects = new ArrayList<>(), knownWords = new ArrayList<>();

	public CharSheet() {
	}

	public CharSheet(String name) {
		this.name = name;
	}

	public CharSheet(String name, boolean isPlayer) {
		this.name = name;
		this.player = isPlayer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCharId() {
		return charId;
	}

	public void setCharId(int charId) {
		this.charId = charId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descript) {
		this.description = descript;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isPlayer() {
		return player;
	}

	public void setPlayer(boolean player) {
		this.player = player;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		if (this.health > maxHealth) {
			this.health = maxHealth;
		}
		if (this.health <= 0) {
			this.health = 0;
			alive = false;
		}
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void addHealth(int amount) {
		setHealth(this.health + amount);
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
		if (this.energy > maxEnergy) {
			this.energy = maxEnergy;
		}
		if (this.energy < 0) {
			this.energy = 0;
			tired++;
		}
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public int getRecovery() {
		return recovery;
	}

	public void setRecovery(int recovery) {
		this.recovery = recovery;
	}

	public void recoverEnergy() {
		setEnergy(energy + (recovery - tired));
	}

	public void useEnergy(int amount) {
		setEnergy(energy - amount);
	}

	public int getAim() {
		return aim;
	}

	public void setAim(int aim) {
		this.aim = aim;
	}

	public void addAim(int amount) {
		this.aim += amount;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public void addDodge(int amount) {
		this.dodge += amount;
	}

	public int getTired() {
		return tired;
	}

	public void setTired(int tired) {
		this.tired = tired;
		if (this.tired < 0) {
			this.tired = 0;
		}
	}

	public void addTired(int amount) {
		setTired(tired + amount);
	}

	public int getBleed() {
		return bleed;
	}

	public void setBleed(int bleed) {
		this.bleed = bleed;
		if (this.bleed < 0) {
			this.bleed = 0;
		}
	}

	public void addBleed(int amount) {
		setBleed(bleed + amount);
	}

	public int getAroma() {
		return aroma;
	}

	public void setAroma(int aroma) {
		this.aroma = aroma;
		if (this.aroma < 0) {
			this.aroma = 0;
		}
	}

	public void addAroma(int amount) {
		setAroma(aroma + amount);
	}

	public boolean isResting() {
		return resting;
	}

	public void setResting(boolean resting) {
		this.resting = resting;
	}

	public boolean isRetreating() {
		return retreating;
	}

	public void setRetreating(boolean retreating) {
		this.retreating = retreating;
	}

	public boolean isBlessed() {
		return blessed;
	}

	public void setBlessed(boolean blessed) {
		this.blessed = blessed;
	}

	public boolean isEnchanted() {
		return enchanted;
	}

	public void setEnchanted(boolean enchanted) {
		this.enchanted = enchanted;
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}

	public boolean addItem(Item i) {
		return inventory.add(i);
	}

	public boolean removeItem(Item i) {
		return inventory.remove(i);
	}

	public boolean hasItem(Item i) {
		return inventory.contains(i);
	}

	public boolean hasItem(String i) {
		for (Item ite : inventory) {
			if (ite.getName().equalsIgnoreCase(i))
				return true;
		}
		return false;
	}

	public Item thisItem(String i) {
		for (Item ite : inventory) {
			if (ite.getName().equalsIgnoreCase(i))
				return ite;
		}
		return null;
	}

	public boolean removeItem(String i) {
		for (Item ite : inventory) {
			if (ite.getName().equalsIgnoreCase(i))
				return removeItem(ite);
		}
		return false;
	}

	public List<String> getStatusEffects() {
		return statusEffects;
	}

	public void setStatusEffects(List<String> statusEffects) {
		this.statusEffects = statusEffects;
	}

	public boolean addStatusEffect(String effect) {
		if (statusEffects.contains(effect)) {
			return false;
		}
		statusEffects.add(effect);
		return true;
	}

	public boolean removeStatusEffect(String effect) {
		return statusEffects.remove(effect);
	}

	public List<String> getKnownWords() {
		return knownWords;
	}

	public void setKnownWords(List<String> knownWords) {
		this.knownWords = knownWords;
	}

	public boolean learnWord(String word) {
		if (knownWords.contains(word)) {
			return false;
		}
		knownWords.add(word);
		return true;
	}

	public void applyEffect(String effect) {
		switch (effect) {
		case "bleed": {
			addBleed(1);
			break;
		}
		case "tired": {
			addTired(1);
			break;
		}
		case "heal": {
			addHealth(10);
			break;
		}
		case "rest": {
			recoverEnergy();
			break;
		}
		default: {
			break;
		}
		}
	}
}