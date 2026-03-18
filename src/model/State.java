package model;

import java.util.List;

public class State {
    private final String code;
    private final List<Polygon> polygons;
    private final Point center;

    public State(String code, List<Polygon> polygons, Point center) {
        this.code = code;
        this.polygons = polygons;
        this.center = center;
    }

    public String getCode() {
        return code;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }

    public Point getCenter() {
        return center;
    }
}
