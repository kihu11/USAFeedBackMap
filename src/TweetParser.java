

public class TweetParser {
    //[33.19788789, -96.6159409]
    // _
    // 2014-02-16 03:14:57
    // Being very spoiled tonight. #dinner #culinaryadventure #valentinegetaway #mckinney #texas http://t.co/cUF6pkkyPq

    private Tweet tweetParse(String line){

        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split("\t");

        if (parts.length != 4){
            throw new IllegalArgumentException("Неверное кол-во полей в строке");
        }

        String coordinates = parts[0].trim();
        String[] cordParts = coordinates.split(",");

        Double longitude = Double.parseDouble(cordParts[0].trim());
        Double latitude = Double.parseDouble(cordParts[0].trim());

    }

    static void main() {
        String part = "[33.19788789, -96.6159409]\t_\t2014-02-16 03:14:57\tBeing very spoiled tonight. #dinner #culinaryadventure #valentinegetaway #mckinney #texas http://t.co/cUF6pkkyPq\n";

        TweetParser parser = new TweetParser();
    }


}
