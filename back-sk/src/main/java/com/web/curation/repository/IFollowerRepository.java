package com.web.curation.repository;

import java.util.List;

import com.web.curation.dto.FollowerDto;

public interface IFollowerRepository {
	
	//팔로워 등록
	public int followerInsert(FollowerDto f);
	
	//팔로워 검색
	public List<FollowerDto> followerList(int u_index, int page_index);	
	
	//팔로워 데이터 삭제
	public int followerDelete(FollowerDto f);
	
	//팔로워 수
	public int followerCount(int u_index);
}
