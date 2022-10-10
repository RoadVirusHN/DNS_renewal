package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.CurationDto;
import com.web.curation.repository.ICurationRepository;

@Service
public class CurationServiceImpl implements ICurationService{

	@Autowired
	ICurationRepository repo;
	
	@Override
	public int regCuration(int u_index, String set_name, String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCuration(int cu_index, int u_index, String set_name, String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CurationDto infoBoard(int cu_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCuration(int cu_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CurationDto> listCuration(int u_index) {
		// TODO Auto-generated method stub
		return null;
	}

}
