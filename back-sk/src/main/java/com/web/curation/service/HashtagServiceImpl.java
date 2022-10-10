package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.BoardhashDto;
import com.web.curation.dto.HashtagDto;
import com.web.curation.repository.IHashtagRepository;

@Service
public class HashtagServiceImpl implements IHashtagService {
	
	@Autowired
	IHashtagRepository repo;
	@Autowired
	IBoardhashService bhSer;
	
	@Override
	public int regHashtag(String content, int b_index) {
		HashtagDto checkDuple = repo.hashtagDuple(content);

		if (checkDuple == null) {			
			repo.hashtagInsert(new HashtagDto(content));	
			int h_index = repo.hashtagDuple(content).getH_index();	 
			bhSer.insertBoardhash(b_index, h_index);
			return 1;
		}			
		else {	 
			 bhSer.insertBoardhash(b_index, checkDuple.getH_index());
			return 0;
		}
	}


	@Override
	public HashtagDto infoHashtag(int h_index) {
		return repo.hashtagInfo(h_index);
	}
	
	@Override
	public List<HashtagDto> searchHashtag(String keyword, int page_index){
		return repo.hashtagSearch(keyword, page_index);
	}		

	@Override
	public HashtagDto dupleHashtag(String content) {
		return repo.hashtagDuple(content);
	}
	
}
