package com.team6.SurveyService.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity(name="hashtag")
@Table(name = "HashtagTb1")
public class Hashtags {

	@Id
	private String hashtag;
	private int count;

	public Hashtags() {
		hashtag=null;
		count=0;
	}
	public Hashtags(String hashtag, int count) {
		this.hashtag = hashtag;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public String getHashtag() {
		return hashtag;
	}


}
