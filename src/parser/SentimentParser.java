package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SentimentParser {

    public Map<String, Double> parse(String filename) throws IOException {

        Map<String, Double> sentiments = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                String phrase = parts[0].trim().toLowerCase();
                double value = Double.parseDouble(parts[1].trim());

                sentiments.put(phrase, value);
            }

        }

        return sentiments;
    }
}
