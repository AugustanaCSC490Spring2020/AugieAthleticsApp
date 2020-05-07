
package edu.augustanacsc490spring2020.augieathletics.data.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("team_score")
    @Expose
    private Object teamScore;
    @SerializedName("opponent_score")
    @Expose
    private Object opponentScore;
    @SerializedName("prescore_info")
    @Expose
    private Object prescoreInfo;
    @SerializedName("postscore_info")
    @Expose
    private Object postscoreInfo;
    @SerializedName("boxscore")
    @Expose
    private Object boxscore;
    @SerializedName("recap")
    @Expose
    private Object recap;

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(Object teamScore) {
        this.teamScore = teamScore;
    }

    public Object getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(Object opponentScore) {
        this.opponentScore = opponentScore;
    }

    public Object getPrescoreInfo() {
        return prescoreInfo;
    }

    public void setPrescoreInfo(Object prescoreInfo) {
        this.prescoreInfo = prescoreInfo;
    }

    public Object getPostscoreInfo() {
        return postscoreInfo;
    }

    public void setPostscoreInfo(Object postscoreInfo) {
        this.postscoreInfo = postscoreInfo;
    }

    public Object getBoxscore() {
        return boxscore;
    }

    public void setBoxscore(Object boxscore) {
        this.boxscore = boxscore;
    }

    public Object getRecap() {
        return recap;
    }

    public void setRecap(Object recap) {
        this.recap = recap;
    }

}
