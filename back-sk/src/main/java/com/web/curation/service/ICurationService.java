package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.CurationDto;

public interface ICurationService {

	public int regCuration(int u_index, String set_name, String keyword);
	
	public int updateCuration(int cu_index, int u_index, String set_name, String keyword);

	public CurationDto infoBoard(int cu_index);
	
	public int deleteCuration(int cu_index); 

	public List<CurationDto> listCuration(int u_index);
}
