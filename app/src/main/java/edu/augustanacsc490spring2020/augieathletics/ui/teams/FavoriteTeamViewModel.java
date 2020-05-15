package edu.augustanacsc490spring2020.augieathletics.ui.teams;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoriteTeamViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavoriteTeamViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}