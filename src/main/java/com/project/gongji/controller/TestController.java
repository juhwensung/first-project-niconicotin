package com.project.gongji.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.gongji.dao.GongjiDAO;
import com.project.gongji.to.GongjiCommentTO;
import com.project.gongji.to.GongjiTO;

@RestController
public class TestController {

	@Autowired
	private GongjiDAO dao;
//	
//	@RequestMapping("api/gongjiList.do")
//	public HashMap<String, Object> gongjiList(HttpServletRequest request, HttpServletResponse response, Model model) {
//
//		HashMap<String, Object> testList = new HashMap<String, Object>();
//		testList.put("gongjiLists", dao.gongjiList());
//		
//		
//		model.addAttribute("gongjiLists", testList);
//		System.out.println(testList);
//		return testList;
//	}
//	
//	
//	@RequestMapping("api/gongjiList.do")
//	public HashMap<String, Object> gongjiList(HttpServletRequest request, HttpServletResponse response, Model model) {
//
//		HashMap<String, Object> testList = new HashMap<String, Object>();
//		ArrayList<GongjiTO> listTO = dao.gongjiList();
//		JSONArray gongjiLists = new JSONArray();
//		for(GongjiTO to2 : listTO) {
//			JSONObject obj = new JSONObject();
//			obj.put("gongji_seq", to2.getGongji_seq());
//			obj.put("gongji_writer_seq", to2.getGongji_writer_seq());
//			obj.put("gongji_subject", to2.getGongji_subject());
//			obj.put("gongji_writer", to2.getGongji_writer());
//			obj.put("gongji_reg_date", to2.getGongji_reg_date().toString());
//			obj.put("gongji_content", to2.getGongji_content());
//			obj.put("gongji_hit", to2.getGongji_hit());
//			obj.put("gongji_cmt_count", to2.getGongji_cmt_count());
//			obj.put("gongji_file_name", to2.getGongji_file_name());
//			obj.put("gongji_file_size", to2.getGongji_file_size());
//			obj.put("gongji_smoke_years", to2.getGongji_smoke_years().toString());
//			obj.put("gongji_public", to2.isGongji_public());
//			
//			gongjiLists.add(obj);
//		}
//		
//		testList.put("gongjiLists", gongjiLists);
//		
//		System.out.println(testList);
//		return testList;
//	}
//	
//	@RequestMapping("api/gongjiLists.do")
//	public JSONObject gongjiList(HttpServletRequest request, HttpServletResponse response, Model model) {
//		Map<String, Object> MapList = new HashMap<>();
//		JSONObject LJson = new JSONObject();
//		HashMap<String, Object> testList = new HashMap<String, Object>();
//		ArrayList<GongjiTO> listTO = dao.gongjiList();
//		JSONArray gongjiLists = new JSONArray();
//		for(GongjiTO to2 : listTO) {
//			JSONObject obj = new JSONObject();
//			obj.put("gongji_seq", to2.getGongji_seq());
//			obj.put("gongji_writer_seq", to2.getGongji_writer_seq());
//			obj.put("gongji_subject", to2.getGongji_subject());
//			obj.put("gongji_writer", to2.getGongji_writer());
//			obj.put("gongji_reg_date", to2.getGongji_reg_date().toString());
//			obj.put("gongji_content", to2.getGongji_content());
//			obj.put("gongji_hit", to2.getGongji_hit());
//			obj.put("gongji_cmt_count", to2.getGongji_cmt_count());
//			obj.put("gongji_file_name", to2.getGongji_file_name());
//			obj.put("gongji_file_size", to2.getGongji_file_size());
//			obj.put("gongji_smoke_years", to2.getGongji_smoke_years().toString());
//			obj.put("gongji_public", to2.isGongji_public());
//			
//			gongjiLists.add(obj);
//		}
//		
//		testList.put("gongjiLists", gongjiLists);
//		LJson.put("gongjiLists", gongjiLists);
//		System.out.println(LJson);
//		return LJson;
//	}
	
//	@RequestMapping("api/gongjiList.do")
//	public Map<String, Object> gongjiList(HttpServletRequest request, HttpServletResponse response, Model model) {
//		Map<String, Object> MapList = new HashMap<>();
//		JSONObject LJson = new JSONObject();
//		HashMap<String, Object> testList = new HashMap<String, Object>();
//		ArrayList<GongjiTO> listTO = dao.gongjiList();
//		JSONArray gongjiLists = new JSONArray();
//		for(GongjiTO to2 : listTO) {
//			JSONObject obj = new JSONObject();
//			obj.put("gongji_seq", to2.getGongji_seq());
//			obj.put("gongji_writer_seq", to2.getGongji_writer_seq());
//			obj.put("gongji_subject", to2.getGongji_subject());
//			obj.put("gongji_writer", to2.getGongji_writer());
//			obj.put("gongji_reg_date", to2.getGongji_reg_date().toString());
//			obj.put("gongji_content", to2.getGongji_content());
//			obj.put("gongji_hit", to2.getGongji_hit());
//			obj.put("gongji_cmt_count", to2.getGongji_cmt_count());
//			obj.put("gongji_file_name", to2.getGongji_file_name());
//			obj.put("gongji_file_size", to2.getGongji_file_size());
//			obj.put("gongji_smoke_years", to2.getGongji_smoke_years().toString());
//			obj.put("gongji_public", to2.isGongji_public());
//			
//			gongjiLists.add(obj);
//		}
//		
//		LJson.put("gongjiLists", gongjiLists);
//		testList.put("gongjiLists", gongjiLists);
//		//MapList.put("gongjiLists", gongjiLists);
//		
//		MapList.put("gongjiLists", LJson);
//		System.out.println(MapList);
//		return MapList;
//	}
//	
//	@RequestMapping("api/gongjiList.do")
//	public JSONObject gongjiList(HttpServletRequest request, HttpServletResponse response, Model model) {
//		Map<String, Object> MapList = new HashMap<>();
//		JSONObject LJson = new JSONObject();
//		HashMap<String, Object> testList = new HashMap<String, Object>();
//		ArrayList<GongjiTO> listTO = dao.gongjiList();
//		JSONArray gongjiLists = new JSONArray();
//		for(GongjiTO to2 : listTO) {
//			JSONObject obj = new JSONObject();
//			obj.put("gongji_seq", to2.getGongji_seq());
//			obj.put("gongji_writer_seq", to2.getGongji_writer_seq());
//			obj.put("gongji_subject", to2.getGongji_subject());
//			obj.put("gongji_writer", to2.getGongji_writer());
//			obj.put("gongji_reg_date", to2.getGongji_reg_date().toString());
//			obj.put("gongji_content", to2.getGongji_content());
//			obj.put("gongji_hit", to2.getGongji_hit());
//			obj.put("gongji_cmt_count", to2.getGongji_cmt_count());
//			obj.put("gongji_file_name", to2.getGongji_file_name());
//			obj.put("gongji_file_size", to2.getGongji_file_size());
//			obj.put("gongji_smoke_years", to2.getGongji_smoke_years().toString());
//			obj.put("gongji_public", to2.isGongji_public());
//			
//			gongjiLists.add(obj);
//		}
//		MapList.put("gongjiLists", gongjiLists);
//		LJson.put("gongjiLists", MapList);
//		testList.put("gongjiLists", gongjiLists);
//		//MapList.put("gongjiLists", gongjiLists);
//		
//		
//		System.out.println(LJson);
//		return LJson;
//	}
//	
	
