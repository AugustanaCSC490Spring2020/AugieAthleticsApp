package edu.augustanacsc490spring2020.augieathletics.ui.sports;

public class GameItems {

    private String Rtitle;
    private String Rtitle2;
    private String Rscore1;
    private String Rscore2;
    private String RDate;
    private String RTime;
    private String Rlocation;

    public GameItems(String rtitle, String rtitle2, String rscore1, String rscore2, String RDate, String RTime, String rlocation) {
        this.Rtitle = rtitle;
        this.Rtitle2 = rtitle2;
        this.Rscore1 = rscore1;
        this.Rscore2 = rscore2;
        this.RDate = RDate;
        this.RTime = RTime;
        this.Rlocation = rlocation;
    }

    public String getRtitle() {
        return Rtitle;
    }

    public String getRtitle2() {
        return Rtitle2;
    }

    public String getRscore1() {
        return Rscore1;
    }

    public String getRscore2() {
        return Rscore2;
    }

    public String getRDate() {
        return RDate;
    }

    public String getRTime() {
        return RTime;
    }

    public String getRlocation() {
        return Rlocation;
    }
}
