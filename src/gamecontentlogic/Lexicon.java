package gamecontentlogic;

/*
 * Author: Eibon
 * added in class of 24/2/2026 at 0941 from New Cinerea Netbeans Project
 * Resume: This code will color categorize keywords in different sections.
 * status: to be expanded
 * */
import java.util.Set;
public class Lexicon {
    public static final Set<String> verbs = Set.of("run", "walk", "attack", "drink", "talk", "rest");
    public static final Set<String> directions = Set.of("north", "south", "east", "west", "up", "down");
    public static final Set<String> nouns = Set.of("garlic", "wolf", "air", "self","grass","tree", "weapon", "bridge", "cave");
    public static final Set<String> items = Set.of("sword");
    public static final Set<String> characters = Set.of("wolf");
    public static final Set<String> modifiers = Set.of("haste");
    public static final Set<String> concepts = Set.of("north", "south", "east", "west", "up", "down");
}
