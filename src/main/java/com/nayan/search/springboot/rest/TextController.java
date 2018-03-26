package com.nayan.search.springboot.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import static java.lang.Math.min;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nayan.search.helper.WriteCsvToResponse;
import com.nayan.search.springboot.model.Text;
@RestController
public class TextController {
	@Autowired
	private TextService textService;
	
	@GetMapping("/search")
	public List<Text> retrieveSearchTextCount() {
		return textService.findAllTextWithCount();
	}
	
	@RequestMapping(value = "/top/{number}", produces = "text/csv")
	public void retrieveTopSearchCount(@PathVariable Integer number, HttpServletResponse response) throws IOException {
		List<Text> sortedSearchTextByCount = textService.findSortedCountText();
		WriteCsvToResponse.writeTopSearchedText(response.getWriter(), sortedSearchTextByCount.subList(0, min(sortedSearchTextByCount.size(), number)));
	}
}