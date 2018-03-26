package com.nayan.search.springboot.model;


public class Text {
	
	
	public Text(String text, int count) {
		super();
		this.text = text;
		this.count = count;
	}
	public Text() {
		
	}
	private String text;
	private int count;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}

