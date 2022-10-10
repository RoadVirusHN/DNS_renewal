package com.web.curation.repository;

import java.util.List;

import com.web.curation.dto.UserDto;

public interface IUserRepository {
	//유저 등록
	public int userInsert(UserDto u);

	//전체 유저 검색
	public List<UserDto> userList();

	//유저 한명 검색
	public UserDto userInfo(String email);
	
	//유저 한명 검색
	public UserDto userInfo(int u_index);

	//유저 데이터 수정
	public int userUpdate(UserDto u);
	
	//유저 데이터 삭제
	public int userDelete(String email);
	
	//유저 비밃번호 찾기
	int updatePw(UserDto dto);
	
	//유저 로그인
	UserDto loginUser(UserDto dto);
	
	//이메일 중복 체크
	UserDto emailDuplicateUserCheck(String email);
	
	//닉네임 중복 체크
	UserDto nicknameDuplicateUserCheck(String nickname);
	
	//public UserDto memFind(UserDto u);
	
	//유저 리스트 검색(닉네임) 
	public List<UserDto> userSearchListPage(String keyword, int page_index);
}
