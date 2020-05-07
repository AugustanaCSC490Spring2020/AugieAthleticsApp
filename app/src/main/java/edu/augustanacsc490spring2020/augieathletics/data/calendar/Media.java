
package edu.augustanacsc490spring2020.augieathletics.data.calendar;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("tv")
    @Expose
    private Object tv;
    @SerializedName("radio")
    @Expose
    private Object radio;
    @SerializedName("video")
    @Expose
    private Object video;
    @SerializedName("audio")
    @Expose
    private Object audio;
    @SerializedName("stats")
    @Expose
    private Object stats;
    @SerializedName("tickets")
    @Expose
    private Object tickets;
    @SerializedName("preview")
    @Expose
    private Object preview;
    @SerializedName("gamefiles")
    @Expose
    private Object gamefiles;
    @SerializedName("custom_display_fields")
    @Expose
    private List<Object> customDisplayFields = null;

    public Object getTv() {
        return tv;
    }

    public void setTv(Object tv) {
        this.tv = tv;
    }

    public Object getRadio() {
        return radio;
    }

    public void setRadio(Object radio) {
        this.radio = radio;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(Object video) {
        this.video = video;
    }

    public Object getAudio() {
        return audio;
    }

    public void setAudio(Object audio) {
        this.audio = audio;
    }

    public Object getStats() {
        return stats;
    }

    public void setStats(Object stats) {
        this.stats = stats;
    }

    public Object getTickets() {
        return tickets;
    }

    public void setTickets(Object tickets) {
        this.tickets = tickets;
    }

    public Object getPreview() {
        return preview;
    }

    public void setPreview(Object preview) {
        this.preview = preview;
    }

    public Object getGamefiles() {
        return gamefiles;
    }

    public void setGamefiles(Object gamefiles) {
        this.gamefiles = gamefiles;
    }

    public List<Object> getCustomDisplayFields() {
        return customDisplayFields;
    }

    public void setCustomDisplayFields(List<Object> customDisplayFields) {
        this.customDisplayFields = customDisplayFields;
    }

}
