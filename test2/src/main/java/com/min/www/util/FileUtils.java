package com.min.www.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class FileUtils {

	// 파일이 저장될 위치.
	private static final String filePath = "/Users/donghyeonmin/upload";
	private static final String imageMemberPath = "/Users/donghyeonmin/upload";

	public List<Map<String, Object>> parseInsertFileInfo(Map<String, Object> map, HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();

		// String root_path = session.getServletContext().getRealPath("/");

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;

		String boardIDX = String.valueOf(map.get("id"));

		File file = new File(filePath); // root_path + filePath
		if (file.exists() == false) {
			file.mkdirs();
		}

		// 파일이 존재한다면.
		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				// 확장자 알기.
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() + "_s" + originalFileName;

				file = new File(filePath + storedFileName); // root_path + filePath + storedFileName
				multipartFile.transferTo(file);

				listMap = new HashMap<String, Object>();
				listMap.put("BOARD_IDX", boardIDX);
				listMap.put("ORIGINAL_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				listMap.put("NICKNAME", session.getAttribute("nickname"));
				list.add(listMap);
				System.out.println("파일 저장위치 :" + filePath);
			}
		}

		return list;
	}

	public void parseInsertFileInfoAjax(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		// String root_path = session.getServletContext().getRealPath("/");

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;

		File file = new File(filePath); // root_path + filePath
		if (file.exists() == false) {
			file.mkdirs();
		}

		// 파일이 존재한다면.
		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				// 확장자 알기.
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = "s_" + CommonUtils.getRandomString() + originalFileName;

				file = new File(filePath + storedFileName); // root_path + filePath + storedFileName
				multipartFile.transferTo(file);

				System.out.println("파일 저장위치 :" + filePath);
			}
		}

	}

	/*
	 * 회원 대표 Image를 등록하는 유틸.
	 *
	 * Upload시 DB 데이터에 경로 넣어주고 저장할때는 파일명 중복방지를 위해 UUID를 사용하고 파일 저장시 데이터가 작게 썸네일 이미지로
	 * 100x100으로 만들어준다. 경로를 return 해주고 <img 태그로 jsp에 리턴해서 뿌려주기.>
	 * 
	 * uploadPath는 bean으로 주입했음. 위치: servlet-context
	 */

	public static String memberImageUpload(String uploadPath, String originalName, byte[] fileData) throws Exception {

		return reSizeImage(uploadPath, originalName, fileData);

		// FileCopyUtils.copy(fileData, target);
		
		

	}

	public static String reSizeImage(String uploadPath, String originalName, byte[] fileData) throws Exception {
		// UUID 발급
		UUID uuid = UUID.randomUUID();
		// 저장할 파일명 = UUID + 원본이름
		String fileName = uuid.toString() + "_" + originalName;
		// 파일 경로를 받아 파일객체 생성
		File target = new File(uploadPath + fileName);
		 /*임시 디렉토리에 업로드된 파일을
		 reSzieImage메소드 호출 후 썸네일 100x100 사이즈로 생성후 저장.
		 그 전에 파일이 임시저장소에 올라가있는데 그걸 디렉토리로 복사해주기
	 	복사 안하고 할수있는 방법 찾아보기 
	 	단점 : 파일을 복사하고 지우는 과정이 들어가기때문에 자원낭비 예상.
	 	
		 */
		
		//임시 디렉토리에 업로드된 파일을 지정된 디렉토리로 복사
		FileCopyUtils.copy(fileData, target);
		
		System.out.println("회원 이미파일 업로드 경로 : " + uploadPath + fileName);
		
		//원본 파일 읽어오기
		// 이미지를 읽기 위한 버퍼
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath, fileName));
		// 100픽셀 단위의 썸네일 생성
		BufferedImage destImg = Scalr.resize(sourceImg, 100, 100, null);
		// 썸네일의 이름을 생성 ( 원본 파일명에 's_'를 붙임)
		String thumbnailName = uploadPath + "s_" + fileName;
		File newFile = new File(thumbnailName);
		// 확장자가져오기.
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		// 썸네일 생성
		ImageIO.write(destImg, formatName, newFile);
		
		// 원본 파일 삭제
		
		target.delete();
		
		// DB에 파일 저장위치 리턴
		return thumbnailName;
		
		
		

	}

	/* 다른 방법의 파일 업로드 */
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		// 2개의 객체를 리턴하기 위해 MAP을 사용
		Map<String, Object> map = new HashMap<>();
		// UUID 발급
		UUID uuid = UUID.randomUUID();
		// 저장할 파일명 = UUID + 원본이름
		String savedName = uuid.toString() + "_" + originalName;
		// 업로드할 렉토리(날짜별 폴더) 생성
		String savedPath = calcPath(uploadPath);
		// 파일 경로(기존의 업로드 경로 + 날짜별 경로), 파일명을 받아 파일 객체 생성.
		File target = new File(uploadPath + savedPath, savedName);
		// 임시 디렉토리에 업로드된 파일을 지정된 디렉토리로 복사
		FileCopyUtils.copy(fileData, target);
		System.out.println("디렉토리 경로 : " + uploadPath + savedPath + savedName);
		// 썸네일을 생성하기 위한 파일의 확장자 검사
		// 파일명이 aaa.bbb.ccc.jpg일 경우 마지막 마침표를 찾기 위해
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;

		// 이미지 파일은 썸네일과 원본파일을 리턴.
		if (MediaUtils.getMediaType(formatName) != null) {
			// 썸네일 생성
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
			map.put("makeThumbnail", uploadedFileName);
			// 나머지는 아이콘.
		} else {
			// 아이콘 생성
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);

		}

		return uploadedFileName;
		/*
		 * //이미지 파일은 썸네일 if (MediaUtils.getMediaType(formatName) != null) { //썸네일 생성
		 * uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName); //나머지는
		 * 아이콘. } else { //아이콘 생성 uploadedFileName = makeIcon(uploadPath, savedPath,
		 * savedName); }
		 * 
		 * return uploadedFileName;
		 * 
		 */
	}

	// 날짜별 디렉토리 추출
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		// File.separator : 디렉토리 구분자(\\)
		// 연도 , ex) \\2017
		String yearPath = Integer.toString(cal.get(Calendar.YEAR));
		System.out.println(yearPath);
		// 월 , ex) \\2017\\03
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		System.out.println(monthPath);
		// 날짜, ex) \\2017\\03\\01
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		System.out.println(datePath);
		// 디렉토리 생성 메서드 호출.
		makeDir(uploadPath, yearPath, monthPath, datePath);
		return datePath;
	}
	// 디렉토리 생성 메서드 호출

	// 디렉토리 생성
	private static void makeDir(String uploadPath, String... paths) {
		// 디렉토리가 존재하면
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}

		// 디렉토리가 존재하지 않으면
		for (String path : paths) {
			//
			File dirPath = new File(uploadPath + path);
			// 디렉토리가 존재하지 않으면
			if (!dirPath.exists()) {
				dirPath.mkdirs(); // 디렉토리 생성.
			}
		}

	}

	// //이미지 원본 가져오기
	// private static String getOriginalImg(String uploadPath, String path, String
	// fileName) throws Exception {
	//
	// // 이미지 읽기 버퍼
	// BufferedImage sourceImg = ImageIO.read(input)
	//
	// }
	// 썸네일 생성
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {

		// 이미지를 읽기 위한 버퍼
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		// 100픽셀 단위의 썸네일 생성
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		// 썸네일의 이름을 생성 ( 원본 파일명에 's_'를 붙임)
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		// 확장자가져오기.
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		// 썸네일 생성
		ImageIO.write(destImg, formatName, newFile);

		// 썸네일의 이름을 리턴

		// File.separatorChar : 디렉토리 구분자
		// 윈도우 \ ,유닉스(리눅스) /
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	// 아이콘 생성
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {

		// 아이콘의 이름
		String iconName = uploadPath + path + File.separator + fileName;
		// 아이콘 이름을 리턴
		// File.separatorChar : 디렉토리 구분자.

		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

}
