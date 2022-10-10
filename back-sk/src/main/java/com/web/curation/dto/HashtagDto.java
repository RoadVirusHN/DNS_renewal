package com.web.curation.dto;

public class HashtagDto {
	private int h_index;
	private String content;
	
	public HashtagDto() {
		super();
	}

	public HashtagDto(String content) {
		super();
		this.content = content;
	}
	
	public int getH_index() {
		return h_index;
	}

	public void setH_index(int h_index) {
		this.h_index = h_index;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "HashtagDto [h_index=" + h_index + ", content="+ content;
	}		
}
