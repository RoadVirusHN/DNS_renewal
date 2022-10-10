package com.web.curation.repository;

import java.util.List;

import com.web.curation.dto.NoticeDto;

public interface INoticeRepository {

	// 알림 등록
	public int noticeInsert(NoticeDto n);

	// 특정 유저 알림 리스트 검색
	public List<NoticeDto> noticeList(int u_index);

	// 알림 하나 검색
	public NoticeDto noticeInfo(int n_index);

	// 알림 수정
	public int noticeUpdate(NoticeDto n);

	// 알림 삭제
	public int noticeDelete(int n_index);
	
	// 알림 삭제
	public int noticeDelete(int following_u_index, int u_index);
	
	// 특정 유저 알림 갯수 검색
	public int noticeCount(int u_index);

}
