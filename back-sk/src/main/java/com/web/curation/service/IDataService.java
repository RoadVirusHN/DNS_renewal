package com.web.curation.service;

import java.util.List;

import com.web.curation.dto.DataDto;

public interface IDataService {
	public int regData(byte[] blob, int b_index, int idx);
	
	public int updateData(int d_index, byte[] blob, int b_index, int idx);

	public DataDto infoData(int d_index);
	
	public int deleteData(int d_index); 

	public List<DataDto> listData(int b_index, int page_index);
	
	public List<DataDto> listData(int b_index);
	
	public List<DataDto> listAllData(int page_index);

}
