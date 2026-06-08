package gamelogic;

import java.util.Random;

import javax.swing.Timer;

import characters.*;
import gameWorld.*;
import views.GameCanvas;
import models.Item;
import models.User;
import utils.Session;

public class GameLogic {
	private User user;
	private final Lexicon lexicon;
	private final TextParser parser;
	private final WorldGraph world;
	private final Random random = new Random();
	private String lastTarget = "";
	private CharSheet player;
	private Timer worldTime;
	private static int minitick = 0, tick = 1, tack = 1, tock = 1, dayEnd = 0, nightEnd = 0, nigthHP = 670,
			countingScore;
	private boolean night = false, set1 = false, set2 = false, set3 = false;

	// public static GameCanvas canvas= new GameCanvas();
	/*
	 * ========================================================= CONSTRUCTOR
	 */
	public GameLogic() {
		user = null;
		lexicon = new Lexicon();
		parser = new TextParser();
		world = new WorldGraph();
		world.getCurrentNode().setExplored(true);
		player = new CharSheet("name", true);
		worldTime = new Timer(6000, e -> updateClock());
		worldTime.start();
	}

	public GameLogic(User user) {
		this.user = user;
		lexicon = new Lexicon();
		parser = new TextParser();
		world = new WorldGraph(user);
		world.getCurrentNode().setExplored(true);
		player = new CharSheet(user.getNickname(), true);
		worldTime = new Timer(6000, e -> updateClock());
		worldTime.start();
	}

	public GameLogic(User user, int setTick, int setTack, int setTock, int setNight) {
		this.user = user;
		lexicon = new Lexicon();
		parser = new TextParser();
		world = new WorldGraph(user);
		world.getCurrentNode().setExplored(true);
		player = new CharSheet(user.getNickname(), true);
		worldTime = new Timer(6000, e -> updateClock());
		worldTime.start();
		this.tick = setTick;
		this.tack = setTack;
		this.tock = setTock;
		this.nightEnd = setNight;
	}

	public GameLogic(int setTick, int setTack, int setTock, int setNight) {
		lexicon = new Lexicon();
		parser = new TextParser();
		world = new WorldGraph(user);
		world.getCurrentNode().setExplored(true);
		player = new CharSheet(user.getNickname(), true);
		worldTime = new Timer(6000, e -> updateClock());
		worldTime.start();
		this.tick = setTick;
		this.tack = setTack;
		this.tock = setTock;
		this.nightEnd = setNight;
	}

	public GameLogic(int setTick, int setTack, int setTock, int setNight, int timeSpeed) {
		lexicon = new Lexicon();
		parser = new TextParser();
		world = new WorldGraph(user);
		world.getCurrentNode().setExplored(true);
		player = new CharSheet(user.getNickname(), true);
		worldTime = new Timer(timeSpeed, e -> updateClock());
		worldTime.start();
		this.tick = setTick;
		this.tack = setTack;
		this.tock = setTock;
		this.nightEnd = setNight;
	}

	public GameLogic(User user, int setTick, int setTack, int setTock, int setNight, int timeSpeed) {
		this.user = user;
		lexicon = new Lexicon();
		parser = new TextParser();
		world = new WorldGraph(user);
		world.getCurrentNode().setExplored(true);
		player = new CharSheet(user.getNickname(), true);
		worldTime = new Timer(timeSpeed, e -> updateClock());
		worldTime.start();
		this.tick = setTick;
		this.tack = setTack;
		this.tock = setTock;
		this.nightEnd = setNight;
	}

