package gamelogic;

public class TextParser {

	public ParsedCommand parse(String input, Lexicon lex) {
		if (input == null || input.isBlank())
			return null;

		ParsedCommand parsedTongue = new ParsedCommand(input);

		String[] words = input.toLowerCase().trim().split("\\s+");

		for (String word : words) {
			if (word.isBlank()) {
				continue;
			}

			switch (Lexicon.wordIs(word)) {
			case "verb": {
				switch (word) {
				case "walk":
				case "run":
					word = "go";
					break;
				case "grab":
					word = "take";
					break;
				}
				if (parsedTongue.getVerb() != null) 
					return null;
				if (!parsedTongue.maySetVerb(word)) 
					return null;
				break;
			}
			case "direction": {
				if (parsedTongue.getTarget() != null) 
					return null;
				if (!parsedTongue.maySetTarget(word)) 
					return null;
				break;
			}
			case "noun": {
				if (parsedTongue.getVerb() != null) {

					if (!parsedTongue.maySetTarget(word)) {
						if (!parsedTongue.maySetItem(word))
							return null;
					}
					;
				} else {
					if (parsedTongue.getItem() != null) 
						return null;
					if (!parsedTongue.maySetItem(word)) 
						return null;
				}
				break;
			}
			case "character": {
				if (parsedTongue.getTarget() != null) 
					return null;
				if (!parsedTongue.maySetTarget(word)) 
					return null;
				break;
			}
			case "modifier": {
				if (parsedTongue.getModifier() != null) 
					return null;
				if (!parsedTongue.maySetModifier(word)) 
					return null;
				break;
			}
			default: {
				break;
			}
			}
		}
		// as of the moment, no verb= invalid command
		if (parsedTongue.getVerb() == null) {
			return null;
		}
		return parsedTongue;
	}
}