package com.project.free_board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.free_board.dao.FreeBoardCommentDAO;
import com.project.free_board.dao.FreeBoardDAO;
import com.project.free_board.to.FreeBoardCommentTO;
import com.project.free_board.to.FreeBoardTO;

@Controller
public class FreeBoardController {
	
	@Autowired
	private FreeBoardDAO dao;
	@Autowired
	private FreeBoardCommentDAO cmtDAO;
	
	@RequestMapping(value = "/free/list.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView freeList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ArrayList<FreeBoardTO> freeLists = dao.freeList();
		
		JSONObject freeListsObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(FreeBoardTO to : freeLists) {
			JSONObject obj = new JSONObject();
			obj.put("free_seq",to.getFree_seq());
			obj.put("free_writer_seq", to.getFree_writer_seq());
			obj.put("free_writer", to.getFree_writer());
			obj.put("free_subject", to.getFree_subject());
			obj.put("free_content", to.getFree_content());
			obj.put("free_hit", to.getFree_hit());
			obj.put("free_reg_date", to.getFree_reg_date().toString());
			obj.put("free_smoke_years", to.getFree_smoke_years().toString());
			obj.put("free_hit", to.getFree_cmt_count());
			
			jsonArray.add(obj);
		}
		
		freeListsObj.put("freeList", jsonArray);
		
		mav.addObject("freeListObj", freeListsObj);
		mav.setViewName("freeViews/freeList");
		return mav;
	}
	
	@RequestMapping(value = "/free/view.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView freeView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		FreeBoardTO to = new FreeBoardTO();
		FreeBoardCommentTO cmtTO = new FreeBoardCommentTO();
		//to.setFree_seq(Integer.parseInt(request.getParameter("free_seq")));
		to.setFree_seq(1);
		cmtTO.setFree_pseq(1);
		
		to = dao.freeView(to);
		JSONObject freeViewObj = new JSONObject();
		freeViewObj.put("free_seq",to.getFree_seq());
		freeViewObj.put("free_writer_seq", to.getFree_writer_seq());
		freeViewObj.put("free_writer", to.getFree_writer());
		freeViewObj.put("free_subject", to.getFree_subject());
		freeViewObj.put("free_content", to.getFree_content());
		freeViewObj.put("free_hit", to.getFree_hit());
		freeViewObj.put("free_reg_date", to.getFree_reg_date().toString());
		freeViewObj.put("free_smoke_years", to.getFree_smoke_years().toString());
		freeViewObj.put("free_hit", to.getFree_cmt_count());
		
		ArrayList<FreeBoardCommentTO> CommentListTO = cmtDAO.freeCommentList(cmtTO);
		JSONArray freeCommentLists = new JSONArray();
		for(FreeBoardCommentTO cmtTO2 : CommentListTO) {
			JSONObject obj = new JSONObject();
			obj.put("free_cmt_seq", cmtTO2.getFree_cmt_seq());
			obj.put("free_pseq", cmtTO2.getFree_pseq());
			obj.put("free_cmt_writer_seq", cmtTO2.getFree_cmt_writer_seq());
			obj.put("free_grp", cmtTO2.getFree_grp());
			obj.put("free_grps", cmtTO2.getFree_grps());
			obj.put("free_grpl", cmtTO2.getFree_grpl());
			obj.put("free_cmt_writer", cmtTO2.getFree_cmt_writer());
			obj.put("free_cmt_content", cmtTO2.getFree_cmt_content());
			obj.put("free_cmt_reg_date", cmtTO2.getFree_cmt_reg_date());
			
			freeCommentLists.add(obj);
		}
		
		mav.addObject("freeViewObj", freeViewObj);
		mav.addObject("freeCommentLists", freeCommentLists);
		
		mav.setViewName("freeViews/freeView2");
		
		return mav;
	}
	
	@RequestMapping(value = "/free/write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView freeWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("freeViews/freeWrite");
		return mav;
	}
	
	@RequestMapping(value = "/free/write_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView freeWriteOk(HttpServletRequest request, HttpServletResponse response, MultipartFile upload) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		FreeBoardTO to = new FreeBoardTO();
		
		//System.out.println(request.getAttribute("free_public_true"));

			to.setFree_seq(Integer.parseInt(request.getParameter("free_seq")));
			to.setFree_writer_seq((int)session.getAttribute("member_seq"));
			to.setFree_writer((String)session.getAttribute("nickname"));
			to.setFree_subject(request.getParameter("free_subject"));
			to.setFree_content(request.getParameter("free_content"));
			to.setFree_smoke_years((Date)session.getAttribute("smoke_years"));
			
		
//		} catch(NullPointerException e) {
//			System.out.println("[에러] : " + e.getMessage());
//		}
		
		int flag = dao.freeWrite_Ok(to);
		mav.addObject("flag", flag);
		mav.setViewName("freeViews/freeWrite_ok");
		return mav;
	}
	
