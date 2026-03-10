package parser;

import model.Tweet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TweetParser {

    public List<Tweet> parseTweet(String filename) throws IOException {
        List<Tweet> tweets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Tweet tweet = tweetParseLine(line);
                tweets.add(tweet);
            }
        }

        return tweets;
    }

    private Tweet tweetParseLine(String line) {

        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split("\t");

        if (parts.length != 4) {
            throw new IllegalArgumentException("Неверное кол-во полей в строке");
        }

        String coordinates = parts[0].trim();
        String time = parts[2].trim();
        String message = parts[3].trim();

        coordinates = coordinates.replace("[", "").replace("]", "");

        String[] cordParts = coordinates.split(",");

        double longitude = Double.parseDouble(cordParts[0].trim());
        double latitude = Double.parseDouble(cordParts[1].trim());

        return new Tweet(longitude, latitude, time, message);
    }
}
