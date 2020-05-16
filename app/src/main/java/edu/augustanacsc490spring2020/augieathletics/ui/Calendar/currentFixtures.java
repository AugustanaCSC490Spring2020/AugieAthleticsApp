package edu.augustanacsc490spring2020.augieathletics.ui.Calendar;

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
import edu.augustanacsc490spring2020.augieathletics.Results.Results;


public class currentFixtures extends AppCompatActivity {
    private RecyclerView recyclerViFixtures;
    private fixturesAdapter adapterFixtures;
    private ArrayList<fixturesItems> parseItems= new ArrayList<>();
    private ProgressBar progressBar;
    Button returnHome,btnResults;
    String dataFixtures="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_fixtures);

        progressBar = findViewById(R.id.Progress_barFixtures);
        recyclerViFixtures = findViewById(R.id.RecylerViewFixtures);
        returnHome = findViewById(R.id.btnReturnHome);
        btnResults=findViewById(R.id.btnResults);

        recyclerViFixtures.setHasFixedSize(true);
        recyclerViFixtures.setLayoutManager(new LinearLayoutManager(this));
        adapterFixtures = new fixturesAdapter(parseItems,this);
        recyclerViFixtures.setAdapter(adapterFixtures);

        Context executeItems = new Context();
        executeItems.execute();


        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(currentFixtures.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(currentFixtures.this, Results.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private class Context extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(currentFixtures.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(currentFixtures.this,android.R.anim.fade_out));
            adapterFixtures.notifyDataSetChanged();
        }


        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL fixturesURL = new URL("https://athletics.augustana.edu/services/adaptive_components.ashx?type=events&start=0&count=10&sport_id=&name=&extra=%7B%7D");
                HttpURLConnection httpConnect = (HttpURLConnection) fixturesURL.openConnection();
                InputStream inputStream = httpConnect.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while(line != null)
                {
                    line = bufferedReader.readLine();
                    dataFixtures=dataFixtures+line;
                }
                System.out.println(dataFixtures);
                JSONArray fixturesArray= new JSONArray(dataFixtures);
                for (int i=0; i <=fixturesArray.length();i++)
                {
                    JSONObject fixutresObject = (JSONObject) fixturesArray.get(i);

                    String teamDate = fixutresObject.getString("date");
                    String date= teamDate.substring(0,10);
                    String teamTime = fixutresObject.getString("time");
                    String location = fixutresObject.getString("location");

                    JSONObject firstObject= fixutresObject.getJSONObject("sport");
                    String teamTitle = firstObject.getString("title");

                    JSONObject secondObject= fixutresObject.getJSONObject("opponent");
                    String teamTitle2 = secondObject.getString("title");

                    parseItems.add(new fixturesItems(teamTitle,teamTitle2,"Date: "+date,"Time: "+teamTime,"Location: "+location));
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