package com.project.cigar.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.ModelAndView;

import com.project.cigar.dao.CigarDAO;
import com.project.cigar.to.CigarListTO;
import com.project.cigar.to.CigarTO;

@RestController
public class CigarAPIController {
	
	@Autowired
	private CigarDAO dao;
	
	private String filePath = System.getProperty("user.dir") + "/src/main/webapp/uploads/cigarUpload/";
	private String backupFilePath = System.getProperty("user.dir") + "/src/main/webapp/uploads/cigarUpload/cigarBackup/";
	
	@RequestMapping(value = "api/cigarListObj.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject cigarList(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		int cpage = 1;
		if((String)(paramMap.get("cpage")) != null && !paramMap.get("cpage").equals("")) {
			cpage = Integer.parseInt((String)(paramMap.get("cpage")));
		}
		CigarListTO listTO = new CigarListTO();
		listTO.setCpage(cpage);
		listTO = dao.cigarList(listTO);
		JSONObject cigarListObj = new JSONObject();
		cigarListObj.put("cpage", listTO.getCpage());
		cigarListObj.put("recordPerPage", listTO.getRecordPerPage());
		cigarListObj.put("blockPerPage", listTO.getBlockPerPage());
		cigarListObj.put("totalPage", listTO.getTotalPage());
		cigarListObj.put("totalRecord", listTO.getTotalRecord());
		cigarListObj.put("startBlock", listTO.getStartBlock());
		cigarListObj.put("endBlock", listTO.getEndBlock());
		JSONArray jsonArray = new JSONArray();
		for(CigarTO to : listTO.getCigarLists()) {
			JSONObject obj = new JSONObject();
			obj.put("cigar_seq", to.getCigar_seq());
			obj.put("cigar_writer_seq", to.getCigar_writer_seq());
			obj.put("cigar_brand", to.getCigar_brand());
			obj.put("cigar_name", to.getCigar_name());
			obj.put("cigar_tar", to.getCigar_tar());
			obj.put("cigar_nicotine", to.getCigar_nicotine());
			obj.put("cigar_file_name", to.getCigar_file_name());
			obj.put("cigar_file_size", to.getCigar_file_size());
			obj.put("cigar_hash_tag", to.getCigar_hash_tag());
			obj.put("cigar_content", to.getCigar_content());
			obj.put("cigar_total_grade", to.getCigar_total_grade());
			obj.put("cigar_reg_date", to.getCigar_reg_date());
			obj.put("cigar_hit", to.getCigar_hit());
			
			jsonArray.add(obj);
		}
		
		cigarListObj.put("cigarList", jsonArray);
		
		return cigarListObj;
	}
	
	@RequestMapping(value = "/api/cigarLists.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONArray cigarListMin(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> paramMap) {
		ArrayList<CigarTO> listTO = dao.cigarListMin();
		JSONArray cigarLists = new JSONArray();
		for(CigarTO to : listTO) {
			JSONObject obj = new JSONObject();
			obj.put("cigar_seq", to.getCigar_seq());
			obj.put("cigar_writer_seq", to.getCigar_writer_seq());
			obj.put("cigar_brand", to.getCigar_brand());
			obj.put("cigar_name", to.getCigar_name());
			obj.put("cigar_tar", to.getCigar_tar());
			obj.put("cigar_nicotine", to.getCigar_nicotine());
			obj.put("cigar_file_name", to.getCigar_file_name());
			obj.put("cigar_file_size", to.getCigar_file_size());
			obj.put("cigar_hash_tag", to.getCigar_hash_tag());
			obj.put("cigar_content", to.getCigar_content());
			obj.put("cigar_total_grade", to.getCigar_total_grade());
			obj.put("cigar_reg_date", to.getCigar_reg_date().toString());
			obj.put("cigar_hit", to.getCigar_hit());
			
			cigarLists.add(obj);
		}
		
		return cigarLists;
	}
	