	@RequestMapping(value = "/free/modify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView freeModify(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		FreeBoardTO to = new FreeBoardTO();
		
		//to.setFree_seq(Integer.parseInt(request.getParameter("free_seq")));
		to.setFree_seq(1);
		to = dao.freeModify(to);
		
		JSONObject freeModifyObj = new JSONObject();
		freeModifyObj.put("free_seq",to.getFree_seq());
		freeModifyObj.put("free_writer_seq", to.getFree_writer_seq());
		freeModifyObj.put("free_writer", to.getFree_writer());
		freeModifyObj.put("free_subject", to.getFree_subject());
		freeModifyObj.put("free_content", to.getFree_content());
		freeModifyObj.put("free_hit", to.getFree_hit());
		freeModifyObj.put("free_reg_date", to.getFree_reg_date().toString());
		freeModifyObj.put("free_smoke_years", to.getFree_smoke_years().toString());
		freeModifyObj.put("free_cmt_count", to.getFree_cmt_count());
		
		mav.addObject("freeModifyObj", freeModifyObj);
		mav.setViewName("freeViews/freeModify");
		
		return mav;
	}
	
	@RequestMapping(value = "/free/modify_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView freeModify_Ok(HttpServletRequest request, HttpServletResponse response, MultipartFile upload) {
		ModelAndView mav = new ModelAndView();
		FreeBoardTO to = new FreeBoardTO();

			to.setFree_seq(Integer.parseInt(request.getParameter("free_seq")));
			to.setFree_subject(request.getParameter("free_subject"));
			to.setFree_content(request.getParameter("free_content"));
			
		
		int flag = dao.freeModifyOk(to);
		mav.addObject("flag", flag);

		mav.setViewName("freeViews/freeModify_ok");		
		return mav;	
	}
	
	@RequestMapping(value = "/free/delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView freeDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		FreeBoardTO to = new FreeBoardTO();
		
		//to.setFree_seq(Integer.parseInt(request.getParameter("free_seq")));
		to.setFree_seq(14);
		to = dao.freeDelete(to);
//		System.out.println(to.getFree_file_name());
		
		JSONObject freeDeleteObj = new JSONObject();
		freeDeleteObj.put("free_seq",to.getFree_seq());
		freeDeleteObj.put("free_writer_seq", to.getFree_writer_seq());
		freeDeleteObj.put("free_writer", to.getFree_writer());
		freeDeleteObj.put("free_subject", to.getFree_subject());
		freeDeleteObj.put("free_content", to.getFree_content());
		freeDeleteObj.put("free_hit", to.getFree_hit());
		freeDeleteObj.put("free_reg_date", to.getFree_reg_date().toString());
		freeDeleteObj.put("free_smoke_years", to.getFree_smoke_years().toString());
		freeDeleteObj.put("free_cmt_count", to.getFree_cmt_count());
		mav.addObject("freeDeleteObj", freeDeleteObj);
		mav.setViewName("freeViews/freeDelete");
		
		return mav;
	}

	@RequestMapping(value = "/free/delete_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView freedeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		FreeBoardTO to = new FreeBoardTO();
		
		to.setFree_seq(Integer.parseInt(request.getParameter("free_seq")));
		int flag = dao.freeDeleteOk(to);
		
		mav.addObject("flag", flag);			
		mav.setViewName("freeViews/freeDelete_ok");
		
		return mav;
	}
	
	@RequestMapping("free/parent_cmt_write.do")
	public ModelAndView freeParentCmtWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		JSONObject freeCmtWriteObj = new JSONObject();
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_pseq(Integer.parseInt(request.getParameter("free_pseq")));
		to = cmtDAO.freeParentCommentWrite(to);
		freeCmtWriteObj.put("free_cmt_seq", 0);
		freeCmtWriteObj.put("free_pseq", to.getFree_pseq());
		freeCmtWriteObj.put("free_grp", to.getFree_grp()+1);
		freeCmtWriteObj.put("free_grps", 0);
		freeCmtWriteObj.put("free_grpl", 0);
		freeCmtWriteObj.put("free_cmt_writer", (String)session.getAttribute("nickname"));
		
