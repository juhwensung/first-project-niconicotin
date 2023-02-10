package com.project.free_board.dao;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.free_board.mapper.FreeBoardCommentMapperInter;
import com.project.free_board.to.FreeBoardCommentTO;

@Repository
@MapperScan("com.project.free_board.mapper")
public class FreeBoardCommentDAO {
	
	@Autowired
	private FreeBoardCommentMapperInter freeBoardCommentMapperInter;
	
	public ArrayList<FreeBoardCommentTO> freeCommentList(FreeBoardCommentTO to){
		ArrayList<FreeBoardCommentTO> freeCommentLists = freeBoardCommentMapperInter.freeCommentList(to);
		return freeCommentLists;
	}
	
	public FreeBoardCommentTO freeParentCommentWrite(FreeBoardCommentTO to) {
		to = freeBoardCommentMapperInter.freeParentCommentWrite(to);
		return to;
	}
	
	public FreeBoardCommentTO freeCommentWrite(FreeBoardCommentTO to) {
		to = freeBoardCommentMapperInter.freeCommentWrite(to);
		return to;
	}
	
	public int freeCmtWriteOk(FreeBoardCommentTO to) {
		if(to.getFree_cmt_seq() == 0) {
			int result = freeBoardCommentMapperInter.freeParentCommentWriteOk(to);
			int flag = 1;
			if(result == 1) {
				flag = 0;
			}
			return flag;
		} else {
			int parentSeq = to.getFree_cmt_seq();
			FreeBoardCommentTO pTO = freeBoardCommentMapperInter.freeCommentPseqGrp(parentSeq);
			freeBoardCommentMapperInter.freeCommentParentsUP(pTO);
			
			int result = freeBoardCommentMapperInter.freeCommentWriteOk(to);
			int flag = 1;
			if(result == 1) {
				flag = 0;
			}
			System.out.println(flag);
			return flag;
		}
		
	}
	
	public FreeBoardCommentTO freeCommentModify(FreeBoardCommentTO to) {
		to = freeBoardCommentMapperInter.freeCommentModify(to);
		return to;
	}
	
	public int freeCommentModifyOk(FreeBoardCommentTO to) {
		int result = freeBoardCommentMapperInter.freeCommentModifyOk(to);
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
	
	public FreeBoardCommentTO freeCommentDelete(FreeBoardCommentTO to) {
		to = freeBoardCommentMapperInter.freeCommentDelete(to);
		return to;
	}
	
	public int freeCommentDeleteOk(FreeBoardCommentTO to) {
		int seq = to.getFree_cmt_seq();
		String content = "삭제된 글입니다.";
		int result = freeBoardCommentMapperInter.freeCommentDeleteOk(content, seq);
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
