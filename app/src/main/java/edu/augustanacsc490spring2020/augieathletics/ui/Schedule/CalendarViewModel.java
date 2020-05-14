package edu.augustanacsc490spring2020.augieathletics.ui.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.app.ProgressDialog;
import android.os.Bundle;

public class CalendarViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ProgressDialog mProgressDialog;

    public CalendarViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("Loading...");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
