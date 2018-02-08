package com.min.www.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.www.Service.BoardService;





@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/board/write")
	public String boardwrite() {
		
		return "boardwrite";
	}

	// 게시글 리스트 조회.
	@RequestMapping(value ="/board/list")
	public String boardList(@RequestParam Map<String, Object> paramMap, Model model) {

		// 조회 하려는 페이지
		int startPage = (paramMap.get("startPage") != null ? Integer.parseInt(paramMap.get("startPage").toString()) : 1);
		// 한페이지에 보여줄 리스트 수
		int visiblePages = (paramMap.get("visiblePages") != null
				? Integer.parseInt(paramMap.get("visiblePages").toString())
				: 10);
		// 일단 전체 건수를 가져온다.
		int totalCnt = boardService.getContentCnt(paramMap);
		
		System.out.println("전체게시물 갯수  :" + totalCnt);
		System.out.println("시작 페이지  :" + startPage);
		System.out.println("한 페이지에 보여줄 리스트  : "  + visiblePages);

		BigDecimal decima1 = new BigDecimal(totalCnt);
		BigDecimal decima2 = new BigDecimal(visiblePages);
		BigDecimal totalPage = decima1.divide(decima2, 0, BigDecimal.ROUND_UP);

		int startLimitPage = 0;
		// 2. mysql limit 범위를 구하기 위해 계산
		if (startPage == 1) {
			startLimitPage = 0;
		} else {
			startLimitPage = (startPage - 1) * visiblePages;
		}
		
		paramMap.put("start", startLimitPage);
		paramMap.put("end", visiblePages);

		// jsp 에서 보여줄 정보 추출
		model.addAttribute("startPage", startPage + ""); // 현재 페이지
		model.addAttribute("totalCnt", totalCnt); // 전체 게시물수
		model.addAttribute("totalPage", totalPage); // 페이지 네비게이션에 보여줄 리스트 수
		model.addAttribute("boardList", boardService.getContentList(paramMap)); // 검색
		System.out.println(boardService.getContentList(paramMap));

		return "boardList";

	}

	// 게시글 상세 보기
	@RequestMapping(value = "/board/view")
	public String boardView(@RequestParam Map<String, Object> paramMap, Model model) {

		model.addAttribute("replyList", boardService.getReplyList(paramMap));
		model.addAttribute("boardView", boardService.getContentView(paramMap));

		return "boardView";
	}

	// 게시글 등록 및 수정
	@RequestMapping(value = "/board/edit")
	public String boardEdit(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, Model model) {
		System.out.println("게시글 등록 및 수정 ()");
		// Referer 검사
		String Referer = request.getHeader("referer");

		if (Referer != null) { // URL로 직접 접근 불가
			if (paramMap.get("id") != null) {// 게시글 수정
				if (Referer.indexOf("/board/view") > -1) {

					// 정보를 가져온다.
					model.addAttribute("boardView", boardService.getContentView(paramMap));
					return "boardEdit";
				} else {
					return "redirect:/board/list";
				}
			} else { // 게시글 등록
				if (Referer.indexOf("/board/list") > -1) {
					return "boardEdit";

				} else {
					return "redirect:/board/list";
				}

			}

		} else {
			return "redirect:/board/list";
		}

	}
	
	/*AJAX호출 (게시글 등록)
	 * and 파일 업로드 구현.
	*/
	@RequestMapping(value="/board/save",method=RequestMethod.POST)
	public String boardSave(@RequestParam Map<String, Object> paramMap,HttpServletRequest request) throws Exception {
		//리턴 값
		/*
		//패스워드 암호화
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String password = encoder.encodePassword(paramMap.get("password").toString(), null);
		paramMap.put("password", password);
		*/
		
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
			System.out.println("업로드된 파일 갯수 : " + iterator.toString());
		if(multipartFile.isEmpty() == false) {
			System.out.println("----------------file start------------");
			System.out.println("name : " + multipartFile.getName());
			System.out.println("filename : " + multipartFile.getOriginalFilename());
			System.out.println("size : " + multipartFile.getSize());
			System.out.println("----------------file end------------");
		}
		}

		//정보입력
		boardService.regContent(paramMap,request);
		
	
		
		return "redirect:/board/list";
	}
	
	
	//AJAX 호출 ( 댓글 등록)
	@RequestMapping(value="/board/reply/save",method=RequestMethod.POST)
	@ResponseBody
	public Object boardReplySave(@RequestParam Map<String, Object> paramMap) {
		System.out.println("댓글 AJAX 호출()");
		System.out.println(paramMap.get("reply_writer"));
		System.out.println(paramMap.get("reply_password"));
		//리턴 값
		Map<String, Object> retVal = new HashMap<String,Object>();
		
		//패스워드 암호화
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String password = encoder.encodePassword(paramMap.get("reply_password").toString(), null);
		paramMap.put("reply_password", password);
		//정보 입력
		int result = boardService.regReply(paramMap);
		
		if(result>0) {
			retVal.put("code", "OK");
			retVal.put("reply_id", paramMap.get("reply_id"));
			retVal.put("message","등록에 성공 하였습니다.");
		}else {
			retVal.put("code", "FAIL");
			retVal.put("message", "등록에 실패 하였습니다.");
		}
	
		return retVal;
	}
	
	//AJAX 호출 ( 댓글 삭제 )
	@RequestMapping(value="/board/reply/del" , method=RequestMethod.POST)
	@ResponseBody
	public Object boardReplyDel(@RequestParam Map<String, Object> paramMap) {
		
		//리턴값
		Map<String,Object> retVal = new HashMap<String,Object>();
		
		//패스워드 암호화
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String password = encoder.encodePassword(paramMap.get("reply_password").toString(), null);
		paramMap.put("reply_password", password);
		
		//정보입력
		int result = boardService.delReply(paramMap);
		
		if(result>0) {
			retVal.put("code", "OK");
		} else {
			retVal.put("code", "FAIL");
			retVal.put("message", "삭제에 실패했습니다. 패스워드를 확인해주세요.");
		}
	
		return retVal;
	}
	
	
	

}
