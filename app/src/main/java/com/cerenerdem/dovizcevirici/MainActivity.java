package com.cerenerdem.dovizcevirici;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    TextView turkishtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turkishtext = (TextView) findViewById(R.id.turkishtext);
    }


    public void click_degerlerigetir(View view) {

        DownloadData downloadData = new DownloadData();

        try {

            //String url = "http://data.fixer.io/api/latest?access_key=5aca0ebe96f3b4a12e865d6ce1dad0ad&format=1";
            String url = "http://data.fixer.io/api/latest?access_key=da1199bcca0fefec965c5b25df14663c&format=1";

            downloadData.execute(url);


        } catch (Exception e) {

        }


    }

    private class DownloadData extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data > 0) {

                    char character = (char) data;
                    result += character;

                    data = inputStreamReader.read();

                }


                return result;

            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("base");
                //System.out.println("base:" + base);

                String rates = jsonObject.getString("rates");


                JSONObject jsonObject1 = new JSONObject(rates);
                String turkislira = jsonObject1.getString("TRY");
                turkishtext.setText("TRY: " + turkislira);


            } catch (Exception e) {

            }


        }
    }
}
