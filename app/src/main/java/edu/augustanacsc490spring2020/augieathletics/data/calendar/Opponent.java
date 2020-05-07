
package edu.augustanacsc490spring2020.augieathletics.data.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Opponent {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("prefix")
    @Expose
    private Object prefix;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("conference")
    @Expose
    private Object conference;
    @SerializedName("mascot")
    @Expose
    private Object mascot;
    @SerializedName("image")
    @Expose
    private Object image;

    public String toString() {
        return title;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getPrefix() {
        return prefix;
    }

    public void setPrefix(Object prefix) {
        this.prefix = prefix;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Object getConference() {
        return conference;
    }

    public void setConference(Object conference) {
        this.conference = conference;
    }

    public Object getMascot() {
        return mascot;
    }

    public void setMascot(Object mascot) {
        this.mascot = mascot;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

}
