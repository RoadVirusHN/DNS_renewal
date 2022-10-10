package com.web.curation.repository;

import com.web.curation.dto.LikesDto;

public interface ILikeRepository {
	
	//좋아요 등록
	public int likeInsert(LikesDto l);
	
	//좋아요 삭제
	public int likeDelete(LikesDto l);
	
	//좋아요 검색
	public int likeInfo(int b_index);
	
	//내가 좋아하는지
	public int doILike(LikesDto l);
	
}
