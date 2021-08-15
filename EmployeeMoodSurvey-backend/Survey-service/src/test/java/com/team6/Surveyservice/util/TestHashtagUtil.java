package com.team6.Surveyservice.util;


import com.team6.SurveyService.dto.Survey;
import com.team6.SurveyService.dto.SurveyId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHashtagUtil {

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
    public void testGetHashTagsFromReason() {

        String reason="Test String #test";
        List<String> hashtagList= Arrays.asList("#test");
        List<String> result = new ArrayList<>();

        String[] words = reason.split(" ");
        for (String w : words) {
            if (w.charAt(0) == '#') {
                result.add(w);
            }
        }

        assertEquals(hashtagList,result);
    }
}
