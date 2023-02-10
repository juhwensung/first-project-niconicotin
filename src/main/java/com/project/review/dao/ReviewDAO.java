package com.project.review.dao;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.cigar.to.CigarTO;
import com.project.review.mapper.ReviewMapperInter;
import com.project.review.to.ReviewDislikeCheckTO;
import com.project.review.to.ReviewLikeCheckTO;
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
	
	public int reviewLike(ReviewLikeCheckTO likeTO, ReviewDislikeCheckTO dislikeTO, ReviewTO to) {
//		System.out.println("넣을 값 : " + likeTO.getLike_users_seq());
//		System.out.println("넣을 값 : " +likeTO.getLike_board_seq());
		
		int memberSeq = likeTO.getLike_users_seq();
		int pseq = likeTO.getLike_board_seq();
		int flag = 2;
		likeTO = reviewMapperInter.reviewLike_Check(likeTO);
		if(likeTO.getLike_users_seq() == 0 && likeTO.getLike_board_seq() == 0) {
//			System.out.println("like 값1 : " +likeTO.getLike_users_seq());
//			System.out.println("like 값1 : " +likeTO.getLike_board_seq());
			dislikeTO = reviewMapperInter.reviewDislike_Check(dislikeTO);
			if(dislikeTO.getDislike_board_seq() == 0 && dislikeTO.getDislike_users_seq() == 0) {
//				System.out.println("dislike 값1 : " +dislikeTO.getDislike_users_seq());
//				System.out.println("dislike 값1 : " +dislikeTO.getDislike_board_seq());
				reviewMapperInter.reviewLike_Insert(memberSeq, pseq);
				reviewMapperInter.reviewLike_Up(to);
			flag = 0;
			} else {
				flag = 3;
			}
		} else if(likeTO.getLike_users_seq() != 0 && likeTO.getLike_board_seq() != 0) {
			reviewMapperInter.reviewLike_Delete(memberSeq, pseq);
			reviewMapperInter.reviewLike_Down(to);
			flag = 1;
		}
		
		return flag;
	}
	
	public int reviewDislike(ReviewLikeCheckTO likeTO, ReviewDislikeCheckTO dislikeTO, ReviewTO to) {
		int memberSeq = dislikeTO.getDislike_users_seq();
		int pseq = dislikeTO.getDislike_board_seq();
		int flag = 2;
		
//		System.out.println(memberSeq);
//		System.out.println(pseq);
		dislikeTO = reviewMapperInter.reviewDislike_Check(dislikeTO);
		if(dislikeTO.getDislike_users_seq() == 0 && dislikeTO.getDislike_board_seq() == 0) {
//			System.out.println("dislike2 값 : " +dislikeTO.getDislike_users_seq());
//			System.out.println("dislike2 값 : " +dislikeTO.getDislike_board_seq());
			likeTO = reviewMapperInter.reviewLike_Check(likeTO);
			if(likeTO.getLike_board_seq() == 0 && likeTO.getLike_users_seq() == 0) {
//				System.out.println("like2 값 : " +likeTO.getLike_users_seq());
//				System.out.println("like2 값 : " +likeTO.getLike_board_seq());
				reviewMapperInter.reviewDislike_Up(to);
				reviewMapperInter.reviewDislike_Insert(memberSeq, pseq);
				flag = 0;
			} else {
				flag = 3;
			}
		} else if(dislikeTO.getDislike_users_seq() != 0 && dislikeTO.getDislike_board_seq() != 0 ) {
			reviewMapperInter.reviewDislike_Down(to);
			reviewMapperInter.reviewDislike_Delete(memberSeq, pseq);
			flag = 1;
		}
		return flag;
	}
	
}
