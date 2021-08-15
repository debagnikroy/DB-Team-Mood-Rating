package com.team6.Surveyservice.controller;

import com.team6.SurveyService.controller.HashtagController;
import com.team6.SurveyService.service.HashtagService;
import com.team6.SurveyService.service.SurveyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HashtagControllerTest {

    @Mock
    private HashtagService hashtagService;

    @InjectMocks
    private HashtagController hashtagController;

    @Test
    public void testGetAllHashtags(){
        List<String> hashtagList=Arrays.asList("#tag1","#tag2");
        when(hashtagService.getTrendingHashtags()).thenReturn(hashtagList);
        ResponseEntity output= hashtagController.getAllHashtags();
        assertEquals(hashtagList,output.getBody());
        assertEquals(HttpStatus.OK,output.getStatusCode());
    }


}
