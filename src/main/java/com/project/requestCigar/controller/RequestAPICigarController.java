package com.project.requestCigar.controller;

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

import com.project.requestCigar.dao.RequestCigarCommentDAO;
import com.project.requestCigar.dao.RequestCigarDAO;
import com.project.requestCigar.to.RequestCigarCommentTO;
import com.project.requestCigar.to.RequestCigarTO;

@RestController
public class RequestAPICigarController {
	
	@Autowired
	private RequestCigarDAO dao;
	
	@Autowired
	private RequestCigarCommentDAO cmtDAO;
	
	private String filePath = System.getProperty("user.dir") + "/src/main/webapp/uploads/requestUpload/";
	private String backupFilePath = System.getProperty("user.dir") + "/src/main/webapp/uploads/requestUpload/requestBackup/";
	
	@RequestMapping(value = "api/requestLists.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONArray requestList(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		
		ArrayList<RequestCigarTO> listTO = dao.requestList();
		JSONArray requestLists = new JSONArray();
		for(RequestCigarTO to : listTO) {
			JSONObject obj = new JSONObject();
			obj.put("request_seq", to.getRequest_seq());
			obj.put("request_writer_seq", to.getRequest_writer_seq());
			obj.put("request_suject", to.getRequest_subject());
			obj.put("request_writer", to.getRequest_writer());
			obj.put("request_reg_date", to.getRequest_reg_date().toString());
			obj.put("request_content", to.getRequest_content());
			obj.put("request_hit", to.getRequest_hit());
			obj.put("request_cmt_count", to.getRequest_cmt_count());
			obj.put("request_cigar_brand", to.getRequest_cigar_brand());
			obj.put("request_cigar_name", to.getRequest_cigar_name());
			obj.put("request_tar", to.getRequest_tar());
			obj.put("request_nicotine", to.getRequest_nicotine());
			obj.put("request_file_name", to.getRequest_file_name());
			obj.put("request_file_size", to.getRequest_file_size());
			obj.put("request_smoke_years", to.getRequest_smoke_years().toString());
			obj.put("request_public", to.isRequest_public());
			
			requestLists.add(obj);
		}
		
		return requestLists;
	}
	
	@RequestMapping(value = "api/request_view.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestView(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		
		RequestCigarTO to = new RequestCigarTO();
		to.setRequest_seq(Integer.parseInt((String)(paramMap.get("request_seq"))));
		//HttpSession session = request.getSession();
		//System.out.println((int)session.getAttribute("member_seq"));
		to = dao.requestView(to);
		JSONObject requestViewObj = new JSONObject();
		requestViewObj.put("request_seq", to.getRequest_seq());
		requestViewObj.put("request_writer_seq", to.getRequest_writer_seq());
		requestViewObj.put("request_suject", to.getRequest_subject());
		requestViewObj.put("request_writer", to.getRequest_writer());
		requestViewObj.put("request_reg_date", to.getRequest_reg_date().toString());
		requestViewObj.put("request_content", to.getRequest_content());
		requestViewObj.put("request_hit", to.getRequest_hit());
		requestViewObj.put("request_cmt_count", to.getRequest_cmt_count());
		requestViewObj.put("request_cigar_brand", to.getRequest_cigar_brand());
		requestViewObj.put("request_cigar_name", to.getRequest_cigar_name());
		requestViewObj.put("request_tar", to.getRequest_tar());
		requestViewObj.put("request_nicotine", to.getRequest_nicotine());
		requestViewObj.put("request_file_name", to.getRequest_file_name());
		requestViewObj.put("request_file_size", to.getRequest_file_size());
		requestViewObj.put("request_smoke_years", to.getRequest_smoke_years().toString());
		requestViewObj.put("request_public", to.isRequest_public());
		
		return requestViewObj;
	}
	
	@RequestMapping(value = "api/request_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void requestWrite(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		
	}
	
