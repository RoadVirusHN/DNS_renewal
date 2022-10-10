package com.web.curation.dto;

public class FollowerDto {
	
	private int u_index;
	private int follower_u_index;
	
	public FollowerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FollowerDto(int u_index, int follower_u_index) {
		super();
		this.u_index = u_index;
		this.follower_u_index = follower_u_index;
	}
	
	public int getU_index() {
		return u_index;
	}
	public void setU_index(int u_index) {
		this.u_index = u_index;
	}
	public int getFollower_u_index() {
		return follower_u_index;
	}
	public void setFollower_u_index(int follower_u_index) {
		this.follower_u_index = follower_u_index;
	}
	
	@Override
	public String toString() {
		return "FollowersDto [u_index=" + u_index + ", follower_u_index=" + follower_u_index + "]";
	}
}
