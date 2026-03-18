package service;

import model.State;
import model.Tweet;

import java.util.List;

public class StateLocator {

    public static State findClosestState(Tweet tweet, List<State> states) {
        double minDist = Double.MAX_VALUE;
        State closest = null;

        for (State state : states) {
            double dist = distance(
                    tweet.getLatitude(),
                    tweet.getLongitude(),
                    state.getCenter().getLatitude(),
                    state.getCenter().getLongitude()
            );

            if (dist < minDist) {
                minDist = dist;
                closest = state;
            }
        }

        return closest;
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double dx = lat1 - lat2;
        double dy = lon1 - lon2;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
