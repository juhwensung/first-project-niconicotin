package com.project.member.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.free_board.to.FreeBoardTO;
import com.project.gongji.to.GongjiTO;
import com.project.member.dao.MemberDAO;
import com.project.member.to.MemberTO;
import com.project.requestCigar.to.RequestCigarTO;
import com.project.review.to.ReviewTO;

@RestController
public class MemberAPIController {
	@Autowired
	private MemberDAO dao;
	
	//로그인 뷰페이지
//	@RequestMapping(value = {"/login.do"} ,method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName( "memberViews/login" );
//		request.getSession();
//		return mav;
//	}
	
	//로그인 확인
	@RequestMapping(value = "api/loginOk.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject login_ok(@RequestBody Map<String, Object> paramMap,HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberTO to = new MemberTO();
		JSONObject loginOk = new JSONObject();
		HttpSession session = request.getSession();
		
		to.setEmail((String)paramMap.get("username"));
		to.setPassword((String)paramMap.get("password"));
		to = dao.login_Ok(to);
		
		try {
			session.setAttribute("emailss", to.getEmail());
			session.setAttribute("member_seq", to.getMember_seq());
			session.setAttribute("name", to.getName());
			session.setAttribute("phone", to.getPhone());
			session.setAttribute("nickname", to.getNickname());
			session.setAttribute("smoke_years", to.getSmoke_years());
			session.setAttribute("birthday", to.getBirthday());
		} catch(NullPointerException e) {
			System.err.println("NULL! : " + e.getMessage());
		}
		loginOk.put("emailss", to.getEmail());
		loginOk.put("member_seq", to.getMember_seq());
		loginOk.put("name", to.getName());
		loginOk.put("phone", to.getPhone());
		loginOk.put("nickname", to.getNickname());
		loginOk.put("smoke_years", to.getSmoke_years());
		loginOk.put("birthday", to.getBirthday());
		//System.out.println(result);
		//login_ok.put("member_data", request.getParameter("email"));
		
		return loginOk;
	}
	
//	// 로그인 성공시 완료 화면 뷰페이지
//	@RequestMapping(value = "/login_complete.do", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView login_complete(HttpServletRequest request, HttpServletResponse response) {		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName( "memberViews/loginComplete" );
//		return mav;
//	}
//	
//	
//	//로그아웃 뷰페이지
//	@RequestMapping(value = "/logout_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView logout_ok(HttpServletRequest request, HttpServletResponse response) {		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName( "memberViews/logoutOk" );
//		return mav;
//	}
//	
//	//id찾기 뷰페이지
//	@RequestMapping(value = "/id_search.do", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView id_Seach(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName( "memberViews/idSearch" );
//		return mav;
//	}
		
	@RequestMapping(value = "api/id_search_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject id_Search_Ok(@RequestParam Map<String, Object> paramMap ,HttpServletRequest request, HttpServletResponse response) {
		MemberTO to = new MemberTO();
		JSONObject id_search_ok = new JSONObject();
		try {
			to.setBirthday(Date.valueOf((String)paramMap.get("birthday")));
			to.setName((String)paramMap.get("name"));
			to.setPhone(request.getParameter("phoneNum1") + request.getParameter("phoneNum2") + request.getParameter("phoneNum3"));
//			System.out.println("con:"+ to.getPhoneNum());
//			System.out.println("con:"+to.getEmail());
		} catch (NullPointerException e) {
			System.err.println("NULL!");
		}
		to = dao.id_Search(to);
		id_search_ok.put("email", to.getEmail());
		return id_search_ok;
	}
	
//	// 회원가입 뷰페이지
//	@RequestMapping(value = "/member.do", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView member(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName( "memberViews/member" );
//		return mav;
//	}
		
