package com.nayan.search.springboot.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.nayan.search.helper.SearchTextFromFile;
import com.nayan.search.springboot.model.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class TextService {
	//private static List<Student> students = new ArrayList<>();
	
	//@RequestMapping(method = RequestMethod.GET, consumes = "application/json")
	public List<Text> findAllTextWithCount() {
		List<Text> texts = SearchTextFromFile.getListTextAndCount();
		return texts;
		//return new ResponseEntity<>(assembler.toResourceCollection(texts), HttpStatus.OK);
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
		//return new ResponseEntity<>(assembler.toResourceCollection(texts), HttpStatus.OK);
	}
	
	/*public Student retrieveStudent(String studentId) {
		for (Student student : students) {
			if(student.getId()!=null) {
			if (student.getId().equals(studentId)) {
				return student;
			}
			}
		}
		return null;
	}*/
	/*public List<Course> retrieveCourses(String studentId) {
		Student student = retrieveStudent(studentId);
		if (student == null) {
			return null;
		}
		return student.getCourses();
	}*/
}