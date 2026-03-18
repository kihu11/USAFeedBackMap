import model.Sentiment;
import model.State;
import model.Tweet;
import parser.*;
import service.*;
import visualization.MapVisualizer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        // 1. Парсим данные
        List<State> states = new StateParser().parseStates("Data/states.json");
        List<Tweet> tweets = new TweetParser().parseTweet("Data/texas_tweets2014.txt");

        Map<String, Double> sentimentMap = new SentimentParser()
                .parseSentiments("Data/sentiments.csv")
                .stream()
                .collect(Collectors.toMap(Sentiment::getWords, Sentiment::getValue));

        SentimentAnalyzer analyzer = new SentimentAnalyzer(sentimentMap);

        Map<String, List<Tweet>> grouped = TweetGrouper.groupByState(tweets, states);

        Map<String, Double> avgSentiments =
                new StateSentimentCalculator(analyzer).calculateAverageSentiments(grouped);

        // 2. Показываем карту
        MapVisualizer.showMap(states, avgSentiments);
    }
}
