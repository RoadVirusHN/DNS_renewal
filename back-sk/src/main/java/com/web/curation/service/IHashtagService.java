package com.web.curation.service;

import java.util.List;
import com.web.curation.dto.HashtagDto;


public interface IHashtagService {

	
	public int regHashtag(String content, int b_index);
	
	public HashtagDto infoHashtag(int h_index);
	
	public List<HashtagDto> searchHashtag(String keyword, int page_index);
	
	public HashtagDto dupleHashtag(String content);


}
