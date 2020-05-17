package edu.augustanacsc490spring2020.augieathletics.ui.Calendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import edu.augustanacsc490spring2020.augieathletics.R;

public class CalendarFragment extends Fragment {

    private CalendarViewModel mViewModel;
    private WebView calendarWebView;
    private View root;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarWebView = root.findViewById(R.id.calendarWebView);
        calendarWebView.loadUrl("https://athletics.augustana.edu/calendar");
        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calendarWebView = root.findViewById(R.id.calendarWebView);
        calendarWebView.loadUrl("https://athletics.augustana.edu/calendar");
        WebSettings webSettings = calendarWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

}