	//@RequestMapping("api/gongjiLists.do")
	public JSONArray gongjiList(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> MapList = new HashMap<>();
		JSONObject LJson = new JSONObject();
		HashMap<String, Object> testList = new HashMap<String, Object>();
		ArrayList<GongjiTO> listTO = dao.gongjiList();
		JSONArray gongjiLists = new JSONArray();
		for(GongjiTO to2 : listTO) {
			JSONObject obj = new JSONObject();
			obj.put("gongji_seq", to2.getGongji_seq());
			obj.put("gongji_writer_seq", to2.getGongji_writer_seq());
			obj.put("gongji_subject", to2.getGongji_subject());
			obj.put("gongji_writer", to2.getGongji_writer());
			obj.put("gongji_reg_date", to2.getGongji_reg_date().toString());
			obj.put("gongji_content", to2.getGongji_content());
			obj.put("gongji_hit", to2.getGongji_hit());
			obj.put("gongji_cmt_count", to2.getGongji_cmt_count());
			obj.put("gongji_file_name", to2.getGongji_file_name());
			obj.put("gongji_file_size", to2.getGongji_file_size());
			obj.put("gongji_smoke_years", to2.getGongji_smoke_years().toString());
			obj.put("gongji_public", to2.isGongji_public());
			
			gongjiLists.add(obj);
		}
		
		testList.put("gongjiLists", gongjiLists);
		LJson.put("gongjiLists", gongjiLists);
		System.out.println(LJson);
		return gongjiLists;
	}
	
