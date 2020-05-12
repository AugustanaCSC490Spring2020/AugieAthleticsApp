package edu.augustanacsc490spring2020.augieathletics.data.calendar;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class SportsCalendar extends AsyncTask<Void, Void, Void>{
    String url;
    ProgressDialog mProgressDialog;
    String json;

    public SportsCalendar() {
        String urlStable = "https://athletics.augustana.edu/services/responsive-calendar.ashx?type=month&sport=0&location=all&date=";
        java.util.Date currentDate = Calendar.getInstance().getTime();
        String formattedDate = new SimpleDateFormat("M%'2F'd%'2F'yyyy+hh'%3A00%3A00'+aa").format(currentDate);
        url = urlStable + formattedDate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            json = IOUtils.toString(new URL(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        mProgressDialog.dismiss();
    }

}




