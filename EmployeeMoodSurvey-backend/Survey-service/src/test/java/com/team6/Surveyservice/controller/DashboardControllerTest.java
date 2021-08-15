package com.team6.Surveyservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team6.SurveyService.controller.DashboardController;
import com.team6.SurveyService.dto.Survey;
import com.team6.SurveyService.dto.SurveyId;
import com.team6.SurveyService.service.HashtagService;
import com.team6.SurveyService.service.SurveyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DashboardControllerTest {
    @Mock
    private SurveyService surveyService;

    @Mock
    private HashtagService hashtagService;


    @InjectMocks
    private DashboardController dashboardController;

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
    public void testGetAllSurvey_WhenSucces(){
        List<Survey> surveyList=new ArrayList<>();

        SurveyId surveyId1=new SurveyId();
        SurveyId surveyId2=new SurveyId();
        surveyId1.setEmail("test@gmail.com");
        surveyId2.setEmail("test@gmail.com");

        Survey survey1 =new Survey(surveyId1,10,"#test1","Reason1");
        Survey survey2 =new Survey(surveyId2,5,"#test2","Reason2");
        surveyList.add(survey1);
        surveyList.add(survey2);

        when(surveyService.getAllSurvey()).thenReturn(surveyList);

        ResponseEntity output=dashboardController.getAllSurvey();
        assertEquals(surveyList,output.getBody());
        assertEquals(HttpStatus.OK,output.getStatusCode());
    }

    @Test
    public void testGetAllHashtags(){
        List<String> hashtagList=Arrays.asList("#tag1","#tag2");
        when(hashtagService.getTrendingHashtags()).thenReturn(hashtagList);
        ResponseEntity output=dashboardController.getAllHashtags();
        assertEquals(hashtagList,output.getBody());
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

        ResponseEntity output=dashboardController.getLatestAvg(days);
        assertEquals(expectedObjectNode,output.getBody());
        assertEquals(HttpStatus.OK,output.getStatusCode());
    }




    public ResponseEntity<?> getByEmail(@PathVariable("email") String email){
        List<Survey> listByEmail = surveyService.getByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(listByEmail);
    }
    @Test
    public void testGetByEmail(){
        SurveyId surveyId1=new SurveyId();
        String email="test@gmail.com";
        surveyId1.setEmail(email);
        Survey survey =new Survey(surveyId1,10,"#test1","Reason1");
        List<Survey> surveyList=Arrays.asList(survey);

        when(surveyService.getByEmail(email)).thenReturn(surveyList);
        ResponseEntity output=dashboardController.getByEmail(email);
        assertEquals(surveyList,output.getBody());
        assertEquals(HttpStatus.OK,output.getStatusCode());
    }

    @Test
    public void testGetLatestData(){
        int days=3;
        List<Survey> latestData = Arrays.asList(survey1,survey2);
        when(surveyService.getLatestData(days)).thenReturn(latestData);
        ResponseEntity output=dashboardController.getLatestData(days);
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

        ResponseEntity output=dashboardController.getAvgCategoriesRating(days);
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
        ResponseEntity output=dashboardController.getBadMoodEmployee(days);
        assertEquals(objectNode,output.getBody());
        assertEquals(HttpStatus.OK,output.getStatusCode());
    }
}
