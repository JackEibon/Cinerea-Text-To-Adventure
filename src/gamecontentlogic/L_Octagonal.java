package gamecontentlogic;


import java.util.Iterator;
import java.util.Scanner;

/*
 * Author: Eibon
 * added in class of 24/2/2026 at 1102 from New Cinerea Netbeans Project
 * Resume: Handles the map creation, manipulation and management via an 
 * Octagonal List, to be changed for a "Grafo"
 * status: useless.
 * */



/*Naming convention 1.0 (11/11/2025)*/
/*
 * debug: A method used to understand what the machine is doing
 * Not to be used at the use of the application as intended
 * 
 * background: a bed of 0´s for the set of the matrix. The list is made
 * and 0´s are put on each of the nodes data.
 * 0 represents a void in the map, while any other number is a map ID.
 * 
 * seed: Number 1 ID, and the starter of any other number. 
 * Number 2 will be connected to 1, number 3 will be connected to either 2 or 1, number 4
 * will be connected to any of the other previous IDs, etc.
 * 
 * Corner: what is known in lists as "head"
 * 
 * p,q,r,mp, mq and mr are arbitrary pointers that help us navigate the grid.
 * iterator: defines the size of the matrix. the square of the double +1 of the 
iterator must be greater than mapSize. Such that: 
(2(iterator)+1)*(2(iterator)+1)>=mapSize //14

 * 
 * mapSize: The quantity of "rooms" or "scenes" in the game.
 * 
 * 
 * */


public class L_Octagonal {
	//Node zero, p, q, r, mzero, mp, mq, mr;// Node type pointers //Naming Convention no longer used as of 11/11/2025
	Node seed, p, q, r, corner, player1, player2, wolfBeast; // Node type pointers 11/11/2025 14:mp, mq and mr rejected pointers
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
		} //listening to Elvis Presley "cant help falling in love", then Queen´s "It´s a hard life"
        
        public void Genesis(int map, int ratIterator) { 
            /*14: Method created, copy of Genesis*/
            mapSize=map;
            iterator= ratIterator;
            Genesis();
		}

        
        public void maParade(){
		p=corner;
		System.out.print(p.getScene()+"	");
		for(int i=-iterator; i<=iterator; i++) {
			for(int j=-iterator; j<=iterator; j++) {
				p=p.getE();	
				System.out.print(p.getScene()+"	");			
				}
			p=corner;	
			for(int j=-iterator; j<i; j++) {
				p=p.getS();			
				}
			System.out.println("	");	
			System.out.print(p.getScene()+"	");
			}
                System.out.print("WolfNode;" + wolfBeast.getScene());
		}
        
        
/*
	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		//L_Octagonal tryout = new L_Octagonal();
		L_Octagonal Mapa = new L_Octagonal();
		Mapa.Genesis();
		Mapa.maParade();

	}
*/
   

    public Node getSeed() {
        return seed;
    }

    public Node getPlayer1() {
        return player1;
    }

    public Node getPlayer2() {
        return player2;
    }

    public Node getWolfBeast() {
        return wolfBeast;
    }

    public GameMaster getWorldDie() {
        return worldDie;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setPlayer(int player,Node destiny) {
        switch(player){
            case 1:{
                this.player1=destiny;
                break;
            }    
        
        case 2:{
                this.player2=destiny;
                break;
            } 
        case 3: default:{
                this.wolfBeast=destiny;
                break;
            } }  
       
    }

    public void setPlayer2(Node player2) {
        this.player2 = player2;
    }

    public void setWolfBeast(Node wolfBeast) {
        this.wolfBeast = wolfBeast;
    }

    public void setWorldDie(GameMaster worldDie) {
        this.worldDie = worldDie;
    }
    
    
    
      
        
        
        
}


/*
 * Code Graveyard
 * Listening to Queen´s  Spread your Wings, then ill hear a ton of queen´s songs
 * Genesis & maParade logic finished
 * pending:
 * All canvas related
 * * Letters, Strings to letters, Window that displays the strings, pointer.
 * All dynamics related (Items, PlayerState)
 * *PlayerMove Logic (Player is a pointer in the list, will move across the nodes/rooms)
 * All rooms IDS
 * (the BIG dream is at least 100 rooms. Will stay with 25 rooms as of now, maybe even 10.)
 * 
 */


