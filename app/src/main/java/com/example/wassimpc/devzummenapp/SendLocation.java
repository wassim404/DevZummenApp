package com.example.wassimpc.devzummenapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mahdi on 18/12/2015.
 */
public class SendLocation extends AsyncTask<String, Void, String> {
    JSONObject jsonObject;
    String feedback;
    private static String rl, nm;
    // context liaison entre le signin et mainactivity
    public Context c;
    public TextView roleField;


    public SendLocation(Context c ) {

        this.c = c;
        // role = roleField;
        // this.role=role ;

    }

    @Override
    protected String doInBackground(String... arg0) {

        JSONPARSER jParser = new JSONPARSER();
        String lng = (String) arg0[0];
        String lat = (String) arg0[1];
        String date = (String) arg0[2];
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("lng",lng ));
        params.add(new BasicNameValuePair("lat", lat));
        params.add(new BasicNameValuePair("date", date));

        try {

            jsonObject = jParser.makeHttpRequest("http://192.168.1.7/connectDevZusammen.php", "GET", params); // if you want to test it just put your computer's IPV4
            feedback = jsonObject.toString();
        } catch (Exception e) {

        }

        return feedback;

    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);



    }

}
