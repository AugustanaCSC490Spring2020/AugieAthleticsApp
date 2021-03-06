package edu.augustanacsc490spring2020.augieathletics.Results;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;

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


import edu.augustanacsc490spring2020.augieathletics.MainActivity;
import edu.augustanacsc490spring2020.augieathletics.R;

public class Results extends AppCompatActivity {

    private RecyclerView recyclerViResults;
    private ResultsAdapter adapterResults;
    private ArrayList<ResultItems> parseresults= new ArrayList<>();
    private ProgressBar progressBar;
    Button returnHome;
    String dataResults="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        progressBar = findViewById(R.id.Progress_barResults);
        recyclerViResults = findViewById(R.id.RecylerViewResults);
        returnHome = findViewById(R.id.ReturnHome);

        recyclerViResults.setHasFixedSize(true);
        recyclerViResults.setLayoutManager(new LinearLayoutManager(this));
        adapterResults = new ResultsAdapter(parseresults,this);
        recyclerViResults.setAdapter(adapterResults);

        Results.Context executeItems = new Results.Context();
        executeItems.execute();


        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Results.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private class Context extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(Results.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(Results.this,android.R.anim.fade_out));
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
                for (int i=0; i <=fixturesArray.length();i++) {
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

                    parseresults.add(new ResultItems(augieSport,opponent,augieScore,opponentScore,"Date: "+date,"Time: "+gameTime,"Location: "+gameLocation));
                    Log.d( "items","title: " + augieSport);
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
