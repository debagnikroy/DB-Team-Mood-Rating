package com.team6.Surveyservice.dto;

import com.team6.SurveyService.dto.SurveyId;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class SurveyIdTest {
    @Test
    public void testDefaultConstructor(){
        SurveyId surveyId = new SurveyId();
        assertNotNull(surveyId);
        assertNull(surveyId.getEmail());
        assertNotNull(surveyId.getTimeStamp());
    }
}
