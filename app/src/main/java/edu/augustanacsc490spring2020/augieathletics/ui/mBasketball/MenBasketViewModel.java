package edu.augustanacsc490spring2020.augieathletics.ui.mBasketball;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MenBasketViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MenBasketViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Men's Basketball Team fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}