	// 회원가입 확인
	@RequestMapping(value = "api/member_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject member_ok(@RequestParam Map<String, Object> paramMap , HttpServletRequest request, HttpServletResponse response) {
		MemberTO to = new MemberTO();
		JSONObject member_Ok_Obj = new JSONObject();
		int flag = 0;
		
		to.setEmail((String)paramMap.get("email"));
		to.setPassword((String)paramMap.get("password"));
		to.setName((String)paramMap.get("name"));
		to.setPhone(request.getParameter("phoneNum1") + request.getParameter("phoneNum2") + request.getParameter("phoneNum3"));
		if((String)paramMap.get("nickname") != null ) {
		to.setNickname((String)paramMap.get("nickname"));
		} else {
			to.setNickname((String)paramMap.get("email"));
		}
		to.setSmoke_years(Date.valueOf((String)paramMap.get("somke_years")));
		to.setBirthday(Date.valueOf((String)paramMap.get("birthday")));
		//int flag = dao.member_ok(to);
		// --- 주민번호 검사 ---
		String jumin_Front = (String)paramMap.get("jumin_front");
		String jumin_Back = (String)paramMap.get("jumin_back");
		String jumin = jumin_Front.trim() + jumin_Back.trim();
				
		int[] bits = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};
		
		int sum = 0;
		for(int i=0 ; i<bits.length ; i++) {
			sum += Integer.parseInt(jumin.substring(i, i+1) ) * bits[i];
		}
		
		int lastNum = Integer.parseInt(jumin.substring(12,13));
		int resultNum = (11- (sum%11) )%10;

		// --- 성인 인증 ---
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String now = localDate.format(dateformatter);
		
		// --- 99년 이전은 앞에 19 붙이기
		if(Integer.parseInt(jumin_Front) < 19991231) {
			jumin_Front = "19" + jumin_Front;
		} else {
			jumin_Front = "20" + jumin_Front;
		}
		
		int adult_Check = Integer.parseInt(now) - Integer.parseInt(jumin_Front) / 10000;
		System.out.println(jumin_Front);
		
		if(lastNum == resultNum) {
			System.out.println("올바른 주민번호");
			if(adult_Check < 19) {
				System.out.println("성인 아님");
				flag = 3;
			} else {
				
				flag = dao.member_Ok(to);
			}
		} else {
			flag = 2;
		}
		
