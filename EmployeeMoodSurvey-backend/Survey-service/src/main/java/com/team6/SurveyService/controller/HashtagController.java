package com.team6.SurveyService.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team6.SurveyService.dto.Survey;
import com.team6.SurveyService.service.HashtagService;
import com.team6.SurveyService.service.SurveyService;


@RestController()
@RequestMapping("/api")
public class HashtagController {

	
	private HashtagService hashtagService;

	@Autowired
	public HashtagController(HashtagService hashtagService){
		this.hashtagService=hashtagService;
	}

	
//	get all hashtags orderby count
	@GetMapping("/dashboard/hashtags")
	public ResponseEntity<?> getAllHashtags(){
		List<String> surveyList = hashtagService.getTrendingHashtags();
		return ResponseEntity.status(HttpStatus.OK).body(surveyList);
	}


	
	
	
}
