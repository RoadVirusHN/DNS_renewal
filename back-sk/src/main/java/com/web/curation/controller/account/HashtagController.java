package com.web.curation.controller.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.HashtagDto;
import com.web.curation.dto.HashtagInfoDto;
import com.web.curation.service.IBoardhashService;
import com.web.curation.service.IHashtagService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hashtag")
public class HashtagController {

	@Autowired
	IHashtagService hSer;
	
	@Autowired
	IBoardhashService bhSer;
	
	@PostMapping("/insert/") //put, Post, Get 등의 Mapping이 있음
	@ApiOperation(value="해쉬태그 생성")
	private ResponseEntity<Map<String, Object>> insertHash(@RequestBody HashtagInfoDto requestbody){ // @RequestBody는 path에 넣어주는 것이 아닌 json으로 포함해서 보낼시 사용
		ResponseEntity<Map<String, Object>> resEntity = null; //RequestParam을 통해 여러 변수를 받을 수 있다.
		Map<String, Object> msg = new HashMap<String, Object>();
		try {
			int res = hSer.regHashtag(requestbody.getContent(), requestbody.getB_index());
			msg.put("resmsg", "해쉬 태그 생성 성공");
			msg.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		} catch (RuntimeException e) {
			msg.put("resmsg", "해쉬 태그 생성 실패" + e);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.BAD_REQUEST);		
		}		
		return resEntity;
	}
	
	@GetMapping("/getInfo/{h_index}/")
	@ApiOperation(value = "해쉬태그 정보 조회", response = HashtagDto.class) //swagger display용 안적어줘도 됨
	private @ResponseBody ResponseEntity<Map<String, Object>> infoHashtag(@PathVariable("h_index") int h_index){ //@PathVariable은  주소에 값을 넣어 전달할 시 사용
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashtagDto hashtag = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			hashtag = hSer.infoHashtag(h_index);
			map.put("resmsg", "조회성공");
			map.put("resvalue", hashtag);
			System.out.println("영어태그");
			System.out.println(hashtag);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			map.put("resmsg", "조회실패" + e);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
		return resEntity;		
	}
	
	@GetMapping("/searchHashtag/{keyword}/{page_index}/")
	@ApiOperation(value = "해쉬태그 키워드 검색", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> listHashtagSearchPage(@PathVariable("keyword") String keyword, @PathVariable("page_index") int page_index){
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();		
		try {
			List<HashtagDto> list = null;
			if(keyword.length() >= 2)
				list = hSer.searchHashtag(keyword, page_index);
			map.put("resmsg", "해쉬태그 리스트 조회 성공");
			map.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			map.put("resmsg", "해쉬태그  리스트 조회 실패 :" + e);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	@GetMapping("/searchHashtag/{content}/")
	@ApiOperation(value = "해쉬태그 키워드 검색")
	private @ResponseBody ResponseEntity<Map<String, Object>> listHashtagSearchPage(@PathVariable("content") String content){
		ResponseEntity<Map<String, Object>> resEntity = null;
		System.out.println(content);
		Map<String, Object> map = new HashMap<String, Object>();		
		try {
			HashtagDto target = null;
			target = hSer.dupleHashtag(content);
			map.put("resmsg", "해쉬태그 검색 성공");
			map.put("resvalue", target);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			map.put("resmsg", "해쉬태그  검색 실패 :" + e);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
//	@PostMapping("/searchHashtag/")
//	@ApiOperation(value = "해쉬태그 키워드 검색")
//	private @ResponseBody ResponseEntity<Map<String, Object>> listHashtagSearchPage(@RequestBody String content){
//		ResponseEntity<Map<String, Object>> resEntity = null;
//		Map<String, Object> map = new HashMap<String, Object>();		
//		try {
//			HashtagDto target = null;
//			target = hSer.dupleHashtag(content);
//			map.put("resmsg", "해쉬태그 검색 성공");
//			map.put("resvalue", target);
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		} catch (RuntimeException e) {
//			map.put("resmsg", "해쉬태그  검색 실패 :" + e);
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
//		}
//		return resEntity;
//	}
}
