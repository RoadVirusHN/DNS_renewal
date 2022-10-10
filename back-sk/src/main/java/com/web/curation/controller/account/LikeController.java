package com.web.curation.controller.account;

import java.util.HashMap;
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

import com.web.curation.dto.LikesDto;
import com.web.curation.service.ILikeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/like")
public class LikeController {

	@Autowired
	ILikeService like;
	

	@PostMapping("/insert")
	@ApiOperation(value = "좋아요 등록")
	private @ResponseBody ResponseEntity<Map<String, Object>> insertLike(@RequestBody LikesDto l) {
		System.out.println(l);
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int insertLike = like.insertLike(l.getB_index(), l.getLike_u_index());
			Map<String, Object> map = new HashMap<String, Object>();
			if (insertLike > 0)
				map.put("resmsg", "등록 성공");
			else
				map.put("resmsg", "등록 실패");

			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(e);
			map.put("resmsg", "등록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	

	@GetMapping("infolike/{b_index}/{like_u_index}")
	@ApiOperation(value = "좋아요 조회")
	private @ResponseBody ResponseEntity<Map<String, Object>> infoLike(@PathVariable("b_index") int b_index, @PathVariable("like_u_index") int like_u_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		System.out.println();
		try {
			int likes = like.infoLike(b_index);
			int doILike = like.doILike(b_index, like_u_index);
			boolean do_i_like = doILike == 1 ? true : false;
			
			LikesDto res = new LikesDto(likes, do_i_like);
			System.out.println(res);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회 성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	
	@DeleteMapping("/deletelike/{b_index}/{like_u_index}")
	@ApiOperation(value = "좋아요 삭제")
	private @ResponseBody ResponseEntity<Map<String, Object>> deleteLike(@PathVariable("b_index") int b_index,
			@PathVariable("like_u_index") int like_u_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int res = like.deleteLike(b_index, like_u_index);
			Map<String, Object> map = new HashMap<String, Object>();
			if (res > 0) {
				map.put("resmsg", "삭제 성공");
			} else {
				map.put("resmsg", "삭제 실패");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		return resEntity;
	}

}
