package com.project.requestCigar.dao;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.requestCigar.to.RequestCigarCommentTO;
import com.project.requestCigar.mapper.RequestCigarCommentMapperInter;

@Repository
@MapperScan("com.project.requestCigar.mapper")
public class RequestCigarCommentDAO {
	
	@Autowired
	private RequestCigarCommentMapperInter requestCigarCommentMapperInter;
	
	public ArrayList<RequestCigarCommentTO> requestCommentList(RequestCigarCommentTO to){
		ArrayList<RequestCigarCommentTO> requestCommentLists = requestCigarCommentMapperInter.requestCommentList(to);
		return requestCommentLists;
	}
	
	public RequestCigarCommentTO requestParentCommentWrite(RequestCigarCommentTO to) {
		to = requestCigarCommentMapperInter.requestParentCommentWrite(to);
		return to;
	}
	
	public RequestCigarCommentTO requestCommentWrite(RequestCigarCommentTO to) {
		to = requestCigarCommentMapperInter.requestCommentWrite(to);
		return to;
	}
	
	public int requestCmtWriteOk(RequestCigarCommentTO to) {
		if(to.getRequest_cmt_seq() == 0) {
			int result = requestCigarCommentMapperInter.requestParentCommentWriteOk(to);
			int flag = 1;
			if(result == 1) {
				flag = 0;
			}
			return flag;
		} else {
			int parentSeq = to.getRequest_cmt_seq();
			RequestCigarCommentTO pTO = requestCigarCommentMapperInter.requestCommentPseqGrp(parentSeq);
			requestCigarCommentMapperInter.requestCommentParentsUP(pTO);
			
			int result = requestCigarCommentMapperInter.requestCommentWriteOk(to);
			int flag = 1;
			if(result == 1) {
				flag = 0;
			}
			System.out.println(flag);
			return flag;
		}
		
	}
	
	public RequestCigarCommentTO requestCommentModify(RequestCigarCommentTO to) {
		to = requestCigarCommentMapperInter.requestCommentModify(to);
		return to;
	}
	
	public int requestCommentModifyOk(RequestCigarCommentTO to) {
		int result = requestCigarCommentMapperInter.requestCommentModifyOk(to);
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
	
	public RequestCigarCommentTO requestCommentDelete(RequestCigarCommentTO to) {
		to = requestCigarCommentMapperInter.requestCommentDelete(to);
		return to;
	}
	
	public int requestCommentDeleteOk(RequestCigarCommentTO to) {
		int seq = to.getRequest_cmt_seq();
		String content = "삭제된 글입니다.";
		int result = requestCigarCommentMapperInter.requestCommentDeleteOk(content, seq);
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
