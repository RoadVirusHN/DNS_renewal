package com.web.curation.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.BoardDto;

@Repository
public class BoardRepositoryImpl implements IBoardRepository{
	
	@Autowired
	SqlSession session;

	@Override
	public int boardInsert(BoardDto b) {
		// TODO Auto-generated method stub
		return session.insert("curation.board.insert", b);
	}

	@Override
	public List<BoardDto> boardList(int u_index, int page_index) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_index", u_index);
		map.put("page_index", page_index);
		return session.selectList("curation.board.selectUser", map);
	}
	
	@Override
	public List<BoardDto> boardList(int u_index) {
		// TODO Auto-generated method stub
		return session.selectList("curation.board.selectUserAll", u_index);
	}

	@Override
	public BoardDto boardInfo(int b_index) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.board.selectOne", b_index);//return session.selectOne("curation.board.selectOne", b_index);
	}
	
	public int boardIdxGet(int u_index, String context, String multi_file){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_index", u_index);
		map.put("context", context);
		map.put("multi_file", multi_file);
		return session.selectOne("curation.board.selectOneForIdx",map);
	}

	@Override
	public int boardUpdate(BoardDto b) {
		// TODO Auto-generated method stub
		return session.update("curation.board.update", b);
	}

	@Override
	public int boardDelete(int b_index) {
		// TODO Auto-generated method stub
		return session.delete("curation.board.delete", b_index);
	}

	@Override
	public List<BoardDto> boardAllList(int page_index) {
		// TODO Auto-generated method stub
		return session.selectList("curation.board.selectList", page_index);
	}
	@Override
	public List<BoardDto> boardSearchList(String keyword){
		return session.selectList("curation.board.selectSearchList", keyword);
	}
	@Override
	public List<BoardDto> boardSearchListPage(String keyword, int page_index){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("터짐 2? : ");
		map.put("keyword", keyword);
		map.put("page_index", page_index);
		System.out.println("터짐3? : ");
		return session.selectList("curation.board.selectSearchListPage", map);
	}
		
}
