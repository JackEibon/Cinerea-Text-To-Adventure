package gameWorld;

public class Blacksmith extends NodeT1{
	
	

	

	public Blacksmith(int scene) {
		super(scene); 

		setBiome("forest");
		setScene(scene);
		setMainDescription("A blacksmith pounds hard against iron ore, he smells of hard, hard work, he says \"for you, silvering ghost"
				+ " i will offer a fine good work"
				+ " for little of cost, bring me a spear or sword,"
				+ " i turn silver into mirror dust,"
				+ " and gems unto enchantments forgotten and old"
				+ " from the silvering kin i need no coin\"");
		setDistantDescription("A faint noise of iron pounding");
		setGoDescriptions("");
		setTakeDescriptions("");
		setRestDescriptions("");
		setTalkDescriptions("") ;
		setItemsDescriptions("") ;		
	}

}
