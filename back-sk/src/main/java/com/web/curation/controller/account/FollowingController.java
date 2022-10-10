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

import com.web.curation.dto.FollowerDto;
import com.web.curation.dto.FollowingsDto;
import com.web.curation.dto.ScrapeDto;
import com.web.curation.service.IFollowingService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/following")
public class FollowingController {
	
	@Autowired
	IFollowingService fSer;
	
	
	@PostMapping("/insertfollowing")
	@ApiOperation(value="팔로잉 등록")
	private ResponseEntity<Map<String, Object>> insertFollowing(@RequestBody FollowingsDto f)	{
		ResponseEntity<Map<String, Object>> resEntity = null;
		System.out.println("팔로잉");
		try {
			int res = fSer.regFollowing(f.getU_index(), f.getFollowing_u_index());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg","등록 성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			
		}
		catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg","등록 실패");
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		return resEntity;
	}
	
	
	
	@DeleteMapping("/deletefollowing/{u_index}/{following_u_index}")
	@ApiOperation(value = "팔로잉 삭제")
	private @ResponseBody ResponseEntity<Map<String, Object>> deleteFollowing(FollowingsDto f)	{
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int res = fSer.deleteFollowing(f.getU_index(), f.getFollowing_u_index());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제성공");
			resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			System.out.println("팔로잉 삭제 오류");
			System.out.println(e);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제실패");
			resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	
	@GetMapping("/listfollowing/{u_index}/{page_index}")
	@ApiOperation(value = "특정회원 팔로잉 리스트 조회", response = FollowingsDto.class)
	private @ResponseBody ResponseEntity<Map<String,Object>> infoFollowing(@PathVariable("u_index") int u_index, @PathVariable("page_index") int page_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<FollowingsDto> following = null;
		try {
			following = fSer.InfoFollowing(u_index, page_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", following);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);	
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(e);
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	
	}
	
	@GetMapping("/isfollowing/{u_index}/{following_u_index}")
	@ApiOperation(value = "특정회원 팔로잉 관계 조회", response = FollowingsDto.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> isFollower(@PathVariable("u_index") int u_index, @PathVariable("following_u_index") int following_u_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			FollowingsDto following = fSer.isFollowing(u_index, following_u_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", following);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(e);
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("countfollowing/{u_index}")
	@ApiOperation(value = "특정회원 팔로잉 리스트 수 조회", response = FollowingsDto.class)
	private @ResponseBody ResponseEntity<Map<String,Object>> countFollowing(@PathVariable("u_index") int u_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		int count = 0;
		try {
			count = fSer.countFollowing(u_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", count);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);	
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println("팔로잉 수 조회 실패");
			System.out.println(e);
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;	
	}
}
