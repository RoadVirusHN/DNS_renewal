package com.web.curation.repository;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.dto.DataDto;

@Repository
public class DataRepositoryImpl implements IDataRepository{

	@Autowired
	SqlSession session;
	
	@Override
	public int DataInsert(DataDto d) {
		// TODO Auto-generated method stub
		return session.insert("curation.data.insert", d);
	}

	@Override
	public List<DataDto> DataList(int b_index) {
		// TODO Auto-generated method stub
		return session.selectList("curation.data.selectUser", b_index);
	}

	@Override
	public List<DataDto> DataList(int u_index, int page_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataDto> DataAllList(int page_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataDto DataInfo(int b_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int DataUpdate(DataDto d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int DataDelete(int b_index) {
		// TODO Auto-generated method stub
		return 0;
	}

}
