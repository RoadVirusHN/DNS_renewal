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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dto.CommentDto;
import com.web.curation.service.ICommentService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	ICommentService cSer;
	
	@PutMapping("/updateComment")
	@ApiOperation(value="댓글 수정")
	private ResponseEntity<Map<String, Object>> updateCom(@RequestBody CommentDto c){
		ResponseEntity<Map<String , Object>> resEntity = null;
		try {
			int res = cSer.updateComment(c.getB_index(), c.getU_index(), c.getDepth(),c.getContext());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg","수정 성공");
			map.put("resvalue",res);
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "수정실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		
		return resEntity;
	}
	
	@GetMapping("/infocomment/{b_index}/{u_index}")
	@ApiOperation(value = "특정 회원 댓글 조회", response = CommentDto.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> infoCom(CommentDto c) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		CommentDto comment = null;
		try {
			comment = cSer.InfoComment(c.getB_index(), c.getU_index());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", comment);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;

	}
	
	@GetMapping("/listcomment/{b_index}")
	@ApiOperation(value = "댓글 리스트 조회 ", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> listComment(@PathVariable("b_index") int b_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<CommentDto> list = cSer.listComment(b_index);
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "댓글 List 조회 성공");
			msg.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "댓글 List 조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/listcomment/{b_index}/{page_index}")
	@ApiOperation(value = "댓글 리스트 조회 ", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> listComment(@PathVariable("b_index") int b_index, @PathVariable("page_index") int page_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			System.out.println("댓글리스트 조회 시도1");
			List<CommentDto> list = cSer.listComment(b_index, page_index);

			System.out.println("댓글리스트 조회 시도1.825");
			Map<String, Object> msg = new HashMap<String, Object>();
			System.out.println("댓글리스트 조회 시도2");
			msg.put("resmsg", "댓글 List 조회 성공");
			msg.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "댓글 List 조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/countcomment/{b_index}")
	@ApiOperation(value = "댓글 갯수 조회 ", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> countComment(@PathVariable("b_index") int b_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int countComment = cSer.countComment(b_index);
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "댓글 List 조회 성공");
			msg.put("resvalue", countComment);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "댓글 List 조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		return resEntity;
	}

	
	
	@PostMapping("/regcomment")
	@ApiOperation(value = "댓글 등록")
	private @ResponseBody ResponseEntity<Map<String, Object>> signUpCom(@RequestBody CommentDto c) {
		System.out.println(c);
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int signUpCom = cSer.regComment(c.getB_index(), c.getU_index(), c.getDepth(), c.getContext());
			Map<String, Object> map = new HashMap<String, Object>();
			if (signUpCom > 0)
				map.put("resmsg", "등록성공");
			else
				map.put("resmsg", "등록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "등록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	@DeleteMapping("/deletecomment/{b_index}/{u_index}")
	@ApiOperation(value = "게시글 삭제")
	private @ResponseBody ResponseEntity<Map<String, Object>> deleteCom(CommentDto c) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int res = cSer.deleteComment(c.getB_index(), c.getU_index());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제성공");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	
}
