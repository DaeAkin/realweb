package com.min.www.Service.member;


import java.util.List;
import java.util.Map;

import com.min.www.dto.member.MemberDto;

public interface MemberService {
	
	List<MemberDto> getMemberlist(Map<String,Object> paramMap);
	
	MemberDto getMember(Map<String,Object> paramMap);
	
	int insertMember(Map<String,Object> paramMap);
	
	int deleteMember(Map<String,Object> paramMap);
	
	int memberIdCheck(Map<String, Object> paramMap);
	
	int memberLogin(Map<String, Object>	paramMap);
	

}