		member_Ok_Obj.put("flag", flag);
		System.out.println();
		//System.out.println(result);
		return member_Ok_Obj;
	}
	
	//회원가입시 아이디 중복 검사
	@RequestMapping(value = "api/member_id_check.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject memberIdCheck(@RequestParam Map<String, Object> paramMap ,HttpServletRequest request, HttpServletRequest response) {
		JSONObject memberIdCheck = new JSONObject();
		MemberTO to = new MemberTO();
		to.setEmail((String)paramMap.get("email"));
		int flag = dao.member_Id_Check(to);
		
		memberIdCheck.put("flag", flag);
		return memberIdCheck;
	}
	
	// 회원정보 보기 뷰페이지
	@RequestMapping(value = "api/memberview.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject member_View(@RequestParam Map<String, Object> paramMap ,HttpServletRequest request, HttpServletRequest response) {
		MemberTO to = new MemberTO();
		ReviewTO rto = new ReviewTO();
		FreeBoardTO fto = new FreeBoardTO();
		GongjiTO gto= new GongjiTO();
		RequestCigarTO requestTO = new RequestCigarTO();
		
		HttpSession session = request.getSession();
//		fto.setFree_writer_seq((int)session.getAttribute("member_seq"));	
//		rto.setReview_writer_seq((int)session.getAttribute("member_seq"));
//		gto.setGongji_writer_seq((int)session.getAttribute("member_seq"));
//		requestTO.setRequest_writer_seq((int)session.getAttribute("member_seq"));

		fto.setFree_writer_seq(10006);	
		rto.setReview_writer_seq(10006);
		gto.setGongji_writer_seq(10006);
		requestTO.setRequest_writer_seq(10006);
		
		ArrayList<FreeBoardTO> freeLists = dao.free_board_lists(fto);
		ArrayList<ReviewTO> reviewLists = dao.review_board_lists(rto);
		ArrayList<GongjiTO> gongjiLists = dao.gongji_board_lists(gto);
		ArrayList<RequestCigarTO> requestLists = dao.request_board_lists(requestTO);

//		to.setMember_seq((int)session.getAttribute("member_seq"));
		to.setMember_seq(10006);
		to = dao.member_View(to);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String smokeYears = format.format(to.getSmoke_years());
		String now = format.format(System.currentTimeMillis());
		int smokeDiff = (Integer.parseInt(now) - Integer.parseInt(smokeYears)) /10000;
		
		JSONObject member_View_Obj = new JSONObject();
		JSONArray memberInfoObj = new JSONArray();
		member_View_Obj.put("member_seq", to.getMember_seq());
		member_View_Obj.put("email", to.getEmail());
		member_View_Obj.put("name", to.getName());
		member_View_Obj.put("nickname", to.getNickname());
		member_View_Obj.put("phoneNum", to.getPhone());
		member_View_Obj.put("smoke_years", smokeDiff);
		member_View_Obj.put("sign_date", to.getSign_date().toString());
		member_View_Obj.put("birthday", to.getBirthday().toString());
		memberInfoObj.add(member_View_Obj);
		
		JSONArray jsonArrayReview = new JSONArray();
		for(ReviewTO reviewTO : reviewLists) {
		JSONObject obj_review = new JSONObject();
		obj_review.put("review_seq", reviewTO.getReview_seq());
		obj_review.put("review_writer_seq", reviewTO.getReview_writer_seq());
		obj_review.put("review_subject", reviewTO.getReview_subject());
		obj_review.put("review_writer", reviewTO.getReview_writer());
		jsonArrayReview.add(obj_review);
		}
		
		JSONArray jsonArrayFree = new JSONArray();
		for(FreeBoardTO freeTo : freeLists) {
		JSONObject obj_free = new JSONObject();
		obj_free.put("free_seq", freeTo.getFree_seq());
		obj_free.put("free_writer_seq", freeTo.getFree_writer_seq());
		obj_free.put("free_subject", freeTo.getFree_subject());
		obj_free.put("free_writer", freeTo.getFree_writer());
		jsonArrayFree.add(obj_free);
		}
		
		JSONArray jsonArrayGongji = new JSONArray();
		for(GongjiTO gongjiTO : gongjiLists) {
		JSONObject obj_Gongji = new JSONObject();
		obj_Gongji.put("gongji_seq", gongjiTO.getGongji_seq());
		obj_Gongji.put("gongji_writer_seq", gongjiTO.getGongji_writer_seq());
		obj_Gongji.put("gongji_subject", gongjiTO.getGongji_subject());
		obj_Gongji.put("gongji_writer", gongjiTO.getGongji_writer());
		jsonArrayGongji.add(obj_Gongji);
		}
		
		JSONArray jsonArrayRequest = new JSONArray();
		for(RequestCigarTO requestsTO : requestLists) {
		JSONObject obj_Request = new JSONObject();
		obj_Request.put("request_seq", requestsTO.getRequest_seq());
		obj_Request.put("request_writer_seq", requestsTO.getRequest_writer_seq());
		obj_Request.put("request_subject", requestsTO.getRequest_subject());
		obj_Request.put("request_writer", requestsTO.getRequest_writer());
		jsonArrayRequest.add(obj_Request);
		}
		
		JSONObject memberview = new JSONObject();
		memberview.put("request_list", jsonArrayRequest);
		memberview.put("gongji_list", jsonArrayGongji);
		memberview.put("free_list", jsonArrayFree);
		memberview.put("review_list", jsonArrayReview);
		memberview.put("memberInfoObj", memberInfoObj);
		
		System.out.println(memberview);
		return memberview;
	}
	
	// 회원정보 수정
	@RequestMapping(value = "api/member_modify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject member_modify(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletRequest response) {
		MemberTO to = new MemberTO();
		HttpSession session = request.getSession();
		JSONObject member_Modify_Obj = new JSONObject();
		
		to.setMember_seq((int)session.getAttribute("member_seq"));
		to = dao.member_modify(to);
		
		member_Modify_Obj.put("email", to.getEmail());
		member_Modify_Obj.put("name", to.getName());
		member_Modify_Obj.put("nickname", to.getNickname());
		member_Modify_Obj.put("phoneNum", to.getPhone());
		member_Modify_Obj.put("smoke_years", to.getSmoke_years());
		member_Modify_Obj.put("sign_date", to.getSign_date());
		member_Modify_Obj.put("birthday", to.getBirthday());
		System.out.println(member_Modify_Obj);

		return member_Modify_Obj;
	}
	
	// 회원정보 수정 확인
	@RequestMapping(value = "api/member_modify_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject member_modify_ok(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletRequest response) {
		MemberTO to = new MemberTO();
		HttpSession session = request.getSession();
		JSONObject member_modify_ok = new JSONObject();
		to.setMember_seq((int)session.getAttribute("member_seq"));
		to.setName((String)paramMap.get("name"));
		to.setPhone((String)paramMap.get("phone"));
		to.setNickname((String)paramMap.get("nickname"));
		to.setSmoke_years(Date.valueOf((String)paramMap.get("smoke_years")));
		to.setBirthday(Date.valueOf((String)paramMap.get("birthday")));
		int flag = dao.member_Modify_Ok(to);
		
		member_modify_ok.put("flag", flag);
		//System.out.println(member_Modify_Ok_Obj);
		
		return member_modify_ok;
	}
	
