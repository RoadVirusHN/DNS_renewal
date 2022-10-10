package com.web.curation.controller.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.BoardhashDto;
import com.web.curation.service.IBoardhashService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/boardhash")
public class BoardhashController {
	
	@Autowired
	IBoardhashService bhSer;
	
	@PostMapping("/insert")
	@ApiOperation(value="해쉬태그와 보드의 관계 추가")
	private ResponseEntity<Map<String, Object>> insertBoardhash(@RequestBody int b_index, @RequestBody int h_index){
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int res = bhSer.insertBoardhash(b_index, h_index);
			map.put("resmsg", "해쉬태그 보드 관계 설정 성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			map.put("resmsg", "해쉬태그 보드 관계 설정 실패" + e);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);	
		}		
		return resEntity;
	}
	@GetMapping("/getBoard/{h_index}")
	@ApiOperation(value="해당 해쉬태그를 포함한 보드들 조회", response =List.class)
	private ResponseEntity<Map<String, Object>> getBoardhashListByH(@PathVariable("h_index") int h_index){
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();		
		try {
			List<BoardhashDto> list = null;
			list = bhSer.getBoardhashListByHashtag(h_index);
			map.put("resmsg", "특정 해쉬태그를 포함한 보드해쉬 리스트 조회 성공");
			map.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);		
		} catch (RuntimeException e) {
			map.put("resmsg", "특정 해쉬태그를 포함한 보드해쉬 리스트 조회 실패" + e);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);	
		}	
		return resEntity;
	}
	
	@GetMapping("/getBoard/{h_index}/{page_index}/")
	@ApiOperation(value="해당 해쉬태그를 포함한 보드들 페이지 조회", response =List.class)
	private ResponseEntity<Map<String, Object>> getBoardhashListByH(@PathVariable("h_index") int h_index,@PathVariable("page_index") int page_index){
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();		
		try {
			List<BoardhashDto> list = null;
			list = bhSer.getBoardhashListByHashtagPage(h_index,page_index);
			map.put("resmsg", "특정 해쉬태그를 포함한 보드해쉬 리스트 페이지 조회 성공");
			map.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);		
		} catch (RuntimeException e) {
			map.put("resmsg", "특정 해쉬태그를 포함한 보드해쉬 리스트 페이지  조회 실패" + e);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);	
		}	
		return resEntity;
	}
	
	@GetMapping("/getHashtag/{b_index}")
	@ApiOperation(value="해당 보드를 포함한 해쉬태그들 조회", response =List.class)
	private ResponseEntity<Map<String, Object>> getBoardhashListByB(@PathVariable("b_index") int b_index){
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();		
		try {
			List<BoardhashDto> list = null;
			list = bhSer.getBoardhashListByFeed(b_index);
			map.put("resmsg", "특정 보드를 포함한 보드해쉬 리스트 조회 성공");
			map.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);		
		} catch (RuntimeException e) {
			map.put("resmsg", "특정 보드를 포함한 보드해쉬 리스트 조회 실패" + e);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);	
		}	
		return resEntity;
	}
	
	@DeleteMapping("/delete/{b_index}/{h_index}")
	@ApiOperation(value="해당 보드와 해쉬태그의 관계 삭제", response=List.class)
	private ResponseEntity<Map<String, Object>> deleteBoardhash(@PathVariable("b_index") int b_index, @PathVariable("h_index") int h_index){
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();		
		try {
			int res = bhSer.deleteBoardhash(b_index, h_index);
			map.put("resmsg", "특정 보드와 해쉬태그 관계 삭제 성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);		
		} catch (RuntimeException e) {
			map.put("resmsg", "특정 보드와 해쉬태그 관계 삭제 실패" + e);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);	
		}	
		return resEntity;
	}
}
