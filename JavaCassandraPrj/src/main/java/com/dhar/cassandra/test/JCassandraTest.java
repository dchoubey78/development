/**
 * 
 */
package com.dhar.cassandra.test;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * @author dharmendrachoubey
 *
 */
public class JCassandraTest {

	Cluster cluster;
	Session session;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JCassandraTest test = new JCassandraTest();
		test.connect();
		/*String serverIp = "127.0.0.1";
	    String keyspace = "system";
	   
	    Cluster cluster = Cluster.builder()
	            .addContactPoints(serverIp)
	            .build();

	    Session session = cluster.connect(keyspace);


	    String cqlStatement = "SELECT * FROM local";
	    for (Row row : session.execute(cqlStatement)) {
	        System.out.println(row.toString());
	    }*/
		
		

	}
	
	/**
	 * 
	 */
	public void connect(){
		System.out.println("Connect Method");
		cluster = Cluster.builder().addContactPoint("127.0.0.1").withPort(9042).build();
		Metadata metadata = cluster.getMetadata();
		System.out.println("ClusterName==>"+metadata.getClusterName());
		session = cluster.connect("dev");
		ResultSet results = session.execute("insert into emp(empid, emp_dept,emp_first, emp_last) values(3,'Management','Prisha','Choubey')");
		for(Row row : results){
			System.out.println("Local:==>"+row.toString());
		}
		session.close();
		cluster.close();
	}
	

}
