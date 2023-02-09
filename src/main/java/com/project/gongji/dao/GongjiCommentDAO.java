package com.project.gongji.dao;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.gongji.mapper.GongjiCommentMapperInter;
import com.project.gongji.to.GongjiCommentTO;

@Repository
@MapperScan("com.project.gongji.mapper")
public class GongjiCommentDAO {
	
	@Autowired
	private GongjiCommentMapperInter gongjiCommentMapperInter;
	
	public ArrayList<GongjiCommentTO> gongjiCommentList(GongjiCommentTO to){
		ArrayList<GongjiCommentTO> gongjiCommentLists = gongjiCommentMapperInter.gongjiCommentList(to);
		return gongjiCommentLists;
	}
	
	public GongjiCommentTO gongjiParentCommentWrite(GongjiCommentTO to) {
		to = gongjiCommentMapperInter.gongjiParentCommentWrite(to);
		return to;
	}
	
	public GongjiCommentTO gongjiCommentWrite(GongjiCommentTO to) {
		to = gongjiCommentMapperInter.gongjiCommentWrite(to);
		return to;
	}
	
	public int gongjiCmtWriteOk(GongjiCommentTO to) {
		if(to.getGongji_cmt_seq() == 0) {
			int result = gongjiCommentMapperInter.gongjiParentCommentWriteOk(to);
			int flag = 1;
			if(result == 1) {
				flag = 0;
			}
			return flag;
		} else {
			int parentSeq = to.getGongji_cmt_seq();
			GongjiCommentTO pTO = gongjiCommentMapperInter.gongjiCommentPseqGrp(parentSeq);
			gongjiCommentMapperInter.gongjiCommentParentsUP(pTO);
			
			int result = gongjiCommentMapperInter.gongjiCommentWriteOk(to);
			int flag = 1;
			if(result == 1) {
				flag = 0;
			}
			System.out.println(flag);
			return flag;
		}
		
	}
	
	public GongjiCommentTO gongjiCommentModify(GongjiCommentTO to) {
		to = gongjiCommentMapperInter.gongjiCommentModify(to);
		return to;
	}
	
	public int gongjiCommentModifyOk(GongjiCommentTO to) {
		int result = gongjiCommentMapperInter.gongjiCommentModifyOk(to);
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
	
	public GongjiCommentTO gongjiCommentDelete(GongjiCommentTO to) {
		to = gongjiCommentMapperInter.gongjiCommentDelete(to);
		return to;
	}
	
	public int gongjiCommentDeleteOk(GongjiCommentTO to) {
		int seq = to.getGongji_cmt_seq();
		String content = "삭제된 글입니다.";
		int result = gongjiCommentMapperInter.gongjiCommentDeleteOk(content, seq);
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
