package com.web.curation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.ScrapeDto;
import com.web.curation.repository.IBoardRepository;
import com.web.curation.repository.IScrapeRepository;

@Service
public class ScrapeServiceImpl implements IScrapeService{

	@Autowired
	IScrapeRepository repo;
	
	@Autowired
	IBoardService bSer;
	
	@Override
	public int regScrape(int b_index, int u_index) {
		// TODO Auto-generated method stub
		return repo.scrapeInsert(new ScrapeDto(b_index, u_index));
	}

	@Override
	public int updateScrape(int s_index, int b_index, int u_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDto> listScrape(int u_index, int page_index) {
		// TODO Auto-generated method stub
		List<ScrapeDto> scrapeList = repo.scrapeList(u_index, page_index);
		List<BoardDto> boards = new ArrayList<BoardDto>();
		for (ScrapeDto scrapeDto : scrapeList) {
			BoardDto boardInfo = bSer.infoBoard(scrapeDto.getB_index());
			//boardInfo.setFileLoc(boardInfo.getMulti_file().split(","));
			boards.add(boardInfo);
		}
		return boards;
	}

	@Override
	public ScrapeDto infoScrape(int b_index, int u_index) {
		// TODO Auto-generated method stub
		return repo.scrapeInfo(b_index, u_index);
	}

	@Override
	public int deleteScrape(int u_index, int b_index) {
		// TODO Auto-generated method stub
		return repo.scrapeDelete(u_index, b_index);
	}

	@Override
	public int countScrape(int u_index) {
		// TODO Auto-generated method stub
		return repo.scrapeCount(u_index);
	}

}
