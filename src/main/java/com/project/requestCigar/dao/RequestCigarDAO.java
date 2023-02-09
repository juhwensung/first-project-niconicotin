package com.project.requestCigar.dao;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.requestCigar.to.RequestCigarTO;
import com.project.requestCigar.mapper.RequestCigarMapperInter;

@Repository
@MapperScan("com.project.requestCigar.mapper")
public class RequestCigarDAO {
	
	@Autowired
	private RequestCigarMapperInter requestCigarMapperInter;
	
	public ArrayList<RequestCigarTO> requestList() {
		ArrayList<RequestCigarTO> requestLists = requestCigarMapperInter.requestList();
		return requestLists;
	}
	
	public RequestCigarTO requestView(RequestCigarTO to) {
		int res = requestCigarMapperInter.requestView_hit(to);
		//System.out.println("조회수 상승 값 : " + res);
		//System.out.println(to.getGongji_seq());
		to = requestCigarMapperInter.requestView(to);
		return to;
	}
	
	public void requestWrite() {
		
	}
	
	public int requestWriteOk(RequestCigarTO to) {
		int result = requestCigarMapperInter.requestWirte_ok(to);
		int flag = 1;
		if(result == 1) {
			flag = 0;
		}
		
		return flag;
	}
	
	public RequestCigarTO requestModify(RequestCigarTO to) {
		to = requestCigarMapperInter.requestModify(to);
		return to;
	}
	
	public int requestModifyOk(RequestCigarTO to) {
		int result = requestCigarMapperInter.requestModify_ok(to);
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
	
	public RequestCigarTO requestDelete(RequestCigarTO to) {
		to = requestCigarMapperInter.requestDelete(to);
		return to;
	}
	
	public int requestDeleteOk(RequestCigarTO to) {
		int result = requestCigarMapperInter.requestDelete_ok(to);
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
}
