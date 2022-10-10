package com.web.curation.repository;

import java.util.List;

import com.web.curation.dto.ScrapeDto;

public interface IScrapeRepository {

	// 스크랩 등록
	public int scrapeInsert(ScrapeDto s);

	// 특정 유저 스크랩 리스트 검색
	public List<ScrapeDto> scrapeList(int u_index, int page_index);
	
	// 툭정 유저 스크랩 갯수 검색
	public int scrapeCount(int u_index);

	// 스크랩 하나 검색
	public ScrapeDto scrapeInfo(int b_index, int u_index);

	// 스크랩 수정
	public int scrapeUpdate(ScrapeDto s);

	// 스크랩 삭제
	public int scrapeDelete(int u_index, int b_index);

}
