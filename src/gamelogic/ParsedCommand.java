package gamelogic;

public class ParsedCommand {
    private String verb;
    private String target;
    private String item;
    private String modifier;
    private String command;
    
    public ParsedCommand() {}
    public ParsedCommand(String command) {this.command = command;}
    
    public String getVerb() {return verb;}
    public void setVerb(String verb) {this.verb = verb;}
    public boolean maySetVerb(String verb) {
        if(this.verb == null) {this.verb = verb;return true;}
        return false;}
    public String getTarget() {return target;}
    public void setTarget(String target) {this.target = target;}
    public boolean maySetTarget(String target) {
        if(this.target == null) {this.target = target;return true;}
        return false;}
    public String getItem() {return item;}
    public void setItem(String item) {this.item = item;}
    public boolean maySetItem(String item) {
        if(this.item == null) {this.item = item;return true;}
        return false;}
    public String getModifier() {return modifier;}
    public void setModifier(String modifier) {this.modifier = modifier;}
    public boolean maySetModifier(String modifier) {
        if(this.modifier == null) {this.modifier = modifier;return true;}
        return false;}
    public String getCommand() {return command;}
    public void setCommand(String command) {this.command = command;}
    public boolean maySetCommand(String command) {
        if(this.command == null) {this.command = command;return true;}
        return false;}
}