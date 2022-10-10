package com.web.curation.repository;

import java.util.List;

import com.web.curation.dto.FollowingsDto;

public interface IFollowingRepository {

	// 팔로잉 등록
	public int followingInsert(FollowingsDto f);

	// 팔로잉 검색
	public List<FollowingsDto> followingList(int u_index, int page_index);

	// 팔로잉 데이터 삭제
	public int followingDelete(FollowingsDto f);
	
	// 팔로잉 검색
	public FollowingsDto isFollowing(int u_index, int following_u_index);
	
	// 팔로잉 갯수 겁색
	public int countFollowing(int u_index);

}
