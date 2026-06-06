package gameWorld;

import java.io.IOException;

import gamelogic.NewItem;

public class Lake extends NodeT1 {

	public Lake(int scene) {
		super(scene);

		setBiome("forest");
		setScene(scene);
		setMainDescription("An enourmous lake receives a gentle river and a small cascade");
		setDistantDescription("Water flowing can be heard");
		try {
			addItem(NewItem.fromDB("silver cross"),"there is a glittering silver cross on the surface of the lake");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("no cross");
		}

	}

}
