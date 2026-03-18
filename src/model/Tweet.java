package model;

public class Tweet {

    private double latitude;
    private double longitude;
    private String dateTime;
    private String message;


    public Tweet(double latitude, double longitude, String dateTime, String message) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
        this.message = message;
    }

    public Tweet() {

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        return "model.Tweet{" +
                "latitude=" + latitude +
                ", longtitude=" + longitude +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
