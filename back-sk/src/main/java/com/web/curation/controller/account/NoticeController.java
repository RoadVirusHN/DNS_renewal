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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.NoticeDto;
import com.web.curation.service.INoticeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

	@Autowired
	INoticeService nSer;
	@GetMapping("listnotice/{u_index}")
	@ApiOperation(value = "특정회원 알림 리스트 조회", response = NoticeDto.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> listNotice(@PathVariable("u_index") int u_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<NoticeDto> listNotice = null;
		try {
			listNotice = nSer.listNotice(u_index);
			System.out.println(listNotice);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", listNotice);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}	
	@GetMapping("countnotice/{u_index}")
	@ApiOperation(value = "특정회원 알림 갯수 조회", response = Integer.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> countNotice(@PathVariable("u_index") int u_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int listNotice = nSer.listNotice(u_index).size();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", listNotice);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	@GetMapping("delete/{n_index}")
	@ApiOperation(value = "특정알림 삭제", response =  String.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> deleteNotice(@PathVariable("n_index") int n_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			nSer.deleteNotice(n_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제 성공");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

}
