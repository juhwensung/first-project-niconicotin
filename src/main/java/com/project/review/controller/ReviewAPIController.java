package com.project.review.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.gongji.to.GongjiCommentTO;
import com.project.review.dao.ReviewCommentDAO;
import com.project.review.dao.ReviewDAO;
import com.project.review.to.ReviewCommentTO;
import com.project.review.to.ReviewDislikeCheckTO;
import com.project.review.to.ReviewLikeCheckTO;
import com.project.review.to.ReviewTO;

@RestController
public class ReviewAPIController {
	
	@Autowired
	private ReviewDAO dao;
	@Autowired
	private ReviewCommentDAO cmtDAO;
	
	@RequestMapping(value = "api/reviewLists.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONArray reviewList(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		ReviewTO to = new ReviewTO();
		to.setReview_cigar_seq(Integer.parseInt((String)(paramMap.get("cigar_seq"))));
		ArrayList<ReviewTO> listTO = dao.reviewList(to);
		JSONArray reviewLists = new JSONArray();
		for(ReviewTO to2 : listTO) {
			JSONObject obj = new JSONObject();
			obj.put("review_seq", to2.getReview_seq());
			obj.put("review_writer_seq", to2.getReview_writer_seq());
			obj.put("review_cigar_seq", to2.getReview_cigar_seq());
			obj.put("review_suject", to2.getReview_subject());
			obj.put("review_writer", to2.getReview_writer());
			obj.put("review_reg_date", to2.getReview_reg_date().toString());
			obj.put("review_content", to2.getReview_content());
			obj.put("review_hit", to2.getReview_hit());
			obj.put("review_cmt_count", to2.getReview_cmt_count());
			obj.put("review_grade", to2.getReview_grade());
			obj.put("review_like", to2.getReview_like());
			obj.put("review_dislike", to2.getReview_dislike());
			obj.put("review_file_name", to2.getReview_file_name());
			obj.put("review_file_size", to2.getReview_file_size());
			obj.put("review_smoke_years", to2.getReview_smoke_years().toString());
			
			reviewLists.add(obj);
		}
		
		return reviewLists;
	}
	
	@RequestMapping(value = "api/review_view.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewView(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt((String)(paramMap.get("review_seq"))));
		HttpSession session = request.getSession();
		//System.out.println((int)session.getAttribute("member_seq"));
		to = dao.reviewView(to);
		JSONObject reviewViewObj = new JSONObject();
		reviewViewObj.put("review_seq", to.getReview_seq());
		reviewViewObj.put("review_writer_seq", to.getReview_writer_seq());
		reviewViewObj.put("review_cigar_seq", to.getReview_cigar_seq());
		reviewViewObj.put("review_suject", to.getReview_subject());
		reviewViewObj.put("review_writer", to.getReview_writer());
		reviewViewObj.put("review_reg_date", to.getReview_reg_date().toString());
		reviewViewObj.put("review_content", to.getReview_content());
		reviewViewObj.put("review_hit", to.getReview_hit());
		reviewViewObj.put("review_cmt_count", to.getReview_cmt_count());
		reviewViewObj.put("review_grade", to.getReview_grade());
		reviewViewObj.put("review_like", to.getReview_like());
		reviewViewObj.put("review_dislike", to.getReview_dislike());
		reviewViewObj.put("review_file_name", to.getReview_file_name());
		reviewViewObj.put("review_file_size", to.getReview_file_size());
		reviewViewObj.put("review_smoke_years", to.getReview_smoke_years().toString());
		
		return reviewViewObj;
	}
	
	@RequestMapping(value = "api/review_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void reviewWrite(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
	
	}
	
	@RequestMapping(value = "api/review_write_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewWriteOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		HttpSession session = request.getSession();
		ReviewTO to = new ReviewTO();
		
		to.setReview_writer_seq((int)session.getAttribute("member_seq"));
		to.setReview_cigar_seq(Integer.parseInt((String)(paramMap.get("review_cigar_seq"))));
		to.setReview_subject((String)(paramMap.get("review_subject")));
		to.setReview_writer((String)(session.getAttribute("review_writer")));
		to.setReview_content((String)(paramMap.get("review_content")));
		to.setReview_grade(Integer.parseInt((String)(paramMap.get("review_grade"))));
		to.setReview_file_name((String)(paramMap.get("review_file_name")));
		to.setReview_file_size(Integer.parseInt((String)(paramMap.get("review_file_size"))));
		//to.setReview_writer_seq(Integer.parseInt((String)(paramMap.get("review_smoke_years")));
		to.setReview_smoke_years((Date)session.getAttribute("smoke_years"));
		
		int flag = dao.reviewWriteOk(to);
		JSONObject reviewWriteOk = new JSONObject();
		reviewWriteOk.put("flag", flag);
		return reviewWriteOk;
	}
	
