package com.web.curation.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.UserDto;

@Repository
public class UserRepositoryImpl implements IUserRepository{
	
	@Autowired
	SqlSession session;

	@Override
	public int userInsert(UserDto u) {
		// TODO Auto-generated method stub
		return session.insert("curation.user.insert", u);
	}

	@Override
	public List<UserDto> userList() {
		// TODO Auto-generated method stub
		return session.selectList("curation.user.selectList");
	}

	@Override
	public UserDto userInfo(String email) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.user.selectOne", email);
	}

	@Override
	public int userUpdate(UserDto u) {
		// TODO Auto-generated method stub
		return session.update("curation.user.update", u);
	}

	@Override
	public int userDelete(String email) {
		// TODO Auto-generated method stub
		return session.delete("curation.user.delete", email);
	}

	@Override
	public int updatePw(UserDto dto) {
		// TODO Auto-generated method stub
		return session.update("curation.user.updatepw", dto);
	}

	@Override
	public UserDto loginUser(UserDto dto) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.user.selectLogin", dto);
	}

	@Override
	public UserDto emailDuplicateUserCheck(String email) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.user.emailDuplicateCheck", email);
	}

	@Override
	public UserDto nicknameDuplicateUserCheck(String nickname) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.user.nicknameDuplicateCheck", nickname);
	}

	@Override
	public UserDto userInfo(int u_index) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.user.selectOnebyu_index", u_index);
	}

	@Override
	public List<UserDto> userSearchListPage(String keyword, int page_index){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("page_index", page_index);
		return session.selectList("curation.user.selectSearchListPage", map);
	}
}
