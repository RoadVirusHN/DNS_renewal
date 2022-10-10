package com.web.curation.repository;
import java.util.List;
import com.web.curation.dto.BoardhashDto;

public interface IBoardhashRepository {
	
	// 피드와 해쉬간 관계 등록
	public int boardhashInsert(BoardhashDto bh);
	
	//특정 해쉬태그가 가지고 있는 피드 리스트 페이지 검색
	public List<BoardhashDto> boardhashListByHashtagPage(int h_index, int page_index);

	//특정 해쉬태그가 가지고 있는 피드 리스트 검색
	public List<BoardhashDto> boardhashListByHashtag(int h_index);
	//특정 피드가 가지고 있는 해쉬태그 리스트 검색
	public List<BoardhashDto> boardhashListByFeed(int b_index);
	
	//특정 피드와 특정 해쉬태그 관계 삭제
	public int boardhashDelete(BoardhashDto bh);

	//특정 피드와 특정 해쉬태그 관계가 이미 존재하는 가?
	public BoardhashDto boardhashDuple(BoardhashDto bh);

}
