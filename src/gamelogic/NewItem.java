package gamelogic;

public class NewItem {

	public static Item nothing() {
		return new Item(0, // id
				"Nothing", // command name
				"", // full name
				""// description
		);
	}

	public static Item sword() {
		Item sword = new Item(40, // id
				"sword", // command name
				"Rusted Sword", // full name
				"A sword eaten by time and air."// description
		);
		
		sword.getTags().add("sharp");
		sword.getTags().add("rusted");
		sword.getTags().add("weapon");
		sword.setImagePath("C:\\Users\\jacke\\Desktop\\Eclipse Workspace\\Cinerea-Text-To-Adventure\\src\\assets\\sprites\\item\\item40.gif");
		
		return sword;
	}
}
