package com.project.free_board.controller;

import java.io.File;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.free_board.dao.FreeBoardCommentDAO;
import com.project.free_board.dao.FreeBoardDAO;
import com.project.free_board.to.FreeBoardCommentTO;
import com.project.free_board.to.FreeBoardTO;
import com.project.gongji.to.GongjiCommentTO;

@RestController
public class FreeBoardAPIController {
	
	@Autowired
	private FreeBoardDAO dao;
	@Autowired
	private FreeBoardCommentDAO cmtDAO;
	
	private String filePath = System.getProperty("user.dir") + "/src/main/webapp/uploads/freeUpload/";
	private String backupFilePath = System.getProperty("user.dir") + "/src/main/webapp/uploads/freeUpload/freeBackup/";
	
	@RequestMapping(value = "api/freeLists.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONArray freeList(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		//String page = (String)(paramMap.get("_page"));
		//String limit = (String)(paramMap.get("_limit"));
		ArrayList<FreeBoardTO> listTO = dao.freeList();
		JSONArray freeLists = new JSONArray();
		
		for(FreeBoardTO to : listTO) {
			JSONObject obj = new JSONObject();
			obj.put("free_seq",to.getFree_seq());
			obj.put("free_writer_seq", to.getFree_writer_seq());
			obj.put("free_writer", to.getFree_writer());
			obj.put("free_subject", to.getFree_subject());
			obj.put("free_content", to.getFree_content());
			obj.put("free_hit", to.getFree_hit());
			obj.put("free_reg_date", to.getFree_reg_date().toString());
			obj.put("free_smoke_years", to.getFree_smoke_years().toString());
			obj.put("free_cmt_hit", to.getFree_cmt_count());
			obj.put("free_file_name", to.getFree_file_name());
			obj.put("free_file_size", to.getFree_file_size());
			obj.put("free_public",to.isFree_public());
			
			freeLists.add(obj);
		}
//		JSONObject obj = new JSONObject();
//		obj.put("_page", page);
//		obj.put("_limit", limit);
		
		//obj.put("freeLists", freeLists);
		
		return freeLists;
	}
	
	@RequestMapping(value = "api/freeView.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public JSONObject freeView(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		 
		FreeBoardTO to = new FreeBoardTO();
		//to.setFree_seq(Integer.parseInt((String)(paramMap.get("free_seq"))));
		//System.out.println(Integer.parseInt((String)(paramMap.get("free_seq"))));
		//to.setFree_seq(1);
		to.setFree_seq(Integer.parseInt(request.getParameter("free_seq")));
		
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
		freeViewObj.put("free_cmt_hit", to.getFree_cmt_count());
		freeViewObj.put("free_file_name", to.getFree_file_name());
		freeViewObj.put("free_file_size", to.getFree_file_size());
		freeViewObj.put("free_public",to.isFree_public());
		
		return freeViewObj;
		
	}
	
	@RequestMapping(value = "/api/free_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void freeWrite(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		 
	}
	
