package edu.augustanacsc490spring2020.augieathletics.ui.Schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import edu.augustanacsc490spring2020.augieathletics.R;

public class CalendarFragment extends AppCompatActivity {

    WebView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_fragment);

        calendar = findViewById(R.id.calendarWebView);
        calendar.loadUrl("https://athletics.augustana.edu/calendar");

    }
}
