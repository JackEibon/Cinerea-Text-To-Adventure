package gamelogic;

import java.util.HashSet;
import java.util.Set;

public class Lexicon {

    private final Set<String> verbs = new HashSet<>();

    public Lexicon() {
        verbs.add("go");
        verbs.add("walk");
        verbs.add("run");
        verbs.add("attack");
        verbs.add("look");
        verbs.add("take");
    }

    public boolean isVerb(String word) {
        return verbs.contains(word.toLowerCase());
    }
}
