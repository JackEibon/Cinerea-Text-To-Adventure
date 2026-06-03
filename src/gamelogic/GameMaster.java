package gamelogic;

import java.io.File;
import java.io.IOException;
import java.util.List;
/*10:menu system and all related
created as of log 10 1321-1641, mostly by myself or entirely. Very proud.
14: menus system moved to Character Sheets
15: 2137 Getting into scene manager as per the incesant begging of Copilot who hates
seeing me drown on piles of switch cases everytime i ask a question. Im scared
from the lists tho
*/
import java.lang.Math;
import java.util.*;

public class GameMaster {
	private int currentTime = 0;

	public void advanceTime(int ticks) {
		currentTime += ticks;
		// update background events...
	}

	public GameMaster getDie() {
		return die;
	}

	GameMaster die;

	public GameMaster() {
		die = null;
	}

	public int d(int max) {
		int minCeiled = 1;
		int maxFloored = (int) Math.floor(max);
		return (int) Math.floor(Math.random() * (maxFloored - minCeiled + 1) + minCeiled);
	}

	int die(int initialrandomScenes) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}

/*
 * Code Graveyard 10: private void updateMenu() { im betting on making it work
 * with keys Pressed. I know it can work, but i am betting time.
 * 
 * switch(GameWindow.getKeyPress()){ case 30:{
 * 
 * } default:{ break; } } } if (GameWindow.getKeyPresseds<KeyEvent) {
 * selectedOption = Math.max(0, selectedOption - 1); } if
 * (GameWindow.keyPresseds.containsKey(KeyEvent.VK_DOWN)) { selectedOption =
 * Math.min(menuOptions.length - 1, selectedOption + 1); } if
 * (GameWindow.keyPresseds.containsKey(KeyEvent.VK_ENTER)) {
 * executeOption(menuOptions[selectedOption]); }
 * 
 * 10: 1539 public String[] updateMenu() { switch(currentMenuState) { case 0:{
 * return menuOptions; } case 1:{ return useOptions; } case 2:{ return
 * takeOptions; } case 3:{ return talkOptions; } case 4:{ return goOptions; }
 * case 5:{ return restOptions; }
 * 
 * } return menuOptions; }
 * 
 * 
 * 
 * } }
 * 
 * private void drawMenu(Graphics g) { int y = 200; for (int i = 0; i <
 * menuOptions.length; i++) { String prefix = (i == selectedOption) ? "> " :
 * "  "; drawSpriteText(g, prefix + menuOptions[i], 100, y); y += 40; } }
 */
