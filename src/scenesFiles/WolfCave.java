package scenesFiles;

public class WolfCave extends Cave{
	public WolfCave(int scene) {
		super(scene);
		setWolfBeenHere(999);
		setScene(scene);
		setMainDescription("The cave is dark, the air is cold, the smell is of cursed iron");
		setDistantDescription("A dark, sinister, cold cave");
	}


}
