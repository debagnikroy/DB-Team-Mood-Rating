package com.team6.SurveyService.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class HashtagUtil {
	public List<String> getHashTagsFromReason(String reason) {
		List<String> result = new ArrayList<>();

		String[] words = reason.split(" ");
		for (String w : words) {
			if (w.charAt(0) == '#') {
				result.add(w);
			}
		}
		return result;
	}
}
