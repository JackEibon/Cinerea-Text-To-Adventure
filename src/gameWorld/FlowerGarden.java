package gameWorld;

import java.io.IOException;

import gamelogic.NewItem;

public class FlowerGarden extends NodeT1 {

	public FlowerGarden(int scene) {
		super(scene);

		setBiome("forest");
		setScene(scene);
		setMainDescription("The flowers smell quite nice, surely nothing bad can happen here");
		setDistantDescription("A flowery garden grows");
		
		try {
			addItem(NewItem.fromDB("garlic flower"),"there is a set of flowered garlic seems ready for the take");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("no garlic");
		}


	}
	

}
