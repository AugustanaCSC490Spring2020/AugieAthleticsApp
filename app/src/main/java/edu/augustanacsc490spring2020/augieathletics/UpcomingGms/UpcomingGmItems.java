package edu.augustanacsc490spring2020.augieathletics.UpcomingGms;

public class UpcomingGmItems {
    private String title;
    private String title2;
    private String Date;
    private String Time;
    private String location;

    public UpcomingGmItems(String title, String title2, String date, String time, String location) {
        this.title = title;
        this.title2 = title2;
        Date = date;
        Time = time;
        this.location = location;
    }

    public UpcomingGmItems(String title, String title2, String date, String time) {
        this.title = title;
        this.title2 = title2;
        Date = date;
        Time = time;
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
