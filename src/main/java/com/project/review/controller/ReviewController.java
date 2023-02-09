package com.project.review.controller;

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

import com.project.review.dao.ReviewDAO;
import com.project.review.to.ReviewTO;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewDAO dao;
	
	@RequestMapping("review/list.do")
	public ModelAndView reviewList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		//to.setReview_cigar_seq(Integer.parseInt(request.getParameter("Review_cigar_seq")));
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
	public ModelAndView reviewView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		//to.setReview_cigar_seq(Integer.parseInt(request.getParameter("review_cigar_seq")));
		to.setReview_seq(1);
		HttpSession session = request.getSession();
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
		
		mav.addObject("reviewViewObj", reviewViewObj);
		mav.setViewName("reviewViews/reviewView");
		return mav;
	}
	
	@RequestMapping("review/write.do")
	public ModelAndView reviewWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reviewViews/reviewWrite");
		return mav;
	}
	
	@RequestMapping("review/write_ok.do")
	public ModelAndView reviewWriteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		
		to.setReview_writer_seq(Integer.parseInt(request.getParameter("review_writer_seq")));
		to.setReview_cigar_seq(Integer.parseInt(request.getParameter("review_cigar_seq")));
		to.setReview_subject(request.getParameter("review_subject"));
		to.setReview_writer(request.getParameter("review_writer"));
		to.setReview_content(request.getParameter("review_content"));
		to.setReview_grade(Integer.parseInt(request.getParameter("review_grade")));
		to.setReview_file_name(request.getParameter("review_file_name"));
		to.setReview_file_size(Integer.parseInt(request.getParameter("review_file_size")));
		//to.setReview_writer_seq(Integer.parseInt(request.getParameter("review_smoke_years")));
		
		int flag = dao.reviewWriteOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("reviewViews/reviewWrite_ok");
		return mav;
	}
	
	@RequestMapping("review/modify.do")
	public ModelAndView reviewModify(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt(request.getParameter("review_seq")));
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
	public ModelAndView reviewModifyOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt(request.getParameter("review_seq")));
		to.setReview_cigar_seq(Integer.parseInt(request.getParameter("review_cigar_seq")));
		to.setReview_subject(request.getParameter("review_subject"));
		to.setReview_content(request.getParameter("review_content"));
		to.setReview_grade(Integer.parseInt(request.getParameter("review_grade")));
		to.setReview_file_name(request.getParameter("review_file_name"));
		to.setReview_file_size(Integer.parseInt(request.getParameter("review_file_size")));
		int flag = dao.reviewModifyOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("reviewViews/reviewModify_ok");
		return mav;
	}
	
	@RequestMapping("review/delete.do")
	public ModelAndView reviewDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt(request.getParameter("review_seq")));
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
	public ModelAndView reviewdeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt(request.getParameter("review_seq")));
		to.setReview_cigar_seq(Integer.parseInt(request.getParameter("review_cigar_seq")));
		int flag = dao.reviewDeleteOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("reviewViews/reviewDelete_ok");
		return mav;
	}
}
