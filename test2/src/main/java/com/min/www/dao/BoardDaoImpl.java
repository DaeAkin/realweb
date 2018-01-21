package com.min.www.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.www.Service.BoardDao;
import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardReplyDto;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	 

	@Override
	public int regContent(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.insert("insertContent",paramMap );	
		}

	@Override
	public int getContentCtn(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectContentCnt", paramMap);
	}

	@Override
	public List<BoardDto> getContentList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("selectContent", paramMap);
	}

	@Override
	public BoardDto getContentView(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectContentView",paramMap);
	}

	@Override
	public int regReply(Map<String, Object> parMap) {
		// TODO Auto-generated method stub
		return sqlSession.insert("insertBoardReply", parMap);
	}

	@Override
	public List<BoardReplyDto> getReplyList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("selectBoardReplyList", paramMap);
	}

	@Override
	public int delReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.delete("deleteBoardReply",paramMap);
	}

	@Override
	public int getBoardCheck(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectBoardCnt",paramMap);
	}

	@Override
	public int delBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.delete("deleteBoard",paramMap);
	}
	
}
