package com.min.www.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.www.Service.BoardDao;
import com.min.www.dto.BoardDto;
import com.mysql.fabric.xmlrpc.base.Data;

@Service
public class TimeUtil {
	@Autowired
	BoardDao boardDao;

	public List<BoardDto> TimeUtilChanger(Map<String, Object> paramMap) {
		//시간 가져오기.
		Data data = new Data();
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		System.out.println("현재 시간 : " + dataFormat);
		
		System.out.println("start = " + paramMap.get("start"));
		
		System.out.println("End = " + paramMap.get("end"));
		
		List<BoardDto> resultDto = boardDao.getContentList(paramMap);
		
		
		String today = getToday();
		
		
		
		//mysql 게시물 오늘날짜이면 시간으로 표시, 아니면 날짜로만 표시.
		for(int i=0; i<resultDto.size(); i++) {
			if(resultDto.get(i).getWritertime().substring(0, 10).equals(today)) {
				System.out.println("시간 변환.");
				resultDto.get(i).setWritertime(resultDto.get(i).getWritertime().substring(11, 16));
				
			} else {
				resultDto.get(i).setWritertime(resultDto.get(i).getWritertime().substring(5, 10));
			}
			
		}
		/*while(itrresultDto.hasNext()){
			System.out.println(itrresultDto.next().getWritertime().substring(0, 10).equals(today));
			if(itrresultDto.next().getWritertime().substring(0, 10).equals(today)) {
				//만약 게시글이 오늘 날짜에 쓰어진거라면.
				itrresultDto.next().setWritertime(
						itrresultDto.next().getWritertime().substring(11, 16));
				System.out.println("오늘 날자의 게시물 날짜 " + itrresultDto.next().getWritertime());
				
				System.out.println(itrresultDto.next().getWritertime());
				System.out.println("시간 변환작업완료!!");
			}else {
				itrresultDto.next().setWritertime(
						itrresultDto.next().getWritertime().substring(5, 10));
			}
				
		}
		*/
		
		
		 return resultDto; 	
	
	}
	
	//현재시간 가져오기.
	public String getToday() {
		
		Date today = new Date();
		
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String CurrentTime = time.format(today);
		
		System.out.println("현재 시간 : " + CurrentTime );
		
		System.out.println(" DB 조회를 위해 자를 시간 :" +CurrentTime.substring(0, 10));
		
		CurrentTime = CurrentTime.substring(0, 10);
		
		return CurrentTime;
	}
	
	/*
	 *  http://blog.naver.com/PostView.nhn?blogId=minilove717&logNo=220686303157&parentCategoryNo=&categoryNo=28&viewDate=&isShowPopularPosts=false&from=postView
	 */
}
