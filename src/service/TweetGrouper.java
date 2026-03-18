package service;

import model.State;
import model.Tweet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TweetGrouper {

    public static Map<String, List<Tweet>> groupByState(List<Tweet> tweets, List<State> states) {

        Map<String, List<Tweet>> result = new HashMap<>();

        for (Tweet tweet : tweets) {
            State state = StateLocator.findClosestState(tweet, states);

            result.computeIfAbsent(state.getCode(), k -> new ArrayList<>())
                    .add(tweet);
        }

        return result;
    }
}
