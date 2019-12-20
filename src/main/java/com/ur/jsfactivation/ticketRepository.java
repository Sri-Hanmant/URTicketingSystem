//Code from https://www.tutorialspoint.com/design_pattern/iterator_pattern.htm
package com.ur.jsfactivation;


import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ticket.dbconnection.hibernateConnection;
import com.ur.entities.Container;
import com.ur.entities.Iterator;
import com.ur.entities.ticket;



public class ticketRepository implements Container {
	
	int numberOfTickets = 0;
	
	private List<ticket> ticketList;
	hibernateConnection conn = new hibernateConnection();
	
	
	
	public List<ticket> getTickets(String email){
		
		Session session = hibernateConnection.getSession();
		Transaction tran = conn.getTransaction(session);
        ticketList = session.createQuery("FROM ticket WHERE user='" + email + "'").list(); 

        conn.closeSessionwithCommit(session);

		return ticketList;	
	
}
	
   @Override
   public Iterator getIterator() {
      return new TicketIterator();
   }

   private class TicketIterator implements Iterator {

      int index = 0;

      @Override
      public boolean hasNext() {
      
         if(index < numberOfTickets){
            return true;
         }
         return false;
      }

      @Override
      public Object next() {
      
         if(this.hasNext()){
            return ticketList.get(index++);
         }
         return null;
      }		
   }
}
