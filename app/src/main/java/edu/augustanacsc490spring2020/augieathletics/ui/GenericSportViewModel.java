package edu.augustanacsc490spring2020.augieathletics.ui;

import android.app.ProgressDialog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.augustanacsc490spring2020.augieathletics.data.roster.Roster;
import edu.augustanacsc490spring2020.augieathletics.data.roster.RosterListener;

public class GenericSportViewModel extends ViewModel implements RosterListener {

    private MutableLiveData<String> mText;
    private String url = "https://athletics.augustana.edu/sports/mens-basketball/roster";
    private ProgressDialog mProgressDialog;
    private String roster;


    public GenericSportViewModel() {

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