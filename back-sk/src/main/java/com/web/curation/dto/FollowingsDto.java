package com.web.curation.dto;

public class FollowingsDto {
	
	private int u_index;
	private int following_u_index;
	public FollowingsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FollowingsDto(int u_index, int following_u_index) {
		super();
		this.u_index = u_index;
		this.following_u_index = following_u_index;
	}
	
	public int getU_index() {
		return u_index;
	}
	public void setU_index(int u_index) {
		this.u_index = u_index;
	}
	public int getFollowing_u_index() {
		return following_u_index;
	}
	public void setFollowing_u_index(int following_u_index) {
		this.following_u_index = following_u_index;
	}
	
	@Override
	public String toString() {
		return "FollowingDto [u_index=" + u_index + ", following_u_index=" + following_u_index + "]";
	}
}
