//This is the hibernateConnection page, that connects to the database

package com.ticket.dbconnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class hibernateConnection {
	
	//Initialization of session factory
	static SessionFactory sessionFactory = null;
	static Session session = null;
	Transaction transaction = null;
	
	//setSession method
	public static void setSession() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	 	  //session = sessionFactory.openSession();	 	  
	}
		
	//getSession method
	public static Session getSession() {	
		  session = sessionFactory.openSession();		  
		  return session;
	 }
	
	//Method for transaction
	public Transaction getTransaction(Session session) {
		transaction = session.beginTransaction();
		return transaction;
	}
		
	//method to commit and close the transaction 
	public void closeSessionwithCommit(Session session) {
             session.getTransaction().commit(); 
             session.close();
             
	}
	
	
}