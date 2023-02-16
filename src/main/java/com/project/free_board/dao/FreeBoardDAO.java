package com.project.free_board.dao;

import java.io.File;
import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.free_board.mapper.FreeBoardMapperInter;
import com.project.free_board.to.FreeBoardTO;

@Repository
@MapperScan("com.project.free_board.mapper")
public class FreeBoardDAO {
	
	@Autowired
	private FreeBoardMapperInter freeBoardMapperInter;
	
	private String filePath = System.getProperty("user.dir") + "/src/main/webapp/uploads/freeUpload/";
	
	public ArrayList<FreeBoardTO> freeList() {
		ArrayList<FreeBoardTO> freeLists = freeBoardMapperInter.freeList();
		return freeLists;
	}
	
	public FreeBoardTO freeView(FreeBoardTO to) {
		to = freeBoardMapperInter.freeView(to);
		int result = freeBoardMapperInter.freeViewHit(to);
		//System.out.println(result);
		return to;
	}
	
	public int freeWrite_Ok(FreeBoardTO to) {
		int result = freeBoardMapperInter.freeWrite_Ok(to);
		int flag = 1;
		if(result == 1) {
			flag = 0;
		}
		return flag;
	}
	
	public FreeBoardTO freeModify(FreeBoardTO to) {
		to = freeBoardMapperInter.freeModify(to);
		return to;
	}
	
	public int freeModifyOk(FreeBoardTO to) {
		
		int flag = 2;
		int result = freeBoardMapperInter.freeModify_Ok(to);

		if(result == 0){
			// 비밀번호가 잘못된경우
			flag = 1;
			
		} else if(result == 1){
			// 정상 작동
			flag = 0;
			
		}
		return flag;
	}
	
	public FreeBoardTO freeDelete(FreeBoardTO to) {
		to = freeBoardMapperInter.freeDelete(to);
		return to;
	}
	
	
	public int freeDeleteOk(FreeBoardTO to) {

		int flag = 2;

		int result = freeBoardMapperInter.freeDelete_Ok(to);
		freeBoardMapperInter.freeDeleteAllComment(to);
		
		if(result == 0){
			// 비밀번호가 잘못된경우
			flag = 1;
		} else if(result == 1){
			// 정상 작동
			flag = 0;
			
		}
		return flag;
	}
	
	public ArrayList<FreeBoardTO> freeSearch(FreeBoardTO to) {
		ArrayList<FreeBoardTO> freeSearchList = freeBoardMapperInter.FreeBoardSearch(to);
		return freeSearchList;
	}
}
