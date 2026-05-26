package gamelogic;

public class TextParser {

    public ParsedCommand parse(String input, Lexicon lexicon) {

        if(input == null || input.isBlank()) {
            return null;
        }

        String[] tokens = input.toLowerCase().trim().split("\\s+");

        if(tokens.length == 0) {
            return null;
        }

        String verb = tokens[0];

        if(!lexicon.isVerb(verb)) {
            return null;
        }

        String target = tokens.length > 1 ? tokens[1] : "";

        if(verb.equals("walk") || verb.equals("run")) {
            verb = "go";
        }

        return new ParsedCommand(verb, target);
    }
}
