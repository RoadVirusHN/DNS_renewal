package com.web.curation.repository;

import java.util.List;

import com.web.curation.dto.BoardDto;

public interface IBoardRepository {

	//게시글 등록
	public int boardInsert(BoardDto b);

	//특정 유저 게시글 리스트 검색
	public List<BoardDto> boardList(int u_index);
	
	//특정 유저 게시글 리스트 검색
	public List<BoardDto> boardList(int u_index, int page_index);
	
	//게시글 리스트 검색
	public List<BoardDto> boardAllList(int page_index);
	
	//게시글 키워드 검색
	public List<BoardDto> boardSearchList(String keyword);
	
	//게시글 키워드 검색 인피니티 스크롤 적용
	public List<BoardDto> boardSearchListPage(String keyword, int page_index);

	//게시긓 하나 검색
	public BoardDto boardInfo(int b_index);

	//게시글의 인덱스 찾기
	public int boardIdxGet(int u_index, String context, String multi_file);
	//게시글 수정
	public int boardUpdate(BoardDto b);
	
	//게시글 삭제
	public int boardDelete(int b_index);

	//public UserDto memFind(UserDto u);
}
