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
import org.springframework.web.bind.annotation.RestController;import com.web.curation.dto.FollowerDto;
import com.web.curation.service.IFollowerService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/follower")
public class FollowerController {

	@Autowired
	IFollowerService fSer;
	
	
	@PostMapping("/insertfollower")
	@ApiOperation(value="팔로워 등록")
	private ResponseEntity<Map<String, Object>> insertFollower(@RequestBody FollowerDto f)	{
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int res = fSer.regFollower(f.getU_index(), f.getFollower_u_index());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg","등록 성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			
		}
		catch (RuntimeException e) {
			System.out.println("팔로워 등록 오류");
			System.out.println(e);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg","등록 실패");
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		return resEntity;
	}
	
	
	
	@DeleteMapping("/deletefollower/{u_index}/{follower_u_index}")
	@ApiOperation(value = "팔로우 삭제")
	private @ResponseBody ResponseEntity<Map<String, Object>> deleteFollower(FollowerDto f)	{
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int res = fSer.deleteFollower(f.getU_index(),f.getFollower_u_index());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제성공");
			resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			System.out.println("팔로워 삭제 오류");
			System.out.println(e);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제실패");
			resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	
	@GetMapping("listfollower/{u_index}/{page_index}")
	@ApiOperation(value = "특정회원 팔로워 리스트 조회", response = FollowerDto.class)
	private @ResponseBody ResponseEntity<Map<String,Object>> infoFollower(@PathVariable("u_index") int u_index, @PathVariable("page_index") int page_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<FollowerDto> follower = null;
		try {
			follower = fSer.InfoFollower(u_index, page_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", follower);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);	
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(e);
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;	
	}
	
	@GetMapping("countfollower/{u_index}")
	@ApiOperation(value = "특정회원 팔로워 리스트 수 조회", response = FollowerDto.class)
	private @ResponseBody ResponseEntity<Map<String,Object>> countFollower(@PathVariable("u_index") int u_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		int count = 0;
		try {
			count = fSer.CountFollower(u_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", count);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);	
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println("팔로워 수 조회 실패");
			System.out.println(e);
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;	
	}
	
}
