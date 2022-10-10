package com.web.curation.dto;

public class ScrapeDto {
	
	private int s_index;
	private int b_index;
	private int u_index;
	
	public ScrapeDto() {
		super();
	}

	public ScrapeDto(int b_index, int u_index) {
		super();
		this.b_index = b_index;
		this.u_index = u_index;
	}

	public int getS_index() {
		return s_index;
	}

	public void setS_index(int s_index) {
		this.s_index = s_index;
	}

	public int getB_index() {
		return b_index;
	}

	public void setB_index(int b_index) {
		this.b_index = b_index;
	}

	public int getU_index() {
		return u_index;
	}

	public void setU_index(int u_index) {
		this.u_index = u_index;
	}

	@Override
	public String toString() {
		return "ScrapeDto [s_index=" + s_index + ", b_index=" + b_index + ", u_index=" + u_index + "]";
	}
	
}
