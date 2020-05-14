package edu.augustanacsc490spring2020.augieathletics.ui.Schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import edu.augustanacsc490spring2020.augieathletics.R;

public class CalendarFragment extends Fragment {

    WebView calendar;
    private CalendarViewModel calendarViewModel;

    protected View onCreate(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mbasket, container, false);
//        goToRoster(root);



        calendar = root.findViewById(R.id.calendarWebView);
        calendar.loadUrl("https://athletics.augustana.edu/calendar");

        return root;
    }
}
