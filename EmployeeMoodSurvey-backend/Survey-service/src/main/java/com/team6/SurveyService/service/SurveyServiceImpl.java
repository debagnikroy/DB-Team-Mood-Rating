package com.team6.SurveyService.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team6.SurveyService.HashtagRepository.HashtagRepository;
import com.team6.SurveyService.SurveyRepository.SurveyRepository;
import com.team6.SurveyService.dto.Hashtags;
import com.team6.SurveyService.dto.Survey;
import com.team6.SurveyService.dto.SurveyId;
import com.team6.SurveyService.util.getHashtagFromReason;


@Service
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private SurveyRepository surveyRepository;
	
	@Autowired
	private HashtagRepository hashtagRepository;

	@Override
	public int getHashtagCount(String hashtag){
		return hashtagRepository.getHashtagCount(hashtag);
	}

	@Override
	public Hashtags findById(String hashtag){
//		Optional<Hashtags>  optionalHashtag= hashtagRepository.findById(hashtag);
//		if(optionalHashtag.isPresent())
//			return optionalHashtag.;
//		else
//			throw new IllegalArgumentException("Hashtag not present");
		return hashtagRepository.findById(hashtag).orElseThrow(()-> new IllegalArgumentException("Hashtag not present"));
	}

	@Override
	public Hashtags saveHashtag(Hashtags hashtag){
		return hashtagRepository.save(hashtag);
	}

	
	@Override
	public Survey addSurvey(Survey survey) {
		// TODO Auto-generated method stub
		String reason = survey.getReason();

//		function To seperate hashtags and reason
		List<String> result = new getHashtagFromReason().getHashTags(reason);
		
		result.addAll(Arrays.asList(survey.getHashtag().split(",")));
		
		for(String hashtag: result) {
			try{
				Hashtags hashtags=findById(hashtag);
				int count = getHashtagCount(hashtag) + 1;
				saveHashtag(new Hashtags(hashtag, count));
			}
			catch(Exception e){
				saveHashtag(new Hashtags(hashtag, 1));
			}

		}
		
//		hashtagRepository.save(new Hashtags(hashtag, 1));
		survey.setHashtag(String.join(",", result));
		
		return surveyRepository.save(survey);
	}

	@Override
	public List<Survey> getAllSurvey() {
		// TODO Auto-generated method stub
		return surveyRepository.findAll();
	}

	@Override
	public List<List<String>> getLatestAvg(int days) {
		// TODO Auto-generated method stub
		List<List<String>> latestAvg=surveyRepository.getLatestAvg(days);
		System.out.println(latestAvg);
		return latestAvg;
	}

	@Override
	public List<Survey> getByEmail(String email) {
		// TODO Auto-generated method stub
		return surveyRepository.getByEmail(email);
	}

	@Override
	public List<Survey> getLatestData(int days) {
		// TODO Auto-generated method stub
		return surveyRepository.getLatestData(days);
	}

	@Override
	public List<List<String>> getAvgCategoriesRating(int days) {
		// TODO Auto-generated method stub
		//return surveyRepository.getAvgCategoriesRating(days);

		List<List<String>> avgCategoriesRating=surveyRepository.getAvgCategoriesRating(days);
		System.out.println("avgCategoriesRating-------->"+avgCategoriesRating);
		return avgCategoriesRating;
	}

	@Override
	public List<List<String>> getBadMoodEmployee(int days) {
		// TODO Auto-generated method stub
		//return surveyRepository.getBadMoodEmployee(days);

		List<List<String>> badMoodEmployee=surveyRepository.getBadMoodEmployee(days);
		System.out.println("badMoodEmployee--------->"+badMoodEmployee);
		return badMoodEmployee;
	}



	
}