	@RequestMapping(value = "/api/cigar_view.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject cigarView(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		CigarTO to = new CigarTO();
		//to.setCigar_seq(Integer.parseInt((String)(paramMap.get("cigar_seq")));
		//to.setCigar_seq(Integer.parseInt((String)paramMap.get("cigar_seq")));
		to.setCigar_seq(Integer.parseInt(request.getParameter("cigar_seq")));
		
		to = dao.cigarView(to);
		JSONObject cigarViewObj = new JSONObject();
		cigarViewObj.put("cigar_seq", to.getCigar_seq());
		cigarViewObj.put("cigar_writer_seq", to.getCigar_writer_seq());
		cigarViewObj.put("cigar_brand", to.getCigar_brand());
		cigarViewObj.put("cigar_name", to.getCigar_name());
		cigarViewObj.put("cigar_tar", to.getCigar_tar());
		cigarViewObj.put("cigar_nicotine", to.getCigar_nicotine());
		cigarViewObj.put("cigar_file_name", to.getCigar_file_name());
		cigarViewObj.put("cigar_file_size", to.getCigar_file_size());
		cigarViewObj.put("cigar_hash_tag", to.getCigar_hash_tag());
		cigarViewObj.put("cigar_content", to.getCigar_content());
		cigarViewObj.put("cigar_total_grade", to.getCigar_total_grade());
		cigarViewObj.put("cigar_reg_date", to.getCigar_reg_date().toString());
		cigarViewObj.put("cigar_hit", to.getCigar_hit());
		
		return cigarViewObj;
	}
	
	@RequestMapping(value = "/api/cigar_write.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void cigarWrite(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		
	}
	
	@RequestMapping(value = "/api/cigar_write_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject cigarWriteOk(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody Map<String,Object> paramMap, @RequestBody MultipartFile upload) {
		HttpSession session = request.getSession();
		CigarTO to = new CigarTO();
		
		to.setCigar_writer_seq((int)session.getAttribute("member_seq"));
		to.setCigar_brand((String)(paramMap.get("cigar_brand")));
		to.setCigar_name((String)(paramMap.get("cigar_name")));
		to.setCigar_tar(Double.parseDouble((String)(paramMap.get("cigar_tar"))));
		to.setCigar_nicotine(Double.parseDouble((String)(paramMap.get("cigar_nicotine"))));
		to.setCigar_hash_tag((String)(paramMap.get("cigar_hash_tag")));
		to.setCigar_content((String)(paramMap.get("cigar_content")));
		to.setCigar_total_grade(Double.parseDouble((String)(paramMap.get("cigar_total_grade"))));
		
		try {
			if( !upload.isEmpty() ) {
				String extention = upload.getOriginalFilename().substring(upload.getOriginalFilename().indexOf("."));
				to.setCigar_file_name(UUID.randomUUID().toString() + extention);
				to.setCigar_file_size((int)upload.getSize());
				byte[] bytes = upload.getBytes();
				Path path = Paths.get((filePath + to.getCigar_file_name()).trim());
	            Files.write(path, bytes);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] : " + e.getMessage());
		}
		/*
		to.setCigar_writer_seq(1);
		to.setCigar_brand("던힐");
		to.setCigar_name("던힐 프로스트");
		to.setCigar_tar(1.0);
		to.setCigar_nicotine(0.10);
		to.setCigar_file_name("던힐.png");
		to.setCigar_file_size(12358);
		to.setCigar_hash_tag("#멘솔#가벼움");
		to.setCigar_content("가볍고 깨끗한 담배");
		to.setCigar_total_grade(4.57);
		*/
		int flag = dao.cigarWriteOk(to);
		JSONObject cigarWriteOk = new JSONObject();
		cigarWriteOk.put("flag", flag);
		return cigarWriteOk;
	}
	
