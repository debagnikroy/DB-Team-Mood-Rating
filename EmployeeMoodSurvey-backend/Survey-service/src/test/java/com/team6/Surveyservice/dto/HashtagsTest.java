package com.team6.Surveyservice.dto;

import com.team6.SurveyService.dto.Hashtags;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class HashtagsTest {
    @Test
    public void testDefaultConstructor(){
        Hashtags hashtags = new Hashtags();
        assertNotNull(hashtags);
        assertEquals(hashtags.getCount(), 0);
        assertNull(hashtags.getHashtag());
    }

    @Test
    public void testAllArgConstructor(){
        Hashtags hashtags = new  Hashtags("#test",1);
        assertEquals(hashtags.getHashtag(), "#test");
        assertEquals(hashtags.getCount(), 1);
    }
}
