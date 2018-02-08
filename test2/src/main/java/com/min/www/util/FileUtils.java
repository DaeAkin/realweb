package com.min.www.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
	
	

	// 파일이 저장될 위치.
	private static final String filePath = "resources/upload/";

	public List<Map<String, Object>> parseInsertFileInfo(Map<String, Object> map, HttpServletRequest request)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		String root_path = session.getServletContext().getRealPath("/");
		
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;

		
		
		String boardIDX = String.valueOf(map.get("id"));

		File file = new File(root_path + filePath);
		if (file.exists() == false) {
			file.mkdirs();
		}
		
		//파일이 존재한다면.
		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				// 확장자 알기.
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() + originalFileExtension;

				file = new File(root_path + filePath + storedFileName);
				multipartFile.transferTo(file);
				
				
				listMap = new HashMap<String, Object>();
				listMap.put("BOARD_IDX", boardIDX);
				listMap.put("ORIGINAL_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
				System.out.println("파일 저장위치 :" +root_path + filePath);
			}
		}

		return list;
	}

}
