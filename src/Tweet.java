public class Tweet {

    private double latitude;
    private double longitude;
    private String dateTime;


    public Tweet(double latitude, double longitude, String dateTime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
    }

    public Tweet() {

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "latitude=" + latitude +
                ", longtitude=" + longitude +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
