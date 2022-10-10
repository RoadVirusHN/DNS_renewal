package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.CommentDto;

public interface ICommentService {
	
	public int regComment(int b_index, int u_index, int depth, String context);
	
	public int updateComment(int b_index, int u_index, int depth, String context);
	
	public List<CommentDto> listComment(int b_index);
	
	public int countComment(int b_index);
	 
	public CommentDto InfoComment(int b_index, int u_index);
	
	public List<CommentDto> listComment(int b_index, int page_index);
	
	public int deleteComment(int b_index, int u_index);

}
