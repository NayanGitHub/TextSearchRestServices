package com.nayan.search.springboot.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import static java.lang.Math.min;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nayan.search.helper.WriteCsvToResponse;
import com.nayan.search.springboot.model.Text;
import com.nayan.search.springboot.model.TextWrapper;
@RestController
public class TextController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TextController.class);
	public static final int NO_OCCURENCE=0;
	@Autowired
	private TextService textService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json", "application/xml"}, consumes = "application/json", headers = { "Accept=application/json",
			"Content-type=application/json" })
	public  @ResponseBody List<Text> retrieveSearchTextCount(@RequestBody TextWrapper wrapper) {
		
		List<Text> allListText = textService.findAllTextWithCount();
		
		LOGGER.debug("All the Text in the File     \n"+allListText);
		
		List<Text> searchListText = new ArrayList<Text>();
		
		List<String> searchTxt = wrapper.getSearchText();
		
		LOGGER.debug("Search Text in the input     \n"+searchTxt);
		
		for (String str : searchTxt) {
			boolean exist = false;
			for (Text currText : allListText) {
				if (str != null && str.equalsIgnoreCase(currText.getText())) {
					LOGGER.info("Text is " + str + "Count is " + currText.getCount());
					searchListText.add(new Text(str, currText.getCount()));
					exist = true;
					break;
			}
			}
			if(!exist) {
			searchListText.add(new Text(str, NO_OCCURENCE));
			LOGGER.info("Text is " + str + "Count is " + NO_OCCURENCE);
			}
		}
		return searchListText;
	}
	
	@RequestMapping(value = "/top/{number}", produces = "text/csv")
	public void retrieveTopSearchCount(@PathVariable Integer number, HttpServletResponse response) throws IOException {
		List<Text> sortedSearchTextByCount = textService.findSortedCountText();
		LOGGER.info("Inside retrieveTopSearchCount with Top :"+number);
		WriteCsvToResponse.writeTopSearchedText(response.getWriter(), sortedSearchTextByCount.subList(0, min(sortedSearchTextByCount.size(), number)));
	}
}