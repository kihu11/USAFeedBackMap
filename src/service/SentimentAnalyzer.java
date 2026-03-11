package service;

import model.Sentiment;
import model.Tweet;

import java.util.List;

public class SentimentAnalyzer {

    public static Double analyzeTweet(Tweet tweet, List<Sentiment> sentiments){

        List<String> words = WordsExtractor.extractWords(tweet.getMessage());

        double sum = 0;
        int count = 0;

        for (String word : words){

            for (Sentiment sentiment : sentiments){

                if (sentiment.getWords().equals(word)){
                    sum += sentiment.getValue();
                    count++;
                }
            }
        }

        if (count == 0){
            return null;
        }

        return sum / count;
    }
}