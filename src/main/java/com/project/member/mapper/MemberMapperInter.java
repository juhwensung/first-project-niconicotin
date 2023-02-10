package com.project.member.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.free_board.to.FreeBoardTO;
import com.project.gongji.to.GongjiTO;
import com.project.member.to.MemberTO;
import com.project.requestCigar.to.RequestCigarTO;
import com.project.review.to.ReviewTO;

@Mapper
public interface MemberMapperInter {
	@Select("select member_seq, email, name, address, phone, nickname, smoke_years, prefer_cigar, birthday from member_table "
			+ "where email=#{email} and password = HEX(AES_ENCRYPT(#{password}, SHA2('apfh2009@naver.com', 256)))")
	public MemberTO login_Ok(MemberTO to);
	
	@Insert("insert into member_table values (0, #{email}, HEX(AES_ENCRYPT(#{password}, SHA2('apfh2009@naver.com', 256))), #{name}, #{address}, #{phone} , #{nickname}, #{smoke_years} , #{prefer_cigar},  now(), #{birthday});")
	int member_Ok(MemberTO to);
	
	@Select("select count(email) email from member_table where email=#{email}")
	MemberTO member_Id_Check(MemberTO to);
	
	@Select("select member_seq, email, name, address, phone, nickname, date_format(smoke_years, '%Y-%m-%d') smoke_years, prefer_cigar, sign_date, birthday "
			+ "from member_table where member_seq=#{member_seq}")
	MemberTO member_View(MemberTO to);

	@Select("select member_seq, email, name, address, phone, nickname, smoke_years, prefer_cigar, sign_date, birthday "
			+ "from member_table where member_seq=#{member_seq}")
	MemberTO member_modify(MemberTO to);
	
	@Update("update member_table set name=#{name}, address=#{address}, phone=#{phone}, prefer_cigar=#{prefer_cigar}, birthday=#{birthday} where member_seq=#{member_seq} ")
	int member_modify_ok(MemberTO to);
	
	@Update("update member_table set password=HEX(AES_ENCRYPT(#{newPassword}, SHA2('apfh2009@naver.com', 256))) "
			+ "where member_seq=#{seq} and password=HEX(AES_ENCRYPT(#{oldPwd}, SHA2('apfh2009@naver.com', 256)))")
	int member_modify_password(String newPassword, int seq, String oldPwd);
	
	@Delete("delete from member_table where email=#{email} and password=HEX(AES_ENCRYPT(#{password}, SHA2('apfh2009@naver.com', 256))) and member_seq=#{member_seq}")
	int member_delete_ok(MemberTO to);
	
	@Select("select email from member_table where name=#{name} and phone=#{phone} and birthday=#{birthday} ")
	MemberTO search_Id(MemberTO to);
	
	@Select("select email, name, member_seq, nickname from member_table where email=#{email}")
	MemberTO mail_ok(MemberTO to);
	
	@Update("update member_table set password=HEX(AES_ENCRYPT(#{password}, SHA2('apfh2009@naver.com', 256))) where member_seq=#{seq}")
	int mail_password_ok(MemberTO to);
	
	@Select("select review_writer_seq, review_writer, review_seq, review_subject from review_board where review_writer_seq=#{review_writer_seq}")
	ArrayList<ReviewTO> my_review_write(ReviewTO rto);
	
	@Select("select free_writer_seq, free_writer, free_seq, free_subject from free_board where free_writer_seq=#{free_writer_seq}")
	ArrayList<FreeBoardTO> my_free_write(FreeBoardTO fto);
	 
	@Select("select gongji_writer_seq, gongji_writer, gongji_seq, gongji_subject from gongji_board where gongji_writer_seq=#{gongji_writer_seq}")
	ArrayList<GongjiTO> my_gongji_write(GongjiTO gto);
	
	@Select("select request_writer_seq, request_writer, request_seq, request_subject from cigar_request where request_writer_seq=#{request_writer_seq}")
	ArrayList<RequestCigarTO> my_request_write(RequestCigarTO requestTO);
	
}