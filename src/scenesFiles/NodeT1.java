package scenesFiles;
import java.util.List;



/*Nodo tipo 1
 * Autor Eibon
 * Creado el 5/3/2026 a las 1220.
 * Es un tipo de Nodo para un Grafo Dirigido Valorado que los construye teniendo en cuenta referencias con puntero.
 * Un nodo tendra todas sus conexiones apuntando a null hasta que sean apuntado estos valores con algun nodo en 
 * especifico*/
public class NodeT1 {
	boolean explored=false;
	/*
	 * 
    
    int scene = 0, 
    int mayTargets[] = {0, 0, 0};
    String biome = "meadow",
           condition1 = "normal",
           condition2 = "normal",
           mainDescription = "there doesn’t seem to be anything interesting",
           distantDescription = "a dense mist can be seen",
           goDescriptions = "",
           takeDescriptions = "",
           restDescriptions = "",
           talkDescriptions = "",
           targetDescriptions = "",
           itemsDescriptions = "",
           sView = "to the south, a shadow stretches endlessly",
           nView = "to the north, darkness looms abyssally",
           eView = "to the east, the blackness devours without end",
           wView = "to the west, it seems to be the end of the world",
           text = mainDescription;
	 * 
	 * */
private int scene=0, odorP = 0, freshBeastBlood = 0, wolfBeenHere = 0, height=0;
private NodeT1 aN1= null, aN2= null,aN3= null, aN4= null,aN5= null,aN6= null,aN7= null,aN8= null;
private int aN1Cost= 999,aN2Cost= 999,aN3Cost= 999,aN4Cost= 999,aN5Cost= 999,aN6Cost= 999,aN7Cost= 999,aN8Cost= 999;
private String aN1p="none",aN2p="none",aN3p="none",aN4p="none",aN5p="none",aN6p="none",aN7p="none",aN8p="none",
biome = "meadow",
mainDescription = "there doesn’t seem to be anything interesting",
distantDescription = "a dense mist can be seen",
goDescriptions = "there is nowhere to go",
takeDescriptions = "",
restDescriptions = "",
talkDescriptions = "",
targetDescriptions = "",
itemsDescriptions = ""
;
private List<String> names;

public NodeT1(int scene){this.scene=scene;}

public boolean isExplored() {return explored;}
public int getScene() {return scene;}
public int getHeight() {return height;}
public int getOdorP() {return odorP;}
public int getFreshBeastBlood() {return freshBeastBlood;}
public int getWolfBeenHere() {return wolfBeenHere;}

public NodeT1 getaN1() {return aN1;}
public NodeT1 getaN2() {return aN2;}
public NodeT1 getaN3() {return aN3;}
public NodeT1 getaN4() {return aN4;}
public NodeT1 getaN5() {return aN5;}
public NodeT1 getaN6() {return aN6;}
public NodeT1 getaN7() {return aN7;}
public NodeT1 getaN8() {return aN8;}

public String getBiome() {return biome;}
public String getMainDescription() {return mainDescription;}
public String getDistantDescription() {return distantDescription;}
public String getGoDescriptions() {return goDescriptions;}
public String getTakeDescriptions() {return takeDescriptions;}
public String getRestDescriptions() {return restDescriptions;}
public String getTalkDescriptions() {return talkDescriptions;}
public String getTargetDescriptions() {return targetDescriptions;}
public String getItemsDescriptions() {return itemsDescriptions;}

public void setExplored(boolean explored) {this.explored = explored;}
public void setScene(int scene) {this.scene = scene;}
public void setOdorP(int odorP) {this.odorP = odorP;}
public void setFreshBeastBlood(int freshBeastBlood) {this.freshBeastBlood = freshBeastBlood;}
public void setWolfBeenHere(int wolfBeenHere) {this.wolfBeenHere = wolfBeenHere;}

public void setaN(int aNT, NodeT1 aN, int cost) {
   
	switch (aNT) {
	case 1: {
		this.aN1 = aN;
		this.aN1Cost=cost;
		break;
	}
	case 2: {
		this.aN2 = aN;
		this.aN2Cost=cost;
		break;
	}
	case 3: {
		this.aN3 = aN;
		this.aN3Cost=cost;
		break;
	}
	case 4: {
		this.aN4 = aN;
		this.aN4Cost=cost;
		break;
	}
	case 5: {
		this.aN5 = aN;
		this.aN5Cost=cost;
		break;
	}
	case 6: {
		this.aN6 = aN;
		this.aN6Cost=cost;
		break;
	}
	case 7: {
		this.aN7 = aN;
		this.aN7Cost=cost;
		break;
	}
	case 8: {
		this.aN8 = aN;
		this.aN8Cost=cost;
		break;
	}
	default:
		throw new IllegalArgumentException("Unexpected value: " + aNT);
	}
	
}
public void setaN(int aNT, NodeT1 aN, int cost, String way) {   
	switch (aNT) {
	case 1: {this.aN1 = aN;this.aN1Cost=cost;this.aN1p=way;break;}
	case 2: {this.aN2 = aN;this.aN2Cost=cost;this.aN2p=way;break;}
	case 3: {this.aN3 = aN;this.aN3Cost=cost;this.aN3p=way;break;}
	case 4: {this.aN4 = aN;this.aN4Cost=cost;this.aN4p=way;break;}
	case 5: {this.aN5 = aN;this.aN5Cost=cost;this.aN5p=way;break;}
	case 6: {this.aN6 = aN;this.aN6Cost=cost;this.aN6p=way;break;}
	case 7: {this.aN7 = aN;this.aN7Cost=cost;this.aN7p=way;break;}
	case 8: {this.aN8 = aN;this.aN8Cost=cost;this.aN8p=way;break;}
	default:throw new IllegalArgumentException("Unexpected value: " + aNT);
	}
}

public void setaN1(NodeT1 aN1) {this.aN1 = aN1;}
public void setaN2(NodeT1 aN2) {this.aN2 = aN2;}
public void setaN3(NodeT1 aN3) {this.aN3 = aN3;}
public void setaN4(NodeT1 aN4) {this.aN4 = aN4;}
public void setaN5(NodeT1 aN5) {this.aN5 = aN5;}
public void setaN6(NodeT1 aN6) {this.aN6 = aN6;}
public void setaN7(NodeT1 aN7) {this.aN7 = aN7;}
public void setaN8(NodeT1 aN8) {this.aN8 = aN8;}

public String getaN1p() {return aN1p;}
public void setaN1p(String aN1p) {this.aN1p = aN1p;}
public String getaN2p() {return aN2p;}
public void setaN2p(String aN2p) {this.aN2p = aN2p;}
public String getaN3p() {return aN3p;}
public void setaN3p(String aN3p) {this.aN3p = aN3p;}
public String getaN4p() {return aN4p;}
public void setaN4p(String aN4p) {this.aN4p = aN4p;}
public String getaN5p() {return aN5p;}
public void setaN5p(String aN5p) {this.aN5p = aN5p;}
public String getaN6p() {return aN6p;}
public void setaN6p(String aN6p) {this.aN6p = aN6p;}
public String getaN7p() {return aN7p;}
public void setaN7p(String aN7p) {this.aN7p = aN7p;}
public String getaN8p() {return aN8p;}
public void setaN8p(String aN8p) {this.aN8p = aN8p;}



public List<String> getNames() {return names;}
public void setNames(List<String> names) {this.names = names;}

public void setHeight(int height) {this.height = height;}
public void setBiome(String biome) {this.biome = biome;}

public void setMainDescription(String mainDescription) {this.mainDescription = mainDescription;}
public void setDistantDescription(String distantDescription) {this.distantDescription = distantDescription;}
public void setGoDescriptions(String goDescriptions) {this.goDescriptions = goDescriptions;}
public void setGoDescriptions() {
    setGoDescriptions("");
	int x=1; //counter
	while(getaN(x)!=null) {
		setGoDescriptions(getGoDescriptions()+getaN(x).getDistantDescription()+
				", you can get there by " +this.getaNp(x)+"!\n" );x++;
	}  
}



public void setTakeDescriptions(String takeDescriptions) {this.takeDescriptions = takeDescriptions;}
public void setRestDescriptions(String restDescriptions) {this.restDescriptions = restDescriptions;}
public void setTalkDescriptions(String talkDescriptions) {this.talkDescriptions = talkDescriptions;}
public void setTargetDescriptions(String targetDescriptions) {this.targetDescriptions = targetDescriptions;}
public void setItemsDescriptions(String itemsDescriptions) {this.itemsDescriptions = itemsDescriptions;}

public void setaN1Cost(int aN1Cost) {this.aN1Cost = aN1Cost;}
public void setaN2Cost(int aN2Cost) {this.aN2Cost = aN2Cost;}
public void setaN3Cost(int aN3Cost) {this.aN3Cost = aN3Cost;}
public void setaN4Cost(int aN4Cost) {this.aN4Cost = aN4Cost;}
public void setaN5Cost(int aN5Cost) {this.aN5Cost = aN5Cost;}
public void setaN6Cost(int aN6Cost) {this.aN6Cost = aN6Cost;}
public void setaN7Cost(int aN7Cost) {this.aN7Cost = aN7Cost;}
public void setaN8Cost(int aN8Cost) {this.aN8Cost = aN8Cost;}

public int getaN1Cost() {return aN1Cost;}
public int getaN2Cost() {return aN2Cost;}
public int getaN3Cost() {return aN3Cost;}
public int getaN4Cost() {return aN4Cost;}
public int getaN5Cost() {return aN5Cost;}
public int getaN6Cost() {return aN6Cost;}
public int getaN7Cost() {return aN7Cost;}
public int getaN8Cost() {return aN8Cost;}
public NodeT1 getaN(int aNT){
	   
	switch (aNT) {
	case 1: {return this.aN1;}
	case 2: {return this.aN2;}
	case 3: {return this.aN3;}
	case 4: {return this.aN4;}
	case 5: {return this.aN5;}
	case 6: {return this.aN6;}
	case 7: {return this.aN7;}
	case 8: {return this.aN8;}
	default:throw new IllegalArgumentException("Unexpected value: " + aNT);}
	}
	
public int getaNCost(int aNT){
		switch (aNT) {
		case 1: {return this.aN1Cost;}
		case 2: {return this.aN2Cost;}
		case 3: {return this.aN3Cost;}
		case 4: {return this.aN4Cost;}
		case 5: {return this.aN5Cost;}
		case 6: {return this.aN6Cost;}
		case 7: {return this.aN7Cost;}
		case 8: {return this.aN8Cost;}
		default:throw new IllegalArgumentException("Unexpected value: " + aNT);}	
}
public String getaNp(int aNT){
	switch (aNT) {
	case 1: {return this.aN1p;}
	case 2: {return this.aN2p;}
	case 3: {return this.aN3p;}
	case 4: {return this.aN4p;}
	case 5: {return this.aN5p;}
	case 6: {return this.aN6p;}
	case 7: {return this.aN7p;}
	case 8: {return this.aN8p;}
	default:throw new IllegalArgumentException("Unexpected value: " + aNT);}	
	}
}
