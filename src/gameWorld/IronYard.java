package gameWorld;

import java.io.IOException;

import gamelogic.NewItem;
import utils.Session;
import models.User;

public class IronYard extends NodeT1 {

	public IronYard(int scene) {

		super(scene);
		setBiome("meadow");
		setScene(scene);
		setMainDescription(
				"War leaves behind few things of value, other than memories, blood, teachings and the weapon one owns.");
		setDistantDescription("Some rusty and broken weapons start showing up on this path");

		User player = Session.getCurrentUser();

		if (player != null) {
				try {
					if(addItem(NewItem.fromDB("rusted " + player.getWeapon().toLowerCase()),
							"\nA "+ "rusted "+ player.getWeapon().toLowerCase() + " remains kind of usable" )) 
						System.out.println( player.getWeapon().toLowerCase() +" added");
					else System.out.println( player.getWeapon().toLowerCase() +" NOT added");
				} catch (IOException e) {
					addItem(NewItem.sword(), "\nA lone sword rests here.");
					//e.printStackTrace();
				}
	
		} else {
			addItem(NewItem.sword(), "\nA lone sword rests here.");
		}
	}

}
