package edu.augustanacsc490spring2020.augieathletics.ui.Calendar;

public class fixturesItems {
    private String imageUrl;
    private String title;
    private String title2;
    private String Date;
    private String Time;
    private String location;

    public fixturesItems() {
    }

    public fixturesItems(String title, String title2, String date, String time, String location) {
        this.title = title;
        this.title2 = title2;
        Date = date;
        Time = time;
        this.location = location;
    }

    public fixturesItems(String title, String title2, String date, String time) {
        this.title = title;
        this.title2 = title2;
        Date = date;
        Time = time;
    }

    public String getImageurl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle2() {
        return title2;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public String getLocation() {
        return location;
    }
}
