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

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.ScrapeDto;
import com.web.curation.service.IScrapeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/scrape")
public class ScrapeController {

	@Autowired
	IScrapeService sSer;
	
	@PostMapping("/insert")
	@ApiOperation(value = "스크랩 등록")
	private @ResponseBody ResponseEntity<Map<String, Object>> insertScrape(@RequestBody ScrapeDto s) {
		System.out.println(s);
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int regScrape = sSer.regScrape(s.getB_index(), s.getU_index());
			Map<String, Object> map = new HashMap<String, Object>();
			if (regScrape > 0)
				map.put("resmsg", "등록 성공");
			else
				map.put("resmsg", "등록 실패");

			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			System.out.println(e);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "등록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("listscrape/{u_index}/{page_index}")
	@ApiOperation(value = "특정회원 스크랩 리스트 조회", response = ScrapeDto.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> listScrape(@PathVariable("u_index") int u_index, @PathVariable("page_index") int page_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<BoardDto> listScrape = null;
		try {
			listScrape = sSer.listScrape(u_index, page_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", listScrape);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("countscrape/{u_index}")
	@ApiOperation(value = "특정회원 스크랩 갯수 조회", response = ScrapeDto.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> countScrape(@PathVariable("u_index") int u_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int countScrape = sSer.countScrape(u_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", countScrape);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("infoscrape/{b_index}/{u_index}")
	@ApiOperation(value = "특정회원 스크랩 정보 조회", response = ScrapeDto.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> infoScrape(@PathVariable("b_index") int b_index, @PathVariable("u_index") int u_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		ScrapeDto infoScrape = null;
		try {
			infoScrape = sSer.infoScrape(b_index, u_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", infoScrape);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@DeleteMapping("/deletescrape/{b_index}/{u_index}")
	@ApiOperation(value = "스크랩 삭제")
	private @ResponseBody ResponseEntity<Map<String, Object>> deleteLike(@PathVariable("u_index") int u_index, @PathVariable("b_index") int b_index) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int deleteScrape = sSer.deleteScrape(u_index, b_index);
			Map<String, Object> map = new HashMap<String, Object>();
			if (deleteScrape > 0) {
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