	@RequestMapping(value = "/api/cigar_modify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject cigarModify(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		CigarTO to = new CigarTO();
		to.setCigar_seq(Integer.parseInt((String)(paramMap.get("cigar_seq"))));
		to = dao.cigarModify(to);
		JSONObject cigarModifyObj = new JSONObject();
		cigarModifyObj.put("cigar_seq", to.getCigar_seq());
		cigarModifyObj.put("cigar_writer_seq", to.getCigar_writer_seq());
		cigarModifyObj.put("cigar_brand", to.getCigar_brand());
		cigarModifyObj.put("cigar_name", to.getCigar_name());
		cigarModifyObj.put("cigar_tar", to.getCigar_tar());
		cigarModifyObj.put("cigar_nicotine", to.getCigar_nicotine());
		cigarModifyObj.put("cigar_file_name", to.getCigar_file_name());
		cigarModifyObj.put("cigar_file_size", to.getCigar_file_size());
		cigarModifyObj.put("cigar_hash_tag", to.getCigar_hash_tag());
		cigarModifyObj.put("cigar_content", to.getCigar_content());
		cigarModifyObj.put("cigar_total_grade", to.getCigar_total_grade());
		cigarModifyObj.put("cigar_reg_date", to.getCigar_reg_date());
		cigarModifyObj.put("cigar_hit", to.getCigar_hit());
		
		return cigarModifyObj;
	}
	
