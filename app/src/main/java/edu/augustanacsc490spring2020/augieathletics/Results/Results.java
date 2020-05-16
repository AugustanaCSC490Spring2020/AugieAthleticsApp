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


import edu.augustanacsc490spring2020.augieathletics.R;
import edu.augustanacsc490spring2020.augieathletics.ui.home.currentFixtures;

public class Results extends AppCompatActivity {

    private RecyclerView recyclerViResults;
    private resultsAdapter adapterResults;
    private ArrayList<resultitems> parseresults= new ArrayList<>();
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
        adapterResults = new resultsAdapter(parseresults,this);
        recyclerViResults.setAdapter(adapterResults);

        Results.Context executeItems = new Results.Context();
        executeItems.execute();


        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Results.this, currentFixtures.class);
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
                for (int i=0; i <=fixturesArray.length();i++)
                {
                    JSONObject resultsObject = (JSONObject) fixturesArray.get(i);

                    String RDate = resultsObject.getString("date");
                    String date= RDate.substring(0,10);
                    String RTime = resultsObject.getString("time");
                    String Rlocation = resultsObject.getString("location");

                    JSONObject firstObject= resultsObject.getJSONObject("sport");
                    String teamTitle = firstObject.getString("title");

                    JSONObject secondObject= resultsObject.getJSONObject("opponent");
                    String teamTitle2 = secondObject.getString("title");
                    JSONObject scoreObject= resultsObject.getJSONObject("result");
                    String scoreT1= scoreObject.getString("team_score");
                    String scoreT2= scoreObject.getString("opponent_score");

                    parseresults.add(new resultitems(teamTitle,teamTitle2,scoreT1,scoreT2,"Date: "+date,"Time: "+RTime,"Location: "+Rlocation));
                    Log.d( "items","title: " + teamTitle);

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
