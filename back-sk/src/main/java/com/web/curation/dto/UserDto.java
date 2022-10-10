package com.web.curation.dto;

import java.io.Serializable;

public class UserDto implements Serializable{

	private int u_index;
	private String email;
	private String pw;
	private String nickname;
	private String comment;
	private String birth;
	private String account_public;
	private String follow_approval;
	private String picture;
	
	public UserDto() {
		super();
	}
	
	public UserDto(String email, String pw, String nickname, String comment, String birth,
			String account_public, String follow_approval, String picture) {
		super();
		this.email = email;
		this.pw = pw;
		this.nickname = nickname;
		this.comment = comment;
		this.birth = birth;
		this.account_public = account_public;
		this.follow_approval = follow_approval;
		this.picture = picture;
	}

	public UserDto(int u_index, String email, String pw, String nickname, String comment, String birth,
			String account_public, String follow_approval, String picture) {
		super();
		this.u_index = u_index;
		this.email = email;
		this.pw = pw;
		this.nickname = nickname;
		this.comment = comment;
		this.birth = birth;
		this.account_public = account_public;
		this.follow_approval = follow_approval;
		this.picture = picture;
	}
	
	public UserDto(String email, String pw) {
		super();
		this.email = email;
		this.pw = pw;
	}

	public int getU_index() {
		return u_index;
	}

	public void setU_index(int u_index) {
		this.u_index = u_index;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAccount_public() {
		return account_public;
	}

	public void setAccount_public(String account_public) {
		this.account_public = account_public;
	}

	public String getFollow_approval() {
		return follow_approval;
	}

	public void setFollow_approval(String follow_approval) {
		this.follow_approval = follow_approval;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "UserDto [u_index=" + u_index + ", email=" + email + ", pw=" + pw + ", nickname=" + nickname
				+ ", comment=" + comment + ", birth=" + birth + ", account_public=" + account_public
				+ ", follow_approval=" + follow_approval + ", picture=" + picture + "]";
	}
		
}
