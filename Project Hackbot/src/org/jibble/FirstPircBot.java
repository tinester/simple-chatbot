package org.jibble;
import java.io.IOException;

import org.jibble.pircbot.*;

public class FirstPircBot extends PircBot{
	
	WeatherFetcher query;
	
	public FirstPircBot(){
		this.setName("ZACHBEWACK");
		query = new WeatherFetcher();
	}
	
	// Enter weather city into chat(#jibble @ freenode.net) to get the weather for that city
	public void onMessage(String channel, String sender,
            String login, String hostname, String message) {
		
			String weather, city;
			
			if (message.toLowerCase().startsWith("weather")) {
				try {
					city = message.substring(8);
					city =city.replaceAll(" ", "");
					System.out.println(city);
					weather = query.startWebRequest(city);
					weather = weather.replaceAll("\"", "");
					sendMessage(channel, sender + ": The weather is " + weather);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					weather = "";
					sendMessage(channel, sender + ": That isn't a valid city");
				}
			}
			else if (message.toLowerCase().startsWith("help")) {
				sendMessage(channel, sender + ": Type weather (city) to get the conditions in that city.");
			}
			else if (message.toLowerCase().startsWith("hello")) {
				sendMessage(channel, sender + ": sup man");
			}
			else if(true){
				
			}
	}
}
