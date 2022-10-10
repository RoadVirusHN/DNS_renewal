package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.BoardhashDto;

public interface IBoardhashService {

	public int insertBoardhash(int b_index, int h_index);
	
	public List<BoardhashDto> getBoardhashListByHashtagPage(int h_index, int page_index);
	public List<BoardhashDto> getBoardhashListByHashtag(int h_index);
	public List<BoardhashDto> getBoardhashListByFeed(int b_index);
	
	public int deleteBoardhash(int b_index, int h_index);

	//특정 피드와 특정 해쉬태그 관계가 이미 존재하는 가?
	public BoardhashDto dupleBoardhash(int b_index, int h_index);
}
