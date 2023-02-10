package com.project.requestCigar.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.requestCigar.to.RequestCigarCommentTO;

@Mapper
public interface RequestCigarCommentMapperInter {

	@Select("select request_cmt_seq, request_pseq, request_cmt_writer_seq, request_grp, request_grps, request_grpl, "
			+ "request_cmt_writer, request_cmt_content, request_cmt_reg_date "
			+ "from request_comment where request_pseq=#{request_pseq} order by request_grp desc, request_grps asc")
	ArrayList<RequestCigarCommentTO> requestCommentList(RequestCigarCommentTO to);
	
	@Select("select max(request_grp) as request_grp, request_pseq from request_comment where request_pseq=#{request_pseq}")
	RequestCigarCommentTO requestParentCommentWrite(RequestCigarCommentTO to);
	
	@Select("select request_cmt_seq, request_pseq, request_grp, request_grps, request_grpl, request_cmt_writer "
			+ "from request_comment where request_cmt_seq=#{request_cmt_seq}")
	RequestCigarCommentTO requestCommentWrite(RequestCigarCommentTO to);
	
	@Select("select request_grp, request_grps from request_comment where request_cmt_seq=#{parentSeq}")
	RequestCigarCommentTO requestCommentPseqGrp(int parentSeq);
	
	@Update("update request_comment set request_grps=request_grps+1 where request_grp=#{request_grp} and request_grps>#{request_grps}")
	void requestCommentParentsUP(RequestCigarCommentTO pTO);
	
	@Insert("insert into request_comment values (0, #{request_pseq}, #{request_cmt_writer_seq}, #{request_grp}, "
			+ "#{request_grps}, #{request_grpl}, #{request_cmt_writer}, #{request_cmt_content}, now())")
	int requestParentCommentWriteOk(RequestCigarCommentTO to);
	
	
	@Insert("insert into request_comment values (0, #{request_pseq}, #{request_cmt_writer_seq}, #{request_grp}, "
			+ "#{request_grps}+1, #{request_grpl}+1, #{request_cmt_writer}, #{request_cmt_content}, now())")
	int requestCommentWriteOk(RequestCigarCommentTO to);
	
	@Select("select request_cmt_seq, request_pseq, request_cmt_writer_seq, request_grp, request_grps, request_grpl, "
			+ "request_cmt_writer, request_cmt_content, request_cmt_reg_date "
			+ "from request_comment where request_cmt_seq=#{request_cmt_seq}")
	RequestCigarCommentTO requestCommentModify(RequestCigarCommentTO to);
	
	@Update("update request_comment set request_cmt_seq=#{request_cmt_seq}, request_pseq=#{request_pseq}, request_cmt_writer_seq=#{request_cmt_writer_seq}, "
			+ "request_grp=#{request_grp}, request_grps=#{request_grps}, request_grpl=#{request_grpl}, "
			+ "request_cmt_writer=#{request_cmt_writer}, request_cmt_content=#{request_cmt_content}, request_cmt_reg_date=#{request_cmt_reg_date} "
			+ "where request_cmt_seq=#{request_cmt_seq}")
	int requestCommentModifyOk(RequestCigarCommentTO to);
	
	@Select("select request_cmt_seq, request_pseq, request_cmt_writer_seq, request_grp, request_grps, request_grpl, "
			+ "request_cmt_writer, request_cmt_content, request_cmt_reg_date "
			+ "from request_comment where request_cmt_seq=#{request_cmt_seq}")
	RequestCigarCommentTO requestCommentDelete(RequestCigarCommentTO to);
	
	@Update("update request_comment set request_cmt_content=#{content} where request_cmt_seq=#{seq}")
	int requestCommentDeleteOk(String content, int seq);
}
