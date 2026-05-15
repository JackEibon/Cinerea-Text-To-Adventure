package gamelogic;

public class GameLogic {

    private final Lexicon lexicon;
    private String currentLocation = "forest";

    public GameLogic() {
        lexicon = new Lexicon();
    }

    public String execute(String input) {
        ParsedCommand command = TextParser.parse(input, lexicon);

        if(command == null) {
            return "The command was not understood.";
        }

        switch(command.getVerb()) {
            case "go":
                return go(command.getTarget());
            case "look":
                return "You observe the area around you carefully.";
            case "attack":
                return "You prepare yourself for combat.";
            default:
                return "Nothing happens.";
        }
    }

    private String go(String destination) {
        if(destination == null || destination.isBlank()) {
            return "Go where?";
        }

        currentLocation = destination;
        return "You travel toward the " + destination + ".";
    }

    public String getCurrentLocation() {
        return currentLocation;
    }
}
