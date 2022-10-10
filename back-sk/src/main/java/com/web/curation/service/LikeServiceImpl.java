package com.web.curation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.LikesDto;
import com.web.curation.repository.ILikeRepository;

@Service
public class LikeServiceImpl implements ILikeService {
	
	@Autowired
	ILikeRepository repo;
	
	@Autowired
	INoticeService nSer;
	
	@Autowired
	IBoardService bSer;

	@Override
	public int insertLike(int b_index, int like_u_index) {
		// TODO Auto-generated method stub
		nSer.regNotice(bSer.infoBoard(b_index).getU_index(), like_u_index, b_index, "like", "false");
		return repo.likeInsert(new LikesDto(b_index, like_u_index));
	}

	@Override
	public int infoLike(int b_index) {
		// TODO Auto-generated method stub
		return repo.likeInfo(b_index);
	}

	@Override
	public int deleteLike(int b_index,int like_u_index) {
		// TODO Auto-generated method stub
		return repo.likeDelete(new LikesDto(b_index, like_u_index));
	}

	@Override
	public int doILike(int b_index, int like_u_index) {
		// TODO Auto-generated method stub
		return repo.doILike(new LikesDto(b_index, like_u_index));
	}

}
