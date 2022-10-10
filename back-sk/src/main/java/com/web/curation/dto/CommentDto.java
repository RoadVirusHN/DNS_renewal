package com.web.curation.dto;

public class CommentDto {
	private int com_index;
	private int b_index;
	private int u_index;
	private int depth;
	private String context;
	private String reg_date;
	
	public CommentDto() {
		super();
	}
	public CommentDto(int b_index, int u_index) {
		super();
		this.b_index = b_index;
		this.u_index = u_index;
	}
	
	public CommentDto(int b_index, int u_index,int depth, String context) {
		super();
		this.b_index = b_index;
		this.u_index = u_index;
		this.depth = depth;
		this.context = context;
	}
	
	public CommentDto(int b_index, int u_index,int depth, String context, String reg_date) {
		super();
		this.b_index = b_index;
		this.u_index = u_index;
		this.depth = depth;
		this.context = context;
		this.reg_date = reg_date;
	}


	public CommentDto(int com_index, int b_index, int u_index,int depth, String context) {
		super();
		this.com_index = com_index;
		this.b_index = b_index;
		this.u_index = u_index;
		this.depth = depth;
		this.context = context;
	}

	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getCom_index() {
		return com_index;
	}

	public void setCom_index(int com_index) {
		this.com_index = com_index;
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

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	public String getReg_date() {
		return reg_date;
	}
	
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	@Override
	public String toString() {
		return "CommentDto [com_index=" + com_index + ", b_index=" + b_index + ", u_index=" + u_index + ", depth="
				+ depth + ", context=" + context + ", reg_date=" + reg_date + "]";
	}
	
	
}
