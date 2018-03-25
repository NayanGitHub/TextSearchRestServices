package com.nayan.search.springboot.model;


public class Text {
	
	
	public Text(String text, long count) {
		super();
		this.text = text;
		this.count = count;
	}
	public Text() {
		
	}
	private String text;
	private long count;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
}

