//Entity table for ticket Details
package com.ur.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "Ticket_Details")
public class ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ticketID")
	private int ticketId;
	private String user;
	private String lastMod;
	private String dateCreated;
	private String worker;
	private String department;
	private String priority;
	private String title;
	private String description;
	private String location;
	private String roomNumber; 
	private String status;
	private int priorityCount;
	private boolean notify = false;
	private String comments;
	
	//getters and setters for tickets details table property
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public boolean isNotify() {
		return notify;
	}
	public void setNotify(boolean notify) {
		this.notify = notify;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String userName) {
		this.user = userName;
	}
	public String getLastMod() {
		return lastMod;
	}
	public void setLastMod(String lastMod) {
		this.lastMod = lastMod;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public int getPriorityCount() {
		return priorityCount;
	}
	public void setPriorityCount(int priorityCount) {
		this.priorityCount = priorityCount;
	}
	
}
