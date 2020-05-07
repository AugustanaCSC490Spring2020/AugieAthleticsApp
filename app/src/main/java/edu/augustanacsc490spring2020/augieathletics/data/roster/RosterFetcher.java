package edu.augustanacsc490spring2020.augieathletics.data.roster;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class RosterFetcher {

    private RosterListener rosterListener;
    private String url = "https://athletics.augustana.edu/sports/";
    private String sport;

    public RosterFetcher(RosterListener rosterListener, String sport) {
        this.rosterListener = rosterListener;
        this.sport = sport;
        url += sport + "/roster";
    }

    public void startFetchingRoster() {
        new RosterFetchTask().execute();

    }

    private class RosterFetchTask extends AsyncTask<Void, Void, Void> {
        Elements playerPosition, playerName, playerOther, coaches;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(url).get();
                playerName = document.select("div.sidearm-roster-player-name");
                playerPosition = document.select("div.sidearm-roster-player-position");
                playerOther = document.select("div.sidearm-roster-player-other.hide-on-large");
                coaches = document.select("div.sidearm-roster-coach-details.flex-item-1.column");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            String rosterText = "";
            for (int i = 0; i < playerName.size(); i++) {
                rosterText += playerName.get(i).text() + " " + playerPosition.get(i).text() + " " +
                        playerOther.get(i).text() + "\n";
            }
            for (int i = 0; i < coaches.size(); i++) {
                rosterText += coaches.get(i).text() + "\n";
            }
            Roster roster = new Roster(rosterText);
            rosterListener.rosterDownloaded(roster);
        }
    }

}

