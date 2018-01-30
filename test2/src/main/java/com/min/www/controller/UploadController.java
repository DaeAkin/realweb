package com.min.www.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadController {
	
	//파일 업로드하는 컨트롤러  클래스 메소드
	@RequestMapping(value="/upload")
	public void upload(HttpServletRequest request) {
		
		MultipartHttpServletRequest multipartHttpServletRequest =
				(MultipartHttpServletRequest)request;
		/*
		 * Iterator는 자바의 컬렉션 프레임웍에서 컬렉션에 저장되어 있는 요소들을 
		 * 읽어오는 방법을 표준화 하여쓴ㄴ데 그중 하나가 Iterator이다.
		 */
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		while(iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			
		if(multipartFile.isEmpty() == false) {
			log
		}
		}
	}
//인자로 M
}