	@RequestMapping(value = "api/request_write_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestWriteOk(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody Map<String,Object> paramMap, @RequestParam MultipartFile upload) {
		
		RequestCigarTO to = new RequestCigarTO();
		HttpSession session = request.getSession();
		
		try {
			to.setRequest_writer_seq((int)session.getAttribute("member_seq"));
			to.setRequest_subject((String)(paramMap.get("request_subject")));
			to.setRequest_writer((String)session.getAttribute("nickname"));
			to.setRequest_content((String)(paramMap.get("request_content")));
			to.setRequest_cigar_brand((String)(paramMap.get("request_cigar_brand")));
			to.setRequest_cigar_name((String)(paramMap.get("request_cigar_name")));
			to.setRequest_tar(Double.parseDouble((String)(paramMap.get("request_tar"))));
			to.setRequest_nicotine(Double.parseDouble((String)(paramMap.get("request_nicotine"))));
			to.setRequest_file_name((String)(paramMap.get("request_file_name")));
			to.setRequest_file_size(Integer.parseInt((String)(paramMap.get("request_file_size"))));
			to.setRequest_smoke_years((Date)session.getAttribute("smoke_years"));
			to.setRequest_public((boolean)paramMap.get("request_public"));
			
			if( !upload.isEmpty() ) {
				String extention = upload.getOriginalFilename().substring(upload.getOriginalFilename().indexOf("."));
				to.setRequest_file_name(UUID.randomUUID().toString() + extention);
				to.setRequest_file_size((int)upload.getSize());
				byte[] bytes = upload.getBytes();
				Path path = Paths.get((filePath + to.getRequest_file_name()).trim());
			    Files.write(path, bytes);
			}
		} catch (NumberFormatException e) {
			System.out.println("[에러] : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[에러] : " + e.getMessage());
		}
		
		int flag = dao.requestWriteOk(to);
		JSONObject requestWriteOk = new JSONObject();
		requestWriteOk.put("flag", flag);
		return requestWriteOk;
	}
	
	@RequestMapping(value = "api/request_modify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestModify(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		RequestCigarTO to = new RequestCigarTO();
		to.setRequest_seq(Integer.parseInt((String)(paramMap.get("request_seq"))));
		to = dao.requestModify(to);
		JSONObject requestModifyObj = new JSONObject();
		requestModifyObj.put("request_seq", to.getRequest_seq());
		requestModifyObj.put("request_writer_seq", to.getRequest_writer_seq());
		requestModifyObj.put("request_suject", to.getRequest_subject());
		requestModifyObj.put("request_writer", to.getRequest_writer());
		requestModifyObj.put("request_reg_date", to.getRequest_reg_date().toString());
		requestModifyObj.put("request_content", to.getRequest_content());
		requestModifyObj.put("request_hit", to.getRequest_hit());
		requestModifyObj.put("request_cmt_count", to.getRequest_cmt_count());
		requestModifyObj.put("request_cigar_brand", to.getRequest_cigar_brand());
		requestModifyObj.put("request_cigar_name", to.getRequest_cigar_name());
		requestModifyObj.put("request_tar", to.getRequest_tar());
		requestModifyObj.put("request_nicotine", to.getRequest_nicotine());
		requestModifyObj.put("request_file_name", to.getRequest_file_name());
		requestModifyObj.put("request_file_size", to.getRequest_file_size());
		requestModifyObj.put("request_smoke_years", to.getRequest_smoke_years().toString());
		requestModifyObj.put("request_public", to.isRequest_public());

		return requestModifyObj;
	}
	
