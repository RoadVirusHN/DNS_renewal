package com.web.curation.dto;

public class HashtagInfoDto {
	private String content;
	private int b_index;

	public HashtagInfoDto(String content, int b_index) {
		super();
		this.content = content;
		this.b_index = b_index;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getB_index() {
		return b_index;
	}
	public void setB_index(int b_index) {
		this.b_index = b_index;
	}
	@Override
	public String toString() {
		return "HashtagInfoDto [content=" + content + ", b_index=" + b_index + "]";
	}	
}
