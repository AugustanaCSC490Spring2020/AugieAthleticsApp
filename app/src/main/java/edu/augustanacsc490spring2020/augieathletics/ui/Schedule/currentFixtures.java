package edu.augustanacsc490spring2020.augieathletics.ui.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import edu.augustanacsc490spring2020.augieathletics.R;


public class currentFixtures extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ParseAdaptor adapterFixtures;
    private ArrayList<ParseItems> parseItems= new ArrayList<>();
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_fixtures);

        progressBar = findViewById(R.id.Progress_barFixtures);
        recyclerView = findViewById(R.id.RecylerViewFixtures);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterFixtures = new ParseAdaptor(parseItems,this);
        recyclerView.setAdapter(adapterFixtures);

        Context executeItems = new Context();
        AsyncTask<Void, Void, Void> execute = executeItems.execute();

    }
    private class Context extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(currentFixtures.this,android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(currentFixtures.this,android.R.anim.fade_out));
            adapterFixtures.notifyDataSetChanged();;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

                final String url = "athletics.augustana.edu/";

            try {
                final Document doc = Jsoup.connect(url).get();

                Elements dataFixtures = doc.select("div.row flex flex-align-center flex-justify-end");
                System.out.println(doc.outerHtml()); //To Check whether we getting any data from Website or Not
                int size=dataFixtures.size();
                for(int i = 0;i < size;i++)
                {
                    String imageData = dataFixtures.select(
                            "/images/default_cal_logo.png")
                            .select("img")
                            .eq(i)
                            .attr("img");
                    String title = dataFixtures.select("div.row")
                            .select("div")
                            .eq(i)
                            .text();
                    parseItems.add(new ParseItems(imageData,title));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
