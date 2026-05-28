package gameWorld;

import java.util.*;

import gamelogic.*;
/*
 * WorldGraph
 * V1 created 1131 27/5/2026, Finished as of 1341 27/5/2026
 * Description:Main world graph controller.
 * 
 * Responsibilities:
 * - Creates all scenes
 * - Connects scenes
 * - Stores world references
 * - Gives utility methods for movement and navigation
 * 
 * -This class is NOT procedural generation yet.
 * -This is a handcrafted world graph.
 * -This class still doesnt handle minimap data, but supposedly will
 * 
 * Future systems:
 * - procedural generation
 * - fog of war
 * - world states
 * - dynamic NPC and Enemy movement
 * - pathfinding
 * - save/load (maybe?)
 * 
 * OldGraph was the previous code meant for this, but organization and commenting was turning tedious, so i remake
 * the structure from 0 and made it with a clearer idea, and the ideal to comment almost everything.
 */

public class WorldGraph {
    /* =========================================================
     * WORLD VARIABLES
     * ========================================================= */
    //RNG, if needed be.
	GameMaster worldDie = new GameMaster();
    //Current player node
    private NodeT1 currentNode;
    /*World registry
     *Key = Scene Id
     *Value = Scene reference
     */
    private HashMap<Integer, NodeT1> worldNodes = new HashMap<>();

    /*
     * Coordinates for minimap (Placeholder, i dont think i will make them like this).
     * Key = Node
     * Value = int[]{x,y}
     */
    private HashMap<NodeT1, int[]> minimapCoordinates = new HashMap<>();
    /* =========================================================
     * SCENE REFERENCES
     * ========================================================= */

    /*
    WeaponYard weaponYard: place where player finds the weapon
    FlowerGarden flowerGarden: place where the player can rest, and get a different aroma
    WoodCottage woodCottage: not in use
    GarlicPalace garlicPalace: not in use
    SkyGazingPlane skyGazingPlane: not in use
    SilverLake silverLake: place where the river falls
    ForestHeart forestHeart: place between lake, valley and mountains
    */
    
