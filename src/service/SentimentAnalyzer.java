package service;

import model.Tweet;

import java.util.List;
import java.util.Map;

public class SentimentAnalyzer {

    private final Map<String, Double> sentiments;


    public SentimentAnalyzer(Map<String, Double> sentiments) {
        this.sentiments = sentiments;
    }
    public Double analyzeTweet (Tweet tweet){

        List<String> words = WordsExtractor.extractWords(tweet.getMessage());

        double sum = 0;
        int count = 0;

        for (int i = 0; i < words.size(); i++){
            StringBuilder phrase = new StringBuilder(words.get(i));
            for (int len = 1; len <= 5 && i + len <= words.size(); len++){
                String key = phrase.toString().toLowerCase(); // ВАЖНО


                Double val = sentiments.get(key);
                if (val != null){
                    sum += val;
                    count++;
                }

                if (i + len < words.size()){
                    phrase.append(" ").append(words.get(i + len));
                }
                if (val != null){
                    System.out.println("HIT: '" + key + "' = " + val);
                }
            }
        }
        return count == 0 ? null : sum / count;
    }

}
