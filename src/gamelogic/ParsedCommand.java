package gamelogic;

public class ParsedCommand {

    private final String verb;
    private final String target;

    public ParsedCommand(String verb, String target) {
        this.verb = verb;
        this.target = target;
    }

    public String getVerb() {
        return verb;
    }

    public String getTarget() {
        return target;
    }
}
