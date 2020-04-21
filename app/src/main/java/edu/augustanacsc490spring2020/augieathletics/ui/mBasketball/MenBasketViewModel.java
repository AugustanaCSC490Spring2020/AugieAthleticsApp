package edu.augustanacsc490spring2020.augieathletics.ui.mBasketball;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MenBasketViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private String url = "https://athletics.augustana.edu/sports/mens-basketball/roster";
    private ProgressDialog mProgressDialog;
    private Elements playerPosition, playerName, playerOther, coaches;
    private String roster;


    public MenBasketViewModel() {
        new Roster().execute();
        mText = new MutableLiveData<>();
        mText.setValue("Loading...");
    }

    public LiveData<String> getText() {
        return mText;
    }

    private class Roster extends AsyncTask<Void, Void, Void> {
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
            roster = "";
            for (int i = 0; i < playerName.size(); i++) {
                roster += playerName.get(i).text() + " " + playerPosition.get(i).text() + " " +
                        playerOther.get(i).text() + "\n";
            }
            for (int i = 0; i < coaches.size(); i++) {
                roster += coaches.get(i).text() + "\n";
            }
            mText.setValue(roster);
        }
    }
}