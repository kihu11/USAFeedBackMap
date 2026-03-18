package model;

import java.util.List;

public class State {
    private final String code;
    private final List<List<double[]>> polygons;
    private final Point center;

    public State(String code, List<List<double[]>> polygons) {
        this.code = code;
        this.polygons = polygons;
        this.center = calculateCenter(polygons);
    }

    public State(String code, List<List<double[]>> polygons, Point center) {
        this.code = code;
        this.polygons = polygons;
        this.center = center;
    }

    public List<List<double[]>> getPolygons() {
        return polygons;
    }

    public String getCode() {
        return code;
    }

    public Point getCenter() {
        return center;
    }

    private Point calculateCenter(List<List<double[]>> polygons) {
        double sumLon = 0;
        double sumLat = 0;
        int count = 0;

        for (List<double[]> polygon : polygons) {
            for (double[] p : polygon) {
                sumLon += p[0];
                sumLat += p[1];
                count++;
            }
        }

        return new Point(sumLon / count, sumLat / count);
    }
}
