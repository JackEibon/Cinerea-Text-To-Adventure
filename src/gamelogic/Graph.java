package gamelogic;

import java.util.Scanner;
import java.util.Iterator;


public class Graph {
	
	GameMaster gameMaster = new GameMaster();
	//Node zero, p, q, r, mzero, mp, mq, mr;// Node type pointers //Naming Convention no longer used as of 11/11/2025
		NodeT1 seed, p, q, r, corner, player1, player2, wolfBeast; // Node type pointers 11/11/2025 14:mp, mq and mr rejected pointers
	/*14: corner,seed & p,q & r: pointers for Genesis and similar use.
	14 p1, p2 & w are entities who will move across the map. This pointers can be called "pieces
	        as if the world was a map, and the pieces were from a chess board*/
	        GameMaster worldDie; //A random int generator inspired by TTRPGs
		int mapSize=100, iterator=14;
		

		Scanner leer = new Scanner(System.in);


	/*Methods*/
	//Constructor: initializes the objects of a class.
		public L_Octagonal() {
			corner= null;
			}
	        
	        

	//Genesis creates a fixed maps of nodes with int values, then updates those values from the "Seed Node"
		public void Genesis() { 
			corner= new Node(); //Hello readers! Here i start trying a new naming convention and correct documentation, at 11/11/25.
			worldDie= new GameMaster(); //The dice will decide the generation of the sprawling seed.
			corner.setScene(0);
			p=corner;
			r=corner;
			for(int i=-iterator; i<=iterator; i++) {
					for(int j=-iterator; j<=iterator; j++) {
						q=new Node();
						q.setScene(0); //background
						//q.setScene(j); // debug
						p.setE(q);
						q.setW(p);
						p=q;	
						if(i>-iterator) {
							r=r.getE();
							r.setS(q);
							q.setN(r);
							}
						if (i==0 && j==0) {
								seed=p;
								seed.setScene(1);
		                        seed.play("first",GameThread.p1);
		                                                
							}
						}
					r=corner;
					while(r.getS()!=null) {
						r=r.getS();
					}
					p=new Node();
					p.setScene(0); //background
					//p.setScene(i); //debug
					r.setS(p);
					p.setN(r);
				}
				p=seed; //here im listening to "buttercup" from jack stauber
				q=seed;
				int contador=1;
			    do
			    {
			    	//System.out.println("crear mundo do while "+ contador); //debug
			    	int randomDirection=0;
			    	while(q.getScene()!=0) 
			    	{
			    		//System.out.println("crear mundo while "+ contador); //debug
			    		randomDirection=worldDie.d(4);
			    		//System.out.println("randomDirection: "+ randomDirection); //debug
				        switch (randomDirection){
				        case 1:
				        	//System.out.println("crear mundo while n "+ contador); //debug
				        	if (p.getN()==null) {
				        		//System.out.println("null"+ contador); //debug
				        		break;
				        	}
				        	q=p.getN();
				        	break;
				        case 2:	
				        	//System.out.println("crear mundo while e "+ contador); //debug
				        	if (p.getE()==null) {
				        		//System.out.println("null"+ contador); //debug
				        		break;
				        	}
				        	q=p.getE();
				        	break;
				        case 3:
				        	//System.out.println("crear mundo while s "+ contador); //debug
				        	if (p.getS()==null) {
				        		//System.out.println("null"+ contador); //debug
				        		break;
				        	}
				        	q=p.getS();
				        	break;
				        case 4:	
				        	//System.out.println("crear mundo while w "+ contador); //debug
				        	if (p.getW()==null) {
				        		//System.out.println("null"+ contador); //debug
				        		break;
				        	}
				        	q=p.getW();
				        	break;
				        	}
				        p=q;
				        }
			    		contador++;
			    		p=seed;
				        q.setScene(contador);
	                    q.play("first",GameThread.p1);//14: method that sets the narrative and options
				        }while(contador<mapSize);
	                    //14:_this for was created for play(false), now called .play("prep")
	                    wolfBeast=q;
	   
	                    p=corner;
	                    q=p;
			for(int i=-iterator; i<=iterator; i++) {
	                    for(int j=-iterator; j<=iterator; j++) {
	                        //System.out.println(i+j+q.getScene()); // debug
	                        q.play("prep",GameThread.p1);
	                        q=q.getE();					
						}
	                    q=p.getS();
	                    p=q;
	                    
				}
	                player1=seed;
	                player2=seed;
			} 

}
