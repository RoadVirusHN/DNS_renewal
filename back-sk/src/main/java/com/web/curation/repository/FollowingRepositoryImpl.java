package com.web.curation.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.FollowingsDto;

@Repository
public class FollowingRepositoryImpl implements IFollowingRepository {

	@Autowired
	SqlSession session;
	
	@Override
	public int followingInsert(FollowingsDto f) {
		// TODO Auto-generated method stub
		return session.insert("curation.following.insert", f);
	}

	@Override
	public List<FollowingsDto> followingList(int u_index, int page_index) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_index", u_index);
		map.put("page_index", page_index);
		return session.selectList("curation.following.selectList", map);
	}

	@Override
	public int followingDelete(FollowingsDto f) {
		// TODO Auto-generated method stub
		return session.delete("curation.following.delete", f);
	}
	
	@Override
	public FollowingsDto isFollowing(int u_index, int following_u_index) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_index", u_index);
		map.put("following_u_index", following_u_index);
		return session.selectOne("curation.following.selectIsFollowing", map);
	}

	@Override
	public int countFollowing(int u_index) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.following.selectCount", u_index);
	}


}