	@RequestMapping(value = "api/review_modify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewModify(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt((String)(paramMap.get("review_seq"))));
		to = dao.reviewModify(to);
		JSONObject reviewModifyObj = new JSONObject();
		reviewModifyObj.put("review_seq", to.getReview_seq());
		reviewModifyObj.put("review_writer_seq", to.getReview_writer_seq());
		reviewModifyObj.put("review_cigar_seq", to.getReview_cigar_seq());
		reviewModifyObj.put("review_suject", to.getReview_subject());
		reviewModifyObj.put("review_writer", to.getReview_writer());
		reviewModifyObj.put("review_reg_date", to.getReview_reg_date().toString());
		reviewModifyObj.put("review_content", to.getReview_content());
		reviewModifyObj.put("review_hit", to.getReview_hit());
		reviewModifyObj.put("review_cmt_count", to.getReview_cmt_count());
		reviewModifyObj.put("review_grade", to.getReview_grade());
		reviewModifyObj.put("review_like", to.getReview_like());
		reviewModifyObj.put("review_dislike", to.getReview_dislike());
		reviewModifyObj.put("review_file_name", to.getReview_file_name());
		reviewModifyObj.put("review_file_size", to.getReview_file_size());
		reviewModifyObj.put("review_smoke_years", to.getReview_smoke_years().toString());

		return reviewModifyObj;
	}
	
	@RequestMapping(value = "api/review_modify_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewModifyOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt((String)(paramMap.get("review_seq"))));
		to.setReview_cigar_seq(Integer.parseInt((String)(paramMap.get("review_cigar_seq"))));
		to.setReview_subject((String)(paramMap.get("review_subject")));
		to.setReview_content((String)(paramMap.get("review_content")));
		to.setReview_grade(Integer.parseInt((String)(paramMap.get("review_grade"))));
		to.setReview_file_name((String)(paramMap.get("review_file_name")));
		to.setReview_file_size(Integer.parseInt((String)(paramMap.get("review_file_size"))));
		int flag = dao.reviewModifyOk(to);
		JSONObject reviewModifyOk = new JSONObject();
		reviewModifyOk.put("flag", flag);
		return reviewModifyOk;
	}
	
	@RequestMapping(value = "api/review_delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt((String)(paramMap.get("review_seq"))));
		to = dao.reviewDelete(to);
		JSONObject reviewDeleteObj = new JSONObject();
		reviewDeleteObj.put("review_seq", to.getReview_seq());
		reviewDeleteObj.put("review_writer_seq", to.getReview_writer_seq());
		reviewDeleteObj.put("review_cigar_seq", to.getReview_cigar_seq());
		reviewDeleteObj.put("review_suject", to.getReview_subject());
		reviewDeleteObj.put("review_writer", to.getReview_writer());
		reviewDeleteObj.put("review_reg_date", to.getReview_reg_date().toString());
		reviewDeleteObj.put("review_content", to.getReview_content());
		reviewDeleteObj.put("review_hit", to.getReview_hit());
		reviewDeleteObj.put("review_cmt_count", to.getReview_cmt_count());
		reviewDeleteObj.put("review_grade", to.getReview_grade());
		reviewDeleteObj.put("review_like", to.getReview_like());
		reviewDeleteObj.put("review_dislike", to.getReview_dislike());
		reviewDeleteObj.put("review_file_name", to.getReview_file_name());
		reviewDeleteObj.put("review_file_size", to.getReview_file_size());
		reviewDeleteObj.put("review_smoke_years", to.getReview_smoke_years().toString());
		
		return reviewDeleteObj;
	}
	
