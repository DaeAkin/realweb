package com.min.www.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadController {
	
	@RequestMapping(value="/upload")
	public String upload() {
		
		return "upload";
	}
	//파일 업로드하는 컨트롤러  클래스 메소드
	@RequestMapping(value="/uploads",method=RequestMethod.POST)
	public void uploadexecute(HttpServletRequest request) {
		
		

	}
//인자로 M
}
