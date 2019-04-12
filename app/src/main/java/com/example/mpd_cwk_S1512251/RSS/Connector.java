package com.example.mpd_cwk_S1512251.RSS;
// Name                 Gavin Walter Jaap
// Student ID           S1512251
// Programme of Study   Computing
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connector {
   public static Object connect(String urlAddress)
   {
       try
       {
           URL url=new URL(urlAddress);
           HttpURLConnection con= (HttpURLConnection) url.openConnection();

           //GET PROPERTIES
           con.setRequestMethod("GET");
           con.setConnectTimeout(15000);
           con.setReadTimeout(15000);
           con.setDoInput(true);

           return con;

       } catch (MalformedURLException e) {
           e.printStackTrace();
           return ErrorTracker.WRONG_URL_FORMAT;

       } catch (IOException e) {
           e.printStackTrace();
           return ErrorTracker.CONNECTION_ERROR;
       }
   }

}