//	//비밀번호 수정 뷰페이지
//	@RequestMapping(value = "/member_modify_password.do", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView member_modify_password (HttpServletRequest request, HttpServletRequest response) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName( "memberViews/memberModifyPassword" );
//		return mav;
//	}
	
	//비밀번호 수정 확인
	@RequestMapping(value = "api/modifyPassword.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject member_modify_password_ok (@RequestParam Map<String, Object> paramMap ,HttpServletRequest request, HttpServletRequest response) {
		MemberTO to = new MemberTO();
		JSONObject modifyPassword = new JSONObject();
		HttpSession session = request.getSession();
		String newPassword = (String)paramMap.get("newpassowrd");
		to.setMember_seq((int)session.getAttribute("member_seq"));
		to.setPassword((String)paramMap.get("oldpassword"));
		
		int flag = dao.member_Modify_Password(newPassword,to);
		modifyPassword.put("flag", flag);
		return modifyPassword;
	}
	
	// 메일 보내기 뷰페이지
//	@RequestMapping(value = "/mail.do", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView mail(HttpServletRequest request, HttpServletRequest response) {		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName( "memberViews/memberMail" );
//		return mav;
//	}

	// 메일 유효성 검사
	@RequestMapping(value = "api/mail_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
	public JSONObject mail_ok(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletRequest response) {		
		MemberTO to = new MemberTO();
		JSONObject mail_ok = new JSONObject();
		
		String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
	    StringBuilder password = new StringBuilder();
	    for(int i = 0; i < 20; i++) {
	    	int index = random.nextInt(CHAR_SET.length());   
	    	password.append(CHAR_SET.charAt(index));    
	    }
	    String tempPassword = password.toString();
	    to.setBirthday(Date.valueOf((String)paramMap.get("birthday")));
		to.setName((String)paramMap.get("name"));
		to.setPhone((String)paramMap.get("phoneNum1") + (String)paramMap.get("phoneNum2") + (String)paramMap.get("phoneNum3"));
		to.setEmail((String)paramMap.get("email"));
		int flag = dao.member_mail(to, tempPassword);
		
		mail_ok.put("flag", flag);
		
		return mail_ok;
	}

//	// 회원 삭제, 회원 탈퇴 뷰페이지
//	@RequestMapping("/member_delete.do")
//	public ModelAndView member_Delete(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName( "memberViews/memberDelete" );
//		return mav;
//	}
	
	//회원 삭제, 회원 탈퇴 확인
	@RequestMapping("api/member_delete.do")
	public JSONObject member_Delete_Ok(@RequestParam Map<String, Object> paramMap ,HttpServletRequest request, HttpServletResponse response) {
		MemberTO to = new MemberTO();
		JSONObject member_delete = new JSONObject();
		HttpSession session = request.getSession();
		
		to.setEmail((String)paramMap.get("email") );
		to.setPassword((String)paramMap.get("password"));
		to.setMember_seq((int)session.getAttribute("member_seq"));
		int flag = dao.member_Delete_Ok(to);
		
		member_delete.put("flag", flag);
		
		return member_delete;
	}
	
}
