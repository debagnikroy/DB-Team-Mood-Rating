package com.team6.Surveyservice.service;

import com.team6.SurveyService.HashtagRepository.HashtagRepository;
import com.team6.SurveyService.service.HashtagService;
import com.team6.SurveyService.service.HashtagServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HashtagServiceTest {

    @Mock
    private HashtagRepository hashtagRepository;

    @InjectMocks
    private HashtagServiceImpl hashtagServiceImpl;

    @Test
    public void testGetTrendingHashtags(){
        List<String> dummyTags=new ArrayList<>();
        dummyTags.add("#tag1");
        dummyTags.add("#tag2");
        when(hashtagRepository.getTrendingHashtag()).thenReturn(dummyTags);

        //executing
        List<String> hashtags = hashtagServiceImpl.getTrendingHashtags();

        assertNotNull(hashtags);
        assertEquals(hashtags.size(), 2);

        //verification
        verify(hashtagRepository, times(1)).getTrendingHashtag();

    }
}
