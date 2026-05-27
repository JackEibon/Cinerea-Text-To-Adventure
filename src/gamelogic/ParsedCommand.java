package gamelogic;

public class ParsedCommand {

    private final String verb;
    private final String target;
    private final String noun;

    public ParsedCommand(String verb, String target, String item) {
        this.verb = verb;
        this.target = target;
        this.noun= item;
    }
    
    public ParsedCommand(String verb, String target) {
        this.verb = verb;
        this.target = target;
        this.noun= null;
    }
    
    public ParsedCommand(String verb) {
        this.verb = verb;
        this.target = null;
        this.noun= null;
    }


    public String getVerb() {
        return verb;
    }

    public String getTarget() {
        return target;
    }
}
