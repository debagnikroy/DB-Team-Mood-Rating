package com.team6.SurveyService.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team6.SurveyService.dto.Survey;
import com.team6.SurveyService.service.HashtagService;
import com.team6.SurveyService.service.SurveyService;

@RestController
@RequestMapping("/api")
public class SurveyController {

	@Autowired
	private SurveyService surveyService;
		
	
	@PostMapping("/survey")
	public ResponseEntity<?> saveSurvey(@RequestBody Survey survey){
		surveyService.addSurvey(survey);
		return ResponseEntity.status(HttpStatus.CREATED).body(survey);
	}

	//	get all response
	@GetMapping("/dashboard/survey")
	public ResponseEntity<?> getAllSurvey(){
		List<Survey> surveyList = surveyService.getAllSurvey();
		return ResponseEntity.status(HttpStatus.OK).body(surveyList);
	}

	@GetMapping("/dashboard/survey/latestAvg/{days}")
	public ResponseEntity<?> getLatestAvg(@PathVariable("days") int days){
		List<List<String>> latestAvgRating = surveyService.getLatestAvg(days);
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		for(List<String> list: latestAvgRating)
			objectNode.put(list.get(0),list.get(1) );
		return ResponseEntity.status(HttpStatus.OK).body(objectNode);
	}
	//	search survey response by email
	@GetMapping("/dashboard/survey/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable("email") String email){
		List<Survey> listByEmail = surveyService.getByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(listByEmail);
	}

	//	get complete response of last X days
	@GetMapping("/dashboard/survey/latestData/{days}")
	public ResponseEntity<?> getLatestData(@PathVariable("days") int days){
		List<Survey> latestData = surveyService.getLatestData(days);
		return ResponseEntity.status(HttpStatus.OK).body(latestData);
	}

	@GetMapping("/dashboard/survey/avgCategoriesRating/{days}")
	public ResponseEntity<?> getAvgCategoriesRating(@PathVariable("days") int days){
		List<List<String>> avgCategoriesRating = surveyService.getAvgCategoriesRating(days);
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		for(List<String> list: avgCategoriesRating)
			objectNode.put(list.get(0),list.get(1) );
		return ResponseEntity.status(HttpStatus.OK).body(objectNode);
	}


	@GetMapping("/dashboard/survey/badMoodSince/{days}")
	public ResponseEntity<?> getBadMoodEmployee(@PathVariable("days") int days){
		List<List<String>> badMoodEmployee = surveyService.getBadMoodEmployee(days);
		ObjectNode objectNode = new ObjectMapper().createObjectNode();
		for(List<String> list: badMoodEmployee)
			objectNode.put(list.get(0),list.get(1) );
		return ResponseEntity.status(HttpStatus.OK).body(objectNode);
	}
	
}