	@RequestMapping(value = "/api/free_write_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeWriteOk(HttpServletRequest request, HttpServletResponse response, 
			MultipartFile upload, @RequestBody Map<String,Object> paramMap) {
		 
		HttpSession session = request.getSession();
		FreeBoardTO to = new FreeBoardTO();
		
		to.setFree_writer_seq((int)session.getAttribute("member_seq"));
		to.setFree_writer((String)session.getAttribute("nickname"));
		to.setFree_subject((String)(paramMap.get("title")));
		to.setFree_content((String)(paramMap.get("body")));
		to.setFree_smoke_years((Date)session.getAttribute("smoke_years"));
		to.setFree_public((boolean)paramMap.get("free_public"));
		
		//System.out.println(request.getAttribute("free_public_true"));
		try {
			
			if( !upload.isEmpty() ) {
				String extention = upload.getOriginalFilename().substring(upload.getOriginalFilename().indexOf("."));
				to.setFree_file_name(UUID.randomUUID().toString() + extention);
				to.setFree_file_size((int)upload.getSize());
				byte[] bytes = upload.getBytes();
				Path path = Paths.get((filePath + to.getFree_file_name()).trim());
		        Files.write(path, bytes);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[IO 에러] : " + e.getMessage());
		}
		
		int flag = dao.freeWrite_Ok(to);
		JSONObject freeWriteOk = new JSONObject();
		freeWriteOk.put("flag", flag);
		return freeWriteOk;
	}
	
	@RequestMapping(value = "/api/free_modify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeModify(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		 
		FreeBoardTO to = new FreeBoardTO();
		
		//to.setFree_seq(Integer.parseInt((String)(paramMap.get("free_seq")));
		to.setFree_seq(Integer.parseInt((String)(paramMap.get("free_seq"))));
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
		freeModifyObj.put("free_file_name", to.getFree_file_name());
		freeModifyObj.put("free_file_size", to.getFree_file_size());
		freeModifyObj.put("free_public", to.isFree_public());
		
		return freeModifyObj;
	}
	
	@RequestMapping(value = "/api/free_modify_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeModifyOk(HttpServletRequest request, HttpServletResponse response, MultipartFile upload, @RequestParam Map<String,Object> paramMap) {
		 
		FreeBoardTO to = new FreeBoardTO();
		String oldfilename = (String)(paramMap.get("free_file_name"));
		
		try {
			to.setFree_seq(Integer.parseInt((String)(paramMap.get("free_seq"))));
			to.setFree_subject((String)(paramMap.get("free_subject")));
			to.setFree_content((String)(paramMap.get("free_content")));
			to.setFree_public((boolean)paramMap.get("free_public"));
			if( !upload.isEmpty() ) {
				byte[] bytes = upload.getBytes();
				String extention = upload.getOriginalFilename().substring(upload.getOriginalFilename().indexOf("."));
				to.setFree_file_name(UUID.randomUUID().toString() + extention);
				to.setFree_file_size((int) upload.getSize());
				
			    Path targetPath = Paths.get(filePath.trim() + oldfilename.trim());
			    Path backuppath = Paths.get(backupFilePath.trim() + oldfilename.trim());			
				Path path = Paths.get(filePath.trim() + to.getFree_file_name().trim());
				
				Files.write(path, bytes);
			    Files.copy(targetPath, backuppath);;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] : " + e.getMessage());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] : " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] : " + e.getMessage());
		}
		
		int flag = dao.freeModifyOk(to, oldfilename);
		JSONObject freeModifyOk = new JSONObject();
		freeModifyOk.put("flag", flag);
		return freeModifyOk;
	}
	
	@RequestMapping(value = "/api/free_delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		 
		FreeBoardTO to = new FreeBoardTO();
		
		to.setFree_seq(Integer.parseInt((String)(paramMap.get("free_seq"))));
		//to.setFree_seq(14);
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
		freeDeleteObj.put("free_file_name", to.getFree_file_name());
		freeDeleteObj.put("free_file_size", to.getFree_file_size());
		
		return freeDeleteObj;
	}

