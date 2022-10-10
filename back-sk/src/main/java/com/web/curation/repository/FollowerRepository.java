package com.web.curation.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.FollowerDto;
@Repository
public class FollowerRepository implements IFollowerRepository {
	
	@Autowired
	SqlSession session;
	
	@Override
	public int followerInsert(FollowerDto f) {
		// TODO Auto-generated method stub
		return session.insert("curation.follower.insert", f);
	}


	@Override
	public int followerDelete(FollowerDto f) {
		// TODO Auto-generated method stub
		return session.delete("curation.follower.delete",f);
	}

	@Override
	public List<FollowerDto> followerList(int u_index, int page_index) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_index", u_index);
		map.put("page_index", page_index);		
		return session.selectList("curation.follower.selectList",map);
	}
	
	@Override
	public int followerCount(int u_index) {
		return session.selectOne("curation.follower.selectCount",u_index);
	}

}
