package service;

import model.State;
import model.Tweet;

import java.util.List;

public class StateLocator {

    public static State findClosestState(Tweet tweet, List<State> states) {
        double lat = tweet.getLatitude();
        double lon = tweet.getLongitude();

        for (State state : states) {
            for (List<double[]> polygon : state.getPolygons()) {
                if (pointInPolygon(lat, lon, polygon)) {
                    return state;
                }
            }
        }

        double minDist = Double.MAX_VALUE;
        State closest = null;

        for (State state : states) {
            double dist = distance(
                    lon, lat,
                    state.getCenter().getLongitude(),
                    state.getCenter().getLatitude()
            );

            if (dist < minDist) {
                minDist = dist;
                closest = state;
            }
        }

        return closest;
    }

    private static boolean pointInPolygon(double lat, double lon, List<double[]> polygon) {
        boolean inside = false;

        for (int i = 0, j = polygon.size() - 1; i < polygon.size(); j = i++) {

            double lon_i = polygon.get(i)[0];
            double lat_i = polygon.get(i)[1];

            double lon_j = polygon.get(j)[0];
            double lat_j = polygon.get(j)[1];

            boolean intersect =
                    ((lat_i > lat) != (lat_j > lat)) &&
                            (lon < (lon_j - lon_i) * (lat - lat_i) / (lat_j - lat_i) + lon_i);

            if (intersect) inside = !inside;
        }

        return inside;
    }

    private static double distance(double lon1, double lat1, double lon2, double lat2) {
        double dx = lon1 - lon2;
        double dy = lat1 - lat2;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
