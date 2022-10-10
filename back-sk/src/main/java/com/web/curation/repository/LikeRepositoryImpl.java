package com.web.curation.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.LikesDto;

@Repository
public class LikeRepositoryImpl implements ILikeRepository {

	@Autowired
	SqlSession session;
	
	@Override
	public int likeInsert(LikesDto l) {
		// TODO Auto-generated method stub
		return session.insert("curation.like.insert", l);
	}

	@Override
	public int likeDelete(LikesDto l) {
		// TODO Auto-generated method stub
		return session.delete("curation.like.delete", l);
	}

	@Override
	public int likeInfo(int b_index) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.like.selectOne", b_index);
	}

	@Override
	public int doILike(LikesDto l) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.like.selectDoILike", l);
	}
	

	
}
