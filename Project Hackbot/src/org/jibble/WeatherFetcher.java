package org.jibble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
 
public class WeatherFetcher
{
   
    String startWebRequest(String city) throws IOException
    {
    	  String weatherURL = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=602ff69bd71ac169f90b8f66a7a48cf0";
    	  
          StringBuilder result = new StringBuilder(); //this is going to hold the JSON Response from the server
          URL url = new URL(weatherURL);
          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
          conn.setRequestMethod("GET");
          BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          String line;
          while ((line = rd.readLine()) != null) {
             result.append(line);
          }
          rd.close();
          System.out.println(result.toString());
         
         String weather =  parseJsonWeather(result.toString());
         
         System.out.println("Weather is "+ weather);
         
          return weather;
       }
   
    String parseJsonWeather(String json) //json is a string of json, we get this from making our request
    {
    	String parsedValue;
        JsonElement jelement = new JsonParser().parse(json); //you will parse it first into a JSONElement
        JsonObject  MasterObject = jelement.getAsJsonObject();  //You will then take that jelement, and then break it down into a json object. Use the JSONEDITORONLINE website, basically, you are trying narrow down to whatever you want
        JsonArray weather = MasterObject.get("weather").getAsJsonArray();
        JsonObject objectInsideArray = weather.get(0).getAsJsonObject();
        parsedValue = objectInsideArray.get("main").toString();
        return parsedValue;  //return our longitude
    }
    
    //String parseJson
    
}
