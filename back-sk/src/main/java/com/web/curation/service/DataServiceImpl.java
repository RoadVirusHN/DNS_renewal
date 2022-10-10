package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.DataDto;
import com.web.curation.repository.IDataRepository;

@Service
public class DataServiceImpl implements IDataService{

	@Autowired
	IDataRepository repo;
	
	@Override
	public int regData(byte[] blob, int b_index, int idx) {
		// TODO Auto-generated method stub
		return repo.DataInsert(new DataDto(blob, b_index, idx));
	}

	@Override
	public int updateData(int d_index, byte[] blob, int b_index, int idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DataDto infoData(int d_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteData(int d_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DataDto> listData(int b_index, int page_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataDto> listData(int b_index) {
		// TODO Auto-generated method stub
		return repo.DataList(b_index);
	}

	@Override
	public List<DataDto> listAllData(int page_index) {
		// TODO Auto-generated method stub
		return null;
	}

}
