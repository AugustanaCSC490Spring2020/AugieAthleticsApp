package edu.augustanacsc490spring2020.augieathletics.data.calendar;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.IOUtils;

public class Calendar extends AsyncTask<Void, Void, Void>{
    String url = "https://athletics.augustana.edu/services/responsive-calendar.ashx?type=month&sport=0&location=all&date=4%2F20%2F2020+12%3A00%3A00+AM";
    ProgressDialog mProgressDialog;
    String json;

    public Calendar(String url) {
        this.url = url;
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




