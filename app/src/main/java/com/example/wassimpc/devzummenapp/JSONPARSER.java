package com.example.wassimpc.devzummenapp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public  class JSONPARSER {
    static JSONObject obj=null ;
    static InputStream is = null ;
    String json ="" ;

    JSONObject makeHttpRequest (String url , String method , ArrayList<NameValuePair> params ){


        if ( method == "POST" ){
            DefaultHttpClient httpClient = new DefaultHttpClient() ;


            HttpPost httpPost = new HttpPost(url);

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params));


                HttpResponse httpResponse = httpClient.execute(httpPost);

                HttpEntity httpEntity = httpResponse.getEntity() ;
                is = httpEntity.getContent() ;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }




        }/////////
        else if (method == "GET") {



            DefaultHttpClient httpClient = new DefaultHttpClient();
            String param = URLEncodedUtils.format(params, "UTF-8");
            url += "?" + param ;

            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse;

            try {
                httpResponse = httpClient.execute(httpGet);



                HttpEntity httpEntity = httpResponse.getEntity() ;

                is = httpEntity.getContent() ;
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }



        BufferedReader reader;
        try {
            reader = new BufferedReader( new InputStreamReader(is ,"iso-8859-1"), 8 );


            StringBuilder sb = new  StringBuilder()  ;

            String line ="" ;

            while ((line = reader.readLine() )!= null ){
                sb.append(line +"\n") ;

            }
            is.close();
            json= sb.toString() ;


            obj = new JSONObject(json);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return obj;


    }




}
