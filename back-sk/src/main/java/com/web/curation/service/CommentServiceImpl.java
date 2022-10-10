package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.CommentDto;
import com.web.curation.repository.ICommentRepository;

@Service
public class CommentServiceImpl implements ICommentService {
	
	@Autowired
	ICommentRepository repo;
	
	@Autowired
	INoticeService nSer;
	
	@Autowired
	IBoardService bSer;

	@Override
	public int regComment(int b_index, int u_index, int depth, String context) {
		// TODO Auto-generated method stub
		try {
			nSer.regNotice(bSer.infoBoard(b_index).getU_index() , u_index, b_index, "comment", "false");
		} catch (Exception e) {
			//TODO: handle exception
			System.out.println("#################################!!");
			System.out.println(e);
		}
		return repo.commentInsert(new CommentDto(b_index, u_index, depth, context));
	}

	@Override
	public int updateComment(int b_index, int u_index, int depth, String context) {
		// TODO Auto-generated method stub
		return repo.commentUpdate(new CommentDto(b_index, u_index, depth, context));
	}

	@Override
	public List<CommentDto> listComment(int b_index) {
		// TODO Auto-generated method stub
		return repo.commentList(b_index);
	}
	
	@Override
	public List<CommentDto> listComment(int b_index, int page_index) {
		// TODO Auto-generated method stub

		System.out.println("댓글리스트 조회 시도1.5");
		return repo.commentList(b_index, page_index);
	}

	@Override
	public CommentDto InfoComment(int b_index, int u_index) {
		// TODO Auto-generated method stub
		return repo.commentInfo(new CommentDto(b_index, u_index));
	}

	@Override
	public int deleteComment(int b_index, int u_index) {
		// TODO Auto-generated method stub
		return repo.commentDelete(new CommentDto(b_index, u_index));
	}

	@Override
	public int countComment(int b_index) {
		// TODO Auto-generated method stub
		return repo.commentCount(b_index);
	}

}