    //valley and forest
    private SilverCrater silverCrater;
    private GreatForest greatForest;
    private Lake silverLake;
    private Blacksmith blacksmith;
    private IronYard oldBattlefield;
    private FlowerGarden flowerGarden;
    //caves
    private Cave entrance;
    private Cave tunnel;
    private Cave mountainHole;
    private Cave howlingHole;
    private Cave gemRoom;
    //mountains
    private MountainPeak heavenPeak;
    private MountainPeak earthenPeak;
    private Bridge bridge;
    //the wolf cave
    private WolfCave wolfCave;
    /* =========================================================
     * CONSTRUCTOR
     * ========================================================= */
    public WorldGraph() {
        //creates Scenes
    	Genesis();
    	//as requested by partner, i accomodated things by function sections to make it clearer. Also is easier to scale and move things around this way
        connectForestAndValley();
        connectMountains();
        connectCaves();
        //
        registerWorldNodes();
        buildMinimapCoordinates();
        currentNode = silverCrater;
    }
    //Creates every scene and gives it an iD;
    private void Genesis() {int x = 0;
        silverCrater = new SilverCrater(x);x++;
        greatForest = new GreatForest(x);x++;
        silverLake = new Lake(x);x++;
        blacksmith = new Blacksmith(x);x++;
        oldBattlefield = new IronYard(x);x++;
        flowerGarden = new FlowerGarden(x);x++;
        entrance = new Cave(x,
                "the cave is moist and cold, it grows bigger into the darkness",
                "Be it an entrance or an exit, here is the end of a cave");x++;
        tunnel = new Cave(x,
                "the cave is moist and cold, the tunnel grows unto different paths",
                "Darkness grows into the stones");x++;
        mountainHole = new Cave(x,
                "the light enters shining through, but you cant reach the crackling dirt at the roof",
                "A hole shines light unto a closed cave");x++;
        howlingHole = new Cave(x,
                "the light enters shining through, but you cant reach the crackling dirt at the roof",
                "A hole shines light unto a closed cave");x++;
        gemRoom = new Cave(x,
                "An otherwise empty room is set to keep a forgotten treasure",
                "A glittering path seaths droplets");x++;
        heavenPeak = new MountainPeak(x,
                "At this top, one sits at the side of clouds and above the world. A river sits near, and one feels lonely and cold",
                "A stone peak partitions heavens, but is within reach");x++;
        bridge = new Bridge(x);x++;
        earthenPeak = new MountainPeak(x,
                "At this top, one sits above the forest. A river sits near, and is surrounded by ever taller mountains",
                "A stone peak is within reach");x++;
        wolfCave = new WolfCave(x);x++;
    }
    //FOREST & VALLEY
    private void connectForestAndValley() {
        silverCrater.setaN(1, greatForest, 2, "walking along a path");
        silverCrater.setaN(2, blacksmith, 1, "walking towards the noise");
        silverCrater.setaN(3, flowerGarden, 1, "walking across the garden");
        silverCrater.setGoDescriptions();

        greatForest.setaN(1, silverCrater, 2, "walking towards the shimmer");
        greatForest.setaN(2, blacksmith, 2, "walking towards the noise");
        greatForest.setaN(3, silverLake, 2, "walking towards the water");
        greatForest.setaN(4, earthenPeak, 10, "climbing hard");
        greatForest.setGoDescriptions();

        blacksmith.setaN(1, greatForest, 2, "walking along a path");
        blacksmith.setaN(2, oldBattlefield, 1, "walking towards the rust");
        blacksmith.setaN(3, silverCrater, 1, "walking towards the shimmer");
        blacksmith.setGoDescriptions();

        silverLake.setaN(1, earthenPeak, 10, "climbing the earthen rocks");
        silverLake.setaN(2, heavenPeak, 12, "climbing towards the high tall peak");
        silverLake.setaN(3, greatForest, 1, "walking across the trees");
        silverLake.setaN(4, flowerGarden, 1, "following the water");
        silverLake.setGoDescriptions();

        flowerGarden.setaN(1, greatForest, 2, "walking across the trees");
        flowerGarden.setaN(2, silverCrater, 1, "following the water");
        flowerGarden.setGoDescriptions();

        oldBattlefield.setaN(1, entrance, 1, "walking towards the cave");
        oldBattlefield.setaN(2, blacksmith, 1, "walking towards the noise");
        oldBattlefield.setaN(3, earthenPeak, 5, "walking up the mountain");
        oldBattlefield.setGoDescriptions();
    }
    //MOUNTAINS
    private void connectMountains() {
        earthenPeak.setaN(1, heavenPeak, 7, "walking up the mountain the long way");
        earthenPeak.setaN(2, bridge, 1, "walking across");
        earthenPeak.setaN(3, greatForest, 5, "jumping, falling sliding down the slope");
        earthenPeak.setaN(4, oldBattlefield, 3, "walking down the mountain");
        earthenPeak.setaN(5, silverLake, 2, "swimming gently down the river");
        earthenPeak.setGoDescriptions();

        bridge.setaN(1, earthenPeak, 1, "walking down");
        bridge.setaN(2, heavenPeak, 1, "walking up");
        bridge.setaN(3, silverLake, 4, "falling into the river and swimming downstream");
        bridge.setGoDescriptions();

        heavenPeak.setaN(1, earthenPeak, 5, "walking the long way down");
        heavenPeak.setaN(2, bridge, 1, "walking across the bridge");
        heavenPeak.setaN(3, mountainHole, 3, "falling into the mountain hole");
        heavenPeak.setaN(4, silverLake, 3, "swimming down as gently as you can");
        heavenPeak.setaN(5, wolfCave, 2, "walking towards the dark cave");
        heavenPeak.setGoDescriptions();
    }
    //CAVE SYSTEM
    private void connectCaves() {
        mountainHole.setaN(1, tunnel, 1, "walking deeper into the cave");
        mountainHole.setGoDescriptions();

        entrance.setaN(1, oldBattlefield, 1, "walking out towards the rust");
        entrance.setaN(2, tunnel, 3, "walking up through the cave");
        entrance.setGoDescriptions();

        tunnel.setaN(1, mountainHole, 1, "walking towards the faint light");
        tunnel.setaN(2, entrance, 2, "walking down towards the exit");
        tunnel.setaN(3, gemRoom, 2, "walking towards the glittering walls");
        tunnel.setaN(4, howlingHole, 3, "walking towards the eerie howls");
        tunnel.setGoDescriptions();

        gemRoom.setaN(1, tunnel, 1, "walking back through the tunnel");
        gemRoom.setaN(2, howlingHole, 2, "walking towards the distant howls");
        gemRoom.setGoDescriptions();

        howlingHole.setaN(1, wolfCave, 3, "falling into the wolf den");
        howlingHole.setaN(2, tunnel, 1, "walking down through the tunnel");
        howlingHole.setaN(3, gemRoom, 2, "walking towards the glittering walls");
        howlingHole.setGoDescriptions();

        wolfCave.setaN(2, heavenPeak, 3, "walking out unto the mountain peak");
        wolfCave.setGoDescriptions();
    }
    /* =========================================================
     * WORLD REGISTRY (hashmap).
     * ========================================================= */
    private void registerWorldNodes() {
        registerNode(silverCrater);
        registerNode(greatForest);
        registerNode(silverLake);
        registerNode(blacksmith);
        registerNode(oldBattlefield);
        registerNode(flowerGarden);
        registerNode(entrance);
        registerNode(tunnel);
        registerNode(mountainHole);
        registerNode(howlingHole);
        registerNode(gemRoom);
        registerNode(heavenPeak);
        registerNode(earthenPeak);
        registerNode(bridge);
        registerNode(wolfCave);
    }
    //I dont understand HashMap that well T.T ChatGpt, what are you talking about? i did this:
    private void registerNode(NodeT1 node) {worldNodes.put(node.getScene(), node);}
    //instead of whatever the ia told me to. put function is not that new to me, unlike the craziness of other HashMap functions
    /* =========================================================
     * MINIMAP SYSTEM
     * ========================================================= */
    private void buildMinimapCoordinates() {
        minimapCoordinates.put(silverCrater, new int[]{0, 0});
        minimapCoordinates.put(greatForest, new int[]{1, 0});
        minimapCoordinates.put(blacksmith, new int[]{1, 1});
        minimapCoordinates.put(flowerGarden, new int[]{0, -1});
        minimapCoordinates.put(silverLake, new int[]{2, 0});

        minimapCoordinates.put(oldBattlefield, new int[]{2, 1});

        minimapCoordinates.put(earthenPeak, new int[]{3, 0});
        minimapCoordinates.put(bridge, new int[]{4, 0});
        minimapCoordinates.put(heavenPeak, new int[]{5, 0});

        minimapCoordinates.put(entrance, new int[]{2, 2});
        minimapCoordinates.put(tunnel, new int[]{3, 2});
        minimapCoordinates.put(gemRoom, new int[]{4, 2});
        minimapCoordinates.put(howlingHole, new int[]{4, 3});
        minimapCoordinates.put(mountainHole, new int[]{5, 2});

        minimapCoordinates.put(wolfCave, new int[]{6, 1});
    }
    /*
     * Prints a primitive minimap.
     * Future improvements:
     * - fog of war/discovery
     * - icons
     * - colors
     */
    public void printMinimap() {
        System.out.println("\n=== MINIMAP ===\n");
        for (NodeT1 node : minimapCoordinates.keySet()) {
            int[] pos = minimapCoordinates.get(node);
            String marker = "[ ]";
            if (node == currentNode) {marker = "[X]";}
            else if (node.isExplored()) {marker = "[O]";}
            System.out.println(marker+ " Scene "+ node.getScene()+ " -> ("+ pos[0]+ ", "+ pos[1]+ ")");}
        System.out.println();
    }
    /* =========================================================
     * MOVEMENT
     * ========================================================= */
    //placeHolder Print in Console
    public void printNarration(String text) {debugText(text);}
    //Debug
    public void debugText(String text) {System.out.println(text);}
    /*Example:
     * moveTo(1)
     * moves you to path iD 1, not to Node Id 1
     */
    public boolean mayMoveTo(int pathId) {
        NodeT1 nextNode = currentNode.getaN(pathId);
        String message = "";
        if (nextNode == null) {message="\nThere is no path there.";printNarration(message);return false;}
        currentNode = nextNode;currentNode.setExplored(true);return true;}
    //Displays available paths
    public void printPaths() {
    	String message = "\n=== PATHS ===\n";
        for (int i = 1; i <= 8; i++) {
            NodeT1 target = currentNode.getaN(i);
            if (target != null) {
                message+= "\n" +i+". "
                                + currentNode.getaNp(i)
                                + " [Cost "
                                + currentNode.getaNCost(i)
                                + "]";
            }
        }
        printNarration(message);
    }
    //Prints current scene Description.
    public void printCurrentScene() {
        String message= "\n==============================\n" + currentNode.getMainDescription()+"\n==============================\n";
        printNarration(message);
    }
    //Getters & Setters
    public NodeT1 getCurrentNode() {return currentNode;}
    public void setCurrentNode(NodeT1 currentNode) {this.currentNode = currentNode;}
    public HashMap<Integer, NodeT1> getWorldNodes() {return worldNodes;}
    public NodeT1 getNodeByID(int id) {return worldNodes.get(id);}
}

