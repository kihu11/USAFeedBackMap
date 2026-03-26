package parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.State;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StateParser {

    public List<State> parseStates(String filename) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new FileReader(filename));

        List<State> states = new ArrayList<>();

        Iterator<String> fieldNames = root.fieldNames();

        while (fieldNames.hasNext()) {
            String code = fieldNames.next();
            JsonNode polygonsNode = root.get(code);

            List<List<double[]>> polygons = new ArrayList<>();

            for (JsonNode polygonNode : polygonsNode) {

                if (!polygonNode.isEmpty() && polygonNode.get(0).isArray()
                        && polygonNode.get(0).get(0).isArray()) {

                    for (JsonNode ringNode : polygonNode) {
                        List<double[]> polygon = parsePoints(ringNode);
                        if (!polygon.isEmpty()) polygons.add(polygon);
                    }

                } else {
                    List<double[]> polygon = parsePoints(polygonNode);
                    if (!polygon.isEmpty()) polygons.add(polygon);
                }
            }

            if (!polygons.isEmpty()) {
                states.add(new State(code, polygons));
            }
        }

        return states;
    }

    private List<double[]> parsePoints(JsonNode node) {
        List<double[]> polygon = new ArrayList<>();

        for (JsonNode pointNode : node) {
            if (pointNode.size() >= 2) {
                double lon = pointNode.get(0).asDouble();
                double lat = pointNode.get(1).asDouble();
                polygon.add(new double[]{lon, lat});
            }
        }

        return polygon;
    }
}