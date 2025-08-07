package com.qa.cql.base;

import java.io.IOException;
import java.util.Properties;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.AuthenticationException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;


public class CQLBase {
	
	static Properties prop;
	static Cluster cluster = null;
    static Session session = null;
    static CQLBase instance = null;
    
   public CQLBase() {
		
		 //Load property file
		try {
			prop = new Properties();
			prop.load(CQLBase.class.getClassLoader().getResourceAsStream("config.properties"));
			
			System.out.println(prop.getProperty("username"));
			
					
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
	}
	

	public static Session getCQLConnection()
	{
		//properties read from property file
		String username = prop.getProperty("username"); 
	    String password =  prop.getProperty("password");
	    String host =  prop.getProperty("host");
	    String keyspace = prop.getProperty("keyspace");
	    
	    //Create connection to CQL database server
 
	    try {
	        cluster = Cluster.builder()
	                .addContactPoints(host)
	                .withPort(9042)
	                .withCredentials(username.trim(), password.trim())
	                .build();
	        
	        session = cluster.connect(keyspace);
	        System.out.println("Connection established to CQL Database");
	        //Handling possible exceptions
	    } catch (NoHostAvailableException e) {
	        System.err.println("No host in the provided contact points could be contacted.");
	        e.printStackTrace();
	    } catch (AuthenticationException e) {
	        System.err.println("Invalid username or password.");
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.err.println("An error occurred during connection:");
	        e.printStackTrace();
	    }
	    
	    return session;
	}
	public static void closeCQLConnection()
	{
		if(session!=null) {
			session.close();
			cluster.close();
		}
	}

}
