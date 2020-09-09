package estructural.flyweight;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class Sentence {
    private String[] words;
    private Map<Integer, WordToken> tokens = new HashMap<>();

    public Sentence(String plainText) {
        words = plainText.split(" ");
    }

    public WordToken getWord(int index) {
        WordToken wt = new WordToken();
        tokens.put(index, wt);
        return tokens.get(index);
    }

    @Override
    public String toString() {
        ArrayList<String> ws = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            String w = words[i];
            if (tokens.containsKey(i) && tokens.get(i).capitalize)
                w = w.toUpperCase();
            ws.add(w);
        }
        return String.join(" ", ws);
    }

    class WordToken {
        public boolean capitalize;
    }
}

public class Evaluate {
    @Test
    public void test() {
        Sentence s = new Sentence("alpha beta gamma");
        s.getWord(1).capitalize = true;
        assertEquals("alpha BETA gamma", s.toString());
    }
}
