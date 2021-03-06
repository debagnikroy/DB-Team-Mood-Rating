package com.team6.SurveyService.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.sql.Timestamp;

@Embeddable
public class SurveyId implements Serializable {
	
	private Date timeStamp;
	private String email;
	public SurveyId(){
		timeStamp= new Timestamp(System.currentTimeMillis());
		email=null;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
