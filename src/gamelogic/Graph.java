package gamelogic;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import scenesFiles.SilverCrater;

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
	
	p,q,r,g;
	
	SilverCrater silverCrater;
	
		
	

	/*Methods*/
	//Constructor: initializes the objects of a class.
		public Graph() {}
    
	//Genesis creates a fixed maps of nodes with int values, then updates those values from the "Seed Node"
		public void Genesis() {
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
				
				
			
		public boolean isTheSameUpasDown(int height1, int heigth2, String special) {return true;}
		//public boolean validateIsAdjNodeValid(NodeT1 a, NodeT1 b) {return true;}
		public int determineCET(int height1, int heigth2, String special) {return 1;} //(CET Cost of Effort per Travel)
		
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
				
					else {}
					if(g.getaN2()==null)
					{}
				 if(g.getaN3()==null)
					{}
					 if(g.getaN4()==null)
					 {						
					
				}}
	
		
		}
}
