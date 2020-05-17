package edu.augustanacsc490spring2020.augieathletics.ui.sports;

import android.content.Intent;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import edu.augustanacsc490spring2020.augieathletics.R;

public class GenericSportFragment extends Fragment {

    private GenericSportViewModel genericSportViewModel;
    private Button rosterButton;
    private String sportFormatRoster;
    private String sportFormatGame;
    private RecyclerView recyclerViResults;
    private GameAdapter adapterResults;
    private ArrayList<GameItems> parseResults = new ArrayList<>();
    private ProgressBar progressBar;
    String dataResults="";
    private TextView text_mBasket;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_generic_sport, container, false);
        rosterButton = root.findViewById(R.id.rosterBtn);
        goToRoster(root);
        Bundle bundle = this.getActivity().getIntent().getExtras();
        if (bundle != null) {
            sportFormatRoster = bundle.getString("sportName");
            if (sportFormatGame.equals("wlax")){
                sportFormatGame = "Women's Lacrosse";
            } else if (sportFormatRoster.substring(0, 3).equals("men")) {
                sportFormatGame = "Men's " + sportFormatRoster.substring(5,6).toUpperCase() + sportFormatRoster.substring(6);
            } else if (sportFormatRoster.substring(0, 5).equals("women")) {
                sportFormatGame = "Women's " + sportFormatRoster.substring(7,8).toUpperCase() + sportFormatRoster.substring(8);
            } else {
                sportFormatGame = sportFormatRoster.substring(0, 1).toUpperCase() + sportFormatRoster.substring(1);
            }
        }


        progressBar = root.findViewById(R.id.Progress_barResults);
        recyclerViResults = root.findViewById(R.id.RecylerViewResults);

        recyclerViResults.setHasFixedSize(true);
        recyclerViResults.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapterResults = new GameAdapter(parseResults, this.getContext());
        recyclerViResults.setAdapter(adapterResults);

        GenericSportFragment.Context executeItems = new GenericSportFragment.Context();
        executeItems.execute();
        return root;
    }

    // Converts the roster into a format that will recognize sport in game feed.
    private String rosterFormatConverter(String teamName) {

        return null;
    }

    private void goToRoster(final View view) {
        rosterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), RosterActivity.class);
                intent.putExtras(getArguments());
                startActivity(intent);
            }
        });
    }


    private class Context extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getContext(),android.R.anim.fade_out));
            adapterResults.notifyDataSetChanged();
        }


        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL fixturesURL = new URL("https://athletics.augustana.edu/services/adaptive_components.ashx?type=results&start=0&count=10&sport_id=&name=&extra=%7B%7D");
                HttpURLConnection httpConnect = (HttpURLConnection) fixturesURL.openConnection();
                InputStream inputStream = httpConnect.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while(line != null)
                {
                    line = bufferedReader.readLine();
                    dataResults=dataResults+line;
                }
                System.out.println(dataResults);
                JSONArray fixturesArray= new JSONArray(dataResults);
                for (int i=0; i <=fixturesArray.length();i++)
                {
                    JSONObject resultsObject = (JSONObject) fixturesArray.get(i);

                    String gameDate = resultsObject.getString("date");
                    String date= gameDate.substring(5,10)+"-"+gameDate.substring(0,4);
                    String gameTime = resultsObject.getString("time");
                    String gameLocation = resultsObject.getString("location");

                    JSONObject augieTeamObject= resultsObject.getJSONObject("sport");
                    String augieSport = augieTeamObject.getString("title");

                    JSONObject opponentObject = resultsObject.getJSONObject("opponent");
                    String opponent = opponentObject.getString("title");
                    JSONObject scoreObject= resultsObject.getJSONObject("result");
                    String augieScore= scoreObject.getString("team_score");
                    String opponentScore= scoreObject.getString("opponent_score");

                    if (augieSport.equals(sportFormatGame)) {
                        parseResults.add(new GameItems(augieSport, opponent, augieScore, opponentScore, "Date: " + date, "Time: " + gameTime, "Location: " + gameLocation));
                        Log.d("items", "title: " + augieSport);
                    }

                }
                System.out.println("SizeOfArray"+fixturesArray.length());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    //https://developer.android.com/reference/android/util/Log

}