	@RequestMapping(value = "api/review_delete_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewDeleteOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		HttpSession session = request.getSession();
		ReviewTO to = new ReviewTO();
		to.setReview_seq(Integer.parseInt((String)(paramMap.get("review_seq"))));
		to.setReview_cigar_seq(Integer.parseInt((String)(paramMap.get("review_cigar_seq"))));
		to.setReview_writer_seq((int)session.getAttribute("member_seq"));
		int flag = dao.reviewDeleteOk(to);
		JSONObject reviewDeleteOk = new JSONObject();
		reviewDeleteOk.put("flag", flag);
		return reviewDeleteOk;
	}
	
	@RequestMapping(value = "api/review_like.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewLike(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		ReviewTO to = new ReviewTO();
		ReviewLikeCheckTO likeTO = new ReviewLikeCheckTO();
		ReviewDislikeCheckTO dislikeTO = new ReviewDislikeCheckTO();
		HttpSession session = request.getSession();
		//to.setReview_seq(Integer.parseInt((String)(paramMap.get("review_seq")));
		to.setReview_seq(Integer.parseInt((String)(paramMap.get("review_seq"))));
		
		dislikeTO.setDislike_board_seq(to.getReview_seq());
		dislikeTO.setDislike_users_seq((int)session.getAttribute("member_seq"));
		likeTO.setLike_board_seq(to.getReview_seq());
		likeTO.setLike_users_seq((int)session.getAttribute("member_seq"));
		int flag = dao.reviewLike(likeTO, dislikeTO ,to);
		JSONObject reviewLike = new JSONObject();
		reviewLike.put("flag", flag);
		return reviewLike;
		
	}
	
	@RequestMapping(value = "api/review_dislike.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewDislike(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		ReviewTO to = new ReviewTO();
		ReviewLikeCheckTO likeTO = new ReviewLikeCheckTO();
		ReviewDislikeCheckTO dislikeTO = new ReviewDislikeCheckTO();
		HttpSession session = request.getSession();
		
		to.setReview_seq(Integer.parseInt((String)(paramMap.get("review_seq"))));
		//to.setReview_seq(1);
		
		dislikeTO.setDislike_board_seq(to.getReview_seq());
		dislikeTO.setDislike_users_seq((int)session.getAttribute("member_seq"));
		
		likeTO.setLike_board_seq(to.getReview_seq());
		likeTO.setLike_users_seq((int)session.getAttribute("member_seq"));
		int flag = dao.reviewDislike(likeTO, dislikeTO , to);
		JSONObject reviewDislike = new JSONObject();
		reviewDislike.put("flag", flag);
		return reviewDislike;
	}
	
	@RequestMapping(value = "api/reviewCommentLists.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public JSONArray reviewCommentList(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		JSONArray reviewCommentLists = new JSONArray();
		ReviewCommentTO cmtTO = new ReviewCommentTO();
		cmtTO.setReview_pseq(Integer.parseInt((String)(paramMap.get("review_pseq"))));
		ArrayList<ReviewCommentTO> CommentListTO = cmtDAO.reviewCommentList(cmtTO);
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
			obj.put("review_cmt_reg_date", cmtTO2.getReview_cmt_reg_date().toString());
			
			reviewCommentLists.add(obj);
		}
		
