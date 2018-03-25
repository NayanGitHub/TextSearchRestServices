package com.nayan.search.springboot.rest;

import java.util.List;
import static java.lang.Math.min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nayan.search.springboot.model.Text;
@RestController
public class TextController {
	@Autowired
	private TextService textService;
	
	@GetMapping("/search")
	public List<Text> retrieveSearchTextCount() {
		return textService.findAllTextWithCount();
	}
	
	@GetMapping("/top/{number}")
	public List<Text> retrieveTopSearchCount(@PathVariable Integer number) {
		List<Text> sortedSearchTextByCount = textService.findSortedCountText();
		
		return  sortedSearchTextByCount.subList(0, min(sortedSearchTextByCount.size(), number));
	}
}