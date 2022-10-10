package com.web.curation.controller.account;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Delete;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.UserDto;
import com.web.curation.service.INoticeService;
import com.web.curation.service.IUserService;
import com.web.curation.service.JwtService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
	// private static final long serialVersionUID = 1L;

	@Autowired
	IUserService uSer;

	@Autowired
	INoticeService nSer;
	
	@Autowired
	ImageController Icontroll;
	
	@Autowired
    JwtService jwtService;
	
	@PutMapping("/updateuser")
	@ApiOperation(value = "회원정보 수정")
	private ResponseEntity<Map<String, Object>> updateMem(HttpServletRequest req) {
//		ResponseEntity<Map<String, Object>> resEntity = null;
//		try {
//			int res = uSer.updateUser(u.getEmail(), u.getPw(), u.getNickname(), u.getComment(), u.getBirth(),
//					u.getAccount_public(), u.getFollow_approval(), u.getPicture());
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("resmsg", "수정성공");
//			map.put("resvalue", res);
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		} catch (RuntimeException e) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("resmsg", "수정실패");
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//
//		}
//		return resEntity;
		String email = req.getParameter("email");
		String pw = req.getParameter("pw");
		String nickname = req.getParameter("nickname");
		String comment = req.getParameter("comment");
		String birth = req.getParameter("birth");
		String account_public = req.getParameter("account_public");
		String follow_approval = req.getParameter("follow_approval");
		String picture = "";
		System.out.println("이건 코멘트 " + comment);
		
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
//			int signupUser = uSer.signupUser(u.getEmail(), u.getPw(), u.getNickname(), u.getComment(), u.getBirth(),
//					u.getAccount_public(), u.getFollow_approval(), u.getPicture());
			int updateUser = 0;
			Map<String, Object> map = new HashMap<String, Object>();

			UserDto infoUser = uSer.infoUser(email);
			String path = "/profileimage" + infoUser.getU_index();
			String realPath = req.getServletContext().getRealPath(path);
			
			System.out.println(realPath);
			File f = new File(realPath);

			if (!f.exists()) {// 경로가 없다면 폴더를 만든다.
				f.mkdir();
			}

			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
			Iterator<String> fileNames = mhsr.getFileNames();

			if (!f.isDirectory()) {
				f.mkdir();
			}

			Map<String, Object> outData = new HashMap<String, Object>();
			try {
				while (fileNames.hasNext()) {
					String fileName = fileNames.next();
					System.out.println(fileName);
					MultipartFile file = mhsr.getFile(fileName);
//					String oriName = new String(file.getOriginalFilename().getBytes("UTF-8"));
					String saveFileName = file.getName(); // 저장할 파일이름을 만드는데 뒤에 확장자도 붙임?
//					System.out.println("oriName "+oriName);
//					String saveFileName = oriName;
					File serverFile = new File(realPath + File.separator + saveFileName);
					file.transferTo(serverFile);

//					map.put("resmsg", "succ");
					map.put("uploaded", 1);
					map.put("url", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/"
							+ saveFileName);
					map.put("fileName", saveFileName);
//					JSONObject outData = new JSONObject();
					outData.put("uploaded", 1);
					outData.put("url", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path
							+ "/" + saveFileName);
					outData.put("fileName", saveFileName);
					System.out.println(outData);
					System.out.println("!!!");
					updateUser = uSer.updateUser(email, pw, nickname, comment, birth, account_public, follow_approval, req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path
							+ "/" + saveFileName);
				}
				if (updateUser > 0)
					map.put("resmsg", "수정성공");
				else
					map.put("resmsg", "수정실패");
				resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resEntity;

		} catch (RuntimeException e) {
			System.out.println(e);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "수정실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/infouser/{email}")
	@ApiOperation(value = "회원정보조회", response = UserDto.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> infoUser(@PathVariable("email") String email) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		UserDto user = null;
		try {
			user = uSer.infoUser(email);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", user);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("/infouserbyindex/{u_index}")
	@ApiOperation(value = "회원정보조회", response = UserDto.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> infoUserbyIndex(@PathVariable("u_index") int u_index) {
		System.out.println(u_index);
		ResponseEntity<Map<String, Object>> resEntity = null;
		UserDto user = null;
		try {
			user = uSer.infoUser(u_index);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			map.put("resvalue", user);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;

	}

//	@PostMapping("/signup")
//	@ApiOperation(value = "회원가입")
//	private @ResponseBody ResponseEntity<Map<String, Object>> signUpMem(@RequestBody UserDto u) {
//		System.out.println(u);
//		
//		ResponseEntity<Map<String, Object>> resEntity = null;
//		try {
//			int signUpMem = uSer.signupUser(u.getEmail(), u.getPw(), u.getNickname(), u.getComment(), u.getBirth(),
//					u.getAccount_public(), u.getFollow_approval(), u.getPicture());
//			Icontroll.uploadImage(req);
//			Map<String, Object> map = new HashMap<String, Object>();
//			if (signUpMem > 0)
//				map.put("resmsg", "등록성공");
//			else
//				map.put("resmsg", "등록실패");
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//
//		} catch (RuntimeException e) {
//			System.out.println(e);
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("resmsg", "등록실패");
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		}
//		return resEntity;
//	}
	
	@PostMapping("/signup")
	@ApiOperation(value = "회원가입")
	private @ResponseBody ResponseEntity<Map<String, Object>> signupUser(HttpServletRequest req) {
//		System.out.println(u);
		String email = req.getParameter("email");
		String pw = req.getParameter("pw");
		String nickname = req.getParameter("nickname");
		String comment = req.getParameter("comment");
		String birth = req.getParameter("birth");
		String account_public = req.getParameter("account_public");
		String follow_approval = req.getParameter("follow_approval");
		String picture = "";
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
//			int signupUser = uSer.signupUser(u.getEmail(), u.getPw(), u.getNickname(), u.getComment(), u.getBirth(),
//					u.getAccount_public(), u.getFollow_approval(), u.getPicture());
			int signupUser = uSer.signupUser(email, pw, nickname, comment, birth, account_public, follow_approval, picture);
			
			Map<String, Object> map = new HashMap<String, Object>();
			if (signupUser > 0)
				map.put("resmsg", "등록성공");
			else
				map.put("resmsg", "등록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			UserDto infoUser = uSer.infoUser(email);			
			nSer.regNotice(infoUser.getU_index(), infoUser.getU_index(), "welcome", "false");
			String path = "/profileimage" + infoUser.getU_index();
			String realPath = req.getServletContext().getRealPath(path);
			
			File f = new File(realPath);

			if (!f.exists()) {// 경로가 없다면 폴더를 만든다.
				f.mkdir();
			}

			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
			Iterator<String> fileNames = mhsr.getFileNames();

			if (!f.isDirectory()) {
				f.mkdir();
			}

			Map<String, Object> outData = new HashMap<String, Object>();
			try {
				while (fileNames.hasNext()) {
					String fileName = fileNames.next();
					System.out.println(fileName);
					MultipartFile file = mhsr.getFile(fileName);
//					String oriName = new String(file.getOriginalFilename().getBytes("UTF-8"));
					String saveFileName = file.getName(); // 저장할 파일이름을 만드는데 뒤에 확장자도 붙임?
//					System.out.println("oriName "+oriName);
//					String saveFileName = oriName;
					File serverFile = new File(realPath + File.separator + saveFileName);
					file.transferTo(serverFile);

//					map.put("resmsg", "succ");
					map.put("uploaded", 1);
					map.put("url", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/"
							+ saveFileName);
					map.put("fileName", saveFileName);
//					JSONObject outData = new JSONObject();
					outData.put("uploaded", 1);
					outData.put("url", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path
							+ "/" + saveFileName);
					outData.put("fileName", saveFileName);
					System.out.println(outData);
					System.out.println("!!!");
					uSer.updateUser(email, pw, nickname, comment, birth, account_public, follow_approval, req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path
							+ "/" + saveFileName);
				}
				resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resEntity;

		} catch (RuntimeException e) {
			System.out.println(e);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "등록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	

	@DeleteMapping("/deleteuser/{email}")
	@ApiOperation(value = "회원정보삭제")
	private @ResponseBody ResponseEntity<Map<String, Object>> deleteMem(@PathVariable("email") String email) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int res = uSer.deleteUser(email);
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
	
	@PutMapping("/findpassword/{email}")
	@ApiOperation(value = "email을 받아 비밀번호 찾기 서비스")
	public ResponseEntity<Map<String, Object>> findPassword(@PathVariable("email") String email) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		boolean flag = false;
		System.out.println(email);
		try {
			flag = uSer.findPw(email);
			Map<String, Object> map = new HashMap<>();
			map.put("resmsg", "찾기 성공");
			map.put("resvalue", flag);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<>();
			map.put("resmsg", "찾기 실패");
			map.put("resvalue", flag);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인")
	private @ResponseBody ResponseEntity<Map<String, Object>> signUpMem(@RequestBody UserDto u, HttpServletRequest req) {
		System.out.println(u);
		System.out.println(req.getHeader("Content-type"));
		System.out.println(req.getHeader("Authorization"));
		ResponseEntity<Map<String, Object>> resEntity = null;
		UserDto loginUser = null;
		String token = null;
		try {
			loginUser = uSer.loginUser(u.getEmail(), u.getPw());
			System.out.println(loginUser);
	        token = jwtService.create(loginUser);
	        System.out.println(token);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "로그인 성공");
			map.put("resvalue", token);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			System.out.println(e);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "로그인 실패");
			map.put("resvalue", loginUser);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
		System.out.println("token 해석 이메일");
		System.out.println(jwtService.getUserEmail(token));
		System.out.println("token 해석 u_index");
		System.out.println(jwtService.getUserIndex(token));
		System.out.println(jwtService.validationToken(token));
		return resEntity;
	}
	
//	@PostMapping(value="/signin")
//    public Result signin(String email, String password, HttpServletResponse response){
////    	Result result = Result.successInstance();
//        UserDto loginMember = uSer.loginUser(email, password);
//        String token = jwtService.create(loginMember);
//        response.setHeader("Authorization", token);
//        result.setData(loginMember);
//        return result;
//    }

	@GetMapping("/checkduplicateemail/{email}")
	@ApiOperation(value = "이메일중복조회", response = UserDto.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> duplicateCheckEmail(@PathVariable("email") String email) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		System.out.println(email);
		UserDto user = null;
		try {
			user = uSer.emailDuplicateCheck(email);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			boolean res = false;
			if(user != null)
				res = true;
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			map.put("resvalue", false);
			System.out.println(e);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;

	}
	
	@GetMapping("/checkduplicatenickname/{nickname}")
	@ApiOperation(value = "닉네임중복조회", response = UserDto.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> duplicateCheckNickname(@PathVariable("nickname") String nickname) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		System.out.println(nickname);
		UserDto user = null;
		try {
			user = uSer.nicknameDuplicateCheck(nickname);
			System.out.println(user);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회성공");
			boolean res = false;
			if(user != null)
				res = true;
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			map.put("resvalue", false);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("/infousers/search/{keyword}/{page_index}")
	@ApiOperation(value = "특정 키워드 포함 닉네임 유저 리스트 조회", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> listUserSearchPage(@PathVariable("keyword") String keyword, @PathVariable("page_index") int page_index) {
		System.out.println("키워드 : " + keyword);
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<UserDto> list = null;
			if(keyword.length() >= 2)
				list = uSer.listSearchUserPage(keyword, page_index);
			
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "유저 List 조회 성공");
			msg.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("resmsg", "유저 List 조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		return resEntity;
	}
}
