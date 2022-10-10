package com.web.curation.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.FollowerDto;
import com.web.curation.repository.IFollowerRepository;

@Service
public class FollowerServiceImpl implements IFollowerService {
	
	@Autowired
	IFollowerRepository repo;
	
	@Override
	public int regFollower(int u_index, int follower_u_index) {
		// TODO Auto-generated method stub
		return repo.followerInsert(new FollowerDto(u_index,follower_u_index));
	}
	
	@Override
	public int deleteFollower(int u_index, int follower_u_index) {
		// TODO Auto-generated method stub
		return repo.followerDelete(new FollowerDto(u_index, follower_u_index));
	}

	@Override
	public  List<FollowerDto> InfoFollower(int u_index, int page_index) {
		// TODO Auto-generated method stub
		return repo.followerList(u_index, page_index);
	}
	
	@Override
	public int CountFollower(int u_index) {
		return repo.followerCount(u_index);
	}



}
