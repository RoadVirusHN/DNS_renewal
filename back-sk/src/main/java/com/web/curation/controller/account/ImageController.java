package com.web.curation.controller.account;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.curation.dto.BoardDto;
import com.web.curation.dto.UserDto;
import com.web.curation.service.IBoardService;
import com.web.curation.service.IUserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/")
public class ImageController {

	@Autowired
	IUserService uSer;
	
	
	@Autowired
	IBoardService bSer;

	@PutMapping("/updateboard")
	@ApiOperation(value = "게시글 수정")
	private ResponseEntity<Map<String, Object>> updateBoard(HttpServletResponse res) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		
//		try {
//			int res = bSer.updateBoard(b.getB_index(), b.getU_index(), b.getContext(), b.getFile_type(), b.getMulti_file(), b.getReg_date());
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
		return resEntity;
	}
//	@GetMapping("/image")
//    public String listUploadedFiles(Model model) throws IOException {
//        
//        model.addAttribute("files", imageService.loadAll().collect(Collectors.toList()));
//        
//        return "index";
//    }

//    @GetMapping("/image/{fileId}")
//    @ResponseBody
//    public ResponseEntity<?> serveFile(@PathVariable int fileId) {
//        try {
//        	System.out.println("controller  ");
//            UploadFile uploadedFile = imageService.load(fileId);
//            System.out.println("controller");
//            System.out.println(uploadedFile.toString());
//            HttpHeaders headers = new HttpHeaders();
//            
//            String fileName = uploadedFile.getFileName();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
//
//            if (MediaUtils.containsImageMediaType(uploadedFile.getContentType())) {
//                headers.setContentType(MediaType.valueOf(uploadedFile.getContentType()));
//            } else {
//                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            }
//
//            Resource resource = imageService.loadAsResource(uploadedFile.getSaveFileName());
//            return ResponseEntity.ok().headers(headers).body(resource);
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @GetMapping("/image/{email}")
	public @ResponseBody ResponseEntity<byte[]> displayFile(@PathVariable("email") String email)throws Exception{
		InputStream in = null;
		ResponseEntity<byte[]> resEntity = null;
		//logger.info("FILE NAME : " + fileName);
		UserDto infoUser = uSer.infoUser(email);
		System.out.println(infoUser.getPicture());
//		try {
//			String formatName = email.substring(fileName.lastIndexOf(".")+1);
//			MediaType mType = MediaUtils.getMediaType(formatName);
//			HttpHeaders headers = new HttpHeaders();
//			in = new FileInputStream(uploadPath+fileName);
//			
//			//step: change HttpHeader ContentType
//			if(mType != null) {
//				//image file(show image)
//				headers.setContentType(mType);
//			}else {
//				//another format file(download file)
//				fileName = fileName.substring(fileName.indexOf("_")+1);//original file Name
//				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//				headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\""); 
//			}
//			
//			resEntity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			resEntity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
//		}finally {
//			in.close();
//		}
			return resEntity;
		
	}

//	@RequestMapping("/image/{fileId}")
//	@ResponseBody
//	private ResponseEntity<Resource> downloadImg(@RequestParam Map<String, Object> param){
//	String filePath = "파일 디렉토리 주소.";
//	File target = new File(filePath);
//	HttpHeaders header = new HttpHeaders();
//	Resource rs = null;
//	if(target.exists()) {
//	try {
//	String mimeType = Files.probeContentType(Paths.get(target.getAbsolutePath()));
//	if(mimeType == null) {
//	mimeType = "octet-stream";
//	}
//	rs = new UrlResource(target.toURI());
//	header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"+ rs.getFilename() +"\"");
//	header.setCacheControl("no-cache");
//	header.setContentType(MediaType.parseMediaType(mimeType));
//	}catch(Exception e) {
//	e.printStackTrace();
//	}
//	}
//	return new ResponseEntity<Resource>(rs, header, HttpStatus.OK);
//	}

	@PostMapping("/imageupload")
	public ResponseEntity<Map<String, Object>> uploadImage(HttpServletRequest req) {

		System.out.println(req.getParameter("email"));

		ResponseEntity<Map<String, Object>> resEntity = null;
		String path = "/profileimage";
		String realPath = req.getServletContext().getRealPath(path);
		System.out.println(realPath);
		File f = new File(realPath);

		if (!f.exists()) {// 경로가 없다면 폴더를 만든다.
			f.mkdir();
		}

		Map<String, Object> map = new HashMap<String, Object>();
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
//				String oriName = new String(file.getOriginalFilename().getBytes("UTF-8"));
				String saveFileName = file.getName(); // 저장할 파일이름을 만드는데 뒤에 확장자도 붙임?
//				System.out.println("oriName "+oriName);
//				String saveFileName = oriName;
				File serverFile = new File(realPath + File.separator + saveFileName);
				file.transferTo(serverFile);

//				map.put("resmsg", "succ");
				map.put("uploaded", 1);
				map.put("url", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/"
						+ saveFileName);
				map.put("fileName", saveFileName);
//				JSONObject outData = new JSONObject();
				outData.put("uploaded", 1);
				outData.put("url", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path
						+ "/" + saveFileName);
				outData.put("fileName", saveFileName);
				System.out.println(outData);
				System.out.println("!!!");
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
	}
}
