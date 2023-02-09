package com.project.review.dao;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.cigar.to.CigarTO;
import com.project.review.mapper.ReviewMapperInter;
import com.project.review.to.ReviewTO;

@Repository
@MapperScan("com.project.review.mapper")
public class ReviewDAO {
	
	@Autowired
	private ReviewMapperInter reviewMapperInter;
	
	public ArrayList<ReviewTO> reviewList(ReviewTO to) {
		ArrayList<ReviewTO> reviewLists = reviewMapperInter.reviewList();
		return reviewLists;
	}
	
	public ReviewTO reviewView(ReviewTO to) {
		int res = reviewMapperInter.reviewView_hit(to);
		System.out.println("조회수 상승 값 : " + res);
		System.out.println(to.getReview_seq());
		to = reviewMapperInter.reviewView(to);
		return to;
	}
	
	public void reviewWrite() {
		
	}
	
	public int reviewWriteOk(ReviewTO to) {
		int result = reviewMapperInter.reviewWirte_ok(to);
		int flag = 1;
		if(result == 1) {
			flag = 0;
			int seq = to.getReview_cigar_seq();
			double avg = reviewMapperInter.reviewAvgGrade(to);
			reviewMapperInter.reviewCigarAvgGrade(avg, seq);
		}
		
		return flag;
	}
	
	public ReviewTO reviewModify(ReviewTO to) {
		to = reviewMapperInter.reviewModify(to);
		return to;
	}
	
	public int reviewModifyOk(ReviewTO to) {
		int result = reviewMapperInter.reviewModify_ok(to);
		int flag = 2;
		
		if(result == 0){
			// 비밀번호가 잘못된경우
			flag = 1;
		} else if(result == 1){
			// 정상 작동
			flag = 0;
			int seq = to.getReview_cigar_seq();
			double avg = reviewMapperInter.reviewAvgGrade(to);
			reviewMapperInter.reviewCigarAvgGrade(avg, seq);
		}
		return flag;
	}
	
	public ReviewTO reviewDelete(ReviewTO to) {
		to = reviewMapperInter.reviewDelete(to);
		return to;
	}
	
	public int reviewDeleteOk(ReviewTO to) {
		int result = reviewMapperInter.reviewDelete_ok(to);
		int flag = 2;
		
		if(result == 0){
			// 비밀번호가 잘못된경우
			flag = 1;
		} else if(result == 1){
			// 정상 작동
			flag = 0;
			int seq = to.getReview_cigar_seq();
			double avg = reviewMapperInter.reviewAvgGrade(to);
			reviewMapperInter.reviewCigarAvgGrade(avg, seq);
		}
		return flag;
	}
	
}
