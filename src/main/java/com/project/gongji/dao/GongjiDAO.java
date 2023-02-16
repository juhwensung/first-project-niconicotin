package com.project.gongji.dao;

import java.io.File;
import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.cigar.to.CigarTO;
import com.project.gongji.mapper.GongjiMapperInter;
import com.project.gongji.to.GongjiTO;

@Repository
@MapperScan("com.project.gongji.mapper")
public class GongjiDAO {
	
	@Autowired
	private GongjiMapperInter gongjiMapperInter;
	
	private String filePath = System.getProperty("user.dir") + "/src/main/webapp/uploads/gongjiUpload/";

	public ArrayList<GongjiTO> gongjiList() {
		ArrayList<GongjiTO> gongjiLists = gongjiMapperInter.gongjiList();
		return gongjiLists;
	}
	
	public GongjiTO gongjiView(GongjiTO to) {
		int res = gongjiMapperInter.gongjiView_hit(to);
		//System.out.println("조회수 상승 값 : " + res);
		//System.out.println(to.getGongji_seq());
		to = gongjiMapperInter.gongjiView(to);
		return to;
	}
	
	public void gongjiWrite() {
		
	}
	
	public int gongjiWriteOk(GongjiTO to) {
		int result = gongjiMapperInter.gongjiWirte_ok(to);
		int flag = 1;
		if(result == 1) {
			flag = 0;
		}
		
		return flag;
	}
	
	public GongjiTO gongjiModify(GongjiTO to) {
		to = gongjiMapperInter.gongjiModify(to);
		return to;
	}
	
	public int gongjiModifyOk(GongjiTO to) {
		int result = gongjiMapperInter.gongjiModify_ok(to);
		int flag = 2;
		
		if(result == 0){
			// 비밀번호가 잘못된경우
			flag = 1;
		} else if(result == 1){
			// 정상 작동
			flag = 0;
		}
		return flag;
	}
	
	public GongjiTO gongjiDelete(GongjiTO to) {
		to = gongjiMapperInter.gongjiDelete(to);
		return to;
	}
	
	public int gongjiDeleteOk(GongjiTO to) {
		int result = gongjiMapperInter.gongjiDelete_ok(to);
		int flag = 2;
		
		if(result == 0){
			// 비밀번호가 잘못된경우
			flag = 1;
		} else if(result == 1){
			// 정상 작동
			flag = 0;
		}
		return flag;
	}
	
	public ArrayList<GongjiTO> gongjiSearch(GongjiTO to) {
		ArrayList<GongjiTO> gongjiSearchList = gongjiMapperInter.GongjiBoardSearch(to);
		return gongjiSearchList;
	}
	
}
