package com.web.curation.repository;

import java.util.List;

import com.web.curation.dto.HashtagDto;

public interface IHashtagRepository {
	// 해쉬태그 등록
	public int hashtagInsert(HashtagDto h);
	
	// 해쉬태그 하나 찾기
	public HashtagDto hashtagInfo(int h_index);
	
	// 해쉬태그 검색하기
	public List<HashtagDto> hashtagSearch(String keyword, int page_index);

	// 해쉬태그 중복 검사
	public HashtagDto hashtagDuple(String content);
}
