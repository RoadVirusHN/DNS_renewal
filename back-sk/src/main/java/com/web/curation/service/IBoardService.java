package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.BoardDto;

public interface IBoardService {
	
	public int regBoard(int u_index, String context, String multi_file);
	
	public int updateBoard(int b_index, int u_index, String context, String multi_file);

	public BoardDto infoBoard(int b_index);
	
	public int getBoardIdx(int u_index, String context, String multi_file);
	
	public int deleteBoard(int b_index); 

	public List<BoardDto> listBoard(int u_index, int page_index);
	
	public List<BoardDto> listBoard(int u_index);
	
	public List<BoardDto> listAllBoard(int page_index);
	
	public List<BoardDto> listSearchBoard(String keyword);
	
	public List<BoardDto> listSearchBoardPage(String keyword, int page_index);
}
