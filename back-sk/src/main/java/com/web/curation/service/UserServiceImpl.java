package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.UserDto;
import com.web.curation.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	IUserRepository repo;
	
	@Autowired
    JavaMailSender emailSender;

	@Override
	public UserDto loginUser(String email, String pw) {
		// TODO Auto-generated method stub
		return repo.loginUser(new UserDto(email, pw));
	}

	@Override
	public boolean findPw(String email) {
		// TODO Auto-generated method stub
		UserDto findPassword = repo.userInfo(email);
		System.out.println(findPassword);
		if (findPassword != null) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(findPassword.getEmail());
			message.setSubject("임시 비밀번호 입니다.");
			String randomPassword = RandomPassword.getRandomPassword();
			findPassword.setPw(randomPassword);
			repo.updatePw(findPassword);
			message.setText(randomPassword);
			emailSender.send(message);
			return true;
		}
		return false;
	}

	@Override
	public int signupUser(String email, String pw, String nickname, String comment, String birth, String account_public, String follow_approval, String picture) {
		// TODO Auto-generated method stub
		return repo.userInsert(new UserDto(email, pw, nickname,comment, birth, account_public, follow_approval, picture));
	}

	@Override
	public int updateUser(String email, String pw, String nickname, String comment, String birth, String account_public, String follow_approval, String picture) {
		// TODO Auto-generated method stub
		return repo.userUpdate(new UserDto(email, pw, nickname,comment, birth, account_public, follow_approval, picture));
	}

	@Override
	public UserDto infoUser(String email) {
		// TODO Auto-generated method stub
		return repo.userInfo(email);
	}

	@Override
	public List<UserDto> listUser() {
		// TODO Auto-generated method stub
		return repo.userList();
	}

	@Override
	public int deleteUser(String email) {
		// TODO Auto-generated method stub
		return repo.userDelete(email);
	}

	@Override
	public UserDto emailDuplicateCheck(String email) {
		// TODO Auto-generated method stub
		return repo.emailDuplicateUserCheck(email);
	}

	@Override
	public UserDto nicknameDuplicateCheck(String nickname) {
		// TODO Auto-generated method stub
		return repo.nicknameDuplicateUserCheck(nickname);
	}

	@Override
	public UserDto infoUser(int u_index) {
		// TODO Auto-generated method stub
		return repo.userInfo(u_index);
	}
	
	@Override
	public List<UserDto> listSearchUserPage(String keyword, int page_index) {
		List<UserDto> userList = repo.userSearchListPage(keyword, page_index);
//		for (UserDto userDto : userList) {
//			userDto.setFileLoc(userDto.getMulti_file().split(","));
//		}
		return userList;
	}
}
