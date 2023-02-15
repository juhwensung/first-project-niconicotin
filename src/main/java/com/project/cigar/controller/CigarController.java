package com.project.cigar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.cigar.dao.CigarDAO;
import com.project.cigar.to.CigarListTO;
import com.project.cigar.to.CigarTO;

@Controller
public class CigarController {
	
	@Autowired
	private CigarDAO dao;
	
	@RequestMapping("/cigar/list.do")
	public ModelAndView cigarList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		int cpage = 1;
		if(request.getParameter("cpage") != null && !request.getParameter("cpage").equals("")) {
			cpage = Integer.parseInt(request.getParameter("cpage"));
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
			
			jsonArray.add(obj);
		}
		
		cigarListObj.put("cigarList", jsonArray);
		
		mav.addObject("cigarListObj", cigarListObj);
		mav.setViewName("cigarViews/cigarList");
		//System.out.println(cigarListObj.get("cpage"));
		//System.out.println(cigarListObj.get("cigarList"));
		return mav;
	}
	
	@RequestMapping("/cigar/view.do")
	public ModelAndView cigarView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		CigarTO to = new CigarTO();
		//to.setCigar_seq(Integer.parseInt(request.getParameter("cigar_seq")));
		to.setCigar_seq(2);
		
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
		
		mav.addObject("cigarViewObj", cigarViewObj);
		mav.setViewName("cigarViews/cigarView");
		
		return mav;
	}
	
	@RequestMapping("/cigar/write.do")
	public ModelAndView cigarWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cigarViews/cigarWrite");
		return mav;
	}
	
	@RequestMapping("/cigar/write_ok.do")
	public ModelAndView cigarWriteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		CigarTO to = new CigarTO();
		
		to.setCigar_writer_seq(Integer.parseInt(request.getParameter("cigar_writer_seq")));
		to.setCigar_brand(request.getParameter("cigar_brand"));
		to.setCigar_name(request.getParameter("cigar_name"));
		to.setCigar_tar(Double.parseDouble(request.getParameter("cigar_tar")));
		to.setCigar_nicotine(Double.parseDouble(request.getParameter("cigar_nicotine")));
		to.setCigar_hash_tag(request.getParameter("cigar_hash_tag"));
		to.setCigar_content(request.getParameter("cigar_content"));
		to.setCigar_total_grade(Double.parseDouble(request.getParameter("cigar_total_grade")));
		
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
		mav.addObject("flag", flag);
		mav.setViewName("cigarViews/cigarWrite_ok");
		return mav;
	}
	
	@RequestMapping("/cigar/modify.do")
	public ModelAndView cigarModify(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		CigarTO to = new CigarTO();
		to.setCigar_seq(Integer.parseInt(request.getParameter("cigar_seq")));
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
		mav.addObject("cigarModifyObj", cigarModifyObj);
		mav.setViewName("cigarViews/cigarModify");
		return mav;
	}
	
	@RequestMapping("/cigar/modify_ok.do")
	public ModelAndView cigarModifyOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		CigarTO to = new  CigarTO();
		to.setCigar_seq(Integer.parseInt(request.getParameter("cigar_seq")));
		to.setCigar_writer_seq(Integer.parseInt(request.getParameter("cigar_writer_seq")));
		to.setCigar_brand(request.getParameter("cigar_brand"));
		to.setCigar_name(request.getParameter("cigar_name"));
		to.setCigar_tar(Double.parseDouble(request.getParameter("cigar_tar")));
		to.setCigar_nicotine(Double.parseDouble(request.getParameter("cigar_nicotine")));
		to.setCigar_file_name(request.getParameter("cigar_file_name"));
		to.setCigar_file_size(Integer.parseInt(request.getParameter("cigar_file_size")));
		to.setCigar_hash_tag(request.getParameter("cigar_hash_tag"));
		to.setCigar_content(request.getParameter("cigar_content"));
		to.setCigar_total_grade(Double.parseDouble(request.getParameter("cigar_total_grade")));
		String rr = "";
		int flag = dao.cigarModifyOk(to, rr);
		
		mav.addObject("flag", flag);
		mav.setViewName("cigarViews/cigarModify_ok");
		return mav;
	}
	
	@RequestMapping("/cigar/delete.do")
	public ModelAndView cigarDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		CigarTO to = new CigarTO();
		to.setCigar_seq(Integer.parseInt(request.getParameter("cigar_seq")));
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
		mav.addObject("cigarDeleteObj", cigarDeleteObj);
		mav.setViewName("cigarViews/cigarDelete");
		return mav;
	}
	
	@RequestMapping("/cigar/delete_ok.do")
	public ModelAndView cigarDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		CigarTO to = new CigarTO();
		to.setCigar_seq(Integer.parseInt(request.getParameter("cigar_seq")));
		int flag = dao.cigarDeleteOk(to);
		mav.addObject("flag", flag);
		mav.setViewName("cigarViews/cigarDelete_ok");
		return mav;
	}
	
	@RequestMapping("/cigar/hash.do")
	public ModelAndView cigarHash(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
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
		
		mav.addObject("hashTagListJ", hashTagListJ);
		mav.setViewName("cigarViews/cigarHashTagList");
		
		return mav;
	}
}
