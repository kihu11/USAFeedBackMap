package visualization;

import model.State;
import model.Tweet;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.List;
import java.util.Map;

public class MapVisualizer extends JPanel {

    private final List<State> states;
    private final Map<String, Double> sentiments;
    private final List<Tweet> tweets;

    private static final double MIN_LON = -125;
    private static final double MAX_LON = -66;
    private static final double MIN_LAT = 24;
    private static final double MAX_LAT = 49;

    private static final double SCALE = 0.75; // уменьшение карты до 75%
    private static final int PADDING = 40;    // отступы от краёв

    public MapVisualizer(List<State> states, Map<String, Double> sentiments, List<Tweet> tweets) {
        this.states = states;
        this.sentiments = sentiments;
        this.tweets = tweets;
        setPreferredSize(new Dimension(1200, 800));
    }

    private double mapX(double lon) {
        return PADDING +
                ((lon - MIN_LON) / (MAX_LON - MIN_LON)) *
                        (getWidth() - 2 * PADDING) * SCALE;
    }

    private double mapY(double lat) {
        return PADDING +
                ((MAX_LAT - lat) / (MAX_LAT - MIN_LAT)) *
                        (getHeight() - 2 * PADDING) * SCALE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (State state : states) {

            Double sentiment = sentiments.get(state.getCode());
            Color color = getColorForSentiment(sentiment);
            g2.setColor(color);

            for (List<double[]> polygon : state.getPolygons()) {

                Path2D path = new Path2D.Double();
                boolean first = true;

                for (double[] p : polygon) {
                    double lon = p[0];
                    double lat = p[1];

                    double x = mapX(lon);
                    double y = mapY(lat);

                    if (first) {
                        path.moveTo(x, y);
                        first = false;
                    } else {
                        path.lineTo(x, y);
                    }
                }

                path.closePath();

                g2.fill(path);
                g2.setColor(Color.BLACK);
                g2.draw(path);
            }
        }

        g2.setColor(Color.RED);
        for (Tweet t : tweets) {
            double x = mapX(t.getLongitude());
            double y = mapY(t.getLatitude());
            g2.fillOval((int)x - 4, (int)y - 4, 8, 8);
        }

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 12));

        for (State state : states) {
            Point c = state.getCenter();
            double x = mapX(c.getLongitude());
            double y = mapY(c.getLatitude());
            g2.drawString(state.getCode(), (int)x - 10, (int)y + 5);
        }
    }

    private Color getColorForSentiment(Double s) {
        if (s == null) return Color.GRAY;

        double v = Math.max(-1, Math.min(1, s * 6));

        if (v < 0) {
            int r = (int)(255 * (1 + v));
            int g = r;
            return new Color(r, g, 255);
        } else {
            int b = (int)(255 * (1 - v));
            return new Color(255, 255, b);
        }
    }

    public static void showMap(List<State> states, Map<String, Double> sentiments, List<Tweet> tweets) {
        JFrame frame = new JFrame("USA Sentiment Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MapVisualizer(states, sentiments, tweets));
        frame.pack();
        frame.setVisible(true);
    }
}
