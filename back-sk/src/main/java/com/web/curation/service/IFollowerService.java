package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.FollowerDto;

public interface IFollowerService {
	
	public int regFollower(int u_index, int follower_u_index); 

	public int deleteFollower(int u_index, int follower_u_index);

	public  List<FollowerDto> InfoFollower(int u_index, int page_index);

	public int CountFollower(int u_index);
}
