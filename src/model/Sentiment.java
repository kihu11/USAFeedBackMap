package model;

public class Sentiment {

    private String words;
    private double value;

    public Sentiment(String words, double value) {
        this.words = words;
        this.value = value;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