/*	No longer used Method created 10/11/2025, discontinued 11/11/2025
* 	public void crearMundo()
{
worldDie= new Dice();
zero=new Node();
zero.setScene(0);
p=zero;
q=zero;
int contador=1;
do
{
	//System.out.println("crear mundo do while "+ contador); //debug
	int randomDirection=0;
	while(q!=null) {
		System.out.println("crear mundo while "+ contador); //debug
		
		p=q;
		randomDirection=worldDie.d(4);
		//System.out.println("randomDirection: "+ randomDirection); //debug
        switch (randomDirection){
        case 1:
        	//System.out.println("crear mundo while n "+ contador); //debug
        	q=p.getN();
        	break;
        case 2:	
        	//System.out.println("crear mundo while e "+ contador); //debug
        	q=p.getE();
        	break;
        case 3:	
        	//System.out.println("crear mundo while s "+ contador); //debug
        	q=p.getS();
        	break;
        case 4:	
        	//System.out.println("crear mundo while w "+ contador); //debug
        	q=p.getW();
        	break;
        }}
        q=new Node();
        q.setScene(contador);
        switch (randomDirection){
        case 1:
        	p.setN(q);
        	q.setS(p);
        	break;
        case 2:	
        	p.setE(q);
        	q.setW(p);
        	break;
        case 3:	
        	p.setS(q);
        	q.setN(p);
        	break;
        case 4:	
        	p.setW(q);
        	q.setE(p);
        	break;
        }
        contador++;
        p=zero;
        }while(contador!=mapSize);
}
*/

/*No longer used
Method created 10/11/2025, discontinued 11/11/2025
public void createBlankMap(){
mzero= new Node();
mzero.setScene(0);
mp=mzero;
mr=mzero;
for(int i=-79; i<=80; i++) {
	for(int j=-80; j<=80; j++) {
		mq=new Node();
		mq.setScene(0); //background
		mq.setScene(j); // debug
		mp.setE(mq);
		mq.setW(mp);
		mp=mq;	
		if(i>-80) {
			mr=mr.getE();
			mr.setS(mp);
			mp.setN(mr);					
		}
			
	}
	mr=mzero;
	while(mr.getS()!=null) {
		mr=mr.getS();
	}
	mp=new Node();
	mp.setScene(0); //background
	//mp.setScene(i); //debug
	mr.setS(mp);
	mp.setN(mr);
}
}*/
/*No longer used
Method created 10/11/2025, discontinued 11/11/2025	
public void FillMap(){
mp=mzero;   /*Logchange: The following statement is no longer the case:
m= map or generated matrix The world is a generated "laberynth", while the map is a fixed, organized common matrix. 
Althought i could fuse both together, 
(making a fixed matrix, then getting to the center and start generating the rooms from there) 
and that would be simpler, i want them to be two separate methods. I will retract from this if it 
proves more complicated than expected. 

for(int i=-79; i<=0; i++) {
	mp=mp.getE();
	mp=mp.getS();
}
worldDie= new Dice();
mq=mp;
p=zero;
q=zero;
	int contador=1;
    do
    {
    	int randomDirection=0;
    	while(q!=null) {
    		p=q;
    		mp.setScene(p.getScene());
    		randomDirection=worldDie.d(4); 
	        switch (randomDirection){
	        case 1:
	        	q=p.getN();
	        	mp=mp.getN();
	        	mp.setScene(q.getScene());
	        	break;
	        case 2:	
	        	q=p.getE();
	        	mp=mp.getE();
	        	mp.setScene(q.getScene());
	        	break;
	        case 3:	
	        	q=p.getS();
	        	mp=mp.getS();
	        	mp.setScene(q.getScene());
	        	break;
	        case 4:	
	        	q=p.getW();
	        	mp=mp.getW();
	        	mp.setScene(q.getScene());
	        	break;
	        }}
	        contador++;
	        p=zero;
	        mp=mq;
	        }while(contador<mapSize);
}*/	    	
/*No longer used
Method created 10/11/2025, discontinued 11/11/2025
public void showMap(){

mp=mzero;
System.out.print(mp.getScene()+" ");
for(int i=-80; i<=80; i++) {
	for(int j=-80; j<=80; j++) {
		mp=mp.getE();	
		System.out.print(mp.getScene()+" ");			
		}
	mp=mzero;	
	for(int j=-80; j<i; j++) {
		mp=mp.getS();			
		}
	System.out.println(" ");	
	System.out.print(mp.getScene()+" ");
	}
}
*/


