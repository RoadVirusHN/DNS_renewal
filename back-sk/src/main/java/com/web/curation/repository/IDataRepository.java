package com.web.curation.repository;

import java.util.List;

import com.web.curation.dto.DataDto;

public interface IDataRepository {
	
		//게시글 데이터 등록
		public int DataInsert(DataDto d);

		//특정 유저 게시글 데이터 리스트 검색
		public List<DataDto> DataList(int b_index);
		
		//특정 유저 게시글 데이터 리스트 검색
		public List<DataDto> DataList(int u_index, int page_index);
		
		//게시글 데이터 리스트 검색
		public List<DataDto> DataAllList(int page_index);

		//게시긓 하나 검색
		public DataDto DataInfo(int b_index);

		//게시글 데이터 수정
		public int DataUpdate(DataDto d);
		
		//게시글 데이터 삭제
		public int DataDelete(int b_index);

}
