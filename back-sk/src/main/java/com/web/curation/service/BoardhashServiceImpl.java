package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.BoardhashDto;
import com.web.curation.repository.IBoardhashRepository;


@Service
public class BoardhashServiceImpl implements IBoardhashService{

	@Autowired
	IBoardhashRepository repo;
	
	@Override
	public int insertBoardhash(int b_index, int h_index) {
		BoardhashDto checkduple = repo.boardhashDuple(new BoardhashDto(b_index, h_index));
		if (checkduple==null) {			
			return repo.boardhashInsert(new BoardhashDto(b_index, h_index));
		}
		else {
			return 0;
		}
	}
	
	@Override
	public List<BoardhashDto> getBoardhashListByHashtagPage(int h_index, int page_index){
		return repo.boardhashListByHashtagPage(h_index, page_index);
	}
	@Override
	public List<BoardhashDto> getBoardhashListByHashtag(int h_index){
		return repo.boardhashListByHashtag(h_index);
	}
	@Override
	public List<BoardhashDto> getBoardhashListByFeed(int b_index){
		return repo.boardhashListByFeed(b_index);
	}
	@Override
	public int deleteBoardhash(int b_index, int h_index) {
		return repo.boardhashDelete(new BoardhashDto(b_index, h_index));
	}
	@Override
	public BoardhashDto dupleBoardhash(int b_index, int h_index) {
		return repo.boardhashDuple(new BoardhashDto(b_index, h_index));
	}	
}
