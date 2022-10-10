package com.web.curation.dto;

public class CurationDto {
	private int cu_index;
	private int u_index;
	private String set_name;
	private String keyword;
	
	public CurationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurationDto(int cu_index, int u_index, String set_name, String keyword) {
		super();
		this.cu_index = cu_index;
		this.u_index = u_index;
		this.set_name = set_name;
		this.keyword = keyword;
	}

	public int getCu_index() {
		return cu_index;
	}

	public void setCu_index(int cu_index) {
		this.cu_index = cu_index;
	}

	public int getU_index() {
		return u_index;
	}

	public void setU_index(int u_index) {
		this.u_index = u_index;
	}

	public String getSet_name() {
		return set_name;
	}

	public void setSet_name(String set_name) {
		this.set_name = set_name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "CurationDto [cu_index=" + cu_index + ", u_index=" + u_index + ", set_name=" + set_name + ", keyword="
				+ keyword + "]";
	}
}
