package gamelogic;
import java.util.List;



/*Nodo tipo 1
 * Autor Eibon
 * Creado el 5/3/2026 a las 1220.
 * Es un tipo de Nodo para un Grafo Dirigido Valorado que los construye teniendo en cuenta referencias con puntero.
 * Un nodo tendra todas sus conexiones apuntando a null hasta que sean apuntado estos valores con algun nodo en 
 * especifico*/
public class NodeT1 {
	boolean bridgeEW = false, bridgeNS = false, explored = false;
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
private int scene=0, odorP = 0, freshBeastBlood = 0, wolfBeenHere = 0;
private NodeT1 aN1= null;
private NodeT1 aN2= null;
private NodeT1 aN3= null;
private NodeT1 aN4= null;
private NodeT1 aN5= null;
private NodeT1 aN6= null;
private NodeT1 aN7= null;
private NodeT1 aN8= null;
private NodeT1 aN9= null;
private NodeT1 aN10= null;
private NodeT1 aN11= null;
private NodeT1 aN12= null;
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

public boolean isBridgeEW() {
    return bridgeEW;
}

public boolean isBridgeNS() {
    return bridgeNS;
}

public boolean isExplored() {
    return explored;
}

public int getScene() {
    return scene;
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

public NodeT1 getaN9() {
    return aN9;
}

public NodeT1 getaN10() {
    return aN10;
}

public NodeT1 getaN11() {
    return aN11;
}

public NodeT1 getaN12() {
    return aN12;
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

public void setBridgeEW(boolean bridgeEW) {
    this.bridgeEW = bridgeEW;
}

public void setBridgeNS(boolean bridgeNS) {
    this.bridgeNS = bridgeNS;
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

public void setaN9(NodeT1 aN9) {
    this.aN9 = aN9;
}

public void setaN10(NodeT1 aN10) {
    this.aN10 = aN10;
}

public void setaN11(NodeT1 aN11) {
    this.aN11 = aN11;
}

public void setaN12(NodeT1 aN12) {
    this.aN12 = aN12;
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

    
}
