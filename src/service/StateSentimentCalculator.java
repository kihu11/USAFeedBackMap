package service;

import model.Tweet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateSentimentCalculator {

    private final SentimentAnalyzer analyzer;

    public StateSentimentCalculator(SentimentAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public Map<String, Double> calculateAverageSentiments(Map<String, List<Tweet>> grouped) {

        Map<String, Double> result = new HashMap<>();

        for (var entry : grouped.entrySet()) {
            String state = entry.getKey();
            List<Tweet> tweets = entry.getValue();

            double sum = 0;
            int count = 0;

            for (Tweet tweet : tweets) {
                Double sentiment = analyzer.analyzeTweet(tweet);
                if (sentiment != null) {
                    sum += sentiment;
                    count++;
                }
            }

            if (count > 0) {
                result.put(state, sum / count);
            }
        }

        return result;
    }
}
