package com.web.curation.repository;

import java.util.List;

import com.web.curation.dto.CurationDto;

public interface ICurationRepository {

	//큐레이션 등록
	public int curationInsert(CurationDto b);

	//특정 유저 큐레이션 리스트 검색
	public List<CurationDto> curationList(int u_index);

	//큐레이션 하나 검색
	public CurationDto curationInfo(int cu_index);

	//큐레이션 수정
	public int curationUpdate(CurationDto b);
		
	//큐레이션 삭제
	public int curationDelete(int cu_index);

}
