package edu.augustanacsc490spring2020.augieathletics.ui.mBasketball;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import edu.augustanacsc490spring2020.augieathletics.data.roster.Roster;
import edu.augustanacsc490spring2020.augieathletics.data.roster.RosterFetcher;
import edu.augustanacsc490spring2020.augieathletics.data.roster.RosterListener;

public class MenBasketViewModel extends ViewModel implements RosterListener {

    private MutableLiveData<String> mText;
    private String url = "https://athletics.augustana.edu/sports/mens-basketball/roster";
    private ProgressDialog mProgressDialog;
    private String roster;


    public MenBasketViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("Loading...");
    }

    public LiveData<String> getText() {
        return mText;
    }

    @Override
    public void rosterDownloaded(Roster roster) {
        mText.setValue(roster.getRosterText());
    }
}