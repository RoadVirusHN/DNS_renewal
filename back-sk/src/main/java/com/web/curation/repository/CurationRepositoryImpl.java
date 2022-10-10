package com.web.curation.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.CurationDto;

@Repository
public class CurationRepositoryImpl implements ICurationRepository{

	@Autowired
	SqlSession session;
	
	@Override
	public int curationInsert(CurationDto b) {
		// TODO Auto-generated method stub
		return session.insert("curation.curation.insert", b);
	}

	@Override
	public List<CurationDto> curationList(int u_index) {
		// TODO Auto-generated method stub
		return session.selectList("curation.curation.selectOne", u_index);
	}

	@Override
	public CurationDto curationInfo(int cu_index) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.curation.selectOne", cu_index);
	}

	@Override
	public int curationUpdate(CurationDto b) {
		// TODO Auto-generated method stub
		return session.update("curation.curation.update", b);
	}

	@Override
	public int curationDelete(int cu_index) {
		// TODO Auto-generated method stub
		return session.delete("curation.curation.delete", cu_index);
	}

}
