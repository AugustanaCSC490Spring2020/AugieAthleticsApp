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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;

import edu.augustanacsc490spring2020.augieathletics.MainActivity;
import edu.augustanacsc490spring2020.augieathletics.R;


public class currentFixtures extends AppCompatActivity {
    private RecyclerView recyclerViFixtures;
    private fixturesAdapter adapterFixtures;
    private ArrayList<fixturesItems> parseItems= new ArrayList<>();
    private ProgressBar progressBar;
    Button returnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_fixtures);

        progressBar = findViewById(R.id.Progress_barFixtures);
        recyclerViFixtures = findViewById(R.id.RecylerViewFixtures);
        returnHome = findViewById(R.id.btnReturnHome);

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

               String url = "https://athletics.augustana.edu/";

            try {
                final Document doc = Jsoup.connect(url).get();
                String fixtureSelector = "game slick-slide slick-active";
                //http://scrapingauthority.com/2016/08/16/web-scraping-in-java-with-jsoup/
                Elements dataFixtures = doc.select(fixtureSelector);
               // System.out.println(doc.outerHtml()); //To Check whether we getting any data from Website or Not

                int size=dataFixtures.size();

                Log.d("doc", "doc: "+doc);   //https://developer.android.com/reference/android/util/Log
                Log.d("data", "data: "+dataFixtures);
                Log.d("size", ""+size);

                for(int i = 0;i < size;i++)
                {
                    String imageUrl = dataFixtures.select("section.sidearm-section-label-fixed")
                            .select("img")
                            .eq(i)
                            .attr("src");
                    String title = dataFixtures.select("div.sport")
                            .select("div")
                            .eq(i)
                            .text();
                    parseItems.add(new fixturesItems(imageUrl,title));
                    Log.d("items", "img: " + imageUrl + " . title: " + title);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
