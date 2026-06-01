package gamelogic;

public class NewItem {
	
	public static Item sword() {
		return new Item(
				1,//id
				"sword",//command name
				"Rusted Sword", //full name
				"A sword eaten by time and air."//description
				);	

	}
}
