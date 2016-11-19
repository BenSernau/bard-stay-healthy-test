package com.segal_gould.ben.healthy;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MenuScraperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_scraper);
        Download download = new Download();
        download.execute();
    }

    private class Download extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            String p = "";
            Document doc;
            try {
                doc = Jsoup.connect("http://segal-gould.com").get();

                Elements containers = doc.getElementsByClass("lead pretty-links");
                Elements paragraphs = containers.select("p");
                for (Element par : paragraphs) {
                    p += par.text() + "\n";
                    Log.e("Paragraph found: ", p);
                }
            } catch (IOException e) {
                Log.e("Caught IOException: ", e.getMessage());
            }
        return p;
        }

        @Override
        protected void onPostExecute(String result) {
            ((TextView)findViewById(R.id.main_text)).setText(result);
        }
    }
}

