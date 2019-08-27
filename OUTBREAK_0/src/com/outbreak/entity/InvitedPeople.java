package com.outbreak.entity;

public class InvitedPeople {

	private String name;
	private String email;
	private String phoneNumber;
	private boolean TOF;
	private InvitedPeople next;
	
	public InvitedPeople() {
		TOF=false;
		next=null;
	}
	
	public InvitedPeople getNext() {
		return next;
	}

	public void setNext(InvitedPeople next) {
		this.next = next;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isTOF() {
		return TOF;
	}
	public void setTOF(boolean tOF) {
		TOF = tOF;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
