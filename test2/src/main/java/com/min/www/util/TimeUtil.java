package com.min.www.util;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.min.www.Service.BoardService;
import com.min.www.dto.BoardDto;
import com.mysql.fabric.xmlrpc.base.Data;

public class TimeUtil {
	@Autowired
	BoardService boardService;

	public void TimeUtilChanger(Map<String, Object> paramMap) {
		//시간 가져오기.
		Data data = new Data();
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		System.out.println("현재 시간 : " + dataFormat);
		
		List<BoardDto> boardDto = boardService.getContentList(paramMap);	
		
		System.out.println("시간 유틸 : " + boardDto);
	}
}