	@RequestMapping(value = "/api/free_delete_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeDeleteOk(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		HttpSession session = request.getSession();
		FreeBoardTO to = new FreeBoardTO();
		
		to.setFree_seq(Integer.parseInt((String)(paramMap.get("free_seq"))));
		to.setFree_file_name((String)(paramMap.get("free_file_name")));
		to.setFree_writer_seq((int)session.getAttribute("member_seq"));
		try {
			to = dao.freeDelete(to);
			Path targetPath = Paths.get(filePath.trim() + to.getFree_file_name());
			Path backuppath = Paths.get(backupFilePath.trim() + to.getFree_file_name());		
			Files.copy(targetPath, backuppath);
			System.out.println(to.getFree_file_name());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		to.setFree_file_name(to.getFree_file_name());
		int flag = dao.freeDeleteOk(to);
		JSONObject freedeleteOk = new JSONObject();
		freedeleteOk.put("flag", flag);
		return freedeleteOk;
	}
	
	@RequestMapping(value = "api/freeSearch", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONArray freeSearch(@RequestParam Map<String, Object> paramMap ,HttpServletRequest request, HttpServletResponse response){
		FreeBoardTO to = new FreeBoardTO();
//		to.setFree_subject(request.getParameter("free_subject"));
//		to.setFree_content(request.getParameter("free_content"));
		
		//to.setFree_subject("공개글");	
		//to.setFree_content("1");
		to.setFree_content((String)paramMap.get("Free_content"));
		to.setFree_writer((String)paramMap.get("Free_writer"));
		to.setFree_subject((String)paramMap.get("Free_subject"));
		ArrayList<FreeBoardTO> freeSearchLists = dao.freeSearch(to);
		JSONArray freeSearchArray = new JSONArray();
		
		for(FreeBoardTO to2 : freeSearchLists) {
			JSONObject obj = new JSONObject();
			obj.put("free_seq", to2.getFree_seq());
			obj.put("free_writer_seq", to2.getFree_writer_seq());
			obj.put("free_subject", to2.getFree_subject());
			obj.put("free_writer", to2.getFree_writer());
			obj.put("free_content", to2.getFree_content());
			obj.put("free_hit", to2.getFree_hit());
			obj.put("free_cmt_count", to2.getFree_cmt_count());
			obj.put("free_file_size", to2.getFree_file_size());
			obj.put("free_file_name", to2.getFree_file_name());
			obj.put("free_smoke_years", to2.getFree_smoke_years());
			obj.put("free_public", to2.isFree_public());
			obj.put("free_reg_date", to2.getFree_reg_date());
			freeSearchArray.add(obj);
		}
		
		JSONObject freeSearch = new JSONObject();
		freeSearch.put("freeSearch", freeSearchArray);
		System.out.println(freeSearchArray);
		
		return freeSearchArray;
	}
	
	@RequestMapping(value = "api/freeCommentLists.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public JSONArray freeCommentList(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		JSONArray freeCommentLists = new JSONArray();
		FreeBoardCommentTO cmtTO = new FreeBoardCommentTO();
		cmtTO.setFree_pseq(Integer.parseInt((String)(paramMap.get("gongji_pseq"))));
		ArrayList<FreeBoardCommentTO> CommentListTO = cmtDAO.freeCommentList(cmtTO);
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
			obj.put("free_cmt_reg_date", cmtTO2.getFree_cmt_reg_date().toString());
			
			freeCommentLists.add(obj);
		}
		
		return freeCommentLists;
		
	}
	
	@RequestMapping(value = "/api/free_parent_cmt_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeParentCmtWrite(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		 
		HttpSession session = request.getSession();
		JSONObject freeCmtWriteObj = new JSONObject();
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_pseq(Integer.parseInt((String)(paramMap.get("free_pseq"))));
		to = cmtDAO.freeParentCommentWrite(to);
		freeCmtWriteObj.put("free_cmt_seq", 0);
		freeCmtWriteObj.put("free_pseq", to.getFree_pseq());
		freeCmtWriteObj.put("free_grp", to.getFree_grp()+1);
		freeCmtWriteObj.put("free_grps", 0);
		freeCmtWriteObj.put("free_grpl", 0);
		freeCmtWriteObj.put("free_cmt_writer", (String)session.getAttribute("nickname"));
		
		return freeCmtWriteObj;
	}
	
	
	@RequestMapping(value = "/api/free_cmt_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeCmtWrite(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		 
		HttpSession session = request.getSession();
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_cmt_seq(Integer.parseInt((String)(paramMap.get("free_cmt_seq"))));
		to = cmtDAO.freeCommentWrite(to);
		JSONObject freeCmtWriteObj = new JSONObject();
		freeCmtWriteObj.put("free_cmt_seq", to.getFree_cmt_seq());
		freeCmtWriteObj.put("free_pseq", to.getFree_pseq());
		freeCmtWriteObj.put("free_grp", to.getFree_grp());
		freeCmtWriteObj.put("free_grps", to.getFree_grps());
		freeCmtWriteObj.put("free_grpl", to.getFree_grpl());
		freeCmtWriteObj.put("free_cmt_writer", (String)session.getAttribute("nickname"));

		return freeCmtWriteObj;
	}
	
	@RequestMapping(value = "/api/free_cmt_write_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeCmtWriteOk(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		 
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		HttpSession session = request.getSession();
		to.setFree_cmt_seq(Integer.parseInt((String)(paramMap.get("free_cmt_seq"))));
		to.setFree_pseq(Integer.parseInt((String)(paramMap.get("free_pseq"))));
		to.setFree_cmt_writer_seq((int)session.getAttribute("member_seq"));
		to.setFree_grp(Integer.parseInt((String)(paramMap.get("free_grp"))));
		to.setFree_grps(Integer.parseInt((String)(paramMap.get("free_grps"))));
		to.setFree_grpl(Integer.parseInt((String)(paramMap.get("free_grpl"))));
		to.setFree_cmt_writer((String)session.getAttribute("nickname"));
		to.setFree_cmt_content((String)(paramMap.get("free_cmt_content")));
		int flag = cmtDAO.freeCmtWriteOk(to);
		JSONObject freeCmtWriteOk = new JSONObject();
		freeCmtWriteOk.put("flag", flag);
		return freeCmtWriteOk;
	}
	
	@RequestMapping(value = "/api/free_cmt_modify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeCmtModify(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		 
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_cmt_seq(Integer.parseInt((String)(paramMap.get("free_cmt_seq"))));
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
		freeCmtModifyObj.put("free_cmt_reg_date", to.getFree_cmt_reg_date().toString());
		
		return freeCmtModifyObj;
	}
	
	@RequestMapping(value = "/api/free_cmt_modify_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeCmtModifyOk(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		 
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_cmt_seq(Integer.parseInt((String)(paramMap.get("free_cmt_seq"))));
		to.setFree_pseq(Integer.parseInt((String)(paramMap.get("free_pseq"))));
		to.setFree_cmt_writer_seq(Integer.parseInt((String)(paramMap.get("free_cmt_writer_seq"))));
		to.setFree_grp(Integer.parseInt((String)(paramMap.get("free_grp"))));
		to.setFree_grps(Integer.parseInt((String)(paramMap.get("free_grps"))));
		to.setFree_grpl(Integer.parseInt((String)(paramMap.get("free_grpl"))));
		to.setFree_cmt_writer((String)(paramMap.get("free_cmt_writer")));
		to.setFree_cmt_content((String)(paramMap.get("free_cmt_content")));
		to.setFree_cmt_reg_date(Date.valueOf((String)(paramMap.get("free_cmt_reg_date"))));
		int flag = cmtDAO.freeCmtWriteOk(to);
		JSONObject freeCmtModifyOk = new JSONObject();
		freeCmtModifyOk.put("flag", flag);
		return freeCmtModifyOk;
	}
	
	@RequestMapping(value = "/api/free_cmt_delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeCmtDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		 
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_cmt_seq(Integer.parseInt((String)(paramMap.get("free_cmt_seq"))));
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
		freeCmtDeleteObj.put("free_cmt_reg_date", to.getFree_cmt_reg_date().toString());

		return freeCmtDeleteObj;
	}
	
	@RequestMapping(value = "/api/free_cmt_delete_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject freeCmtDeleteOk(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		HttpSession session = request.getSession();
		FreeBoardCommentTO to = new FreeBoardCommentTO();
		to.setFree_cmt_seq(Integer.parseInt((String)(paramMap.get("free_cmt_seq"))));
		to.setFree_cmt_writer_seq((int)session.getAttribute("member_seq"));
		int flag = cmtDAO.freeCommentDeleteOk(to);
		JSONObject freeCmtDeleteOk = new JSONObject();
		freeCmtDeleteOk.put("flag", flag);
		return freeCmtDeleteOk;
	}
}