	@RequestMapping(value = "/api/cigar_modify_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject cigarModifyOk(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody Map<String,Object> paramMap, @RequestParam MultipartFile upload) {
		HttpSession session = request.getSession();
		String oldfilename = (String)paramMap.get("cigar_file_name");
		CigarTO to = new  CigarTO();
		
		try {
			to.setCigar_seq(Integer.parseInt((String)(paramMap.get("cigar_seq"))));
			to.setCigar_writer_seq((int)session.getAttribute("member_seq"));
			to.setCigar_brand((String)(paramMap.get("cigar_brand")));
			to.setCigar_name((String)(paramMap.get("cigar_name")));
			to.setCigar_tar(Double.parseDouble((String)(paramMap.get("cigar_tar"))));
			to.setCigar_nicotine(Double.parseDouble((String)(paramMap.get("cigar_nicotine"))));
			to.setCigar_hash_tag((String)(paramMap.get("cigar_hash_tag")));
			to.setCigar_content((String)(paramMap.get("cigar_content")));
			if( !upload.isEmpty() ) {
				byte[] bytes = upload.getBytes();
				String extention = upload.getOriginalFilename().substring(upload.getOriginalFilename().indexOf("."));
				to.setCigar_file_name(UUID.randomUUID().toString() + extention);
				to.setCigar_file_size((int) upload.getSize());
			    Path targetPath = Paths.get(filePath.trim() + oldfilename.trim());
			    Path backupPath = Paths.get(backupFilePath.trim() + oldfilename.trim());			
				Path path = Paths.get(filePath.trim() + to.getCigar_file_name().trim());
				
				Files.write(path, bytes);
			    Files.copy(targetPath, backupPath);;
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
		int flag = dao.cigarModifyOk(to, oldfilename);
		
		JSONObject cigarModifyOk = new JSONObject();
		cigarModifyOk.put("flag", flag);
		return cigarModifyOk;
	}
	
	@RequestMapping(value = "/api/cigar_delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject cigarDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		 
		CigarTO to = new CigarTO();
		to.setCigar_seq(Integer.parseInt((String)(paramMap.get("cigar_seq"))));
		to = dao.cigarDelete(to);
		JSONObject cigarDeleteObj = new JSONObject();
		cigarDeleteObj.put("cigar_seq", to.getCigar_seq());
		cigarDeleteObj.put("cigar_writer_seq", to.getCigar_writer_seq());
		cigarDeleteObj.put("cigar_brand", to.getCigar_brand());
		cigarDeleteObj.put("cigar_name", to.getCigar_name());
		cigarDeleteObj.put("cigar_tar", to.getCigar_tar());
		cigarDeleteObj.put("cigar_nicotine", to.getCigar_nicotine());
		cigarDeleteObj.put("cigar_file_name", to.getCigar_file_name());
		cigarDeleteObj.put("cigar_file_size", to.getCigar_file_size());
		cigarDeleteObj.put("cigar_hash_tag", to.getCigar_hash_tag());
		cigarDeleteObj.put("cigar_content", to.getCigar_content());
		cigarDeleteObj.put("cigar_total_grade", to.getCigar_total_grade());
		cigarDeleteObj.put("cigar_reg_date", to.getCigar_reg_date());
		cigarDeleteObj.put("cigar_hit", to.getCigar_hit());
		
		return cigarDeleteObj;
	}
	
	@RequestMapping(value = "/api/cigar_delete_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject cigarDeleteOk(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		HttpSession session = request.getSession();
		CigarTO to = new CigarTO();
		to.setCigar_seq(Integer.parseInt((String)(paramMap.get("cigar_seq"))));
		to.setCigar_writer_seq((int)session.getAttribute("member_seq"));
		try {
			to = dao.cigarDelete(to);
			Path targetPath = Paths.get(filePath.trim() + to.getCigar_file_name());
			Path backupPath = Paths.get(backupFilePath.trim() + to.getCigar_file_name());		
			Files.copy(targetPath, backupPath);
			//System.out.println(to.getCigar_file_name());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		int flag = dao.cigarDeleteOk(to);
		JSONObject cigarDeleteOk = new JSONObject();
		cigarDeleteOk.put("flag", flag);
		return cigarDeleteOk;
	}
	
	@RequestMapping(value = "/api/hash.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONArray cigarHash(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> paramMap) {
		JSONArray hashTagListJ = new JSONArray();
		ArrayList<String> hashTagList = dao.hashTagListDAO();
		ArrayList<String> hashTagAll = new ArrayList<String>();
		
		for(String str : hashTagList) {
			String[] arr = str.split("#");
			for(int j = 1; j < arr.length; j++) {
				hashTagAll.add("#" + arr[j]);
			}
		}
		List<String> arr1 = hashTagAll.stream().distinct().collect(Collectors.toList());
		for(int i = 0; i < arr1.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("cigar_hash_tag", arr1.get(i));
			obj.put("keyNum", i+1);
			hashTagListJ.add(obj);
		}

		return hashTagListJ;
	}
	
	@RequestMapping("/api/cigarSearch.do")
	public JSONArray ciarSearch(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) {
		CigarTO to = new CigarTO();
		to.setCigar_brand((String)paramMap.get("cigar_brand"));
		to.setCigar_name((String)paramMap.get("cigar_name"));
		to.setCigar_hash_tag((String)paramMap.get("cigar_hash_tag"));
		ArrayList<CigarTO> cigarSearchList = dao.cigarSearch(to);
		
		JSONArray cigarSearchArray = new JSONArray();
		for(CigarTO to2 : cigarSearchList) {
			JSONObject obj = new JSONObject();
			obj.put("cigar_seq", to2.getCigar_seq());
			obj.put("cigar_brand", to2.getCigar_brand());
			obj.put("cigar_name", to2.getCigar_name());
			obj.put("cigar_cigar_tar", to2.getCigar_tar());
			obj.put("cigar_nicotine", to2.getCigar_nicotine());
			obj.put("cigar_hash_tag", to2.getCigar_hash_tag());
			obj.put("cigar_file_name_", to2.getCigar_file_name());
			obj.put("cigar_file_size", to2.getCigar_file_size());
			obj.put("cigar_content", to2.getCigar_content());
			obj.put("cigar_total_grade", to2.getCigar_total_grade());
			obj.put("cigar_reg_date", to2.getCigar_reg_date());
			obj.put("cigar_hit", to2.getCigar_hit());
			cigarSearchArray.add(obj);
		}
		
		System.out.println(cigarSearchArray);
		return cigarSearchArray;
	}
}
