package service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsExtractor {

    private static final Pattern WORD_PATTERN = Pattern.compile("[a-zA-Z+]");
    public static List<String> extractWords(String text){

        List<String> words = new ArrayList<>();

        Matcher matcher = WORD_PATTERN.matcher(text);

        while (matcher.find()){
            words.add(matcher.group().toLowerCase());
        }

        return words;
    }
}
