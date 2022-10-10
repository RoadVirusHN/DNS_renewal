package com.web.curation.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.BoardhashDto;

@Repository
public class BoardhashRepositoryImpl implements IBoardhashRepository {

	@Autowired
	SqlSession session;
	
	// 피드와 해쉬간 관계 등록
	@Override
	public int boardhashInsert(BoardhashDto bh) {		
		return  session.insert("curation.boardhash.insert", bh);
	}
	
	//특정 해쉬태그가 가지고 있는 피드 리스트 페이지 검색
	@Override
	public List<BoardhashDto> boardhashListByHashtagPage(int h_index, int page_index){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("h_index", h_index);
		map.put("page_index", page_index);
		return session.selectList("curation.boardhash.selectBoardListPage", map);
	}
	//특정 해쉬태그가 가지고 있는 피드 리스트 검색
	@Override
	public List<BoardhashDto> boardhashListByHashtag(int h_index){
		return session.selectList("curation.boardhash.selectBoardList", h_index);
	}
	
	//특정 피드가 가지고 있는 해쉬태그 리스트 검색
	@Override
	public List<BoardhashDto> boardhashListByFeed(int b_index){
		return session.selectList("curation.boardhash.selectHashtagList", b_index);
	}
	
	//특정 피드와 특정 해쉬태그 관계 삭제
	@Override
	public int boardhashDelete(BoardhashDto bh) {
		return session.delete("curation.boardhash.deleteBoardhash", bh);
	}
	
	//특정 피드와 특정 해쉬태그 관계가 이미 존재하는 가?
	@Override
	public BoardhashDto boardhashDuple(BoardhashDto bh) {
		return 	session.selectOne("curation.boardhash.selectBoardhash", bh);
	}
}
