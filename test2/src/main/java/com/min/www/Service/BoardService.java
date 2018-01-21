package com.min.www.Service;

import java.util.List;
import java.util.Map;

import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardReplyDto;

public interface BoardService {
	
	int regContent(Map<String, Object> paramMap);
	
	int getContentCnt(Map<String, Object> paramMap);

	List<BoardDto> getContentList(Map<String, Object> paramMap);
	
	BoardDto getContentView(Map<String, Object> paramMap);
	
	int regReply(Map<String, Object> paramMap);
	
	List<BoardReplyDto> getReplyList(Map<String, Object> paramMap);
	
	int delReply(Map<String, Object> paramMap);
	
	int getBoardCheck(Map<String, Object> paramMap);
	
	int delBoard(Map<String, Object> paramMap);

}
