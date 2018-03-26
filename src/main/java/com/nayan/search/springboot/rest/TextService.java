package com.nayan.search.springboot.rest;

import java.util.List;

import com.nayan.search.helper.SearchTextFromFile;
import com.nayan.search.springboot.model.*;


import org.springframework.stereotype.Component;

@Component
public class TextService {
	
	public List<Text> findAllTextWithCount() {
		List<Text> texts = SearchTextFromFile.getListTextAndCount();
		return texts;
	}
	
	public List<Text> findSortedCountText() {
		List<Text> texts = SearchTextFromFile.getListTextAndCount();
		texts.sort((Text z1, Text z2) -> {
			   if (z1.getCount() < z2.getCount())
			     return 1;
			   if (z1.getCount() > z2.getCount())
			     return -1;
			   return 0;
			});
		return texts;
	}
}