	@RequestMapping(value = "api/request_modify_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestModifyOk(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam Map<String,Object> paramMap,@RequestParam MultipartFile upload) {
		 
		RequestCigarTO to = new RequestCigarTO();
		String oldfilename = (String)paramMap.get("request_file_name");
		try {
			to.setRequest_seq(Integer.parseInt((String)(paramMap.get("request_seq"))));
			to.setRequest_subject((String)(paramMap.get("request_subject")));
			to.setRequest_content((String)(paramMap.get("request_content")));
			to.setRequest_cigar_brand((String)(paramMap.get("request_cigar_brand")));
			to.setRequest_cigar_name((String)(paramMap.get("request_cigar_name")));
			to.setRequest_tar(Double.parseDouble((String)(paramMap.get("request_tar"))));
			to.setRequest_nicotine(Double.parseDouble((String)(paramMap.get("request_nicotine"))));
			to.setRequest_public((boolean)paramMap.get("request_public"));
			if( !upload.isEmpty() ) {
				byte[] bytes = upload.getBytes();
				String extention = upload.getOriginalFilename().substring(upload.getOriginalFilename().indexOf("."));
				to.setRequest_file_name(UUID.randomUUID().toString() + extention);
				to.setRequest_file_size((int) upload.getSize());
				
			    Path targetPath = Paths.get(filePath.trim() + oldfilename.trim());
			    Path backuppath = Paths.get(backupFilePath.trim() + oldfilename.trim());			
				Path path = Paths.get(filePath.trim() + to.getRequest_file_name().trim());
				
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
		
		int flag = dao.requestModifyOk(to, oldfilename);
		JSONObject requestModifyOk = new JSONObject();
		requestModifyOk.put("flag", flag);
		return requestModifyOk;
	}
	
	@RequestMapping(value = "api/request_delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		RequestCigarTO to = new RequestCigarTO();
		to.setRequest_seq(Integer.parseInt((String)(paramMap.get("request_seq"))));
		to = dao.requestDelete(to);
		JSONObject requestDeleteObj = new JSONObject();
		requestDeleteObj.put("request_seq", to.getRequest_seq());
		requestDeleteObj.put("request_writer_seq", to.getRequest_writer_seq());
		requestDeleteObj.put("request_suject", to.getRequest_subject());
		requestDeleteObj.put("request_writer", to.getRequest_writer());
		requestDeleteObj.put("request_reg_date", to.getRequest_reg_date().toString());
		requestDeleteObj.put("request_content", to.getRequest_content());
		requestDeleteObj.put("request_hit", to.getRequest_hit());
		requestDeleteObj.put("request_cmt_count", to.getRequest_cmt_count());
		requestDeleteObj.put("request_cigar_brand", to.getRequest_cigar_brand());
		requestDeleteObj.put("request_cigar_name", to.getRequest_cigar_name());
		requestDeleteObj.put("request_tar", to.getRequest_tar());
		requestDeleteObj.put("request_nicotine", to.getRequest_nicotine());
		requestDeleteObj.put("request_file_name", to.getRequest_file_name());
		requestDeleteObj.put("request_file_size", to.getRequest_file_size());
		requestDeleteObj.put("request_smoke_years", to.getRequest_smoke_years().toString());
		requestDeleteObj.put("request_public", to.isRequest_public());

		return requestDeleteObj;
	}
	
	@RequestMapping(value = "api/request_delete_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestdeleteOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		HttpSession session = request.getSession();
		RequestCigarTO to = new RequestCigarTO();
		to.setRequest_seq(Integer.parseInt((String)(paramMap.get("request_seq"))));
		to.setRequest_writer_seq((int)session.getAttribute("member_seq"));
		try {
			to = dao.requestDelete(to);
			Path targetPath = Paths.get(filePath.trim() + to.getRequest_file_name());
			Path backuppath = Paths.get(backupFilePath.trim() + to.getRequest_file_name());		
			Files.copy(targetPath, backuppath);
			System.out.println(to.getRequest_file_name());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		to.setRequest_file_name(to.getRequest_file_name());
		int flag = dao.requestDeleteOk(to);
		JSONObject requestdeleteOk = new JSONObject();
		requestdeleteOk.put("flag", flag);
		
		return requestdeleteOk;
	}
	
	@RequestMapping(value = "api/requestCommentLists.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public JSONArray gongjiCommentList(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		JSONArray requestCommentLists = new JSONArray();
		RequestCigarCommentTO cmtTO = new RequestCigarCommentTO();
		cmtTO.setRequest_pseq(Integer.parseInt((String)(paramMap.get("request_pseq"))));
		ArrayList<RequestCigarCommentTO> CommentListTO = cmtDAO.requestCommentList(cmtTO);
		for(RequestCigarCommentTO cmtTO2 : CommentListTO) {
			JSONObject obj = new JSONObject();
			obj.put("request_cmt_seq", cmtTO2.getRequest_cmt_seq());
			obj.put("request_pseq", cmtTO2.getRequest_pseq());
			obj.put("request_cmt_writer_seq", cmtTO2.getRequest_cmt_writer_seq());
			obj.put("request_grp", cmtTO2.getRequest_grp());
			obj.put("request_grps", cmtTO2.getRequest_grps());
			obj.put("request_grpl", cmtTO2.getRequest_grpl());
			obj.put("request_cmt_writer", cmtTO2.getRequest_cmt_writer());
			obj.put("request_cmt_content", cmtTO2.getRequest_cmt_content());
			obj.put("request_cmt_reg_date", cmtTO2.getRequest_cmt_reg_date().toString());
			
			requestCommentLists.add(obj);
		}
		
		return requestCommentLists;
	}
	
	@RequestMapping(value = "api/request_parent_cmt_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestParentCmtWrite(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		HttpSession session = request.getSession();
		JSONObject requestCmtWriteObj = new JSONObject();
		RequestCigarCommentTO to = new RequestCigarCommentTO();
		to.setRequest_pseq(Integer.parseInt((String)(paramMap.get("request_pseq"))));
		to = cmtDAO.requestParentCommentWrite(to);
		requestCmtWriteObj.put("request_cmt_seq", 0);
		requestCmtWriteObj.put("request_pseq", to.getRequest_pseq());
		requestCmtWriteObj.put("request_grp", to.getRequest_grp()+1);
		requestCmtWriteObj.put("request_grps", 0);
		requestCmtWriteObj.put("request_grpl", 0);
		requestCmtWriteObj.put("request_cmt_writer", (String)session.getAttribute("nickname"));
		
		return requestCmtWriteObj;
	}
	
	
	@RequestMapping(value = "api/request_cmt_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestCmtWrite(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		HttpSession session = request.getSession();
		RequestCigarCommentTO to = new RequestCigarCommentTO();
		to.setRequest_cmt_seq(Integer.parseInt((String)(paramMap.get("request_cmt_seq"))));
		to = cmtDAO.requestCommentWrite(to);
		JSONObject requestCmtWriteObj = new JSONObject();
		requestCmtWriteObj.put("request_cmt_seq", to.getRequest_cmt_seq());
		requestCmtWriteObj.put("request_pseq", to.getRequest_pseq());
		requestCmtWriteObj.put("request_grp", to.getRequest_grp());
		requestCmtWriteObj.put("request_grps", to.getRequest_grps());
		requestCmtWriteObj.put("request_grpl", to.getRequest_grpl());
		requestCmtWriteObj.put("request_cmt_writer", (String)session.getAttribute("nickname"));

		return requestCmtWriteObj;
	}
	
	@RequestMapping(value = "api/request_cmt_write_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestCmtWriteOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		RequestCigarCommentTO to = new RequestCigarCommentTO();
		HttpSession session = request.getSession();
		to.setRequest_cmt_seq(Integer.parseInt((String)(paramMap.get("request_cmt_seq"))));
		to.setRequest_pseq(Integer.parseInt((String)(paramMap.get("request_pseq"))));
		to.setRequest_cmt_writer_seq((int)session.getAttribute("member_seq"));
		to.setRequest_grp(Integer.parseInt((String)(paramMap.get("request_grp"))));
		to.setRequest_grps(Integer.parseInt((String)(paramMap.get("request_grps"))));
		to.setRequest_grpl(Integer.parseInt((String)(paramMap.get("request_grpl"))));
		to.setRequest_cmt_writer((String)session.getAttribute("nickname"));
		to.setRequest_cmt_content((String)(paramMap.get("request_cmt_content")));
		int flag = cmtDAO.requestCmtWriteOk(to);
		JSONObject requestCmtWriteOk = new JSONObject();
		requestCmtWriteOk.put("flag", flag);
		return requestCmtWriteOk;
	}
	
	@RequestMapping(value = "api/request_cmt_modify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestCmtModify(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		RequestCigarCommentTO to = new RequestCigarCommentTO();
		to.setRequest_cmt_seq(Integer.parseInt((String)(paramMap.get("request_cmt_seq"))));
		to = cmtDAO.requestCommentModify(to);
		JSONObject requestCmtModifyObj = new JSONObject();
		requestCmtModifyObj.put("request_cmt_seq", to.getRequest_cmt_seq());
		requestCmtModifyObj.put("request_pseq", to.getRequest_pseq());
		requestCmtModifyObj.put("request_cmt_writer_seq", to.getRequest_cmt_writer_seq());
		requestCmtModifyObj.put("request_grp", to.getRequest_grp());
		requestCmtModifyObj.put("request_grps", to.getRequest_grps());
		requestCmtModifyObj.put("request_grpl", to.getRequest_grpl());
		requestCmtModifyObj.put("request_cmt_writer", to.getRequest_cmt_writer());
		requestCmtModifyObj.put("request_cmt_content", to.getRequest_cmt_content());
		requestCmtModifyObj.put("request_cmt_reg_date", to.getRequest_cmt_reg_date().toString());

		return requestCmtModifyObj;
	}
	
	@RequestMapping(value = "api/request_cmt_modify_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestCmtModifyOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		RequestCigarCommentTO to = new RequestCigarCommentTO();
		to.setRequest_cmt_seq(Integer.parseInt((String)(paramMap.get("request_cmt_seq"))));
		to.setRequest_pseq(Integer.parseInt((String)(paramMap.get("request_pseq"))));
		to.setRequest_cmt_writer_seq(Integer.parseInt((String)(paramMap.get("request_cmt_writer_seq"))));
		to.setRequest_grp(Integer.parseInt((String)(paramMap.get("request_grp"))));
		to.setRequest_grps(Integer.parseInt((String)(paramMap.get("request_grps"))));
		to.setRequest_grpl(Integer.parseInt((String)(paramMap.get("request_grpl"))));
		to.setRequest_cmt_writer((String)(paramMap.get("request_cmt_writer")));
		to.setRequest_cmt_content((String)(paramMap.get("request_cmt_content")));
		to.setRequest_cmt_reg_date(Date.valueOf((String)(paramMap.get("request_cmt_reg_date"))));
		int flag = cmtDAO.requestCmtWriteOk(to);
		JSONObject requestCmtModifyOk = new JSONObject();
		requestCmtModifyOk.put("flag", flag);
		return requestCmtModifyOk;
	}
	
	@RequestMapping(value = "api/requestSearch", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONArray requestSearch(@RequestParam Map<String, Object>paramMap, HttpServletRequest request, HttpServletResponse response) {
		RequestCigarTO to = new RequestCigarTO();
		to.setRequest_cigar_brand((String)paramMap.get("Request_cigar_brand"));
		to.setRequest_cigar_name((String)paramMap.get("Request_cigar_name"));
		to.setRequest_content((String)paramMap.get("Request_content"));
		to.setRequest_writer((String)paramMap.get("Request_writer"));
		to.setRequest_subject((String)paramMap.get("Request_subject"));
		ArrayList<RequestCigarTO> requestSearchtLists = dao.requestSearch(to);
		
		JSONArray reqeustSearchArray = new JSONArray();
		for(RequestCigarTO to2 : requestSearchtLists) {
			JSONObject obj = new JSONObject();
			obj.put("request_seq", to2.getRequest_seq());
			obj.put("request_wirter", to2.getRequest_writer());
			obj.put("request_wirter_seq", to2.getRequest_writer_seq());
			obj.put("request_subject", to2.getRequest_subject());
			obj.put("request_content", to2.getRequest_content());
			obj.put("request_smoke_years", to2.getRequest_smoke_years());
			obj.put("request_cmt_count", to2.getRequest_cmt_count());
			obj.put("request_cigar_brand", to2.getRequest_cigar_brand());
			obj.put("request_cigar_name", to2.getRequest_cigar_name());
			obj.put("request_request_tar", to2.getRequest_tar());
			obj.put("request_nicotine", to2.getRequest_nicotine());
			obj.put("request_file_name_", to2.getRequest_file_name());
			obj.put("request_file_size", to2.getRequest_file_size());
			obj.put("request_reg_date", to2.getRequest_reg_date());
			obj.put("request_hit", to2.getRequest_hit());
			reqeustSearchArray.add(obj);
		}
		
		System.out.println(reqeustSearchArray);
		return reqeustSearchArray;
	}
	
	@RequestMapping(value = "api/request_cmt_delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestCmtDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		RequestCigarCommentTO to = new RequestCigarCommentTO();
		to.setRequest_cmt_seq(Integer.parseInt((String)(paramMap.get("request_cmt_seq"))));
		to = cmtDAO.requestCommentDelete(to);
		JSONObject requestCmtDeleteObj = new JSONObject();
		requestCmtDeleteObj.put("request_cmt_seq", to.getRequest_cmt_seq());
		requestCmtDeleteObj.put("request_pseq", to.getRequest_pseq());
		requestCmtDeleteObj.put("request_cmt_writer_seq", to.getRequest_cmt_writer_seq());
		requestCmtDeleteObj.put("request_grp", to.getRequest_grp());
		requestCmtDeleteObj.put("request_grps", to.getRequest_grps());
		requestCmtDeleteObj.put("request_grpl", to.getRequest_grpl());
		requestCmtDeleteObj.put("request_cmt_writer", to.getRequest_cmt_writer());
		requestCmtDeleteObj.put("request_cmt_content", to.getRequest_cmt_content());
		requestCmtDeleteObj.put("request_cmt_reg_date", to.getRequest_cmt_reg_date().toString());

		return requestCmtDeleteObj;
	}
	
	@RequestMapping(value = "api/request_cmt_delete_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject requestCmtDeleteOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		HttpSession session = request.getSession();
		RequestCigarCommentTO to = new RequestCigarCommentTO();
		to.setRequest_cmt_seq(Integer.parseInt((String)(paramMap.get("request_cmt_seq"))));
		to.setRequest_cmt_writer_seq((int)session.getAttribute("member_seq"));
		int flag = cmtDAO.requestCommentDeleteOk(to);
		JSONObject requestCmtDeleteOk = new JSONObject();
		requestCmtDeleteOk.put("flag", flag);
		return requestCmtDeleteOk;
	}
}
