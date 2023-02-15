package com.project.review.controller;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.review.dao.ReviewCommentDAO;
import com.project.review.dao.ReviewDAO;
import com.project.review.to.ReviewCommentTO;
import com.project.review.to.ReviewDislikeCheckTO;
import com.project.review.to.ReviewLikeCheckTO;
import com.project.review.to.ReviewTO;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewDAO dao;
	@Autowired
	private ReviewCommentDAO cmtDAO;
	
	@RequestMapping("review/list.do")
	public ModelAndView reviewList(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		//to.setReview_cigar_seq(Integer.parseInt(review.getParameter("Review_cigar_seq")));
		to.setReview_cigar_seq(2);
		ArrayList<ReviewTO> listTO = dao.reviewList(to);
		JSONArray reviewLists = new JSONArray();
		for(ReviewTO to2 : listTO) {
			JSONObject obj = new JSONObject();
			obj.put("review_seq", to2.getReview_seq());
			obj.put("review_writer_seq", to2.getReview_writer_seq());
			obj.put("review_cigar_seq", to2.getReview_cigar_seq());
			obj.put("review_suject", to2.getReview_subject());
			obj.put("review_writer", to2.getReview_writer());
			obj.put("review_reg_date", to2.getReview_reg_date());
			obj.put("review_content", to2.getReview_content());
			obj.put("review_hit", to2.getReview_hit());
			obj.put("review_cmt_count", to2.getReview_cmt_count());
			obj.put("review_grade", to2.getReview_grade());
			obj.put("review_like", to2.getReview_like());
			obj.put("review_dislike", to2.getReview_dislike());
			obj.put("review_file_name", to2.getReview_file_name());
			obj.put("review_file_size", to2.getReview_file_size());
			obj.put("review_smoke_years", to2.getReview_smoke_years());
			
			reviewLists.add(obj);
		}
		
		mav.addObject("reviewLists", reviewLists);
		mav.setViewName("reviewViews/reviewList");
		return mav;
	}
	
	@RequestMapping("review/view.do")
	public ModelAndView reviewView(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		ReviewCommentTO cmtTO = new ReviewCommentTO();
		//to.setReview_cigar_seq(Integer.parseInt(review.getParameter("review_cigar_seq")));
		to.setReview_seq(1);
		cmtTO.setReview_pseq(1);
		HttpSession session = review.getSession();
		System.out.println((int)session.getAttribute("member_seq"));
		to = dao.reviewView(to);
		JSONObject reviewViewObj = new JSONObject();
		reviewViewObj.put("review_seq", to.getReview_seq());
		reviewViewObj.put("review_writer_seq", to.getReview_writer_seq());
		reviewViewObj.put("review_cigar_seq", to.getReview_cigar_seq());
		reviewViewObj.put("review_suject", to.getReview_subject());
		reviewViewObj.put("review_writer", to.getReview_writer());
		reviewViewObj.put("review_reg_date", to.getReview_reg_date());
		reviewViewObj.put("review_content", to.getReview_content());
		reviewViewObj.put("review_hit", to.getReview_hit());
		reviewViewObj.put("review_cmt_count", to.getReview_cmt_count());
		reviewViewObj.put("review_grade", to.getReview_grade());
		reviewViewObj.put("review_like", to.getReview_like());
		reviewViewObj.put("review_dislike", to.getReview_dislike());
		reviewViewObj.put("review_file_name", to.getReview_file_name());
		reviewViewObj.put("review_file_size", to.getReview_file_size());
		reviewViewObj.put("review_smoke_years", to.getReview_smoke_years());
		
		ArrayList<ReviewCommentTO> CommentListTO = cmtDAO.reviewCommentList(cmtTO);
		JSONArray reviewCommentLists = new JSONArray();
		for(ReviewCommentTO cmtTO2 : CommentListTO) {
			JSONObject obj = new JSONObject();
			obj.put("review_cmt_seq", cmtTO2.getReview_cmt_seq());
			obj.put("review_pseq", cmtTO2.getReview_pseq());
			obj.put("review_cmt_writer_seq", cmtTO2.getReview_cmt_writer_seq());
			obj.put("review_grp", cmtTO2.getReview_grp());
			obj.put("review_grps", cmtTO2.getReview_grps());
			obj.put("review_grpl", cmtTO2.getReview_grpl());
			obj.put("review_cmt_writer", cmtTO2.getReview_cmt_writer());
			obj.put("review_cmt_content", cmtTO2.getReview_cmt_content());
			obj.put("review_cmt_reg_date", cmtTO2.getReview_cmt_reg_date());
			
			reviewCommentLists.add(obj);
		}
		
		mav.addObject("reviewViewObj", reviewViewObj);
		mav.addObject("reviewCommentLists", reviewCommentLists);
		mav.setViewName("reviewViews/reviewView");
		return mav;
	}
	
	@RequestMapping("review/write.do")
	public ModelAndView reviewWrite(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reviewViews/reviewWrite");
		return mav;
	}
	
	@RequestMapping("review/write_ok.do")
	public ModelAndView reviewWriteOk(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		
		to.setReview_writer_seq(Integer.parseInt(review.getParameter("review_writer_seq")));
		to.setReview_cigar_seq(Integer.parseInt(review.getParameter("review_cigar_seq")));
		to.setReview_subject(review.getParameter("review_subject"));
		to.setReview_writer(review.getParameter("review_writer"));
		to.setReview_content(review.getParameter("review_content"));
		to.setReview_grade(Integer.parseInt(review.getParameter("review_grade")));
		to.setReview_file_name(review.getParameter("review_file_name"));
		to.setReview_file_size(Integer.parseInt(review.getParameter("review_file_size")));
		//to.setReview_writer_seq(Integer.parseInt(review.getParameter("review_smoke_years")));
		
		int flag = dao.reviewWriteOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("reviewViews/reviewWrite_ok");
		return mav;
	}
	
	@RequestMapping("review/modify.do")
	public ModelAndView reviewModify(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt(review.getParameter("review_seq")));
		to = dao.reviewModify(to);
		JSONObject reviewModifyObj = new JSONObject();
		reviewModifyObj.put("review_seq", to.getReview_seq());
		reviewModifyObj.put("review_writer_seq", to.getReview_writer_seq());
		reviewModifyObj.put("review_cigar_seq", to.getReview_cigar_seq());
		reviewModifyObj.put("review_suject", to.getReview_subject());
		reviewModifyObj.put("review_writer", to.getReview_writer());
		reviewModifyObj.put("review_reg_date", to.getReview_reg_date());
		reviewModifyObj.put("review_content", to.getReview_content());
		reviewModifyObj.put("review_hit", to.getReview_hit());
		reviewModifyObj.put("review_cmt_count", to.getReview_cmt_count());
		reviewModifyObj.put("review_grade", to.getReview_grade());
		reviewModifyObj.put("review_like", to.getReview_like());
		reviewModifyObj.put("review_dislike", to.getReview_dislike());
		reviewModifyObj.put("review_file_name", to.getReview_file_name());
		reviewModifyObj.put("review_file_size", to.getReview_file_size());
		reviewModifyObj.put("review_smoke_years", to.getReview_smoke_years());
		mav.addObject("reviewModifyObj", reviewModifyObj);
		mav.setViewName("reviewViews/reviewModify");
		return mav;
	}
	
	@RequestMapping("review/modify_ok.do")
	public ModelAndView reviewModifyOk(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt(review.getParameter("review_seq")));
		to.setReview_cigar_seq(Integer.parseInt(review.getParameter("review_cigar_seq")));
		to.setReview_subject(review.getParameter("review_subject"));
		to.setReview_content(review.getParameter("review_content"));
		to.setReview_grade(Integer.parseInt(review.getParameter("review_grade")));
		to.setReview_file_name(review.getParameter("review_file_name"));
		to.setReview_file_size(Integer.parseInt(review.getParameter("review_file_size")));
		String rr = "";
		int flag = dao.reviewModifyOk(to, rr);
		mav.addObject("flag", flag);
		mav.setViewName("reviewViews/reviewModify_ok");
		return mav;
	}
	
	@RequestMapping("review/delete.do")
	public ModelAndView reviewDelete(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt(review.getParameter("review_seq")));
		to = dao.reviewDelete(to);
		JSONObject reviewDeleteObj = new JSONObject();
		reviewDeleteObj.put("review_seq", to.getReview_seq());
		reviewDeleteObj.put("review_writer_seq", to.getReview_writer_seq());
		reviewDeleteObj.put("review_cigar_seq", to.getReview_cigar_seq());
		reviewDeleteObj.put("review_suject", to.getReview_subject());
		reviewDeleteObj.put("review_writer", to.getReview_writer());
		reviewDeleteObj.put("review_reg_date", to.getReview_reg_date());
		reviewDeleteObj.put("review_content", to.getReview_content());
		reviewDeleteObj.put("review_hit", to.getReview_hit());
		reviewDeleteObj.put("review_cmt_count", to.getReview_cmt_count());
		reviewDeleteObj.put("review_grade", to.getReview_grade());
		reviewDeleteObj.put("review_like", to.getReview_like());
		reviewDeleteObj.put("review_dislike", to.getReview_dislike());
		reviewDeleteObj.put("review_file_name", to.getReview_file_name());
		reviewDeleteObj.put("review_file_size", to.getReview_file_size());
		reviewDeleteObj.put("review_smoke_years", to.getReview_smoke_years());
		mav.addObject("reviewDeleteObj", reviewDeleteObj);
		mav.setViewName("reviewViews/reviewDelete");
		return mav;
	}
	
	@RequestMapping("review/delete_ok.do")
	public ModelAndView reviewdeleteOk(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt(review.getParameter("review_seq")));
		to.setReview_cigar_seq(Integer.parseInt(review.getParameter("review_cigar_seq")));
		int flag = dao.reviewDeleteOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("reviewViews/reviewDelete_ok");
		return mav;
	}
	
	@RequestMapping("review/like.do")
	public ModelAndView review_like(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		ReviewLikeCheckTO likeTO = new ReviewLikeCheckTO();
		ReviewDislikeCheckTO dislikeTO = new ReviewDislikeCheckTO();
		HttpSession session = review.getSession();
		//to.setReview_seq(Integer.parseInt(review.getParameter("review_seq")));
		to.setReview_seq(1);
		
		dislikeTO.setDislike_board_seq(to.getReview_seq());
		dislikeTO.setDislike_users_seq((int)session.getAttribute("member_seq"));
		likeTO.setLike_board_seq(to.getReview_seq());
		likeTO.setLike_users_seq((int)session.getAttribute("member_seq"));
		int flag = dao.reviewLike(likeTO, dislikeTO ,to);
		
		mav.addObject("flag", flag);
		mav.setViewName("reviewViews/reviewLike");
		
		return mav;
	}
	
	@RequestMapping("review/dislike.do")
	public ModelAndView review_dislike(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		ReviewLikeCheckTO likeTO = new ReviewLikeCheckTO();
		ReviewDislikeCheckTO dislikeTO = new ReviewDislikeCheckTO();
		HttpSession session = review.getSession();
		
		//to.setReview_seq(Integer.parseInt(review.getParameter("review_seq")));
		to.setReview_seq(1);
		
		dislikeTO.setDislike_board_seq(to.getReview_seq());
		dislikeTO.setDislike_users_seq((int)session.getAttribute("member_seq"));
		
		likeTO.setLike_board_seq(to.getReview_seq());
		likeTO.setLike_users_seq((int)session.getAttribute("member_seq"));
		int flag = dao.reviewDislike(likeTO, dislikeTO , to);
		
		mav.addObject("flag", flag);
		mav.setViewName("reviewViews/reviewDislike");
		return mav;
	}
	
	@RequestMapping("reviewCigar/parent_cmt_write.do")
	public ModelAndView reviewParentCmtWrite(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = review.getSession();
		JSONObject reviewCmtWriteObj = new JSONObject();
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_pseq(Integer.parseInt(review.getParameter("review_pseq")));
		to = cmtDAO.reviewParentCommentWrite(to);
		reviewCmtWriteObj.put("review_cmt_seq", 0);
		reviewCmtWriteObj.put("review_pseq", to.getReview_pseq());
		reviewCmtWriteObj.put("review_grp", to.getReview_grp()+1);
		reviewCmtWriteObj.put("review_grps", 0);
		reviewCmtWriteObj.put("review_grpl", 0);
		reviewCmtWriteObj.put("review_cmt_writer", (String)session.getAttribute("nickname"));
		
		mav.addObject("reviewCmtWriteObj", reviewCmtWriteObj);
		mav.setViewName("reviewViews/reviewCmtWrite");
		return mav;
	}
	
	
	@RequestMapping("reviewCigar/cmt_write.do")
	public ModelAndView reviewCmtWrite(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = review.getSession();
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_cmt_seq(Integer.parseInt(review.getParameter("review_cmt_seq")));
		to = cmtDAO.reviewCommentWrite(to);
		JSONObject reviewCmtWriteObj = new JSONObject();
		reviewCmtWriteObj.put("review_cmt_seq", to.getReview_cmt_seq());
		reviewCmtWriteObj.put("review_pseq", to.getReview_pseq());
		reviewCmtWriteObj.put("review_grp", to.getReview_grp());
		reviewCmtWriteObj.put("review_grps", to.getReview_grps());
		reviewCmtWriteObj.put("review_grpl", to.getReview_grpl());
		reviewCmtWriteObj.put("review_cmt_writer", (String)session.getAttribute("nickname"));
		mav.addObject("reviewCmtWriteObj", reviewCmtWriteObj);
		mav.setViewName("reviewViews/reviewCmtWrite");
		return mav;
	}
	
	@RequestMapping("reviewCigar/cmt_write_ok.do")
	public ModelAndView reviewCmtWriteOk(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewCommentTO to = new ReviewCommentTO();
		HttpSession session = review.getSession();
		to.setReview_cmt_seq(Integer.parseInt(review.getParameter("review_cmt_seq")));
		to.setReview_pseq(Integer.parseInt(review.getParameter("review_pseq")));
		to.setReview_cmt_writer_seq((int)session.getAttribute("member_seq"));
		to.setReview_grp(Integer.parseInt(review.getParameter("review_grp")));
		to.setReview_grps(Integer.parseInt(review.getParameter("review_grps")));
		to.setReview_grpl(Integer.parseInt(review.getParameter("review_grpl")));
		to.setReview_cmt_writer((String)session.getAttribute("nickname"));
		to.setReview_cmt_content(review.getParameter("review_cmt_content"));
		int flag = cmtDAO.reviewCmtWriteOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("reviewViews/reviewCmtWrite_ok");
		return mav;
	}
	
	@RequestMapping("reviewCigar/cmt_modify.do")
	public ModelAndView reviewCmtModify(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_cmt_seq(Integer.parseInt(review.getParameter("review_cmt_seq")));
		to = cmtDAO.reviewCommentModify(to);
		JSONObject reviewCmtModifyObj = new JSONObject();
		reviewCmtModifyObj.put("review_cmt_seq", to.getReview_cmt_seq());
		reviewCmtModifyObj.put("review_pseq", to.getReview_pseq());
		reviewCmtModifyObj.put("review_cmt_writer_seq", to.getReview_cmt_writer_seq());
		reviewCmtModifyObj.put("review_grp", to.getReview_grp());
		reviewCmtModifyObj.put("review_grps", to.getReview_grps());
		reviewCmtModifyObj.put("review_grpl", to.getReview_grpl());
		reviewCmtModifyObj.put("review_cmt_writer", to.getReview_cmt_writer());
		reviewCmtModifyObj.put("review_cmt_content", to.getReview_cmt_content());
		reviewCmtModifyObj.put("review_cmt_reg_date", to.getReview_cmt_reg_date());
		mav.addObject("reviewCmtModifyObj", reviewCmtModifyObj);
		mav.setViewName("reviewViews/reviewCmtModify");
		return mav;
	}
	
	@RequestMapping("reviewCigar/cmt_modify_ok.do")
	public ModelAndView reviewCmtModifyOk(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_cmt_seq(Integer.parseInt(review.getParameter("review_cmt_seq")));
		to.setReview_pseq(Integer.parseInt(review.getParameter("review_pseq")));
		to.setReview_cmt_writer_seq(Integer.parseInt(review.getParameter("review_cmt_writer_seq")));
		to.setReview_grp(Integer.parseInt(review.getParameter("review_grp")));
		to.setReview_grps(Integer.parseInt(review.getParameter("review_grps")));
		to.setReview_grpl(Integer.parseInt(review.getParameter("review_grpl")));
		to.setReview_cmt_writer(review.getParameter("review_cmt_writer"));
		to.setReview_cmt_content(review.getParameter("review_cmt_content"));
		to.setReview_cmt_reg_date(Date.valueOf(review.getParameter("review_cmt_reg_date")));
		int flag = cmtDAO.reviewCmtWriteOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("reviewViews/reviewCmtModify_ok");
		return mav;
	}
	
	@RequestMapping("reviewCigar/cmt_delete.do")
	public ModelAndView reviewCmtDelete(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_cmt_seq(Integer.parseInt(review.getParameter("review_cmt_seq")));
		to = cmtDAO.reviewCommentDelete(to);
		JSONObject reviewCmtDeleteObj = new JSONObject();
		reviewCmtDeleteObj.put("review_cmt_seq", to.getReview_cmt_seq());
		reviewCmtDeleteObj.put("review_pseq", to.getReview_pseq());
		reviewCmtDeleteObj.put("review_cmt_writer_seq", to.getReview_cmt_writer_seq());
		reviewCmtDeleteObj.put("review_grp", to.getReview_grp());
		reviewCmtDeleteObj.put("review_grps", to.getReview_grps());
		reviewCmtDeleteObj.put("review_grpl", to.getReview_grpl());
		reviewCmtDeleteObj.put("review_cmt_writer", to.getReview_cmt_writer());
		reviewCmtDeleteObj.put("review_cmt_content", to.getReview_cmt_content());
		reviewCmtDeleteObj.put("review_cmt_reg_date", to.getReview_cmt_reg_date());
		mav.addObject("reviewCmtDeleteObj", reviewCmtDeleteObj);
		mav.setViewName("reviewViews/reviewCmtDelete");
		return mav;
	}
	
	@RequestMapping("reviewCigar/cmt_delete_ok.do")
	public ModelAndView reviewCmtDeleteOk(HttpServletRequest review, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_cmt_seq(Integer.parseInt(review.getParameter("review_cmt_seq")));
		int flag = cmtDAO.reviewCommentDeleteOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("reviewViews/reviewCmtDelete_ok");
		return mav;
	}
}
