package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.NoticeDto;
import com.web.curation.repository.INoticeRepository;

@Service
public class NoticeServiceImpl implements INoticeService{

	@Autowired
	INoticeRepository repo;
	
	@Autowired
	IUserService uSer;
	
	@Override
	public int regNotice(int u_index, int inv_index, String act, String seen) {
		// TODO Auto-generated method stub
		return repo.noticeInsert(new NoticeDto(u_index, inv_index, act, seen));
	}
	
	@Override
	public int regNotice(int u_index, int inv_index, int b_index, String act, String seen) {
		// TODO Auto-generated method stub
		return repo.noticeInsert(new NoticeDto(u_index, inv_index, b_index, act, seen));
	}

	@Override
	public int updateNotice(int n_index, int u_index, int inv_index, String act, String seen) {
		// TODO Auto-generated method stub
		return repo.noticeUpdate(new NoticeDto(n_index, seen));
	}

	@Override
	public List<NoticeDto> listNotice(int u_index) {
		// TODO Auto-generated method stub
		List<NoticeDto> noticeList = repo.noticeList(u_index);
		for (NoticeDto noticeDto : noticeList) {
			noticeDto.setInv_nickName(uSer.infoUser(noticeDto.getInv_index()).getNickname());
		}
		System.out.println(noticeList);
		return noticeList;
	}

	@Override
	public NoticeDto infoNotice(int n_index) {
		// TODO Auto-generated method stub
		return repo.noticeInfo(n_index);
	}

	@Override
	public int deleteNotice(int n_index) {
		// TODO Auto-generated method stub
		return repo.noticeDelete(n_index);
	}

	@Override
	public int deleteNotice(int following_u_index, int u_index) {
		// TODO Auto-generated method stub
		return repo.noticeDelete(following_u_index, u_index);
	}

	@Override
	public int countNotice(int u_index) {
		// TODO Auto-generated method stub
		return repo.noticeCount(u_index);
	}


	
}
