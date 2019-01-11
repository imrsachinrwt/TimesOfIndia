package com.sachin.example.timesofindia;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
//    private TextView newsTitle;
//    private TextView copyRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms";
        listView = findViewById(R.id.listView);
//        newsTitle = findViewById(R.id.newsTitle);
//        copyRight = findViewById(R.id.copyRight);

        DownloadData downloadData = new DownloadData();
        downloadData.execute(url);


    }

    class DownloadData extends AsyncTask<String, Void, String> {
        private static final String TAG = "DownloadData";

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ParseApplication parseApplication = new ParseApplication();
            parseApplication.parse(s);
//            copyRight.setText(parseApplication.getApplications().get(0).getCopyRight());
//            newsTitle.setText(parseApplication.getApplications().get(0).getNewsTitle());
            arrayAdapter adapter = new arrayAdapter(MainActivity.this, R.layout.feed_record, parseApplication.getApplications());
            listView.setAdapter(adapter);

        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder data = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                int charRead;
                char[] inputBuffer = new char[500];
                while (true) {
                    charRead = reader.read(inputBuffer);
                    if (charRead < 0) {
                        break;

                    }
                    if (charRead > 0) {
                        data.append(String.valueOf(inputBuffer, 0, charRead));
                    }

                }


            } catch (MalformedURLException e) {
                Log.d(TAG, "doInBackground: Invalid Url");

            } catch (IOException e) {
                Log.d(TAG, "doInBackground: downloading error");

            }
            Log.d(TAG, "doInBackground: downloading data..." + data.toString());
            return data.toString();
        }
    }


}
