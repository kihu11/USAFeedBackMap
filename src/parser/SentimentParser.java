package parser;

import model.Sentiment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SentimentParser {

    public List<Sentiment> parseSentiments(String filename) throws IOException {

        List<Sentiment> sentiments = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length != 2) {
                    continue;
                }

                String word = parts[0].trim();
                double value = Double.parseDouble(parts[1].trim());

                sentiments.add(new Sentiment(word, value));
            }
        }

        return sentiments;
    }
}