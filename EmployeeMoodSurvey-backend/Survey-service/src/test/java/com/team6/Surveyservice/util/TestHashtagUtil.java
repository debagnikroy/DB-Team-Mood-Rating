package com.team6.Surveyservice.util;


import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHashtagUtil {
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
