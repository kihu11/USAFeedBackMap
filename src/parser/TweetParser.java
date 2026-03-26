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
                if (tweet != null) {
                    tweets.add(tweet);
                }
            }
        }

        return tweets;
    }

    private Tweet tweetParseLine(String line) {

        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split("\t");


        String coordinates = parts[0].trim();
        String time = parts[2].trim();
        String message = parts[3].trim();

        coordinates = coordinates.replace("[", "").replace("]", "");

        String[] cordParts = coordinates.split(",");

        if (cordParts.length != 2) {
            return null;
        }

        double lat = Double.parseDouble(cordParts[0].trim());
        double lon = Double.parseDouble(cordParts[1].trim());

        if (lat < 20 || lat > 55) return null;
        if (lon < -130 || lon > -60) return null;

        return new Tweet(lat, lon, time, message);
    }
}
