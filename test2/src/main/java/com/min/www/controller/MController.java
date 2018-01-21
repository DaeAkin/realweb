package com.min.www.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.www.Service.member.MemberService;

@Controller
public class MController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/member")
	public String member() {
		
		return "memberCheck";
	}
	
	
	@RequestMapping(value="/member/check" , method=RequestMethod.POST)
	public String memberCheck(@RequestParam Map<String, Object> paramMap) {
		System.out.println("ID : " +paramMap.get("id"));
		System.out.println("PASSWORD : " + paramMap.get("password").toString());
		System.out.println("email : " +paramMap.get("email").toString());
	
		
		System.out.println("insert 성공 메세지 : " + 
		memberService.insertMember(paramMap));
		
		System.out.println("성공");
		
		
		
		return "boardList";
	}
	
	@RequestMapping(value="/member/idCheck")
	@ResponseBody
	public Object memberIdCheck(@RequestParam Map<String, Object> paramMap) {
		
		Map<String,Object> reVal = new HashMap<String, Object>();
		System.out.println("중복 확인할 아이디 : " + paramMap.get("id"));
		
		
		int result = memberService.memberIdCheck(paramMap);
		
		System.out.println("ID Check 쿼리 확인 :" + result);
		
		if(result == 0) {
			reVal.put("code", "OK");
		} else {
			reVal.put("code", "FAIL");
			
		}
		System.out.println("reVal의 변수는 : " +reVal.get("code"));

		return reVal;
	}
	
	@RequestMapping(value="/member/loginform")
	public String memberLoginform() {
		
		return "loginform";
	}
	@RequestMapping(value="/member/login")
	@ResponseBody
	public Object memberLogin(@RequestParam Map<String, Object> paramMap,  HttpSession session) {
		Map<String, Object> retVal = new HashMap<String, Object>();
		
		//1. sql문에 대입하여 아이디 확인 작업 서비스에서 처리.
		int reVal = memberService.memberLogin(paramMap);
		
		
		//2. session 생성해주기. 
		// 0이 아니면 로그인처리 
		if(reVal != 0) {
			session.setAttribute("loginuser", paramMap.get("id"));
			retVal.put("code", "OK");
			// 세션 이름 = loginuser에 id를 넣어줌.
		
			//redirect로 메인메이피로 넘어가기는 jsp에서 처리.
			
		} 
		
	 
		
		return retVal;
	}

}
