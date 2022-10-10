package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.NoticeDto;

public interface INoticeService {

	public int regNotice(int u_index, int inv_index, String act, String seen);
	
	public int regNotice(int u_index, int inv_index, int b_index, String act, String seen);

	public int updateNotice(int n_index, int u_index, int inv_index, String act, String seen);

	public List<NoticeDto> listNotice(int u_index);

	public NoticeDto infoNotice(int n_index);

	public int deleteNotice(int n_index);
	
	public int deleteNotice(int following_u_index, int u_index);
	
	public int countNotice(int u_index);
}