/*Code Graveyard*/

/**OLD GRAPH SYSTEM**/
/*
package gamelogic;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;

import scenesFiles.*;



public class Graph {
*/	
	
	//GameMaster worldDie = new GameMaster(); //GameMaster worldDie; //A random int generator inspired by TTRPGs
	//Each of the scenes:
	
	/* 
	WeaponYard weaponYard*
	FlowerGarden flowerGarden
	WoodCottage woodCottage
	GarlicPalace garlicPalace
	SkyGazingPlane skyGazingPlane
	SilverLake silverLake
	ForestHeart forestHeart
	 
	MountainPath mountainPath,
	MountainPath mountainHigh,
	MountainPath mountainPeak,
	MountainPath mountainRange,
	MountainPath mountainSpring,
	MountainPath mountainCave,
	
	RabbitCave rabbitCave,
	LongCave longCave,
	MineCave mineCave,
	WolfCave wolfCave,
	DungeonCave dungeonCave
	 */
	

	//int 
	//numberPaths=4, //this number decides the quantity of paths that we have available for each Node. It is currently 4, as the other 4 will be leaved for special exits or scripted pathways
	//numberGates= 2, //this number decides maximum quantity of gates for each scene
	//mapSize=5; //this number decides which scenes (by ID) will be on play and which will not. Ex: If it is 4, scenes 001,002,003 and 004 will be on play
	

	
	//NodeT1  
	/*
	weaponYard,
	flowerGarden, //May have an NPC
	woodCottage, //Has an NPC
	garlicPalace,
	skyGazingPlane,
	silverLake, //Has an NPC
	forestHeart,
	
	mountainPath,
	mountainHigh,
	mountainPeak,
	mountainRange,
	mountainSpring,
	mountainCave,
	
	rabbitCave,
	longCave,
	mineCave,
	wolfCave,
	dungeonCave,
	
	wayWalk1,
	wayWalk2,
	wayWalk3,
	wayWalk4,
	wayWalk5,
	*/
	//p,q,r,g;
	/**
	 * 
	 *//*
	public Graph(){
		//Seriously thinking about a json File... 
		int x=0; //this variable is a counter while i figure out the scenes.
		SilverCrater silverCrater= new SilverCrater(x);x++;
		GreatForest greatForest= new GreatForest(x);x++;
		Lake silverLake=new Lake(x);x++;
		Blacksmith blacksmith= new Blacksmith(x);x++;
		IronYard oldBattlefield=new IronYard(x);x++;
		FlowerGarden flowerGarden=new FlowerGarden(x);x++;
		Cave entrance = new Cave(x,"the cave is moist and cold, it grows bigger into the darkness", 
				"Be it an entrance or an exit, here is the end of a cave");x++;
		Cave tunnel = new Cave(x,"the cave is moist and cold, the tunnel grows unto different paths", 
				"Darkness grows into the stones");x++;
		Cave mountainHole = new Cave(x,"the light enters shining through, but you cant reach the crackling dirt at the roof", 
				"A hole shines light unto a closed cave");x++;
		Cave howlingHole = new Cave(x,"the light enters shining through, but you cant reach the crackling dirt at the roof", 
				"A hole shines light unto a closed cave");x++;
		Cave gemRoom = new Cave(x,"An otherwise empty room is set to keep a forgotten treasure", 
				"A glittering path seaths droplets");x++;
		MountainPeak heavenPeak = new MountainPeak(x,"At this top, one sits at the side of clouds and above the world. A river sits near, and one feels lonely and "
				+ "cold", 
				"A stone peak partitions heavens, but is within reach");x++;
		Bridge bridge= new Bridge(x);x++;
		MountainPeak earthenPeak = new MountainPeak(x,"At this top, one sits above the forest. A river sits near, and is surrounded by ever taller mountains", 
				"A stone peak is within reach");x++;
		WolfCave wolfCave= new WolfCave(x);x++;
		
		/*FOREST AND VALLEY*/
	/*	silverCrater.setaN(1,greatForest,2,"walking along a path");
		silverCrater.setaN(2,blacksmith,1,"walking towards the noise");
		silverCrater.setaN(3,flowerGarden,1,"walking across the garden");
		silverCrater.setGoDescriptions();
		
		greatForest.setaN(1,silverCrater,2,"walking towards the shimmer");
		greatForest.setaN(2,blacksmith,2,"walking towards the noise");
		greatForest.setaN(3,silverLake,2,"walking towards the water");
		greatForest.setaN(4,earthenPeak,10,"climbing hard");
		greatForest.setGoDescriptions();
		
		blacksmith.setaN(1,greatForest,2,"walking along a path");
		blacksmith.setaN(2,oldBattlefield,1,"walking towards the rust");
		blacksmith.setaN(3,silverCrater,1,"walking towards the shimmer");
		blacksmith.setGoDescriptions();
		
		silverLake.setaN(3,greatForest,1,"walking across the trees");
		silverLake.setaN(4,flowerGarden,1,"following the water");
		silverLake.setaN(1,earthenPeak,10,"climbing the earthen rocks");
		silverLake.setaN(2,heavenPeak,12,"climbing towards the high tall peak");
		silverLake.setGoDescriptions();
		
		flowerGarden.setaN(1,greatForest,2,"walking across the trees");
		flowerGarden.setaN(2,silverCrater,1,"following the water");
		flowerGarden.setGoDescriptions();
		
		oldBattlefield.setaN(1,entrance,1,"walking towards the cave");
		oldBattlefield.setaN(2,blacksmith,1,"walking towards the noise");
		oldBattlefield.setaN(3,earthenPeak,5,"walking up the mountain");
		oldBattlefield.setGoDescriptions();
		*/
		/*MOUNTAINS*/
		/*earthenPeak.setaN(1,heavenPeak,7,"walking up the mountain the long way");
		earthenPeak.setaN(2,bridge,1,"walking across");
		earthenPeak.setaN(3,greatForest,5,"jumping, falling sliding down the slope");
		earthenPeak.setaN(4,oldBattlefield,3,"walking down the mountain");
		earthenPeak.setaN(5,silverLake,2,"swimming gently down the river");
		earthenPeak.setGoDescriptions();
		
		bridge.setaN(1,earthenPeak,1,"walking down");
		bridge.setaN(2,heavenPeak,1,"walking up");
		bridge.setaN(3,silverLake,4,"falling into the river and swimming downstream");
		bridge.setGoDescriptions();
		
		heavenPeak.setaN(1,earthenPeak,5,"walking the long way down");
		heavenPeak.setaN(2,bridge,1,"walking across the bridge");
		heavenPeak.setaN(3,mountainHole,3,"falling into the mountain hole");
		heavenPeak.setaN(4,silverLake,3,"swimming down as gently as you can");
		heavenPeak.setaN(5,wolfCave,2,"walking towards the dark cave");
		heavenPeak.setGoDescriptions();
*/
		/*CAVE SYSTEM*/
	/*	mountainHole.setaN(1,tunnel,1,"walking deeper into the cave");
		mountainHole.setGoDescriptions();

		entrance.setaN(1,oldBattlefield,1,"walking out towards the rust");
		entrance.setaN(2,tunnel,3,"walking up through the cave");
		entrance.setGoDescriptions();
		
		tunnel.setaN(1,mountainHole,1,"walking towards the faint light");
		tunnel.setaN(2,entrance,2,"walking down towards the exit");
		tunnel.setaN(3,gemRoom,2,"walking towards the glittering walls");
		tunnel.setaN(4,howlingHole,3,"walking towards the eerie howls");
		tunnel.setGoDescriptions();

		gemRoom.setaN(1,tunnel,1,"walking back through the tunnel");
		gemRoom.setaN(2,howlingHole,2,"walking towards the distant howls");
		gemRoom.setGoDescriptions();
		
		howlingHole.setaN(1,wolfCave,3,"falling into the wolf den");
		howlingHole.setaN(2,tunnel,1,"walking down through the tunnel");
		howlingHole.setaN(3,gemRoom,2,"walking towards the glittering walls");
		howlingHole.setGoDescriptions();

		wolfCave.setaN(2,heavenPeak,3,"walking out unto the mountain peak");
		wolfCave.setGoDescriptions();
		}
}


*/
		
	

	/*Methods*/
	//Constructor: initializes the objects of a class.
		//public Graph() {}
    
	//Genesis creates a fixed maps of nodes with int values, then updates those values from the "Seed Node"
	/*	public void Genesis() {
			g=silverCrater;
			int contador=1;
			while(true) {
				g.setScene(contador);
				
				
				boolean flagForthisWhile=true;
				do
				{ flagForthisWhile=false;}
				while(flagForthisWhile);
				if (!(g.isCave())) {
					//int nofGates= worldDie.d(numberPaths); //number of paths or gates that this scene will have
					switch (worldDie.d(numberPaths)) {
					case 1: {
						if(g.getaN1()==null)
						{
							g.setaN1(p);
							
							
						}
						
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value");
						break;
					}	
					
						else {}
						if(g.getaN2()==null)
						{}
					 if(g.getaN3()==null)
						{}
						 if(g.getaN4()==null)
						 {						
						
					}
						
					
				}
				
				
			
		//public boolean isTheSameUpasDown(int height1, int heigth2, String special) {return true;}
		//public boolean validateIsAdjNodeValid(NodeT1 a, NodeT1 b) {return true;}
		//public int determineCET(int height1, int heigth2, String special) {return 1;} //(CET Cost of Effort per Travel)
		
		public boolean ConnecttwoNodes (NodeT1 p, NodeT1 q) 
		{
			switch (worldDie.d(numberPaths)) {
				case 1: {
					if(g.getaN1()==null)
					{
						g.setaN1(p);
						
						
					}
					
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value");
					break;
				}	
				
				/*	else {}
					if(g.getaN2()==null)
					{}
				 if(g.getaN3()==null)
					{}
					 if(g.getaN4()==null)
					 {				
					
				}}
	
		
		}
}
*/
	
	
	



