package com.dhar.twitter.test;

import java.io.IOException;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Hello world!
 *
 */
public class TwitterApp 
{
    public static void main( String[] args ) throws IOException, TwitterException
    {
	 	  ConfigurationBuilder cb = new ConfigurationBuilder();
	 	  cb.setDebugEnabled(true)
	 	   				.setOAuthConsumerKey("LpNPiifZkKbDXSXlICE20ViG3")
	 	   				.setOAuthConsumerSecret("qpqJaTmYerxty1GR2F9wKcZVYidJI8eN23u04PnbOfqZmAnz6V")
	 	   				.setOAuthAccessToken("144477628-C9KwmMLcPMDNJGHo9giDyVh5FA8aPTiaNCP52Gex")
	 	   				.setOAuthAccessTokenSecret("kW7ESogjCbUgcbtgjcyodAOm4VufF8DpI6VdgimPAWRJl");
    	  
	 	  TwitterFactory tf = new TwitterFactory(cb.build());
    	    
	 	  Twitter twitter = tf.getInstance();
	 	  
	 	  //twitter.get
	 	  
	 	  try {
	        Query query = new Query("Narendra modi");
	        QueryResult result;
	        do {
	            result = twitter.search(query);
	            List<Status> tweets = result.getTweets();
	            for (Status tweet : tweets) {
	            	System.out.println("******************************");
	                System.out.println("ScreenName:==>"+tweet.getUser().getScreenName());
	                System.out.println("Tweet Id:==>"+tweet.getId());
	                System.out.println("Tweet text===>"+tweet.getText());
	                System.out.println("******************************");
	            	
	            	//System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	            }
	        } while ((query = result.nextQuery()) != null);
	        System.exit(0);
	 	  } catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to search tweets: " + te.getMessage());
	        System.exit(-1);
	 	  }
    }
}
