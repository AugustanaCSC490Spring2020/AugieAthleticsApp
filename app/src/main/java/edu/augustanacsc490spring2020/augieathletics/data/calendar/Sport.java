
package edu.augustanacsc490spring2020.augieathletics.data.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sport {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("shortname")
    @Expose
    private String shortname;
    @SerializedName("short_display")
    @Expose
    private Object shortDisplay;
    @SerializedName("global_sport_shortname")
    @Expose
    private String globalSportShortname;

    public String toString() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public Object getShortDisplay() {
        return shortDisplay;
    }

    public void setShortDisplay(Object shortDisplay) {
        this.shortDisplay = shortDisplay;
    }

    public String getGlobalSportShortname() {
        return globalSportShortname;
    }

    public void setGlobalSportShortname(String globalSportShortname) {
        this.globalSportShortname = globalSportShortname;
    }

}
