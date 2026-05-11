package scenesFiles;

public class MountainPeak extends NodeT1{
	
	

	

	public MountainPeak(int scene) {
		super(scene); 

		setBiome("Mountain");
		setScene(scene);
		setMainDescription("");
		setDistantDescription("A giant set of boulders and rocks is the base for a giant peak, partitioning heaven ");	
	}

	public MountainPeak(int scene, String mainD, String distantD){
		super(scene);
		setBiome("cave");
		setScene(scene);
		setDescriptions(mainD, distantD);}
}

