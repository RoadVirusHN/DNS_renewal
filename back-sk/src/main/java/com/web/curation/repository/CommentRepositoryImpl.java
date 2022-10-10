package com.web.curation.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.CommentDto;
@Repository
public class CommentRepositoryImpl implements ICommentRepository {
	
	@Autowired
	SqlSession session;

	@Override
	public int commentInsert(CommentDto c) {
		// TODO Auto-generated method stub
		return session.insert("curation.comment.insert", c);
	}

	@Override
	public List<CommentDto> commentList(int b_index) {
		// TODO Auto-generated method stub
		return session.selectList("curation.comment.selectList", b_index);
	}
	
	@Override
	public List<CommentDto> commentList(int b_index, int page_index) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("b_index", b_index);
		map.put("page_index", page_index);

		System.out.println("댓글리스트 조회 시도1.75");
		return session.selectList("curation.comment.selectListPage", map);
	}

	@Override
	public CommentDto commentInfo(CommentDto c) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.comment.selectOne", c);
	}

	@Override
	public int commentUpdate(CommentDto c) {
		// TODO Auto-generated method stub
		return session.update("curation.comment.update", c);
	}

	@Override
	public int commentDelete(CommentDto c) {
		// TODO Auto-generated method stub
		return session.delete("curation.comment.delete", c);
	}

	@Override
	public int commentCount(int b_index) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.comment.selectCount", b_index);
	}


}
