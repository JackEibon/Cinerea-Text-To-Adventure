package gamelogic;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;

import scenesFiles.*;



public class Graph {
	
	
	GameMaster worldDie = new GameMaster(); //GameMaster worldDie; //A random int generator inspired by TTRPGs
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
	

	int 
	numberPaths=4, //this number decides the quantity of paths that we have available for each Node. It is currently 4, as the other 4 will be leaved for special exits or scripted pathways
	numberGates= 2, //this number decides maximum quantity of gates for each scene
	mapSize=5; //this number decides which scenes (by ID) will be on play and which will not. Ex: If it is 4, scenes 001,002,003 and 004 will be on play
	

	
	NodeT1  
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
	p,q,r,g;
	/**
	 * 
	 */
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
		silverCrater.setaN(1,greatForest,2,"walking along a path");
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
		
		/*MOUNTAINS*/
		earthenPeak.setaN(1,heavenPeak,7,"walking up the mountain the long way");
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

		/*CAVE SYSTEM*/
		mountainHole.setaN(1,tunnel,1,"walking deeper into the cave");
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
	
	
	


