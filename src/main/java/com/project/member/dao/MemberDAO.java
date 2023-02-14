package com.project.member.dao;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import com.project.free_board.to.FreeBoardTO;
import com.project.gongji.to.GongjiTO;
import com.project.member.mapper.MemberMapperInter;
import com.project.member.to.MemberTO;
import com.project.requestCigar.to.RequestCigarTO;
import com.project.review.to.ReviewTO;

@Repository
@MapperScan("com.project.member.mapper")
public class MemberDAO {

	@Autowired
	private MemberMapperInter memberMapperInter;
	
	@Autowired
	private JavaMailSender javaMailSender;

	public MemberTO login_Ok(MemberTO to) throws Exception {
		to = memberMapperInter.login_Ok(to);
		return to;
	}
	
	public MemberTO id_Search(MemberTO to) {
		to = memberMapperInter.search_Id(to);
		return to;
	}
	
	public int member_Id_Check(MemberTO to) {
		int flag = 2;
		System.out.println(to.getEmail());
		to = memberMapperInter.member_Id_Check(to);
		
		if(to.getEmail().equals("0")) {
			System.out.println(to.getEmail());
			flag = 0;
			System.out.println("사용가능한 id다 이거야");
		} else {
			flag = 1;
			System.out.println("중복 ID");
		};
		return flag;
	}
	
	public MemberTO member_View(MemberTO to) {
		to = memberMapperInter.member_View(to);
		return to;
	}
	
	public MemberTO member_mail(MemberTO to) {
		to = memberMapperInter.mail_ok(to);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		try {
			if(to.getEmail() != null) {
				String toName = to.getName();
				String subject = "비밀번호 변경 이메일입니다.";
				String content = "<a href='http://localhost:8080/login.do?meber_seq="+to.getMember_seq()+"'>이동하기</a>";
				mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(to.getEmail(), toName, "utf-8"));
				mimeMessage.setSubject(subject, "utf-8");
				mimeMessage.setText(content, "utf-8", "html");	
				javaMailSender.send(mimeMessage);
				System.out.println("전송이 완료 되었습니다");
			} else { 
				System.out.println("이메일이 존재 하지 않습니다.");
			}
			
			} catch (MailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return to;
	}
	
	public int mail_password_ok(MemberTO to) {
		int flag = 1;
		int result = memberMapperInter.mail_password_ok(to);
		if(result == 1 ) {
			flag = 0;
		}
		return flag;
	}
	
	public ArrayList<FreeBoardTO> free_board_lists(FreeBoardTO fto) {
		ArrayList<FreeBoardTO> freeLists = memberMapperInter.my_free_write(fto);
		//System.out.println(free_lists.size());
		return freeLists;
	}
	
	public ArrayList<ReviewTO> review_board_lists(ReviewTO rto) {
		ArrayList<ReviewTO> reviewLists = memberMapperInter.my_review_write(rto);
		//System.out.println(review_lists.size());
		return reviewLists;
	}
	
	public ArrayList<GongjiTO> gongji_board_lists(GongjiTO gto) {
		ArrayList<GongjiTO> gongjiLists = memberMapperInter.my_gongji_write(gto);
		return gongjiLists;
	}
	
	public ArrayList<RequestCigarTO> request_board_lists(RequestCigarTO requestTO) {
		ArrayList<RequestCigarTO> requestLists = memberMapperInter.my_request_write(requestTO);
		return requestLists;
	}
	
	public MemberTO member_modify(MemberTO to) {
		to = memberMapperInter.member_modify(to);
		return to;
	}
	
	public int member_Ok(MemberTO to) {
		int flag = 1;
		int result = memberMapperInter.member_Ok(to);
		
		if(result == 1 ) {
			flag = 0;
		}
		return flag;
	}
	
	public int member_Modify_Ok(MemberTO to) {
		int result = memberMapperInter.member_modify_ok(to);
		int flag = 1;
		if(result == 1 ) {
			flag = 0;
		}
		return flag;
	}
	
	public int member_Modify_Password(String newPassword, MemberTO to) {
		String oldPwd = to.getPassword();
		int seq = to.getMember_seq(); 
		int result = memberMapperInter.member_modify_password(newPassword, seq, oldPwd);

		int flag = 1;
		if(result == 1 ) {
			flag = 0;
		}
		return flag;
	}
	
	public int member_Delete_Ok(MemberTO to) {
		int flag =1;
		int result = memberMapperInter.member_delete_ok(to);
		if(result == 1) {
			flag = 0;
		}
		
		return flag;
	}
}
