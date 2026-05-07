package scenesFiles;

public class Cave extends NodeT1{
	
	

	

	public Cave(int scene) {
		super(scene);
		setBiome("cave");
		setScene(scene);

		
	}

	public Cave(int scene, String mainD, String distantD){
		super(scene);
		setBiome("cave");
		setScene(scene);
		setDescriptions(mainD, distantD);}


}