	public String execute(String input) {
		if (player.isAlive() && nightEnd < 3) {
			ParsedCommand command = parser.parse(input, lexicon);
			if (command == null)
				return "The command was not understood.";
			// if(command.getTarget() != null) lastTarget = command.getTarget();
			switch (command.getVerb()) {

			/* MOVEMENT */
			case "go":
			case "walk":
			case "run":
				return move(command);
			/* Observation Placeholder */
			case "look":
				return getLook(command);
			case "paths":
				return getPathsText();
			case "map":
			case "minimap":
				return getMinimapText();
			case "where":
				return getLocationText();
			/* Combat placeholder */
			case "attack":
				return interpretAttack(command);

			/* Take and leave */
			case "grab":
			case "take":
				return takeItem(command);
			case "drop":
			case "leave":
				return dropItem(command);
			/* HELP */
			case "help":
				return getHelpText();

			default:
				return "Nothing happens.";

			}

		} else {
			return gameOver();

		}
	}

	/*
	 * ========================================================= MOVEMENT SYSTEM
	 */
	private String move(ParsedCommand command) {

		boolean flagforPoints = false;
		int cost;

		if (command.getTarget() == null || command.getTarget().isBlank()) {
			int randomPath = getRandomValidPath();
			if (randomPath == -1)
				return "There is nowhere to go.";
			cost = world.getCurrentNode().getaNCost(randomPath);
			if (!world.getCurrentNode().getaN(randomPath).isExplored())
				flagforPoints = true;
			world.mayMoveTo(randomPath);
			updateClock(100 * cost);
			if (flagforPoints)
				player.addPoints(world.getCurrentNode().getScene());
			return getSceneText();
		}
		
		int destination = Integer.parseInt(command.getTarget());

		// wooooo lets make a Try catch
		try {
			cost = world.getCurrentNode().getaNCost(destination);
			if (!world.getCurrentNode().getaN(destination).isExplored())
				flagforPoints = true;
			if (!world.mayMoveTo(destination))
				return "You cannot go that way.";

			updateClock(101 * cost);
			if (flagforPoints)
				player.addPoints(world.getCurrentNode().getScene());
			return getSceneText();

		} catch (NumberFormatException e) {
			// in the Future: go cave go bridge go lake
			return "You cannot find \"" + destination + "\".";
		}
	}

	// Chooses random available path.
	private int getRandomValidPath() {
		int[] validPaths = new int[8]; // i wish people explained to me the keyword "new" when i was younger...
		int count = 0;
		NodeT1 current = world.getCurrentNode();

		for (int i = 1; i <= 8; i++) {
			if (current.getaN(i) != null) {
				validPaths[count] = i;
				count++;
			}
		}
		if (count == 0) {
			return -1;
		}
		return validPaths[random.nextInt(count)];
	}

	/*
	 * ========================================================= WORLD TEXT
	 */
	private String getLook(ParsedCommand command) {
		String where = command.getTarget();
		if (where == null || where.isBlank()) {
			return getSceneText();
		}
		switch (where) {
		case "ahead":
			return getDistantDescriptions();
		case "paths":
		case "ways":
			return getPathsText();
		case "map":
		case "minimap":
			return getMinimapText();
		case "inventory":
			player.setLookWhere("inventory");
			return getSceneText();
		case "up":
			player.setLookWhere("up");
		case "here":
		case "around":
		default:
			return getSceneText();
		}
	}

