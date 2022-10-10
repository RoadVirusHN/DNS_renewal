package com.web.curation.repository;

import java.util.List;

import com.web.curation.dto.CommentDto;

public interface ICommentRepository {
	
	// 댓글 등록
	public int commentInsert(CommentDto c);
	
	// 댓글 특정 게시글 리스트 검색
	public List<CommentDto> commentList(int b_index);
	
	public List<CommentDto> commentList(int b_index, int page_index);
	// 댓긓 하나 검색
	public CommentDto commentInfo(CommentDto c);
	//댓글 수정
	public int commentUpdate(CommentDto c);
	
	// 댓글 삭제
	public int commentDelete(CommentDto c);
	
	// 댓글 갯수
	public int commentCount(int b_index);

}