		return reviewCommentLists;
		
	}
	
	@RequestMapping(value = "api/review_parent_cmt_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewParentCmtWrite(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		HttpSession session = request.getSession();
		JSONObject reviewCmtWriteObj = new JSONObject();
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_pseq(Integer.parseInt((String)(paramMap.get("review_pseq"))));
		to = cmtDAO.reviewParentCommentWrite(to);
		reviewCmtWriteObj.put("review_cmt_seq", 0);
		reviewCmtWriteObj.put("review_pseq", to.getReview_pseq());
		reviewCmtWriteObj.put("review_grp", to.getReview_grp()+1);
		reviewCmtWriteObj.put("review_grps", 0);
		reviewCmtWriteObj.put("review_grpl", 0);
		reviewCmtWriteObj.put("review_cmt_writer", (String)session.getAttribute("nickname"));
		
		return reviewCmtWriteObj;
	}
	
	
	@RequestMapping(value = "api/review_cmt_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewCmtWrite(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		HttpSession session = request.getSession();
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_cmt_seq(Integer.parseInt((String)(paramMap.get("review_cmt_seq"))));
		to = cmtDAO.reviewCommentWrite(to);
		JSONObject reviewCmtWriteObj = new JSONObject();
		reviewCmtWriteObj.put("review_cmt_seq", to.getReview_cmt_seq());
		reviewCmtWriteObj.put("review_pseq", to.getReview_pseq());
		reviewCmtWriteObj.put("review_grp", to.getReview_grp());
		reviewCmtWriteObj.put("review_grps", to.getReview_grps());
		reviewCmtWriteObj.put("review_grpl", to.getReview_grpl());
		reviewCmtWriteObj.put("review_cmt_writer", (String)session.getAttribute("nickname"));
		
		return reviewCmtWriteObj;
	}
	
	@RequestMapping(value = "api/review_cmt_write_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewCmtWriteOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		ReviewCommentTO to = new ReviewCommentTO();
		HttpSession session = request.getSession();
		to.setReview_cmt_seq(Integer.parseInt((String)(paramMap.get("review_cmt_seq"))));
		to.setReview_pseq(Integer.parseInt((String)(paramMap.get("review_pseq"))));
		to.setReview_cmt_writer_seq((int)session.getAttribute("member_seq"));
		to.setReview_grp(Integer.parseInt((String)(paramMap.get("review_grp"))));
		to.setReview_grps(Integer.parseInt((String)(paramMap.get("review_grps"))));
		to.setReview_grpl(Integer.parseInt((String)(paramMap.get("review_grpl"))));
		to.setReview_cmt_writer((String)session.getAttribute("nickname"));
		to.setReview_cmt_content((String)(paramMap.get("review_cmt_content")));
		int flag = cmtDAO.reviewCmtWriteOk(to);
		JSONObject reviewCmtWriteOk = new JSONObject();
		reviewCmtWriteOk.put("flag", flag);
		return reviewCmtWriteOk;
	}
	
	@RequestMapping(value = "api/review_cmt_modify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewCmtModify(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_cmt_seq(Integer.parseInt((String)(paramMap.get("review_cmt_seq"))));
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
		reviewCmtModifyObj.put("review_cmt_reg_date", to.getReview_cmt_reg_date().toString());

		return reviewCmtModifyObj;
	}
	
	@RequestMapping(value = "api/review_cmt_modify_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewCmtModifyOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_cmt_seq(Integer.parseInt((String)(paramMap.get("review_cmt_seq"))));
		to.setReview_pseq(Integer.parseInt((String)(paramMap.get("review_pseq"))));
		to.setReview_cmt_writer_seq(Integer.parseInt((String)(paramMap.get("review_cmt_writer_seq"))));
		to.setReview_grp(Integer.parseInt((String)(paramMap.get("review_grp"))));
		to.setReview_grps(Integer.parseInt((String)(paramMap.get("review_grps"))));
		to.setReview_grpl(Integer.parseInt((String)(paramMap.get("review_grpl"))));
		to.setReview_cmt_writer((String)(paramMap.get("review_cmt_writer")));
		to.setReview_cmt_content((String)(paramMap.get("review_cmt_content")));
		to.setReview_cmt_reg_date(Date.valueOf((String)(paramMap.get("review_cmt_reg_date"))));
		int flag = cmtDAO.reviewCmtWriteOk(to);
		JSONObject reviewCmtModifyOk = new JSONObject();
		reviewCmtModifyOk.put("flag", flag);
		return reviewCmtModifyOk;
	}
	
	@RequestMapping(value = "api/review_cmt_delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewCmtDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_cmt_seq(Integer.parseInt((String)(paramMap.get("review_cmt_seq"))));
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
		reviewCmtDeleteObj.put("review_cmt_reg_date", to.getReview_cmt_reg_date().toString());

		return reviewCmtDeleteObj;
	}
	
	@RequestMapping(value = "api/review_cmt_delete_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject reviewCmtDeleteOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		HttpSession session = request.getSession();
		ReviewCommentTO to = new ReviewCommentTO();
		to.setReview_cmt_seq(Integer.parseInt((String)(paramMap.get("review_cmt_seq"))));
		to.setReview_cmt_writer_seq((int)session.getAttribute("member_seq"));
		int flag = cmtDAO.reviewCommentDeleteOk(to);
		JSONObject reviewCmtDeleteOk = new JSONObject();
		reviewCmtDeleteOk.put("flag", flag);
		return reviewCmtDeleteOk;
	}
}
