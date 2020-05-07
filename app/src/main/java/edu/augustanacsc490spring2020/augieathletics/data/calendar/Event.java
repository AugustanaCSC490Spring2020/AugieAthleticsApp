
package edu.augustanacsc490spring2020.augieathletics.data.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("conference")
    @Expose
    private boolean conference;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("location_indicator")
    @Expose
    private String locationIndicator;
    @SerializedName("show_at_vs")
    @Expose
    private boolean showAtVs;
    @SerializedName("at_vs")
    @Expose
    private String atVs;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("noplay_text")
    @Expose
    private String noplayText;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("promotion")
    @Expose
    private Object promotion;
    @SerializedName("is_a_doubleheader")
    @Expose
    private boolean isADoubleheader;
    @SerializedName("sport")
    @Expose
    private Sport sport;
    @SerializedName("opponent")
    @Expose
    private Opponent opponent;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("facility")
    @Expose
    private Facility facility;
    @SerializedName("tournament")
    @Expose
    private Object tournament;
    @SerializedName("gamelinks")
    @Expose
    private Object gamelinks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isConference() {
        return conference;
    }

    public void setConference(boolean conference) {
        this.conference = conference;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationIndicator() {
        return locationIndicator;
    }

    public void setLocationIndicator(String locationIndicator) {
        this.locationIndicator = locationIndicator;
    }

    public boolean isShowAtVs() {
        return showAtVs;
    }

    public void setShowAtVs(boolean showAtVs) {
        this.showAtVs = showAtVs;
    }

    public String getAtVs() {
        return atVs;
    }

    public void setAtVs(String atVs) {
        this.atVs = atVs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoplayText() {
        return noplayText;
    }

    public void setNoplayText(String noplayText) {
        this.noplayText = noplayText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getPromotion() {
        return promotion;
    }

    public void setPromotion(Object promotion) {
        this.promotion = promotion;
    }

    public boolean isIsADoubleheader() {
        return isADoubleheader;
    }

    public void setIsADoubleheader(boolean isADoubleheader) {
        this.isADoubleheader = isADoubleheader;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public void setOpponent(Opponent opponent) {
        this.opponent = opponent;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Object getTournament() {
        return tournament;
    }

    public void setTournament(Object tournament) {
        this.tournament = tournament;
    }

    public Object getGamelinks() {
        return gamelinks;
    }

    public void setGamelinks(Object gamelinks) {
        this.gamelinks = gamelinks;
    }

}
