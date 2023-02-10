package com.project.review.dao;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.review.to.ReviewCommentTO;
import com.project.review.mapper.ReviewCommentMapperInter;

@Repository
@MapperScan("com.project.review.mapper")
public class ReviewCommentDAO {
	
	@Autowired
	private ReviewCommentMapperInter reviewCommentMapperInter;
	
	public ArrayList<ReviewCommentTO> reviewCommentList(ReviewCommentTO to){
		ArrayList<ReviewCommentTO> reviewCommentLists = reviewCommentMapperInter.reviewCommentList(to);
		return reviewCommentLists;
	}
	
	public ReviewCommentTO reviewParentCommentWrite(ReviewCommentTO to) {
		to = reviewCommentMapperInter.reviewParentCommentWrite(to);
		return to;
	}
	
	public ReviewCommentTO reviewCommentWrite(ReviewCommentTO to) {
		to = reviewCommentMapperInter.reviewCommentWrite(to);
		return to;
	}
	
	public int reviewCmtWriteOk(ReviewCommentTO to) {
		if(to.getReview_cmt_seq() == 0) {
			int result = reviewCommentMapperInter.reviewParentCommentWriteOk(to);
			int flag = 1;
			if(result == 1) {
				flag = 0;
			}
			return flag;
		} else {
			int parentSeq = to.getReview_cmt_seq();
			ReviewCommentTO pTO = reviewCommentMapperInter.reviewCommentPseqGrp(parentSeq);
			reviewCommentMapperInter.reviewCommentParentsUP(pTO);
			
			int result = reviewCommentMapperInter.reviewCommentWriteOk(to);
			int flag = 1;
			if(result == 1) {
				flag = 0;
			}
			System.out.println(flag);
			return flag;
		}
		
	}
	
	public ReviewCommentTO reviewCommentModify(ReviewCommentTO to) {
		to = reviewCommentMapperInter.reviewCommentModify(to);
		return to;
	}
	
	public int reviewCommentModifyOk(ReviewCommentTO to) {
		int result = reviewCommentMapperInter.reviewCommentModifyOk(to);
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
	
	public ReviewCommentTO reviewCommentDelete(ReviewCommentTO to) {
		to = reviewCommentMapperInter.reviewCommentDelete(to);
		return to;
	}
	
	public int reviewCommentDeleteOk(ReviewCommentTO to) {
		int seq = to.getReview_cmt_seq();
		String content = "삭제된 글입니다.";
		int result = reviewCommentMapperInter.reviewCommentDeleteOk(content, seq);
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
