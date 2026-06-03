package gameWorld;

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
			switch (player.getWeapon().toLowerCase()) {
			case "sword":
				addItem(NewItem.sword(), "\nA battered sword remains among the grasslands.");
				break;
			case "spear":
				addItem(NewItem.spear(), "\nA long spear sticks out of the mud.");
				break;

			case "gun":// addItem(NewItem.gun(),
				// "\nAn old firearm lies half buried in the dirt.");
				break;
			default:
				addItem(NewItem.sword(), "\nA lone sword rests here.");
			}
		} else {
			addItem(NewItem.sword(), "\nA lone sword rests here.");
		}
	}

}
