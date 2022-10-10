package com.web.curation.controller.account;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.DataDto;
import com.web.curation.service.IBoardService;
import com.web.curation.service.IDataService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/board")
public class BoardController {
	// private static final long serialVersionUID = 1L;

	@Autowired
	IBoardService bSer;

	@Autowired
	IDataService dSer;

	@PutMapping("/updateboard")
	@ApiOperation(value = "게시글 수정")
	private ResponseEntity<Map<String, Object>> updateBoard(@RequestBody BoardDto b) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int res = bSer.updateBoard(b.getB_index(), b.getU_index(), b.getContext(), b.getMulti_file());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "수정성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "수정실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}

	@GetMapping("/infoboard/{b_index}")
	@ApiOperation(value = "게시글 조회", response = BoardDto.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> infoBoard(@PathVariable("b_index") int b_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		BoardDto board = null;
		try {
			board = bSer.infoBoard(b_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", board);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;

	}

//	@GetMapping("/listboard")
//	@ApiOperation(value = "게시글 리스트 조회 ", response = List.class)
//	private @ResponseBody ResponseEntity<Map<String, Object>> listAllBoard() {
//		ResponseEntity<Map<String, Object>> resEntity = null;
//		try {
//			List<BoardDto> list = bSer.listAllBoard();
//			Map<String, Object> msg = new HashMap<String, Object>();
//			msg.put("resmsg", "게시글 List 조회 성공");
//			msg.put("resvalue", list);
//			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
//
//		} catch (RuntimeException e) {
//			Map<String, Object> msg = new HashMap<String, Object>();
//			msg.put("resmsg", "게시글 List 조회 실패");
//			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
//		}
//		return resEntity;
//	}

	@GetMapping("/listboard/{u_index}")
	@ApiOperation(value = "특정 회원 게시글 리스트 조회 ", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> listBoard(@PathVariable("u_index") int u_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<BoardDto> list = bSer.listBoard(u_index);

			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "게시글 List 조회 성공");
			msg.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "게시글 List 조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/listboard/{u_index}/{page_index}")
	@ApiOperation(value = "특정 회원 게시글 리스트 조회 ", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> listBoardFinal(@PathVariable("u_index") int u_index,
			@PathVariable("page_index") int page_index) {
		System.out.println("유저 인덱스 : " + u_index);
		System.out.println("페이지 인덱스 : " + page_index);
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<BoardDto> list = null;
			if (u_index != -1)
				list = bSer.listBoard(u_index, page_index);
			else
				list = bSer.listAllBoard(page_index);
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "게시글 List 조회 성공");
			msg.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);

		} catch (RuntimeException e) {
			System.out.println(e);
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "게시글 List 조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/listboard/search/{keyword}/{page_index}")
	@ApiOperation(value = "특정 키워드 포함 게시글 리스트 페이지별 조회 ", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> listBoardSearchPage(
			@PathVariable("keyword") String keyword, @PathVariable("page_index") int page_index) {
		System.out.println("키워드 : " + keyword);
		System.out.println("페이지 : " + page_index);
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<BoardDto> list = null;
			System.out.println("내용 : " + keyword.length());
			if (keyword.length() >= 2)
				list = bSer.listSearchBoardPage(keyword, page_index);

			System.out.println("내용 : " + list);
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "게시글 List 조회 성공");
			msg.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "게시글 List 조회 실패");
			System.out.println(e);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/listboard/search/{keyword}")
	@ApiOperation(value = "특정 키워드 포함 게시글 리스트 조회", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> listBoardSearch(@PathVariable("keyword") String keyword) {
		System.out.println("키워드 : " + keyword);
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<BoardDto> list = null;
			if (keyword.length() >= 2)
				list = bSer.listSearchBoard(keyword);

			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "게시글 List 조회 성공");
			msg.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "게시글 List 조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		return resEntity;
	}
//	@PostMapping("/regboard")
//	@ApiOperation(value = "게시글 등록")
//	private @ResponseBody ResponseEntity<Map<String, Object>> signUpMem(@RequestBody BoardDto u) {
//		System.out.println(u);
//		ResponseEntity<Map<String, Object>> resEntity = null;
//		try {
//			int signUpMem = bSer.regBoard(u.getU_index(), u.getContext(), u.getReg_date());
//			Map<String, Object> map = new HashMap<String, Object>();
//			if (signUpMem > 0)
//				map.put("resmsg", "등록성공");
//			else
//				map.put("resmsg", "등록실패");
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//
//		} catch (RuntimeException e) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("resmsg", "등록실패");
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		}
//		return resEntity;
//	}

	@PostMapping("/regboard")
	@ApiOperation(value = "게시글 등록")
	private @ResponseBody ResponseEntity<Map<String, Object>> regBoard(HttpServletRequest req) {
		System.out.println("일단왔다");
		int u_index = Integer.parseInt(req.getParameter("u_index"));
		String context = req.getParameter("text");
		String file_type = req.getParameter("type");
		System.out.println("text : " + context);
		System.out.println("type : " + file_type);
		System.out.println("imgList : " + req.getParameter("imgList"));
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<BoardDto> list = bSer.listBoard(u_index);
		int b_index = bSer.regBoard(u_index, context, "");

		// String path = "/boardimage/" + u_index + "/" + list.size() + 1;
		String path = "/board" + u_index;
		String realPath = req.getServletContext().getRealPath(path);

		System.out.println(realPath);
		File f = new File(realPath);

		if (!f.exists() || !f.isDirectory()) {
			f.mkdir();
		}

		String datarealPath = realPath + "/" + list.size();
		System.out.println(datarealPath);
		File dataf = new File(datarealPath);
		path = path + "/" + list.size();

		if (!dataf.exists() || !dataf.isDirectory()) {
			dataf.mkdir();
		}
		List<DataDto> datalist = new ArrayList<DataDto>();
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
		Iterator<String> fileNames = mhsr.getFileNames();
		StringBuilder imgLocations = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			while (fileNames.hasNext()) {
				String fileName = fileNames.next();
				System.out.println("fileName : " + fileName);
				MultipartFile file = mhsr.getFile(fileName);
				byte[] byteArr = file.getBytes();
				String saveFileName = file.getName();
				System.out.println("saveFileName : " + saveFileName);

				if (saveFileName.contains("code")) {
					datalist.add(new DataDto(byteArr, b_index, saveFileName.charAt(saveFileName.length() - 1) - '0'));
					System.out.println(datalist);
					dSer.regData(byteArr, b_index, saveFileName.charAt(saveFileName.length() - 1) - '0');
					imgLocations.append(' ');
					imgLocations.append(',');
				} else {
					File serverFile = new File(datarealPath + File.separator + saveFileName);
					file.transferTo(serverFile);
					map.put("uploaded", 1);
					map.put("url", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path
							+ "/" + saveFileName);
					map.put("fileName", saveFileName);
					imgLocations.append(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path
							+ "/" + saveFileName);
					imgLocations.append(',');
				}
			}

			map.put("b_index", b_index);
			bSer.updateBoard(b_index, u_index, context, imgLocations.toString());

			System.out.println(map);
			System.out.println("!!!");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resEntity;
	}

	@DeleteMapping("/deleteboard/{b_index}")
	@ApiOperation(value = "게시글 삭제")
	private @ResponseBody ResponseEntity<Map<String, Object>> deleteMem(@PathVariable("b_index") int b_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int res = bSer.deleteBoard(b_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

}
