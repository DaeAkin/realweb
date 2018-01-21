package com.min.www.dao.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.min.www.Service.member.MemberDao;
import com.min.www.Service.member.MemberService;
import com.min.www.dto.member.MemberDto;
@Service("MemberService")
public class MemberServiceImpl implements MemberService{
	
	@Resource(name="MemberDao")
	MemberDao memberDao;

	@Override
	public List<MemberDto> getMemberlist(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.getMemberlist(paramMap);
	}

	@Override
	public MemberDto getMember(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		// 아이디 비밀번호 꺼내와서 if넣어주기.
		
		return memberDao.getMember(paramMap);
	}

	@Override
	public int insertMember(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.insertMember(paramMap);
	}

	@Override
	public int deleteMember(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.deleteMember(paramMap);
	}

	@Override
	public int memberIdCheck(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return memberDao.memberIdCheck(paramMap);
	}

	@Override
	public int memberLogin(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		int result = 0;
		
		MemberDto memberDto = memberDao.getMember(paramMap);

		
		if(memberDto.getId().equals(paramMap.get("id")) &&
				memberDto.getPassword().equals(paramMap.get("password")) ) {
			return result = 1;
		} else {
			
		return result; 
		
		}
		
	}
	
	

}
