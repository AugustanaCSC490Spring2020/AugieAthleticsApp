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
private ParseAdaptor adapter;
private ArrayList<ParseItems> parseItems= new ArrayList<>();
private ProgressBar progressBar;
private Elements data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_fixtures);
        progressBar=findViewById(R.id.Progress_barF);
        recyclerView=(RecyclerView)findViewById(R.id.RecylerViewF);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParseAdaptor(parseItems,this);
        recyclerView.setAdapter(adapter);

        Context context = new Context();
        context.execute();

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
            adapter.notifyDataSetChanged();;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

                final String url = "https://athletics.augustana.edu/";



            try {
                final Document doc = Jsoup.connect(url).get();

                data = doc.select("div.slick-active.slick-slide.game");
                System.out.println(doc.outerHtml()); //To Check whether we getting any data from Website or Not
                int size=data.size();
                for(int i = 0;i < size;i++)
                {
                    String imageurl = data.select(
                            "div.athletics.augustana.edu/images/default_cal_logo.png")
                            .select("img")
                            .eq(i)
                            .attr("src");
                    String title = data.select("div.sidearm-section-label-fixed")
                            .select("div")
                            .eq(i)
                            .text();
                    parseItems.add(new ParseItems(imageurl,title));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
