package com.team6.Surveyservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team6.SurveyService.controller.SurveyController;
import com.team6.SurveyService.dto.Survey;
import com.team6.SurveyService.dto.SurveyId;
import com.team6.SurveyService.service.SurveyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class SurveyControllerTest {
    @Mock
    private SurveyService surveyService;

    @InjectMocks
    private SurveyController surveyController;

    private static Survey survey1,survey2;

    @BeforeAll
    public static void setup(){
        Timestamp time=new Timestamp(System.currentTimeMillis());
        SurveyId surveyId1=new SurveyId();
        SurveyId surveyId2=new SurveyId();
        surveyId1.setEmail("test@gmail.com");
        surveyId1.setEmail("test@gmail.com");

        survey1 =new Survey(surveyId1,10,"#test1","Reason1");
        survey2 =new Survey(surveyId2,5,"#test2","Reason2");
    }

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

    @Test
    public void testGetAllSurvey_WhenSucces(){
        List<Survey> surveyList=new ArrayList<>();

        surveyList.add(survey1);
        surveyList.add(survey2);

        when(surveyService.getAllSurvey()).thenReturn(surveyList);

        ResponseEntity output=surveyController.getAllSurvey();
        assertEquals(surveyList,output.getBody());
        assertEquals(HttpStatus.OK,output.getStatusCode());
    }

    @Test
    public void testGetLatestAvg_WhenSuccess(){

        int days=3;
        List<List<String >> latestAvgRatings=new ArrayList();
        latestAvgRatings.add(Arrays.asList("2021-08-13","5.0"));
        latestAvgRatings.add(Arrays.asList("2021-08-14","5.75"));
        latestAvgRatings.add(Arrays.asList("2021-08-15","8.0"));

        when(surveyService.getLatestAvg(days)).thenReturn(latestAvgRatings);

        ObjectNode expectedObjectNode = new ObjectMapper().createObjectNode();
        for(List<String> list: latestAvgRatings)
            expectedObjectNode.put(list.get(0),list.get(1) );

        ResponseEntity output=surveyController.getLatestAvg(days);
        assertEquals(expectedObjectNode,output.getBody());
        assertEquals(HttpStatus.OK,output.getStatusCode());
    }


    @Test
    public void testGetByEmail(){
        SurveyId surveyId1=new SurveyId();
        String email="test@gmail.com";
        surveyId1.setEmail(email);
        Survey survey =new Survey(surveyId1,10,"#test1","Reason1");
        List<Survey> surveyList=Arrays.asList(survey);

        when(surveyService.getByEmail(email)).thenReturn(surveyList);
        ResponseEntity output=surveyController.getByEmail(email);
        assertEquals(surveyList,output.getBody());
        assertEquals(HttpStatus.OK,output.getStatusCode());
    }

    @Test
    public void testGetLatestData(){
        int days=3;
        List<Survey> latestData = Arrays.asList(survey1,survey2);
        when(surveyService.getLatestData(days)).thenReturn(latestData);
        ResponseEntity output=surveyController.getLatestData(days);
        assertEquals(latestData,output.getBody());
        assertEquals(HttpStatus.OK,output.getStatusCode());
    }

    @Test
    public void testGetAvgCategoriesRating(){

        int days=3;
        List<List<String >> avgCategoriesRating=new ArrayList();
        avgCategoriesRating.add(Arrays.asList("5 - 6","5"));
        avgCategoriesRating.add(Arrays.asList("3 - 4","5.75"));
        avgCategoriesRating.add(Arrays.asList("9 - 10","8.0"));

        when(surveyService.getAvgCategoriesRating(days)).thenReturn(avgCategoriesRating);
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        for(List<String> list: avgCategoriesRating)
            objectNode.put(list.get(0),list.get(1) );

        ResponseEntity output=surveyController.getAvgCategoriesRating(days);
        assertEquals(objectNode,output.getBody());
        assertEquals(HttpStatus.OK,output.getStatusCode());
    }


    @Test
    public void getBadMoodEmployee(){
        int days=3;
        List<List<String >> badMoodEmployee=new ArrayList();
        badMoodEmployee.add(Arrays.asList("abc1@gmail.com","1"));
        badMoodEmployee.add(Arrays.asList("abc2@gmail.com","2.5"));
        badMoodEmployee.add(Arrays.asList("abc3@gmail.com","4"));

        when(surveyService.getBadMoodEmployee(days)).thenReturn(badMoodEmployee);

        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        for(List<String> list: badMoodEmployee)
            objectNode.put(list.get(0),list.get(1) );
        ResponseEntity output=surveyController.getBadMoodEmployee(days);
        assertEquals(objectNode,output.getBody());
        assertEquals(HttpStatus.OK,output.getStatusCode());
    }


}
