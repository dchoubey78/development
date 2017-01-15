package com.dhar.facebook.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;


/**
 * Hello world!
 *
 */
public class FacebookApp 
{
    public static void main( String[] args )
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
        	.setOAuthAccessToken("922687574503437|1qTK1S2tSKTDQcCj-EcYzzOcPi0")
        	.setOAuthAppId("922687574503437")
        	.setOAuthAppSecret("32783b613285593642a3f0061e567516")
        	.setOAuthPermissions("email, publish_stream, id, name, first_name, last_name, read_stream , generic")
        	.setUseSSL(true)
        	.setJSONStoreEnabled(true);
        Configuration configuration = cb.build();
        FacebookFactory fc = new FacebookFactory(configuration);
        Facebook facebook = fc.getInstance();
        try { // Set search string and get results 
        	String searchPost = "TripAdvisor"; 
        	Date date = new Date(); 
        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd_MM_yyyy_hh_mm"); 
        	String fileName = "D:\\FacebookConfigFolder\\File\\" + searchPost + "_" + simpleDateFormat.format(date) + ".txt"; 
        	String results = getFacebookPostes(facebook, searchPost); 
        	File file = new File(fileName); 
        	if (!file.exists()) { 
        		file.createNewFile(); 
        		FileWriter fw = new FileWriter(file.getAbsoluteFile()); 
        		BufferedWriter bw = new BufferedWriter(fw); 
        		bw.write(results); bw.close(); 
        		System.out.println("Completed"); 
        		} 
        	} catch (IOException e) { 
        		e.printStackTrace(); 
        	} catch (FacebookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
     }
    
 // This method is used to get Facebook posts based on the search string set // above 
    public static String getFacebookPostes(Facebook Facebook, String searchPost) throws FacebookException { 
    	String searchResult = "Item : " + searchPost + "\n"; StringBuffer searchMessage = new StringBuffer(); 
    	ResponseList<Post> results = Facebook.getPosts(searchPost); 
    	for (Post post : results) { 
    		System.out.println(post.getMessage()); 
    		searchMessage.append(post.getMessage() + "\n"); 
    		for (int j = 0; j < post.getComments().size(); j++) { 
    			searchMessage.append(post.getComments().get(j).getFrom() .getName() + ", "); 
    			searchMessage.append(post.getComments().get(j).getMessage() + ", "); 
    			searchMessage.append(post.getComments().get(j).getCreatedTime() + ", "); 
    			searchMessage.append(post.getComments().get(j).getLikeCount() + "\n"); 
    			} 
    		} 
    	String feedString = getFacebookFeed(Facebook, searchPost); 
    	searchResult = searchResult + searchMessage.toString(); 
    	searchResult = searchResult + feedString; return searchResult; 
    }
    
    public static String getFacebookFeed(Facebook Facebook, String searchPost) throws FacebookException { 
    	String searchResult = ""; 
    	StringBuffer searchMessage = new StringBuffer(); 
    	ResponseList<Post> results = Facebook.getFeed(searchPost); 
    	for (Post post : results) { 
    		System.out.println(post.getMessage()); 
    		searchMessage.append(post.getFrom().getName() + ", "); 
    		searchMessage.append(post.getMessage() + ", "); 
    		searchMessage.append(post.getCreatedTime() + "\n"); 
    		} 
    	searchResult = searchResult + searchMessage.toString(); 
    	return searchResult; 
    }
    
    /*public static String stringToJson(String data) { 
        JsonConfig cfg = new JsonConfig(); 
    	try { 
    		JSONObject jsonObject = JSONObject.fromObject(data, cfg); 
    		System.out.println("JSON = " + jsonObject.toString()); 
    		} 
    	catch (Exception e) { 
    		e.printStackTrace(); 
    	} 
    	    return "JSON Created"; 
    	} */
    
}
