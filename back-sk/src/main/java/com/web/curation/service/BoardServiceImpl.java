package com.web.curation.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.DataDto;
import com.web.curation.repository.IBoardRepository;

@Service
public class BoardServiceImpl implements IBoardService{

	@Autowired
	IBoardRepository repo;
	
	@Autowired
	IDataService dSer;
	
	@Override
	public int regBoard(int u_index, String context, String multi_file) {
		// TODO Auto-generated method stub
		if (repo.boardInsert(new BoardDto(u_index, context, multi_file))!=0)
			return getBoardIdx(u_index, context, multi_file);
		else
			return 0;
	}

	@Override
	public int updateBoard(int b_index, int u_index, String context, String multi_file) {
		// TODO Auto-generated method stub
		return repo.boardUpdate(new BoardDto(b_index, u_index, context, multi_file));
	}

	@Override
	public BoardDto infoBoard(int b_index) {
		// TODO Auto-generated method stub		
		BoardDto board = repo.boardInfo(b_index);
		List<DataDto> listData = dSer.listData(board.getB_index());
		System.out.println("listData : " + listData);
		byte[][] datas = new byte[listData.size()][];
		for (int i = 0; i < listData.size(); i++) {
			datas[i] = listData.get(i).getdata_file();
		}
		board.setData_file(datas);
		board.setFileLoc(board.getMulti_file().split(",")); // board에 이거 해줘야함 왜?
		return board;
	}
	@Override
	public int getBoardIdx(int u_index, String context, String multi_file) {
		return repo.boardIdxGet(u_index, context, multi_file);
	}

	@Override
	public int deleteBoard(int b_index) {
		// TODO Auto-generated method stub
		return repo.boardDelete(b_index);
	}

	@Override
	public List<BoardDto> listBoard(int u_index, int page_index) {
		// TODO Auto-generated method stub
		List<BoardDto> boardList = repo.boardList(u_index, page_index);
		for (BoardDto boardDto : boardList) {
			List<DataDto> listData = dSer.listData(boardDto.getB_index());
			System.out.println("listData : " + listData);
			byte[][] datas = new byte[listData.size()][];
//			SerialBlob[] blob = new SerialBlob[listData.size()];
			for (int i = 0; i < listData.size(); i++) {
				datas[i] = listData.get(i).getdata_file();
//				try {
//					blob[i] =  new javax.sql.rowset.serial.SerialBlob(listData.get(i).getdata_file());
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println(blob[i]);
			}
			boardDto.setData_file(datas);
			//boardDto.setB(blob);
			boardDto.setFileLoc(boardDto.getMulti_file().split(","));
		}
		return boardList;
	}
	
	@Override
	public List<BoardDto> listBoard(int u_index) {
		// TODO Auto-generated method stub
		List<BoardDto> boardList = repo.boardList(u_index);
		for (BoardDto boardDto : boardList) {
			List<DataDto> listData = dSer.listData(boardDto.getB_index());
			System.out.println("listData : " + listData);
			byte[][] datas = new byte[listData.size()][];
			for (int i = 0; i < listData.size(); i++) {
				datas[i] = listData.get(i).getdata_file();
			}
			boardDto.setData_file(datas);
			boardDto.setFileLoc(boardDto.getMulti_file().split(","));
		}
		return boardList;
	}

	@Override
	public List<BoardDto> listAllBoard(int page_index) {
		// TODO Auto-generated method stub
		List<BoardDto> boardList = repo.boardAllList(page_index);
		for (BoardDto boardDto : boardList) {
			List<DataDto> listData = dSer.listData(boardDto.getB_index());
			System.out.println("listData : " + listData);
			byte[][] datas = new byte[listData.size()][];
			for (int i = 0; i < listData.size(); i++) {
				datas[i] = listData.get(i).getdata_file();
			}
			boardDto.setData_file(datas);
			boardDto.setFileLoc(boardDto.getMulti_file().split(","));
		}
		return boardList;
	}

	@Override
	public List<BoardDto> listSearchBoard(String keyword) {
		// TODO Auto-generated method stub
		List<BoardDto> boardList = repo.boardSearchList(keyword);
		for (BoardDto boardDto : boardList) {
			List<DataDto> listData = dSer.listData(boardDto.getB_index());
			System.out.println("listData : " + listData);
			byte[][] datas = new byte[listData.size()][];
			for (int i = 0; i < listData.size(); i++) {
				datas[i] = listData.get(i).getdata_file();
			}
			boardDto.setData_file(datas);
			boardDto.setFileLoc(boardDto.getMulti_file().split(","));
		}
		return boardList;
	}
	@Override
	public List<BoardDto> listSearchBoardPage(String keyword, int page_index) {
		// TODO Auto-generated method stub
		List<BoardDto> boardList = repo.boardSearchListPage(keyword, page_index);
		for (BoardDto boardDto : boardList) {
			List<DataDto> listData = dSer.listData(boardDto.getB_index());
			System.out.println("listData : " + listData);
			byte[][] datas = new byte[listData.size()][];
			for (int i = 0; i < listData.size(); i++) {
				datas[i] = listData.get(i).getdata_file();
			}
			boardDto.setData_file(datas);
			boardDto.setFileLoc(boardDto.getMulti_file().split(","));
		}
		return boardList;
	}
}
