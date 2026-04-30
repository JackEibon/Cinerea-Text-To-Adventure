package scenesFiles;
import java.util.List;



/*Nodo tipo 1
 * Autor Eibon
 * Creado el 5/3/2026 a las 1220.
 * Es un tipo de Nodo para un Grafo Dirigido Valorado que los construye teniendo en cuenta referencias con puntero.
 * Un nodo tendra todas sus conexiones apuntando a null hasta que sean apuntado estos valores con algun nodo en 
 * especifico*/
public class NodeT1 {
	boolean bridge=false, cave=false, explored=false;
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
private NodeT1 aN1= null;
private int aN1Cost= 999;
private NodeT1 aN2= null;
private int aN2Cost= 999;
private NodeT1 aN3= null;
private int aN3Cost= 999;
private NodeT1 aN4= null;
private int aN4Cost= 999;
private NodeT1 aN5= null;
private int aN5Cost= 999;
private NodeT1 aN6= null;
private int aN6Cost= 999;
private NodeT1 aN7= null;
private int aN7Cost= 999;
private NodeT1 aN8= null;
private int aN8Cost= 999;
private String 
biome = "meadow",
mainDescription = "there doesn’t seem to be anything interesting",
distantDescription = "a dense mist can be seen",
goDescriptions = "",
takeDescriptions = "",
restDescriptions = "",
talkDescriptions = "",
targetDescriptions = "",
itemsDescriptions = ""
;

public NodeT1(int scene){
    this.scene=scene;
    }

public boolean isBridge() {
    return bridge;
}

public boolean isCave() {
    return cave;
}

public boolean isExplored() {
    return explored;
}

public int getScene() {
    return scene;
}

public int getHeight() {
    return height;
}

public int getOdorP() {
    return odorP;
}

public int getFreshBeastBlood() {
    return freshBeastBlood;
}

public int getWolfBeenHere() {
    return wolfBeenHere;
}

public NodeT1 getaN1() {
    return aN1;
}

public NodeT1 getaN2() {
    return aN2;
}

public NodeT1 getaN3() {
    return aN3;
}

public NodeT1 getaN4() {
    return aN4;
}

public NodeT1 getaN5() {
    return aN5;
}

public NodeT1 getaN6() {
    return aN6;
}

public NodeT1 getaN7() {
    return aN7;
}

public NodeT1 getaN8() {
    return aN8;
}


public String getBiome() {
    return biome;
}

public String getMainDescription() {
    return mainDescription;
}

public String getDistantDescription() {
    return distantDescription;
}

public String getGoDescriptions() {
    return goDescriptions;
}

public String getTakeDescriptions() {
    return takeDescriptions;
}

public String getRestDescriptions() {
    return restDescriptions;
}

public String getTalkDescriptions() {
    return talkDescriptions;
}

public String getTargetDescriptions() {
    return targetDescriptions;
}

public String getItemsDescriptions() {
    return itemsDescriptions;
}

public void setBridge(boolean bridge) {
    this.bridge = bridge;
}
public void setCave(boolean cave) {
    this.cave = cave;
}

public void setExplored(boolean explored) {
    this.explored = explored;
}

public void setScene(int scene) {
    this.scene = scene;
}

public void setOdorP(int odorP) {
    this.odorP = odorP;
}

public void setFreshBeastBlood(int freshBeastBlood) {
    this.freshBeastBlood = freshBeastBlood;
}

public void setWolfBeenHere(int wolfBeenHere) {
    this.wolfBeenHere = wolfBeenHere;
}

public void setaN1(NodeT1 aN1) {
    this.aN1 = aN1;
}

public void setaN2(NodeT1 aN2) {
    this.aN2 = aN2;
}

public void setaN3(NodeT1 aN3) {
    this.aN3 = aN3;
}

public void setaN4(NodeT1 aN4) {
    this.aN4 = aN4;
}

public void setaN5(NodeT1 aN5) {
    this.aN5 = aN5;
}

public void setaN6(NodeT1 aN6) {
    this.aN6 = aN6;
}

public void setaN7(NodeT1 aN7) {
    this.aN7 = aN7;
}

public void setaN8(NodeT1 aN8) {
    this.aN8 = aN8;
}



public void setBiome(String biome) {
    this.biome = biome;
}

public void setMainDescription(String mainDescription) {
    this.mainDescription = mainDescription;
}

public void setDistantDescription(String distantDescription) {
    this.distantDescription = distantDescription;
}

public void setGoDescriptions(String goDescriptions) {
    this.goDescriptions = goDescriptions;
}

public void setTakeDescriptions(String takeDescriptions) {
    this.takeDescriptions = takeDescriptions;
}

public void setRestDescriptions(String restDescriptions) {
    this.restDescriptions = restDescriptions;
}

public void setTalkDescriptions(String talkDescriptions) {
    this.talkDescriptions = talkDescriptions;
}

public void setTargetDescriptions(String targetDescriptions) {
    this.targetDescriptions = targetDescriptions;
}

public void setItemsDescriptions(String itemsDescriptions) {
    this.itemsDescriptions = itemsDescriptions;
}

public void setaN1Cost(int aN1Cost) {
    this.aN1Cost = aN1Cost;
}

public void setaN2Cost(int aN2Cost) {
    this.aN2Cost = aN2Cost;
}

public void setaN3Cost(int aN3Cost) {
    this.aN3Cost = aN3Cost;
}

public void setaN4Cost(int aN4Cost) {
    this.aN4Cost = aN4Cost;
}

public void setaN5Cost(int aN5Cost) {
    this.aN5Cost = aN5Cost;
}

public void setaN6Cost(int aN6Cost) {
    this.aN6Cost = aN6Cost;
}

public void setaN7Cost(int aN7Cost) {
    this.aN7Cost = aN7Cost;
}

public void setaN8Cost(int aN8Cost) {
    this.aN8Cost = aN8Cost;
}


public int getaN1Cost() {
    return aN1Cost;
}

public int getaN2Cost() {
    return aN2Cost;
}

public int getaN3Cost() {
    return aN3Cost;
}

public int getaN4Cost() {
    return aN4Cost;
}

public int getaN5Cost() {
    return aN5Cost;
}

public int getaN6Cost() {
    return aN6Cost;
}

public int getaN7Cost() {
    return aN7Cost;
}

public int getaN8Cost() {
    return aN8Cost;
}

    
}