	//@RequestMapping("api/view.do")
	public JSONObject gongjiView(HttpServletRequest request, HttpServletResponse response) {
		GongjiTO to = new GongjiTO();
		//to.setGongji_cigar_seq(Integer.parseInt(request.getParameter("gongji_cigar_seq")));
		to.setGongji_seq(Integer.parseInt(request.getParameter("gongji_seq")));
		//HttpSession session = request.getSession();
		//System.out.println((int)session.getAttribute("member_seq"));
		to = dao.gongjiView(to);
		JSONObject gongjiViewObj = new JSONObject();
		gongjiViewObj.put("gongji_seq", to.getGongji_seq());
		gongjiViewObj.put("gongji_writer_seq", to.getGongji_writer_seq());
		gongjiViewObj.put("gongji_suject", to.getGongji_subject());
		gongjiViewObj.put("gongji_writer", to.getGongji_writer());
		gongjiViewObj.put("gongji_reg_date", to.getGongji_reg_date());
		gongjiViewObj.put("gongji_content", to.getGongji_content());
		gongjiViewObj.put("gongji_hit", to.getGongji_hit());
		gongjiViewObj.put("gongji_cmt_count", to.getGongji_cmt_count());
		gongjiViewObj.put("gongji_file_name", to.getGongji_file_name());
		gongjiViewObj.put("gongji_file_size", to.getGongji_file_size());
		gongjiViewObj.put("gongji_smoke_years", to.getGongji_smoke_years());
		gongjiViewObj.put("gongji_public", to.isGongji_public());
		// ?gongji_seq=1
		System.out.println(gongjiViewObj);
		return gongjiViewObj;
	}
	
	/*
	ArrayList<GongjiCommentTO> CommentListTO = cmtDAO.gongjiCommentList(cmtTO);
		JSONArray gongjiCommentLists = new JSONArray();
		for(GongjiCommentTO cmtTO2 : CommentListTO) {
			JSONObject obj = new JSONObject();
			obj.put("gongji_cmt_seq", cmtTO2.getGongji_cmt_seq());
			obj.put("gongji_pseq", cmtTO2.getGongji_pseq());
			obj.put("gongji_cmt_writer_seq", cmtTO2.getGongji_cmt_writer_seq());
			obj.put("gongji_grp", cmtTO2.getGongji_grp());
			obj.put("gongji_grps", cmtTO2.getGongji_grps());
			obj.put("gongji_grpl", cmtTO2.getGongji_grpl());
			obj.put("gongji_cmt_writer", cmtTO2.getGongji_cmt_writer());
			obj.put("gongji_cmt_content", cmtTO2.getGongji_cmt_content());
			obj.put("gongji_cmt_reg_date", cmtTO2.getGongji_cmt_reg_date());
			
			gongjiCommentLists.add(obj);
		}
		 
	*/
}

























