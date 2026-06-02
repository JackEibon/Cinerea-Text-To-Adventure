package gamelogic;
import java.util.Random;
import gameWorld.*;

public class GameLogic {
    private final Lexicon lexicon;
    private final TextParser parser;
    private final WorldGraph world;
    private final Random random = new Random();
    private String lastTarget = "";
    private CharSheet player;
    /* =========================================================
     * CONSTRUCTOR
     * ========================================================= */
    public GameLogic() {
        lexicon = new Lexicon();
        parser = new TextParser();
        world = new WorldGraph();
        world.getCurrentNode().setExplored(true);
        player= new CharSheet("name", true);
    }

    public String execute(String input) {
        ParsedCommand command = parser.parse(input, lexicon);
        if(command == null) return "The command was not understood.";
        //if(command.getTarget() != null) lastTarget = command.getTarget();
        switch(command.getVerb()) {

            /* MOVEMENT */
            case "go": case "walk": case "run": return move(command);
            /* Observation Placeholder */
            case "look": return getLook(command);
            case "paths":return getPathsText();
            case "map":case "minimap":return getMinimapText();
            case "where": return getLocationText();
            /* Combat placeholder */
            case "attack": return interpretAttack(command);
            
           /*Take and leave*/
            case "take": return takeItem(command);
            case "leave": return dropItem(command);
            /* HELP */
            case "help": return getHelpText();

            default: return "Nothing happens.";
        }
    }
    /* =========================================================
     * MOVEMENT SYSTEM
     * ========================================================= */
    private String move(ParsedCommand command) {
        String destination = command.getTarget();

        if(destination == null||destination.isBlank()) {
            int randomPath = getRandomValidPath();
            if(randomPath == -1) {return "There is nowhere to go.";}
            world.mayMoveTo(randomPath);
            return getSceneText();}

        //wooooo lets make a Try catch
        try {int pathId = Integer.parseInt(destination);
        boolean moved = world.mayMoveTo(pathId);
        if(!moved) return "You cannot go that way.";
        return getSceneText();

        } catch(NumberFormatException e) {
            /*
             * in the Future:
             * go cave
             * go bridge
             * go lake
             */
            return "You cannot find \"" + destination + "\".";
        }
    }
    //Chooses random available path.
    private int getRandomValidPath() {
        int[] validPaths = new int[8]; //i wish people explained to me the keyword "new" when i was younger...
        int count = 0;
        NodeT1 current = world.getCurrentNode();

        for(int i = 1; i <= 8; i++) {if(current.getaN(i) != null) {
                validPaths[count] = i;
                count++;
            }
        }
        if(count == 0) {return -1;}
        return validPaths[random.nextInt(count)];
    }

    /* =========================================================
     * WORLD TEXT; Observation System
     * ========================================================= */
    private String getLook(ParsedCommand command) {
    	String where=command.getTarget();
    	if(where == null||where.isBlank()) {
            return getSceneText();}
    	switch (where) {
    	case "ahead": return getDistantDescriptions();
		case "paths": case "ways": return getPathsText();
		case"map": case"minimap":return getMinimapText();
		case"here": case "around":default:return getSceneText();
    	}
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
        for(int i = 1; i <= 8; i++) {
            NodeT1 destinationNode = current.getaN(i);
            if(destinationNode != null) {
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
        return
        "\n(Minimap placeholder)\n"
        + "Future GUI minimap system pending.";
    }

    //debug purpose
    private String getLocationText() {
        return
        "Current Scene ID: "
        + world.getCurrentNode().getScene();
    }

    /* =========================================================
     * COMBAT INTERPRETATION
     * ========================================================= */

    private String interpretAttack(ParsedCommand command) {

        /*
         * Placeholder system.
         * Future parser semantic interpretation goes here.
         */

        String target = command.getTarget();

        if(target == null || target.isBlank()) {
            return "Attack what?";
        }

        return "You prepare to attack " + target + ".";
    }
    

    /* =========================================================
     * Inventory
     * ========================================================= */
    private String takeItem(ParsedCommand c) {
    	String target =c.getTarget();
    	Item x=world.getCurrentNode().suchItem(target);
    	if (x != null){
    		if (world.getCurrentNode().removeItem(target)) {
    			
    			if (player.addItem(x)) 
    			{world.getCurrentNode().setTargetDescriptions();
    				return "You took the " + target;}
    			world.getCurrentNode().addItem(x);
    			return "You couldnt take it";}
    		return "you cant take it";}
    	return "no such thing in place";
    }
    
    private String dropItem(ParsedCommand c) {
    	String target =c.getTarget();
    	Item x=player.thisItem(target);
    	if (x != null){
    		if (world.getCurrentNode().addItem(x)) {
    			if (player.removeItem(x)) {
    				world.getCurrentNode().setTargetDescriptions();
    				return "You dropped " + target;}
    			world.getCurrentNode().removeItem(x);
    			return "You couldnt drop it";}
    		return "you cant drop it here";}
    	return "you have no such thing";
    }



    /* =========================================================
     * HELP
     * ========================================================= */

    private String getHelpText() {
        return
        "\n=== COMMANDS ===\n"
        + "\nlook"
        + "\npaths"
        + "\ngo 1"
        + "\nwalk"
        + "\nrun"
        + "\nmap"
        + "\nwhere";
    }

    /* =========================================================
     * GETTERS
     * ========================================================= */

    public WorldGraph getWorld() {
        return world;
    }
}