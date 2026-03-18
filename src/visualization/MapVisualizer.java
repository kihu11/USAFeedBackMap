package visualization;

import model.State;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class MapVisualizer extends JPanel {

    private final List<State> states;
    private final Map<String, Double> sentiments;

    public MapVisualizer(List<State> states, Map<String, Double> sentiments) {
        this.states = states;
        this.sentiments = sentiments;
        setPreferredSize(new Dimension(1200, 800));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));

        for (State state : states) {

            Double sentiment = sentiments.get(state.getCode());
            Color color = getColorForSentiment(sentiment);
            g2.setColor(color);

            for (List<double[]> polygon : state.getPolygons()) {

                Polygon poly = new Polygon();

                for (double[] point : polygon) {
                    int x = (int) ((point[0] + 130) * 10); // масштаб
                    int y = (int) ((50 - point[1]) * 10);  // инверсия Y
                    poly.addPoint(x, y);
                }

                g2.fillPolygon(poly);
                g2.setColor(Color.BLACK);
                g2.drawPolygon(poly);
            }
        }
    }

    private Color getColorForSentiment(Double s) {
        if (s == null) return Color.GRAY;      // нет данных
        if (s > 0) return Color.YELLOW;        // позитив
        if (s < 0) return Color.BLUE;          // негатив
        return Color.WHITE;                    // нейтральный
    }

    public static void showMap(List<State> states, Map<String, Double> sentiments) {
        JFrame frame = new JFrame("USA Sentiment Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MapVisualizer(states, sentiments));
        frame.pack();
        frame.setVisible(true);
    }
}
