package com.web.curation.service;

import com.web.curation.dto.UserDto;

public interface JwtService {

	String create(UserDto dto);

	//토큰 유효성 검증
	boolean validationToken(String jwt);
	
	//토큰 만료 확인
	boolean getExpToken(String jwt);
	
	//토큰으로 부터 유저 이메일 추출
	String getUserEmail(String jwt);
	
	//토큰으로 부터 유저 닉네임 추출
	String getUserIndex(String jwt);
	//byte[] generateKey();
}
