package edu.augustanacsc490spring2020.augieathletics.ui.Schedule;

public class ParseItems {
    private String imageurl;
    private String title;

    public ParseItems() {
    }

    public ParseItems(String imageurl, String title) {
        this.imageurl = imageurl;
        this.title = title;
    }

    public ParseItems(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
