package com.team6.Surveyservice.controller;

import com.team6.SurveyService.controller.DashboardController;
import com.team6.SurveyService.controller.SurveyController;
import com.team6.SurveyService.dto.Survey;
import com.team6.SurveyService.dto.SurveyId;
import com.team6.SurveyService.service.SurveyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class SurveyControllerTest {
    @Mock
    private SurveyService surveyService;

    @InjectMocks
    private SurveyController surveyController;

    @Test
    public void saveSurvey(){
        Timestamp time=new Timestamp(System.currentTimeMillis());
        SurveyId surveyId1=new SurveyId();
        surveyId1.setEmail("test@gmail.com");
        Survey survey =new Survey(surveyId1,10,"#test1","Reason1");

        when(surveyService.addSurvey(survey)).thenReturn(survey);

        ResponseEntity output=surveyController.saveSurvey(survey);
        assertEquals(HttpStatus.CREATED,output.getStatusCode());
    }
}
