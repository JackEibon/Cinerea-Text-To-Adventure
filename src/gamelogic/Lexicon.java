package gamelogic;

import java.util.Set; //while i planned on using only Lists, AI recommended Sets. 

/*
 * Set: unordered list of unique elements
 * List: Ordered list of not specifically unique elements
 * */

public class Lexicon {
	public static final Set<String> verbs = Set.of(
			"climb", "run", "walk", "go", "swim", 
			//"rest", "drink", "eat", 
			"search","look",
			//"attack", "talk", 
			"take", "grab", 
			"drop", "leave");

	public static final Set<String> directions = Set.of(
			"north", "south", "east", "west", 
			"up", "down",
			"inventory",
			"paths","ways",  "ahead",
			"around", "here",
			"1", "2", "3", "4", "5", "6", "7", "8");
	public static final Set<String> items = Set.of(
			"sword", "gun", "weapon", "spear",
			"garlic", "flower",
			"silver" ,"cross" , 
			"gem","ruby", "emerald", "sapphire", "amathyst",
			"map", "minimap");
	public static final Set<String> characters = Set.of("wolf", "blacksmith");
	public static final Set<String> modifiers = Set.of("quick", "slowly", "carefully");

	public static boolean isVerb(String word) {
		return verbs.contains(word.toLowerCase());
	}

	public static boolean isNoun(String word) {
		return items.contains(word.toLowerCase());
	}

	public static boolean isDirection(String word) {
		return directions.contains(word.toLowerCase());
	}

	public static boolean isModifier(String word) {
		return modifiers.contains(word.toLowerCase());
	}

	public static boolean isCharacter(String word) {
		return characters.contains(word.toLowerCase());
	}

	public static String wordIs(String word) {
		if (isVerb(word))
			return "verb";
		if (isNoun(word))
			return "noun";
		if (isDirection(word))
			return "direction";
		if (isModifier(word))
			return "modifier";
		if (isCharacter(word))
			return "character";
		return "non";
	}
}

/*
 * 
 * import java.util.HashSet; import java.util.Set;
 * 
 * public class Lexicon {
 * 
 * private final Set<String> verbs = new HashSet<>();
 * 
 * public Lexicon() { verbs.add("go"); verbs.add("walk"); verbs.add("run");
 * verbs.add("attack"); verbs.add("look"); verbs.add("take");
 * 
 * }
 * 
 * public boolean isVerb(String word) { return
 * verbs.contains(word.toLowerCase()); } }
 * 
 */

/*
 * Author: Eibon added in class of 24/2/2026 at 0941 from New Cinerea Netbeans
 * Project Resume: This code will color categorize keywords in different
 * sections. status: to be expanded
 * 
 * import java.util.Set; public class Lexicon { public static final Set<String>
 * verbs = Set.of("run", "walk", "attack", "drink", "talk", "rest"); public
 * static final Set<String> directions = Set.of("north", "south", "east",
 * "west", "up", "down"); public static final Set<String> nouns =
 * Set.of("garlic", "wolf", "air", "self","grass","tree", "weapon", "bridge",
 * "cave"); public static final Set<String> items = Set.of("sword"); public
 * static final Set<String> characters = Set.of("wolf"); public static final
 * Set<String> modifiers = Set.of("haste"); public static final Set<String>
 * concepts = Set.of("north", "south", "east", "west", "up", "down"); }
 * 
 * 
 */