		mav.addObject("freeCmtWriteObj", freeCmtWriteObj);
		mav.setViewName("freeViews/freeCmtWrite");
		return mav;
	}
	
	
	@RequestMapping("free/cmt_write.do")
	public ModelAndView freeCmtWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_cmt_seq(Integer.parseInt(request.getParameter("free_cmt_seq")));
		to = cmtDAO.freeCommentWrite(to);
		JSONObject freeCmtWriteObj = new JSONObject();
		freeCmtWriteObj.put("free_cmt_seq", to.getFree_cmt_seq());
		freeCmtWriteObj.put("free_pseq", to.getFree_pseq());
		freeCmtWriteObj.put("free_grp", to.getFree_grp());
		freeCmtWriteObj.put("free_grps", to.getFree_grps());
		freeCmtWriteObj.put("free_grpl", to.getFree_grpl());
		freeCmtWriteObj.put("free_cmt_writer", (String)session.getAttribute("nickname"));
		mav.addObject("freeCmtWriteObj", freeCmtWriteObj);
		mav.setViewName("freeViews/freeCmtWrite");
		return mav;
	}
	
	@RequestMapping("free/cmt_write_ok.do")
	public ModelAndView freeCmtWriteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		HttpSession session = request.getSession();
		to.setFree_cmt_seq(Integer.parseInt(request.getParameter("free_cmt_seq")));
		to.setFree_pseq(Integer.parseInt(request.getParameter("free_pseq")));
		to.setFree_cmt_writer_seq((int)session.getAttribute("member_seq"));
		to.setFree_grp(Integer.parseInt(request.getParameter("free_grp")));
		to.setFree_grps(Integer.parseInt(request.getParameter("free_grps")));
		to.setFree_grpl(Integer.parseInt(request.getParameter("free_grpl")));
		to.setFree_cmt_writer((String)session.getAttribute("nickname"));
		to.setFree_cmt_content(request.getParameter("free_cmt_content"));
		int flag = cmtDAO.freeCmtWriteOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("freeViews/freeCmtWrite_ok");
		return mav;
	}
	
	@RequestMapping("free/cmt_modify.do")
	public ModelAndView freeCmtModify(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_cmt_seq(Integer.parseInt(request.getParameter("free_cmt_seq")));
		to = cmtDAO.freeCommentModify(to);
		JSONObject freeCmtModifyObj = new JSONObject();
		freeCmtModifyObj.put("free_cmt_seq", to.getFree_cmt_seq());
		freeCmtModifyObj.put("free_pseq", to.getFree_pseq());
		freeCmtModifyObj.put("free_cmt_writer_seq", to.getFree_cmt_writer_seq());
		freeCmtModifyObj.put("free_grp", to.getFree_grp());
		freeCmtModifyObj.put("free_grps", to.getFree_grps());
		freeCmtModifyObj.put("free_grpl", to.getFree_grpl());
		freeCmtModifyObj.put("free_cmt_writer", to.getFree_cmt_writer());
		freeCmtModifyObj.put("free_cmt_content", to.getFree_cmt_content());
		freeCmtModifyObj.put("free_cmt_reg_date", to.getFree_cmt_reg_date());
		mav.addObject("freeCmtModifyObj", freeCmtModifyObj);
		mav.setViewName("freeViews/freeCmtModify");
		return mav;
	}
	
	@RequestMapping("free/cmt_modify_ok.do")
	public ModelAndView freeCmtModifyOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_cmt_seq(Integer.parseInt(request.getParameter("free_cmt_seq")));
		to.setFree_pseq(Integer.parseInt(request.getParameter("free_pseq")));
		to.setFree_cmt_writer_seq(Integer.parseInt(request.getParameter("free_cmt_writer_seq")));
		to.setFree_grp(Integer.parseInt(request.getParameter("free_grp")));
		to.setFree_grps(Integer.parseInt(request.getParameter("free_grps")));
		to.setFree_grpl(Integer.parseInt(request.getParameter("free_grpl")));
		to.setFree_cmt_writer(request.getParameter("free_cmt_writer"));
		to.setFree_cmt_content(request.getParameter("free_cmt_content"));
		to.setFree_cmt_reg_date(Date.valueOf(request.getParameter("free_cmt_reg_date")));
		int flag = cmtDAO.freeCmtWriteOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("freeViews/freeCmtModify_ok");
		return mav;
	}
	
	@RequestMapping("free/cmt_delete.do")
	public ModelAndView freeCmtDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_cmt_seq(Integer.parseInt(request.getParameter("free_cmt_seq")));
		to = cmtDAO.freeCommentDelete(to);
		JSONObject freeCmtDeleteObj = new JSONObject();
		freeCmtDeleteObj.put("free_cmt_seq", to.getFree_cmt_seq());
		freeCmtDeleteObj.put("free_pseq", to.getFree_pseq());
		freeCmtDeleteObj.put("free_cmt_writer_seq", to.getFree_cmt_writer_seq());
		freeCmtDeleteObj.put("free_grp", to.getFree_grp());
		freeCmtDeleteObj.put("free_grps", to.getFree_grps());
		freeCmtDeleteObj.put("free_grpl", to.getFree_grpl());
		freeCmtDeleteObj.put("free_cmt_writer", to.getFree_cmt_writer());
		freeCmtDeleteObj.put("free_cmt_content", to.getFree_cmt_content());
		freeCmtDeleteObj.put("free_cmt_reg_date", to.getFree_cmt_reg_date());
		mav.addObject("freeCmtDeleteObj", freeCmtDeleteObj);
		mav.setViewName("freeViews/freeCmtDelete");
		return mav;
	}
	
	@RequestMapping("free/cmt_delete_ok.do")
	public ModelAndView freeCmtDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_cmt_seq(Integer.parseInt(request.getParameter("free_cmt_seq")));
		int flag = cmtDAO.freeCommentDeleteOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("freeViews/freeCmtDelete_ok");
		return mav;
	}
}
