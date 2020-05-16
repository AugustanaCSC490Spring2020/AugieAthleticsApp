package edu.augustanacsc490spring2020.augieathletics.ui.sports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.augustanacsc490spring2020.augieathletics.MainActivity;
import edu.augustanacsc490spring2020.augieathletics.R;
import edu.augustanacsc490spring2020.augieathletics.data.roster.Roster;
import edu.augustanacsc490spring2020.augieathletics.data.roster.RosterFetcher;
import edu.augustanacsc490spring2020.augieathletics.data.roster.RosterListener;
import edu.augustanacsc490spring2020.augieathletics.ui.GenericSportViewModel;

public class RosterActivity extends AppCompatActivity implements RosterListener {

    private Button returnBtn;
    private TextView rosterListTextView;
    private GenericSportViewModel genericSportViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster);

        RosterFetcher fetcher = new RosterFetcher(this, "mens-basketball");
        fetcher.startFetchingRoster();


        returnBtn = findViewById(R.id.rosterReturnBtn);
        rosterListTextView = findViewById(R.id.rosterListTextView);


    }

    public void returnToMain(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void rosterDownloaded(Roster roster) {
        rosterListTextView.setMovementMethod(new ScrollingMovementMethod());
        rosterListTextView.setText(roster.getRosterText());
    }
}
