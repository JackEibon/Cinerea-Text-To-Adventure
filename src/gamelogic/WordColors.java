package gamelogic;

import java.awt.Color;

public class WordColors {
    public static Color ACTION_COLOR = Color.YELLOW;
    public static Color CONCEPT_COLOR = Color.GREEN;
    public static Color ITEM_COLOR = Color.RED;
    public static Color CHARACTER_COLOR = new Color(128, 0, 128); // purple
    public static Color MODIFIER_COLOR = Color.BLUE;

    // Darken helper
    private static Color darken(Color c) {
        return new Color(
            (int)(c.getRed() * 0.6),
            (int)(c.getGreen() * 0.6),
            (int)(c.getBlue() * 0.6)
        );
    }

    public static Color getColorForWord(String word, boolean isTarget) {
    	word=word.toLowerCase();
        Color c;
        
        switch (Lexicon.wordIs(word)) {
		case "verb": {c = ACTION_COLOR;break;}
		case "direction":{c = CONCEPT_COLOR;break;}
		case "noun":{c = ITEM_COLOR;break;}
		case "modifier":{c = MODIFIER_COLOR;break;}
		case "character":{c = CHARACTER_COLOR;break;}
		default: {c= Color.WHITE;break;}}
        
        return isTarget ? darken(c) : c;
    }


}



/*OLD WORDCOLORS*/

/*
 *package gamecontentlogic;
/*
 * Author: Eibon
 * added in class of 24/2/2026 at 0938 from New Cinerea Netbeans Project
 * Resume: This code will color the text to provide visual feedback about 
 * what kind of commands one gives
 * it is meant to be changed for new kinds of fonts
 * status: minimum fixes required
 *

import java.awt.Color;

public class WordColors {
    public static Color ACTION_COLOR = Color.YELLOW;
    public static Color CONCEPT_COLOR = Color.GREEN;
    public static Color ITEM_COLOR = Color.RED;
    public static Color CHARACTER_COLOR = new Color(128, 0, 128); // purple
    public static Color MODIFIER_COLOR = Color.BLUE;

    // Darken helper
    private static Color darken(Color c) {
        return new Color(
            (int)(c.getRed() * 0.6),
            (int)(c.getGreen() * 0.6),
            (int)(c.getBlue() * 0.6)
        );
    }

    public static Color getColorForWord(String word, boolean isTarget) {
        Color base;
        if (Lexicon.verbs.contains(word)) base = ACTION_COLOR;
        else if (Lexicon.directions.contains(word)) base = CONCEPT_COLOR;
        else if (Lexicon.items.contains(word)) base = ITEM_COLOR;
        else if (Lexicon.characters.contains(word)) base = CHARACTER_COLOR;
        else if (Lexicon.modifiers.contains(word)) base = MODIFIER_COLOR;
        else base = Color.WHITE;

        return isTarget ? darken(base) : base;
    }

}
 
 * 
 * */
 