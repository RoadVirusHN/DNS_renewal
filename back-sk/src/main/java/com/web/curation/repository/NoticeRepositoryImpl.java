package com.web.curation.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.NoticeDto;

@Repository
public class NoticeRepositoryImpl implements INoticeRepository{

	@Autowired
	SqlSession session;
	
	@Override
	public int noticeInsert(NoticeDto n) {
		// TODO Auto-generated method stub
		n.setInv_nickName("");
		return session.insert("curation.notice.insert", n);
	}

	@Override
	public List<NoticeDto> noticeList(int u_index) {
		// TODO Auto-generated method stub
		return session.selectList("curation.notice.selectUser", u_index);
	}

	@Override
	public NoticeDto noticeInfo(int n_index) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.notice.selectOne", n_index);
	}

	@Override
	public int noticeUpdate(NoticeDto n) {
		// TODO Auto-generated method stub
		return session.update("curation.notice.update", n);
	}

	@Override
	public int noticeDelete(int n_index) {
		// TODO Auto-generated method stub
		return session.delete("curation.notice.delete", n_index);
	}

	@Override
	public int noticeDelete(int following_u_index, int u_index) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("following_u_index", following_u_index);
		map.put("u_index", u_index);
		return session.delete("curation.notice.deleteOne", map);
	}

	@Override
	public int noticeCount(int u_index) {
		// TODO Auto-generated method stub
		return session.selectOne("curation.notice.selectCount", u_index);
	}

}
