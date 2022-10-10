package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.FollowingsDto;
import com.web.curation.repository.IFollowingRepository;

@Service
public class FollowingServiceImpl implements IFollowingService {
	
	@Autowired
	IFollowingRepository repo;
	
	@Autowired
	INoticeService nSer;
	
	@Override
	public int regFollowing(int u_index, int following_u_index) {
		// TODO Auto-generated method stub
		nSer.regNotice(following_u_index, u_index, "follow", "false");
		return repo.followingInsert(new FollowingsDto(u_index, following_u_index));
	}

	@Override
	public int deleteFollowing(int u_index, int following_u_index) {
		// TODO Auto-generated method stub
		return repo.followingDelete(new FollowingsDto(u_index, following_u_index));
	}

	@Override
	public List<FollowingsDto> InfoFollowing(int u_index, int page_index) {
		// TODO Auto-generated method stub
		return repo.followingList(u_index, page_index);
	}
	
	@Override
	public FollowingsDto isFollowing(int u_index, int following_u_index) {
		// TODO Auto-generated method stub
		return repo.isFollowing(u_index, following_u_index);
	}

	@Override
	public int countFollowing(int u_index) {
		// TODO Auto-generated method stub
		return repo.countFollowing(u_index);
	}


}
