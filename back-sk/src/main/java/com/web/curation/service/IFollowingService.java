package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.FollowingsDto;


public interface IFollowingService {

	public int regFollowing(int u_index, int following_u_index); 

	public int deleteFollowing(int u_index, int following_u_index);

	public  List<FollowingsDto> InfoFollowing(int u_index, int page_index);
	
	public  FollowingsDto isFollowing(int u_index, int following_u_index);
	
	public int countFollowing(int u_index);
}
