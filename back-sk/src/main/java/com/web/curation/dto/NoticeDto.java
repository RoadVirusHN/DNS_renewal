package com.web.curation.dto;

public class NoticeDto {
	private int n_index;
	private int u_index;
	private int inv_index;
	private String inv_nickName;
	private int b_index;
	private String act;
	private String seen;
	
	public NoticeDto() {
		super();
	}
	
	
	public NoticeDto(int n_index, int u_index, int inv_index, String inv_nickName, int b_index, String act, String seen) {
		super();
		this.n_index = n_index;
		this.u_index = u_index;
		this.inv_index = inv_index;
		this.inv_nickName = inv_nickName;
		this.b_index = b_index;
		this.act = act;
		this.seen = seen;
	}
	
	public NoticeDto(int u_index, int inv_index, String act, String seen) {
		super();
		this.u_index = u_index;
		this.inv_index = inv_index;
		this.act = act;
		this.seen = seen;
	}
	
	public NoticeDto(int u_index, int inv_index, int b_index, String act, String seen) {
		super();
		this.u_index = u_index;
		this.inv_index = inv_index;
		this.b_index = b_index;
		this.act = act;
		this.seen = seen;
	}
	
	public NoticeDto(int n_index, String seen) {
		super();
		this.n_index = n_index;
		this.seen = seen;
	}
	
	public int getN_index() {
		return n_index;
	}
	public void setN_index(int n_index) {
		this.n_index = n_index;
	}
	public int getU_index() {
		return u_index;
	}
	public void setU_index(int u_index) {
		this.u_index = u_index;
	}
	public int getInv_index() {
		return inv_index;
	}
	public void setInv_index(int inv_index) {
		this.inv_index = inv_index;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getSeen() {
		return seen;
	}
	public void setSeen(String seen) {
		this.seen = seen;
	}


	public int getB_index() {
		return b_index;
	}


	public void setB_index(int b_index) {
		this.b_index = b_index;
	}


	public String getInv_nickName() {
		return inv_nickName;
	}


	public void setInv_nickName(String inv_nickName) {
		this.inv_nickName = inv_nickName;
	}


	@Override
	public String toString() {
		return "NoticeDto [n_index=" + n_index + ", u_index=" + u_index + ", inv_index=" + inv_index + ", inv_nickName="
				+ inv_nickName + ", b_index=" + b_index + ", act=" + act + ", seen=" + seen + "]";
	}
	
	
}
