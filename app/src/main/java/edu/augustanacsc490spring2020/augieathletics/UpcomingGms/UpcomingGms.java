package edu.augustanacsc490spring2020.augieathletics.UpcomingGms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
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
import java.text.ParseException;
import java.util.ArrayList;

import edu.augustanacsc490spring2020.augieathletics.IntroActivity;
import edu.augustanacsc490spring2020.augieathletics.MainActivity;
import edu.augustanacsc490spring2020.augieathletics.NotificationCreator;
import edu.augustanacsc490spring2020.augieathletics.R;

import static edu.augustanacsc490spring2020.augieathletics.MainActivity.CHANNEL_ID;


public class UpcomingGms extends AppCompatActivity {
    private RecyclerView recyclerViFixtures;
    private UpcomingGmAdapter adapterFixtures;
    private ArrayList<UpcomingGmItems> parseItems= new ArrayList<>();
    private ProgressBar progressBar;
    Button returnHome,btnResults;
    String dataFixtures="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_gms);
        createNotificationChannel();

        progressBar = findViewById(R.id.Progress_barFixtures);
        recyclerViFixtures = findViewById(R.id.RecylerViewFixtures);
        returnHome = findViewById(R.id.btnReturnHome);
        btnResults=findViewById(R.id.btnResults);

        recyclerViFixtures.setHasFixedSize(true);
        recyclerViFixtures.setLayoutManager(new LinearLayoutManager(this));
        adapterFixtures = new UpcomingGmAdapter(parseItems,this);
        recyclerViFixtures.setAdapter(adapterFixtures);

        Context executeItems = new Context();
        executeItems.execute();


        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpcomingGms.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        btnResults.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(UpcomingGms.this, Results.class);
//                startActivity(intent);
//                finish();
//            }
//        });

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "AugieAthleticsChannel";
            String description = "Channel for Augie Athletics";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void createNotif(String augieSport, String opponent, String gameTime, String gameDate) throws ParseException {
        String title = "Upcoming Augie " + augieSport + " Game!";
        String content = "Augie is playing " + opponent + " Today at " + gameTime + "! Go Vikings!";
        NotificationCreator.createNotif(this, title, content, gameTime, gameDate);
        Intent intent = new Intent(this, NotificationCreator.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0, intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();
        long tenSecondsInMillis = 1000 * 10;

        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis,
                pendingIntent);

    }


    private class Context extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(UpcomingGms.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(UpcomingGms.this,android.R.anim.fade_out));
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
                    JSONObject fixturesObject = (JSONObject) fixturesArray.get(i);

                    String gameDate = fixturesObject.getString("date");
                    String date= gameDate.substring(5,10)+"-"+gameDate.substring(0,4);
                    String gameTime = fixturesObject.getString("time");
                    String location = fixturesObject.getString("location");

                    JSONObject augieSportObject= fixturesObject.getJSONObject("sport");
                    String augieTeamTitle = augieSportObject.getString("title");

                    JSONObject opponentObject= fixturesObject.getJSONObject("opponent");
                    String opponentTeamTitle = opponentObject.getString("title");

                    createNotif(augieTeamTitle,opponentTeamTitle,gameTime,gameDate);
                    gameTime = adjustAMPM(gameTime);
                    parseItems.add(new UpcomingGmItems(augieTeamTitle,opponentTeamTitle,"Date: "+date,"Time: "+gameTime,"Location: "+location));
                    Log.d( "items","title: " + augieTeamTitle);
                }
                System.out.println("SizeOfArray"+fixturesArray.length());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return null;
        }

        private String adjustAMPM(String str) {
            if (str.contains("p.m.")) {
                str = str.substring(0, str.length() - 5) + "pm";
            } else if (str.contains("a.m.")) {
                str = str.substring(0, str.length() - 5) + "am";
            }
            return str;
        }

    }
    //https://developer.android.com/reference/android/util/Log
}