	public String gameOver() {
		player.setLookWhere("score");
		String t = "";
		t += "\n==============================\n";
		t += "\n===========GAME OVER==========\n";
		int score = nightEnd * 100;
		for (Item ite : player.getInventory()) {
			for (String tag : ite.getTags()) {
				score += 10;
				if (tag.equalsIgnoreCase("gem"))
					score += 500;

			}

		}
		// else if (!w.isAlive()) score += 500;
		if (player.isAlive())
			score += 356;
		score += player.getPoints() + 10000;
		player.setPoints(score);

		t += "\nScore: " + score;
		t += "\nCredits:\n" + "\n"

				+ "\nProgramming by Etneilav & Abraham" + "\nhead of Organization: Etneilav"
				+ "\nAnimation & Design by Abraham" + "\nSpecial Thanks to my Family"
				+ "\nStars finished by my Girlfriend" + "\nBase Drawings by KaoriDraws"
				+ "\nDedicated to my brother and to my girlfriend"
				+ "\nSpecial thanks to all 7 programming Teachers i had so far at the University";

		if (countingScore < score && (minitick % 23 == 0))
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score - 50) && minitick % 5 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score - 200) && minitick % 3 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score - 1000))
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score / 2) && minitick % 7 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score / 3) && minitick % 5 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score / 4) && minitick % 3 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score / 5) && minitick % 2 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score / 6))
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score - 10) && minitick % 2 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score - 42) && minitick % 3 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score - 100) && minitick % 11 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score - 500))
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score / 2) && minitick % 7 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score / 3) && minitick % 5 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score / 4) && minitick % 3 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score / 5) && minitick % 2 == 0)
			countingScore++;
		player.setCountingScore(countingScore);
		if (countingScore < (score / 2))
			countingScore++;
		player.setCountingScore(countingScore);

		t += "\n==============================\n";
		t += "\n==============================\n";

		return t;

	}

	private String getSceneText() {
		String t = "";
		t += "\n==============================\n";
		t += world.getCurrentNode().getDescriptions();
		t += "\n==============================\n";
		return t;
	}

	private String getDistantDescriptions() {
		String t = "\n=== PATHS ===\n";
		return world.getCurrentNode().getGoDescriptions();
	}

	private String getPathsText() {
		String t = "\n=== PATHS ===\n";
		NodeT1 current = world.getCurrentNode();
		for (int i = 1; i <= 8; i++) {
			NodeT1 destinationNode = current.getaN(i);
			if (destinationNode != null) {
				t += "\n";
				t += i + ". ";
				t += current.getaNp(i);
				t += " [Cost ";
				t += current.getaNCost(i);
				t += "]";
			}
		}
		return t;
	}

	private String getMinimapText() {
		return "\n(Minimap placeholder)\n" + "Future GUI minimap system pending.";
	}

	// debug purpose
	private String getLocationText() {
		return "Current Scene ID: " + world.getCurrentNode().getScene();
	}

	/*
	 * ========================================================= Inventory
	 */
	private String takeItem(ParsedCommand c) {
		String target = c.getTarget();
		Item x = world.getCurrentNode().suchItem(target);
		updateClock(8);
		if (x != null) {
			if (world.getCurrentNode().removeItem(target)) {

				if (player.addItem(x)) {
					world.getCurrentNode().setTargetDescriptions();
					// canvas.addInventoryById(x.getId());
					updateClock(2);
					return "You took the " + target;
				}
				world.getCurrentNode().addItem(x);
				return "You couldnt take it";
			}
			return "you cant take it";
		}
		return "no such thing in place";
	}

	private String dropItem(ParsedCommand c) {
		String target = c.getTarget();
		Item x = player.thisItem(target);
		updateClock();
		if (x != null) {
			if (world.getCurrentNode().addItem(x)) {
				if (player.removeItem(x)) {
					updateClock();
					world.getCurrentNode().setTargetDescriptions();
					// canvas.removeInventoryById(x.getId());
					return "You dropped " + target;
				}
				world.getCurrentNode().removeItem(x);
				return "You couldnt drop it";
			}
			return "you cant drop it here";
		}
		return "you have no such thing";
	}

	/*
	 * ========================================================= COMBAT
	 */

	private String interpretAttack(ParsedCommand command) {

		/*
		 * Placeholder system. Future parser semantic interpretation goes here.
		 */

		String target = command.getTarget();

		if (target == null || target.isBlank()) {
			return "Attack what?";
		}

		return "You prepare to attack " + target + ".";
	}

	/*
	 * ========================================================= HELP
	 */

	private String getHelpText() {
		return "\n=== COMMANDS ===\n" + "\nlook" + "\npaths" + "\ngo 1" + "\nwalk" + "\nrun" + "\nmap" + "\nwhere";
	}

	/*
	 * ========================================================= GETTERS
	 */

	public void updateClock(int wait) {
		minitick += wait;
		advanceNightSky(wait - 1);
		clock();
		player.setTock(tock);
	}

	public void updateClock() {
		minitick++;
		clock();
		player.setTock(tock);
	}

	public void clock() {
		if (minitick % 5 == 0 && night) {

			advanceNightSky();

		}

		if (minitick >= 100) {
			minitick -= 100;
			nigthHP--;

			tick++;
			System.out.println("tick: " + tick + " night " + night + " hp:" + nigthHP);

		}
		if (tick >= 10) {
			tick -= 10;
			tack++;
		}
		if (tack >= 3) {
			tack -= 3;
			tock++;

		}
		if (tock >= 7) {
			tock -= 6;
			nightEnd++;
		}
		switch (tock) {
		case 1:
			night = false;
			break; // morning
		case 4:
			night = true;
			dayEnd++;
			break;
		case 6:
			nigthHP = 670;
			break;
		// night

		}
		player.setTock(tock);
	}

	private void advanceNightSky() {
		for (Star star : world.getNightSky()) {
			star.upAdvance();
			if (star.getY() > nigthHP) {
				star.setAppear(night);

			}
			if (star.getY() > nigthHP && tock == 6) {
				star.setAppear(false);

			}
		}
	}

	private void advanceNightSky(int up) {
		for (Star star : world.getNightSky()) {
			star.upAdvance(up);
			if (star.getY() > nigthHP) {
				star.setAppear(night);

			}
			if (star.getY() > nigthHP && tock == 6) {
				star.setAppear(false);

			}
		}
	}

	/* Old Clock */
	/*
	 * public void clock() { if (framecount >= 99) { tickNow = tick; tick++;
	 * //GameWindow.logPanel.addMessage("tick:"+tick); tickNowFun(p1);
	 * tickNowFun(w);
	 * 
	 * Node current = Cinerea.world.corner; for (int i = 0; i < 30; i++) { current =
	 * Cinerea.world.corner; for (int r = 0; r < i; r++) { < if (current.s != null)
	 * { current = current.getS(); } } }
	 * 
	 * if (tick >= 10) { tick = 0; tack++; if (!p1.isAlive() && !discarded1) { //
	 * p1.PlayerNode().setmayTargets(0, p1.PlayerNode().getmayTargets()[0] - 1);
	 * discarded1 = true; } if (tack >= 3) { tack = 0; tock++; if (tock == 7) { tock
	 * = 1; nightEnd++; } switch (tock) { case 1: night = false; break; // morning
	 * case 4: night = true; dayEnd++; break; // night case 6: break; // epitome } }
	 * } } }
	 * 
	 */

	public static int getTick() {
		return tick;
	}

	public static void setTick(int tick) {
		GameLogic.tick = tick;
	}

	public static int getTack() {
		return tack;
	}

	public static void setTack(int tack) {
		GameLogic.tack = tack;
	}

	public static int getTock() {
		return tock;
	}

	public static void setTock(int tock) {
		GameLogic.tock = tock;
	}

	public static int getDayEnd() {
		return dayEnd;
	}

	public static void setDayEnd(int dayEnd) {
		GameLogic.dayEnd = dayEnd;
	}

	public static int getNightEnd() {
		return nightEnd;
	}

	public static void setNightEnd(int nightEnd) {
		GameLogic.nightEnd = nightEnd;
	}

	public WorldGraph getWorld() {
		return world;
	}

	public static int getCountingScore() {
		return countingScore;
	}

	public static void setCountingScore(int countingScore) {
		GameLogic.countingScore = countingScore;
	}

	public static int getNigthHP() {
		return nigthHP;
	}

	public static void setNigthHP(int nigthHP) {
		GameLogic.nigthHP = nigthHP;
	}

	public CharSheet getPlayerSheet() {

		return player;
	}

}