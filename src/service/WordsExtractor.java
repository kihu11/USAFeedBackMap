package service;

import java.util.ArrayList;
import java.util.List;

public class WordsExtractor {

    public static List<String> extractWords(String text) {
        List<String> result = new ArrayList<>();
        if (text == null || text.isEmpty()) return result;


        text = text.replaceAll("https?://\\S+", " ");
        text = text.replaceAll("[@#]", " ");
        text = text.replaceAll("[^\\p{L}\\p{N}\\s]", " ");
        text = text.toLowerCase();

        for (String w : text.split("\\s+")) {
            if (!w.isEmpty()) {
                result.add(w);
            }
        }
        return result;
    }
}
