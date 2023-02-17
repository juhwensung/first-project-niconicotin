package com.project.gongji.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.gongji.dao.GongjiCommentDAO;
import com.project.gongji.dao.GongjiDAO;
import com.project.gongji.to.GongjiCommentTO;
import com.project.gongji.to.GongjiTO;

@RestController
public class GongjiAPIController {
	
	@Autowired
	private GongjiDAO dao;
	@Autowired
	private GongjiCommentDAO cmtDAO;
	
	private String filePath = System.getProperty("user.dir") + "/src/main/webapp/uploads/gongjiUpload/";
	private String backupFilePath = System.getProperty("user.dir") + "/src/main/webapp/uploads/gongjiUpload/gongjiBackup/";

//	
//	@RequestMapping(value = "api/gongjiLists.do", method = {RequestMethod.GET, RequestMethod.POST})
//	public JSONArray gongjiList(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
//
//		ArrayList<GongjiTO> listTO = dao.gongjiList();
//		JSONArray gongjiLists = new JSONArray();
//		for(GongjiTO to2 : listTO) {
//			JSONObject obj = new JSONObject();
//			obj.put("gongji_seq", to2.getGongji_seq());
//			obj.put("gongji_writer_seq", to2.getGongji_writer_seq());
//			obj.put("testSub", to2.getGongji_subject());
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
//		//LJson.put("gongjiLists", gongjiLists);
//		//System.out.println(LJson);
//		return gongjiLists;
//	}
//	
	@RequestMapping(value = "api/gongjiLists.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONArray gongjiList(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		String page = (String)(paramMap.get("_page"));
		String limit = (String)(paramMap.get("_limit"));
		
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
			//obj.put("gongji_smoke_years", to2.getGongji_smoke_years().toString());
			
			gongjiLists.add(obj);
		}
		//JSONObject obj = new JSONObject();
		//obj.put("page", page);
		//obj.put("limit", limit);
		//obj.put("gongjiLists", gongjiLists);
		//LJson.put("gongjiLists", gongjiLists);
		//System.out.println(LJson);
		return gongjiLists;
	}
	
	@RequestMapping(value = "api/gongjiView.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiView(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		GongjiTO to = new GongjiTO();
		//to.setGongji_cigar_seq(Integer.parseInt((String)(paramMap.get("gongji_cigar_seq")));
		//to.setGongji_seq(Integer.parseInt((String)(paramMap.get("gongji_seq"))));
		to.setGongji_seq(Integer.parseInt(request.getParameter("gongji_seq")));
		//HttpSession session = request.getSession();
		//System.out.println((int)session.getAttribute("member_seq"));
		to = dao.gongjiView(to);
		JSONObject gongjiViewObj = new JSONObject();
		gongjiViewObj.put("gongji_seq", to.getGongji_seq());
		gongjiViewObj.put("gongji_writer_seq", to.getGongji_writer_seq());
		gongjiViewObj.put("gongji_suject", to.getGongji_subject());
		gongjiViewObj.put("gongji_writer", to.getGongji_writer());
		gongjiViewObj.put("gongji_reg_date", to.getGongji_reg_date().toString());
		gongjiViewObj.put("gongji_content", to.getGongji_content());
		gongjiViewObj.put("gongji_hit", to.getGongji_hit());
		gongjiViewObj.put("gongji_cmt_count", to.getGongji_cmt_count());
		gongjiViewObj.put("gongji_smoke_years", to.getGongji_smoke_years().toString());
		
		return gongjiViewObj;
	}

	@RequestMapping(value = "api/gongji_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void gongjiWrite(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
	}
	
	@RequestMapping(value = "api/gongji_write_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiWriteOk(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody Map<String,Object> paramMap) {
		HttpSession session = request.getSession();
		//System.out.println("확인 : " + (String)(paramMap.get("gongji_subject")));
		//System.out.println("session : " + session.getAttribute("member_seq"));
		GongjiTO to = new GongjiTO();
		

			to.setGongji_writer_seq((int)session.getAttribute("member_seq"));
			to.setGongji_subject((String)(paramMap.get("title")));
			to.setGongji_writer((String)session.getAttribute("nickname"));
			to.setGongji_content((String)(paramMap.get("body")));
			to.setGongji_smoke_years((Date)session.getAttribute("createdAt"));


		int flag = dao.gongjiWriteOk(to);
		JSONObject gongjiWriteOk = new JSONObject();
		gongjiWriteOk.put("flag", flag);
		return gongjiWriteOk;
	}
	
	@RequestMapping(value = "api/gongji_modify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiModify(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		
		GongjiTO to = new GongjiTO();
		to.setGongji_seq(Integer.parseInt((String)(paramMap.get("gongji_seq"))));
		to = dao.gongjiModify(to);
		JSONObject gongjiModifyObj = new JSONObject();
		gongjiModifyObj.put("gongji_seq", to.getGongji_seq());
		gongjiModifyObj.put("gongji_writer_seq", to.getGongji_writer_seq());
		gongjiModifyObj.put("gongji_suject", to.getGongji_subject());
		gongjiModifyObj.put("gongji_writer", to.getGongji_writer());
		gongjiModifyObj.put("gongji_reg_date", to.getGongji_reg_date().toString());
		gongjiModifyObj.put("gongji_content", to.getGongji_content());
		gongjiModifyObj.put("gongji_hit", to.getGongji_hit());
		gongjiModifyObj.put("gongji_cmt_count", to.getGongji_cmt_count());
		gongjiModifyObj.put("gongji_smoke_years", to.getGongji_smoke_years().toString());

		return gongjiModifyObj;
	}
	
	@RequestMapping(value = "/api/gongji_modify_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiModifyOk(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody Map<String,Object> paramMap, @RequestParam MultipartFile upload) {
		//System.out.println((String)(paramMap.get("gongji_subject"));
		//System.out.println(paramMap.get("gongji_seq"));
		//System.out.println(paramMap.get("gongji_subject"));
		//System.out.println(paramMap.get("gongji_content"));
		GongjiTO to = new GongjiTO();

			to.setGongji_seq(Integer.valueOf((String)paramMap.get("gongji_seq")));
			to.setGongji_subject((String)(paramMap.get("gongji_subject")));
			to.setGongji_content((String)(paramMap.get("gongji_content")));
			

		
		int flag = dao.gongjiModifyOk(to);
		JSONObject gongjiModifyOk = new JSONObject();
		gongjiModifyOk.put("flag", flag);
		return gongjiModifyOk;
	}
	
	@RequestMapping(value = "api/gongji_delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		GongjiTO to = new GongjiTO();
		to.setGongji_seq(Integer.parseInt((String)(paramMap.get("gongji_seq"))));
		to = dao.gongjiDelete(to);
		JSONObject gongjiDeleteObj = new JSONObject();
		gongjiDeleteObj.put("gongji_seq", to.getGongji_seq());
		gongjiDeleteObj.put("gongji_writer_seq", to.getGongji_writer_seq());
		gongjiDeleteObj.put("gongji_suject", to.getGongji_subject());
		gongjiDeleteObj.put("gongji_writer", to.getGongji_writer());
		gongjiDeleteObj.put("gongji_reg_date", to.getGongji_reg_date().toString());
		gongjiDeleteObj.put("gongji_content", to.getGongji_content());
		gongjiDeleteObj.put("gongji_hit", to.getGongji_hit());
		gongjiDeleteObj.put("gongji_cmt_count", to.getGongji_cmt_count());
		gongjiDeleteObj.put("gongji_smoke_years", to.getGongji_smoke_years().toString());

		return gongjiDeleteObj;
	}
	
	@RequestMapping(value = "api/gongji_delete_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjideleteOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		HttpSession session = request.getSession();
		GongjiTO to = new GongjiTO();
		to.setGongji_seq(Integer.parseInt((String)(paramMap.get("gongji_seq"))));
		to.setGongji_writer_seq((int)session.getAttribute("member_seq"));
		
		int flag = dao.gongjiDeleteOk(to);
		JSONObject gongjideleteOk = new JSONObject();
		gongjideleteOk.put("flag", flag);
		return gongjideleteOk;
	}
	
	@RequestMapping(value = "api/gongjiCommentLists.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public JSONArray gongjiCommentList(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		JSONArray gongjiCommentLists = new JSONArray();
		GongjiCommentTO cmtTO = new GongjiCommentTO();
		cmtTO.setGongji_pseq(Integer.parseInt((String)(paramMap.get("gongji_pseq"))));
		ArrayList<GongjiCommentTO> listCmtTO = cmtDAO.gongjiCommentList(cmtTO);
		for(GongjiCommentTO cmtTO2 : listCmtTO) {
			JSONObject obj = new JSONObject();
			obj.put("gongji_cmt_seq", cmtTO2.getGongji_cmt_seq());
			obj.put("gongji_pseq", cmtTO2.getGongji_pseq());
			obj.put("gongji_cmt_writer_seq", cmtTO2.getGongji_cmt_writer_seq());
			obj.put("gongji_grp", cmtTO2.getGongji_grp());
			obj.put("gongji_grps", cmtTO2.getGongji_grps());
			obj.put("gongji_grpl", cmtTO2.getGongji_grpl());
			obj.put("gongji_cmt_writer", cmtTO2.getGongji_cmt_writer());
			obj.put("gongji_cmt_content", cmtTO2.getGongji_cmt_content());
			obj.put("gongji_cmt_reg_date", cmtTO2.getGongji_cmt_reg_date().toString());
			
			gongjiCommentLists.add(obj);
		}
		
		return gongjiCommentLists;
	}
	
	@RequestMapping(value = "api/gongji_parent_cmt_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiParentCmtWrite(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		HttpSession session = request.getSession();
		JSONObject gongjiCmtWriteObj = new JSONObject();
		GongjiCommentTO to = new GongjiCommentTO();
		to.setGongji_pseq(Integer.parseInt((String)(paramMap.get("gongji_pseq"))));
		to = cmtDAO.gongjiParentCommentWrite(to);
		gongjiCmtWriteObj.put("gongji_cmt_seq", 0);
		gongjiCmtWriteObj.put("gongji_pseq", to.getGongji_pseq());
		gongjiCmtWriteObj.put("gongji_grp", to.getGongji_grp()+1);
		gongjiCmtWriteObj.put("gongji_grps", 0);
		gongjiCmtWriteObj.put("gongji_grpl", 0);
		gongjiCmtWriteObj.put("gongji_cmt_writer", (String)session.getAttribute("nickname"));
		
		return gongjiCmtWriteObj;
	}
	
	
	@RequestMapping(value = "api/gongji_cmt_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiCmtWrite(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		HttpSession session = request.getSession();
		GongjiCommentTO to = new GongjiCommentTO();
		to.setGongji_cmt_seq(Integer.parseInt((String)(paramMap.get("gongji_cmt_seq"))));
		to = cmtDAO.gongjiCommentWrite(to);
		JSONObject gongjiCmtWriteObj = new JSONObject();
		gongjiCmtWriteObj.put("gongji_cmt_seq", to.getGongji_cmt_seq());
		gongjiCmtWriteObj.put("gongji_pseq", to.getGongji_pseq());
		gongjiCmtWriteObj.put("gongji_grp", to.getGongji_grp());
		gongjiCmtWriteObj.put("gongji_grps", to.getGongji_grps());
		gongjiCmtWriteObj.put("gongji_grpl", to.getGongji_grpl());
		gongjiCmtWriteObj.put("gongji_cmt_writer", (String)session.getAttribute("nickname"));

		return gongjiCmtWriteObj;
	}
	
	@RequestMapping(value = "api/gongji_cmt_write_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiCmtWriteOk(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		 
		GongjiCommentTO to = new GongjiCommentTO();
		HttpSession session = request.getSession();
		to.setGongji_cmt_seq(Integer.parseInt((String)(paramMap.get("gongji_cmt_seq"))));
		to.setGongji_pseq(Integer.parseInt((String)(paramMap.get("gongji_pseq"))));
		to.setGongji_cmt_writer_seq((int)session.getAttribute("member_seq"));
		to.setGongji_grp(0);
		to.setGongji_grps(0);
		to.setGongji_grpl(0);
		to.setGongji_cmt_writer((String)session.getAttribute("nickname"));
		to.setGongji_cmt_content((String)(paramMap.get("gongji_cmt_content")));
		int flag = cmtDAO.gongjiCmtWriteOk(to);
		JSONObject gongjiCmtWriteOk = new JSONObject();
		gongjiCmtWriteOk.put("flag", flag);
		return gongjiCmtWriteOk;
	}
	
	@RequestMapping(value = "api/gongji_cmt_modify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiCmtModify(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		GongjiCommentTO to = new GongjiCommentTO();
		to.setGongji_cmt_seq(Integer.parseInt((String)(paramMap.get("gongji_cmt_seq"))));
		to = cmtDAO.gongjiCommentModify(to);
		JSONObject gongjiCmtModifyObj = new JSONObject();
		gongjiCmtModifyObj.put("gongji_cmt_seq", to.getGongji_cmt_seq());
		gongjiCmtModifyObj.put("gongji_pseq", to.getGongji_pseq());
		gongjiCmtModifyObj.put("gongji_cmt_writer_seq", to.getGongji_cmt_writer_seq());
		gongjiCmtModifyObj.put("gongji_grp", to.getGongji_grp());
		gongjiCmtModifyObj.put("gongji_grps", to.getGongji_grps());
		gongjiCmtModifyObj.put("gongji_grpl", to.getGongji_grpl());
		gongjiCmtModifyObj.put("gongji_cmt_writer", to.getGongji_cmt_writer());
		gongjiCmtModifyObj.put("gongji_cmt_content", to.getGongji_cmt_content());
		gongjiCmtModifyObj.put("gongji_cmt_reg_date", to.getGongji_cmt_reg_date().toString());

		return gongjiCmtModifyObj;
	}
	
	@RequestMapping(value = "api/gongji_cmt_modify_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiCmtModifyOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		GongjiCommentTO to = new GongjiCommentTO();
		to.setGongji_cmt_seq(Integer.parseInt((String)(paramMap.get("gongji_cmt_seq"))));
		to.setGongji_pseq(Integer.parseInt((String)(paramMap.get("gongji_pseq"))));
		to.setGongji_cmt_content((String)(paramMap.get("gongji_cmt_content")));
		int flag = cmtDAO.gongjiCmtWriteOk(to);
		JSONObject gongjiCmtModifyOk = new JSONObject();
		gongjiCmtModifyOk.put("flag", flag);
		return gongjiCmtModifyOk;
	}
	
	@RequestMapping(value = "api/gongji_cmt_delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiCmtDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		GongjiCommentTO to = new GongjiCommentTO();
		to.setGongji_cmt_seq(Integer.parseInt((String)(paramMap.get("gongji_cmt_seq"))));
		to = cmtDAO.gongjiCommentDelete(to);
		JSONObject gongjiCmtDeleteObj = new JSONObject();
		gongjiCmtDeleteObj.put("gongji_cmt_seq", to.getGongji_cmt_seq());
		gongjiCmtDeleteObj.put("gongji_pseq", to.getGongji_pseq());
		gongjiCmtDeleteObj.put("gongji_cmt_writer_seq", to.getGongji_cmt_writer_seq());
		gongjiCmtDeleteObj.put("gongji_grp", to.getGongji_grp());
		gongjiCmtDeleteObj.put("gongji_grps", to.getGongji_grps());
		gongjiCmtDeleteObj.put("gongji_grpl", to.getGongji_grpl());
		gongjiCmtDeleteObj.put("gongji_cmt_writer", to.getGongji_cmt_writer());
		gongjiCmtDeleteObj.put("gongji_cmt_content", to.getGongji_cmt_content());
		gongjiCmtDeleteObj.put("gongji_cmt_reg_date", to.getGongji_cmt_reg_date().toString());

		return gongjiCmtDeleteObj;
	}
	
	@RequestMapping(value = "api/gongji_cmt_delete_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject gongjiCmtDeleteOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		HttpSession session = request.getSession();
		GongjiCommentTO to = new GongjiCommentTO();
		to.setGongji_cmt_seq(Integer.parseInt((String)(paramMap.get("gongji_cmt_seq"))));
		to.setGongji_cmt_writer_seq((int)session.getAttribute("member_seq"));
		int flag = cmtDAO.gongjiCommentDeleteOk(to);
		JSONObject gongjiCmtDeleteOk = new JSONObject();
		gongjiCmtDeleteOk.put("flag", flag);
		return gongjiCmtDeleteOk;
	}
	
}
