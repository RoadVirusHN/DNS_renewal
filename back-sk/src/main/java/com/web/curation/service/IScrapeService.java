package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.ScrapeDto;

public interface IScrapeService {
	
	public int regScrape(int b_index, int u_index);

	public int updateScrape(int s_index, int b_index, int u_index);

	public List<BoardDto> listScrape(int u_index, int page_index);
	
	public int countScrape(int u_index);

	public ScrapeDto infoScrape(int b_index, int u_index);

	public int deleteScrape(int u_index, int b_index);
}
