package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.UserDto;

public interface IUserService {

	public UserDto loginUser(String email, String password);

	public boolean findPw(String email);
	
	public int signupUser(String email, String pw, String nickname, String comment, String birth, String account_public, String follow_approval, String picture);
	
	public int updateUser(String email, String pw, String nickname, String comment, String birth, String account_public, String follow_approval, String picture);

	public UserDto infoUser(String email);
	
	public UserDto infoUser(int u_index);
	
	public UserDto emailDuplicateCheck(String email);
	
	public UserDto nicknameDuplicateCheck(String nickname);
	
	public int deleteUser(String email); 

	public List<UserDto> listUser();

	public List<UserDto> listSearchUserPage(String keyword, int page_index);
}
