//Priority table entity
package com.ur.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//declaration of entity and setting the table name
@Entity()
@Table(name = "Priority_Details")
public class priority {
	
	@Id
	//@GeneratedValue annotation specifies that a value will be automatically generated for that field
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@column is used to specify a mapped column for a persistent property or field. 
	@Column(name = "priorityID")
	private int priorityID;
	private String userId;
	private int ticketId;
	
	//getters and setters for table properties
	public int getPriorityID() {
		return priorityID;
	}
	public void setPriorityID(int priorityID) {
		this.priorityID = priorityID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	
}
