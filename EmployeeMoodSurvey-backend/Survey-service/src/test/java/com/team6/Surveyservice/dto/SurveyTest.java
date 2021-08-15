package com.team6.Surveyservice.dto;

import com.team6.SurveyService.dto.Hashtags;
import com.team6.SurveyService.dto.Survey;
import com.team6.SurveyService.dto.SurveyId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurveyTest {
    @Test
    public void testDefaultConstructor(){
        Survey survey = new Survey();
        assertNotNull(survey);
        assertEquals(survey.getId(), null);
        assertNull(survey.getHashtag());
        assertNull(survey.getReason());
        assertEquals(survey.getMoodRating(), 0);
    }
    @Test
    public void testAllArgConstructor(){
        SurveyId surveyId=new SurveyId();
        surveyId.setEmail("test@gmail.com");
        Survey survey = new Survey(surveyId,10,"#test","Reason");
        assertNotNull(survey);
        assertEquals(survey.getId(), surveyId);
        assertEquals(survey.getHashtag(),"#test");
        assertEquals(survey.getReason(),"Reason");
        assertEquals(survey.getMoodRating(), 10);
    }

}
