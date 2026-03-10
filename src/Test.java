public class Test {

    static void main() {
        String line = "[33.19788789, -96.6159409]\t_\t2014-02-16 03:14:57\tBeing very spoiled tonight. #dinner #culinaryadventure #valentinegetaway #mckinney #texas http://t.co/cUF6pkkyPq\n";

        String[] parts = line.split("\t");

        if (parts.length != 4){
            throw new IllegalArgumentException("Неверное кол-во полей в строке");
        }

        String coordinates = parts[0].trim();
        String time = parts[2].trim();
        String message = parts[3].trim();

        coordinates = coordinates.replace("[", "").replace("]", "");

        String[] cordParts = coordinates.split(",");

        Double longitude = Double.parseDouble(cordParts[0].trim());
        Double latitude = Double.parseDouble(cordParts[1].trim());

        System.out.println(coordinates);
        System.out.println(latitude);
        System.out.println(longitude);
        System.out.println(time);
        System.out.println(message);

    }
}
