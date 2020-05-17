package edu.augustanacsc490spring2020.augieathletics.ui.sports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.augustanacsc490spring2020.augieathletics.MainActivity;
import edu.augustanacsc490spring2020.augieathletics.R;
import edu.augustanacsc490spring2020.augieathletics.data.roster.Roster;
import edu.augustanacsc490spring2020.augieathletics.data.roster.RosterFetcher;
import edu.augustanacsc490spring2020.augieathletics.data.roster.RosterListener;

public class RosterActivity extends AppCompatActivity implements RosterListener {

    private Button returnBtn;
    private TextView rosterTitle;
    private TextView rosterListTextView;
    private GenericSportViewModel genericSportViewModel;
    private String sportTeam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster);

        sportTeam = this.getIntent().getExtras().getString("sportName");

        rosterTitle = findViewById(R.id.rosterTitle);
        setRosterTitle();

        RosterFetcher fetcher = new RosterFetcher(this, sportTeam);
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

    public void setRosterTitle() {
        String sportFormatGame;
        if (sportTeam.equals("wlax")){
            sportFormatGame = "Women's Lacrosse";
        } else if (sportTeam.substring(0, 3).equals("men")) {
            sportFormatGame = "Men's " + sportTeam.substring(5,6).toUpperCase() + sportTeam.substring(6).replace("-"," ");
        } else if (sportTeam.substring(0, 5).equals("women")) {
            sportFormatGame = "Women's " + sportTeam.substring(7,8).toUpperCase() + sportTeam.substring(8).replace("-"," ");
        } else {
            sportFormatGame = sportTeam.substring(0, 1).toUpperCase() + sportTeam.substring(1).replace("-"," ");
        }
        rosterTitle.setText(sportFormatGame + " Roster");
    }

}
