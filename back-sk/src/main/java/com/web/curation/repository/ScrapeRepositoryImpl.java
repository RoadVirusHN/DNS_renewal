package com.web.curation.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.ScrapeDto;

@Repository
public class ScrapeRepositoryImpl implements IScrapeRepository{

	@Autowired
	SqlSession session;
	
	@Override
	public int scrapeInsert(ScrapeDto s) {
		// TODO Auto-generated method stub
		System.out.println(s);
		return session.insert("curation.scrape.insert", s);
	}

	@Override
	public List<ScrapeDto> scrapeList(int u_index, int page_index) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_index", u_index);
		map.put("page_index", page_index);
		return session.selectList("curation.scrape.selectUser", map);
	}

	@Override
	public ScrapeDto scrapeInfo(int b_index, int u_index) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("b_index", b_index);
		map.put("u_index", u_index);
		return session.selectOne("curation.scrape.selectOne", map);
	}

	@Override
	public int scrapeUpdate(ScrapeDto n) {
		// TODO Auto-generated method stub
		return session.update("curation.scrape.update", n);
	}

	@Override
	public int scrapeDelete(int u_index, int b_index) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_index", u_index);
		map.put("b_index", b_index);
		return session.delete("curation.scrape.delete", map);
	}

	@Override
	public int scrapeCount(int u_index) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.scrape.selectCount", u_index);
	}

}
