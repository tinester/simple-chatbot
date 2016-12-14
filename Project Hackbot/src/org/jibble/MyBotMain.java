package org.jibble;

import java.io.IOException;

import org.jibble.pircbot.*;

public class MyBotMain {
	
	static WeatherFetcher query;
	
	public static void main(String[] args) throws Exception{
		
		// Now start our bot up
		FirstPircBot bot = new FirstPircBot();
		
		// Enable debugging output
		bot.setVerbose(true);
		
		// Connect to the IRC server
		bot.connect("irc.freenode.net");
		
		// Join the #pircbot channel
		bot.joinChannel("#cs2336");
		
		query = new WeatherFetcher();
		
		try {
            query.startWebRequest("dallas");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
