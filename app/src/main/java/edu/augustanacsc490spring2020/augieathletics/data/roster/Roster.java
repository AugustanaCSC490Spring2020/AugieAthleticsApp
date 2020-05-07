package edu.augustanacsc490spring2020.augieathletics.data.roster;

public class Roster {
    private String rosterText;

    public String getRosterText() {
        return rosterText;
    }

    public void setRosterText(String rosterText) {
        this.rosterText = rosterText;
    }

    public Roster(String rosterText) {
        this.rosterText = rosterText;
    }
}
