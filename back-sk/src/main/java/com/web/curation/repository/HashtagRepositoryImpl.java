package com.web.curation.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.HashtagDto;
import com.web.curation.dto.UserDto;


@Repository
public class HashtagRepositoryImpl implements IHashtagRepository{
	
	@Autowired
	SqlSession session;
	
	
	@Override
	public int hashtagInsert(HashtagDto h) {
		return session.insert("curation.hashtag.insert",h);
	}
		
	@Override
	public HashtagDto hashtagInfo(int h_index) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.hashtag.selectOne", h_index);
	}
	
	@Override
	public List<HashtagDto> hashtagSearch(String keyword, int page_index) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("page_index", page_index);
		return session.selectList("curation.hashtag.selectSearchListPage", map);
	}
	@Override
	public HashtagDto hashtagDuple(String content) {
		 return session.selectOne("curation.hashtag.selectOneByContent", content);
	}
	
	
	
}
