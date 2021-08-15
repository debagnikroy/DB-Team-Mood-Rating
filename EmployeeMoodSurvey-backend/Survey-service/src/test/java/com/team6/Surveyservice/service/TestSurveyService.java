package com.team6.Surveyservice.service;

import com.team6.SurveyService.HashtagRepository.HashtagRepository;
import com.team6.SurveyService.SurveyRepository.SurveyRepository;
import com.team6.SurveyService.dto.Hashtags;
import com.team6.SurveyService.dto.Survey;
import com.team6.SurveyService.dto.SurveyId;
import com.team6.SurveyService.service.HashtagServiceImpl;
import com.team6.SurveyService.service.SurveyServiceImpl;
import com.team6.SurveyService.util.getHashtagFromReason;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestSurveyService {

    @Mock
    private HashtagRepository hashtagRepository;

    @InjectMocks
    private HashtagServiceImpl hashtagServiceImpl;

    @Mock
    private SurveyRepository surveyRepository;

    @InjectMocks
    private SurveyServiceImpl surveyServiceImpl;

    private Survey survey1,survey2;

    TestSurveyService(){
        Timestamp time=new Timestamp(System.currentTimeMillis());
        SurveyId surveyId1=new SurveyId();
        SurveyId surveyId2=new SurveyId();
        surveyId1.setEmail("test@gmail.com");
        surveyId1.setEmail("test@gmail.com");
        survey1 = new Survey(surveyId1,10,"#test1","Reason1");
        survey2 = new Survey(surveyId2,5,"#test2","Reason2");
    }

    @Test
    public void testGetHashtagCount(){

        String hashtag="#hashtag";
        when(hashtagRepository.getHashtagCount(hashtag)).thenReturn(1);

        //executing
        int count = surveyServiceImpl.getHashtagCount(hashtag);

        assertEquals(count, 1);

        //verification
        verify(hashtagRepository, times(1)).getHashtagCount(hashtag);
    }

    @Test
    public void testFindByIdEmpty(){

        String hashtag="#hashtag";
        when(hashtagRepository.findById(hashtag)).thenReturn(Optional.empty());

        try {
            Hashtags hashtags = surveyServiceImpl.findById(hashtag);
            fail("Expected an exception of IllegalArgument");
        } catch (Exception exception){
            assertNotNull(exception);
            assertTrue(exception instanceof IllegalArgumentException);
            assertEquals(exception.getMessage(), "Hashtag not present");
        }
    }

    @Test
    public void testFindByIdPresent(){

        String hashtag="#hashtag";
        Hashtags hashtags=new Hashtags("#test",1);
        when(hashtagRepository.findById(hashtag)).thenReturn(Optional.of(hashtags));

        try {
            Hashtags hashtagReturned = surveyServiceImpl.findById(hashtag);
            assertNotNull(hashtagReturned);
        } catch (Exception exception){
            fail("Should not come to the exception block::");
        }

        //verify(studentRepository, times(1)).findById(12L);
    }

    @Test
    public void testSaveHashtags(){

        Hashtags hashtag=new Hashtags("#hashtag",5);
        Hashtags savedHashtag=new Hashtags("#hashtag",5);

        when(hashtagRepository.save(hashtag)).thenReturn(savedHashtag);

        //executing
        Hashtags returnedHashtag = surveyServiceImpl.saveHashtag(hashtag);

        assertNotNull(returnedHashtag);
        assertEquals(returnedHashtag.getCount(), 5);
        assertEquals(returnedHashtag.getHashtag(), "#hashtag");

        verify(hashtagRepository, times(1)).save(hashtag);
    }

    @Test
    public void testSaveSurvey(){

        Timestamp time=new Timestamp(System.currentTimeMillis());
        SurveyId surveyId=new SurveyId();
        surveyId.setEmail("test@gmail.com");
        Survey survey = new Survey(surveyId,10,"#test","Reason");
        Survey savedSurvey = new Survey(surveyId,10,"#test","Reason");
        String reason = survey.getReason();

//		function To seperate hashtags and reason
        List<String> result = new getHashtagFromReason().getHashTags(reason);

        result.addAll(Arrays.asList(survey.getHashtag().split(",")));

        String allHashtagsString=String.join(",", result);
        survey.setHashtag(allHashtagsString);

        when(surveyRepository.save(survey)).thenReturn(savedSurvey);

        //executing
        Survey returnedSurvey = surveyServiceImpl.addSurvey(survey);

        assertNotNull(returnedSurvey);
        assertEquals(returnedSurvey.getMoodRating(), 10);
        assertEquals(returnedSurvey.getId(), surveyId);
        assertEquals(returnedSurvey.getHashtag(), allHashtagsString);
        assertEquals(returnedSurvey.getReason(), "Reason");

        verify(surveyRepository, times(1)).save(survey);
    }

    @Test
    public void getAllSurvey(){
        List<Survey> surveyList=new ArrayList<>();
        surveyList.add(survey1);
        surveyList.add(survey2);

        when(surveyRepository.findAll()).thenReturn(surveyList);

        //executing
        List<Survey> returnedSurveyList = surveyServiceImpl.getAllSurvey();

        assertNotNull(returnedSurveyList);
        assertEquals(returnedSurveyList.size(), 2);

        //verification
        verify(surveyRepository, times(1)).findAll();

    }

    @Test
    public void testGetLatestAvg(){
        int days=3;
        List<List<String >> latestAvg=new ArrayList();
        latestAvg.add(Arrays.asList("2021-08-13","5.0"));
        latestAvg.add(Arrays.asList("2021-08-14","5.75"));
        latestAvg.add(Arrays.asList("2021-08-15","8.0"));

        when(surveyRepository.getLatestAvg(days)).thenReturn(latestAvg);

        //executing
        List<List<String >> returnedLatestAvg = surveyServiceImpl.getLatestAvg(days);

        assertNotNull(returnedLatestAvg);
        assertEquals(returnedLatestAvg.size(), 3);
        assertEquals(latestAvg,returnedLatestAvg);

        //verification
        verify(surveyRepository, times(1)).getLatestAvg(days);
    }

    @Test
    public void testGetByEmail(){

        String email="test@gmail.com";
        when(surveyRepository.getByEmail(email)).thenReturn(Arrays.asList(survey1,survey2));

        //executing
        List<Survey> returnedSurveyList = surveyServiceImpl.getByEmail(email);

        assertNotNull(returnedSurveyList);
        assertEquals(returnedSurveyList.size(), 2);
        assertEquals(Arrays.asList(survey1,survey2),returnedSurveyList);

        //verification
        verify(surveyRepository, times(1)).getByEmail(email);
    }

    @Test
    public void testGetLatestData(){
        int days=3;
        when(surveyRepository.getLatestData(days)).thenReturn(Arrays.asList(survey1,survey2));

        //executing
        List<Survey> returnedSurveyList = surveyServiceImpl.getLatestData(days);

        assertNotNull(returnedSurveyList);
        assertEquals(returnedSurveyList.size(), 2);
        assertEquals(Arrays.asList(survey1,survey2),returnedSurveyList);

        //verification
        verify(surveyRepository, times(1)).getLatestData(days);
    }



    @Test
    public void testGetAvgCategoriesRating(){
        int days=3;
        List<List<String >> avgCategoriesRating=new ArrayList();
        avgCategoriesRating.add(Arrays.asList("5 - 6","5"));
        avgCategoriesRating.add(Arrays.asList("3 - 4","5.75"));
        avgCategoriesRating.add(Arrays.asList("9 - 10","8.0"));

        when(surveyRepository.getAvgCategoriesRating(days)).thenReturn(avgCategoriesRating);

        //executing
        List<List<String >> returnedAvgCategoriesRating = surveyServiceImpl.getAvgCategoriesRating(days);

        assertNotNull(returnedAvgCategoriesRating);
        assertEquals(returnedAvgCategoriesRating.size(), 3);
        assertEquals(avgCategoriesRating,returnedAvgCategoriesRating);

        //verification
        verify(surveyRepository, times(1)).getAvgCategoriesRating(days);
    }

    @Test
    public void testGetBadMoodEmployee(){
        int days=3;
        List<List<String >> badMoodEmployee=new ArrayList();
        badMoodEmployee.add(Arrays.asList("abc1@gmail.com","1"));
        badMoodEmployee.add(Arrays.asList("abc2@gmail.com","2.5"));
        badMoodEmployee.add(Arrays.asList("abc3@gmail.com","4"));

        when(surveyRepository.getBadMoodEmployee(days)).thenReturn(badMoodEmployee);

        //executing
        List<List<String >> returnedBadMoodEmployee = surveyServiceImpl.getBadMoodEmployee(days);

        assertNotNull(returnedBadMoodEmployee);
        assertEquals(returnedBadMoodEmployee.size(), 3);
        assertEquals(badMoodEmployee,returnedBadMoodEmployee);

        //verification
        verify(surveyRepository, times(1)).getBadMoodEmployee(days);
    }


}
