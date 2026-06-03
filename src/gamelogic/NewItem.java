package gamelogic;

import java.util.ArrayList;
import java.util.List;

public class NewItem {
	
	public static Item nothing() {return new Item(
				0,//id
				"Nothing",//command name
				"", //full name
				""//description
				);}
		
	
	public static Item sword() {
		Item x=new Item(
				40,//id
				"sword",//command name
				"Rusted Sword", //full name
				"rustedSword",
				"A sword eaten by time and air.",//description
				 new ArrayList<>(List.of(
				                "rusted",
				                "weapon",
				                "metal",
				                "sharp")));	
		return x;}
	
	public static Item spear() {
		Item x=new Item(
				43,//id
				"spear",//command name
				"Rusted Spear", //full name
				"rustedSpear",
				"A spear eaten by time and air.",//description
				 new ArrayList<>(List.of(
				                "rusted",
				                "weapon",
				                "long",
				                "sharp")));	
		return x;}
	
	public static Item gun() {
		Item x=new Item(
				46,//id
				"gun",//command name
				"Rusted gun", //full name
				"rustedGun",
				"A gun eaten by time and air.",//description
				 new ArrayList<>(List.of(
				                "rusted",
				                "weapon",
				                "firearm",
				                "projectile")));	
		return x;}
		

}
