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
		Blacksmith blacksmith= new Blacksmith(x);x++;
		FlowerGarden flowerGarden=new FlowerGarden(x);x++;
		Cave entrance = new Cave (x);x++;
		Cave tunnel = new Cave (x);x++;
		Cave mountainHole = new Cave (x);x++;
		Cave howlingHole = new Cave (x);x++;
		Cave gemRoom = new Cave (x);x++;
		MountainPeak heavenPeak = new MountainPeak(x);x++;
		Bridge bridge= new Bridge(x);x++;
		MountainPeak earthenPeak = new MountainPeak(x);x++;
		WolfCave wolfCave= new WolfCave(x);x++;
		Lake silverLake=new Lake(x);x++;
		IronYard oldBattlefield=new IronYard(x);x++;
		
		silverCrater.setaN(1,greatForest,2);
		silverCrater.setaN(2,blacksmith,1);
		silverCrater.setaN(3,flowerGarden,1);
		
		greatForest.setaN(1,silverCrater,2);
		greatForest.setaN(2,blacksmith,2);
		greatForest.setaN(3,silverLake,2);
		greatForest.setaN(4,earthenPeak,10);
		
		blacksmith.setaN(1,greatForest,2);
		blacksmith.setaN(2,oldBattlefield,1);
		blacksmith.setaN(3,silverCrater,1);
		
		silverLake.setaN(1,earthenPeak,10);
		silverLake.setaN(2,heavenPeak,12);
		silverLake.setaN(3,greatForest,1);
		silverLake.setaN(4,flowerGarden,1);
		
		flowerGarden.setaN(1,greatForest,2);
		flowerGarden.setaN(2,silverCrater,1);
		
		oldBattlefield.setaN(1,entrance,1);
		oldBattlefield.setaN(2,blacksmith,1);
		oldBattlefield.setaN(3,earthenPeak,5);
		
		earthenPeak.setaN(1,heavenPeak,7);//walking and climbing the long long way up
		earthenPeak.setaN(2,bridge,1); //walking
		earthenPeak.setaN(3,greatForest,5); //sliding, climbing and falling down
		earthenPeak.setaN(4,oldBattlefield,3); //walking the path down
		earthenPeak.setaN(5,silverLake,2); //swimming to the not so strong river
		
		bridge.setaN(1,earthenPeak,1);//walking
		bridge.setaN(2,heavenPeak,1); //walking
		bridge.setaN(3,silverLake,4); //falling and then swimming to the not so strong river
		
		heavenPeak.setaN(1,earthenPeak,5); //walking and climbing the long long way down
		heavenPeak.setaN(2,bridge,1); //walking
		heavenPeak.setaN(3,mountainHole,3);//falling
		heavenPeak.setaN(4,silverLake,3);//swimming to the strong river
		heavenPeak.setaN(5,wolfCave,2); //walking
		
		mountainHole.setaN(1,tunnel,1); //walking
		
		entrance.setaN(1,oldBattlefield,1);//walking
		entrance.setaN(2,tunnel,3);//walking up
		
		tunnel.setaN(1,mountainHole,1); //walking
		tunnel.setaN(2,entrance,2); //walking down
		tunnel.setaN(3,gemRoom,2);//walking up
		tunnel.setaN(4,howlingHole,3);//walking up
		
		gemRoom.setaN(1,tunnel,1);//walking down
		gemRoom.setaN(2,howlingHole,2);//walking 
		
		howlingHole.setaN(1,wolfCave,3); //falling
		howlingHole.setaN(2,tunnel,1); //walking down
		howlingHole.setaN(3,gemRoom,2);//walking
		
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
	
	
	


