package edu.uga.cs.countryquiz;

public class DataProvider {
    private String date;
    private String score;

    public DataProvider(String date, String score){
        this.date = date;
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public String getScore() {
        return score;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
