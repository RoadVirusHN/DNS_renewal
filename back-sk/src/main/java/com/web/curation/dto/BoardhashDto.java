package com.web.curation.dto;

public class BoardhashDto {
	private int bh_index;
	private int h_index;
	private int b_index;
	
	public BoardhashDto() {
		super();
	}

	public BoardhashDto(int b_index, int h_index) {
		super();
		this.b_index = b_index;
		this.h_index = h_index;
	}

	public int getBH_index() {
		return bh_index;
	}

	public void setBH_index(int bh_index) {
		this.bh_index = bh_index;
	}

	public int getB_index() {
		return b_index;
	}

	public void setB_index(int b_index) {
		this.b_index = b_index;
	}
	
	public int getH_index() {
		return h_index;
	}

	public void setH_index(int h_index) {
		this.h_index = h_index;
	}


	@Override
	public String toString() {
		return "BoardhashDto [bh_index=" + bh_index +", b_index="+ b_index +", h_index="+ h_index ;
	}		
}
