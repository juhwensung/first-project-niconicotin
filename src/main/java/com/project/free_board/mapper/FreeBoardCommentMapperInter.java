package com.project.free_board.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.free_board.to.FreeBoardCommentTO;

@Mapper
public interface FreeBoardCommentMapperInter {
	
	@Select("select free_cmt_seq, free_pseq, free_cmt_writer_seq, free_grp, free_grps, free_grpl, "
			+ "free_cmt_writer, free_cmt_content, free_cmt_reg_date "
			+ "from free_comment where free_pseq=#{free_pseq} order by free_grp desc, free_grps asc")
	ArrayList<FreeBoardCommentTO> freeCommentList(FreeBoardCommentTO to);
	
	@Select("select max(free_grp) as free_grp, free_pseq from free_comment where free_pseq=#{free_pseq}")
	FreeBoardCommentTO freeParentCommentWrite(FreeBoardCommentTO to);
	
	@Select("select free_cmt_seq, free_pseq, free_grp, free_grps, free_grpl, free_cmt_writer "
			+ "from free_comment where free_cmt_seq=#{free_cmt_seq}")
	FreeBoardCommentTO freeCommentWrite(FreeBoardCommentTO to);
	
	@Select("select free_grp, free_grps from free_comment where free_cmt_seq=#{parentSeq}")
	FreeBoardCommentTO freeCommentPseqGrp(int parentSeq);
	
	@Update("update free_comment set free_grps=free_grps+1 where free_grp=#{free_grp} and free_grps>#{free_grps}")
	void freeCommentParentsUP(FreeBoardCommentTO pTO);
	
	@Insert("insert into free_comment values (0, #{free_pseq}, #{free_cmt_writer_seq}, #{free_grp}, "
			+ "#{free_grps}, #{free_grpl}, #{free_cmt_writer}, #{free_cmt_content}, now())")
	int freeParentCommentWriteOk(FreeBoardCommentTO to);
	
	
	@Insert("insert into free_comment values (0, #{free_pseq}, #{free_cmt_writer_seq}, #{free_grp}, "
			+ "#{free_grps}+1, #{free_grpl}+1, #{free_cmt_writer}, #{free_cmt_content}, now())")
	int freeCommentWriteOk(FreeBoardCommentTO to);
	
	@Select("select free_cmt_seq, free_pseq, free_cmt_writer_seq, free_grp, free_grps, free_grpl, "
			+ "free_cmt_writer, free_cmt_content, free_cmt_reg_date "
			+ "from free_comment where free_cmt_seq=#{free_cmt_seq}")
	FreeBoardCommentTO freeCommentModify(FreeBoardCommentTO to);
	
	@Update("update free_comment set free_cmt_seq=#{free_cmt_seq}, free_pseq=#{free_pseq}, free_cmt_writer_seq=#{free_cmt_writer_seq}, "
			+ "free_grp=#{free_grp}, free_grps=#{free_grps}, free_grpl=#{free_grpl}, "
			+ "free_cmt_writer=#{free_cmt_writer}, free_cmt_content=#{free_cmt_content}, free_cmt_reg_date=#{free_cmt_reg_date} "
			+ "where free_cmt_seq=#{free_cmt_seq}")
	int freeCommentModifyOk(FreeBoardCommentTO to);
	
	@Select("select free_cmt_seq, free_pseq, free_cmt_writer_seq, free_grp, free_grps, free_grpl, "
			+ "free_cmt_writer, free_cmt_content, free_cmt_reg_date "
			+ "from free_comment where free_cmt_seq=#{free_cmt_seq}")
	FreeBoardCommentTO freeCommentDelete(FreeBoardCommentTO to);
	
	@Update("update free_comment set free_cmt_content=#{content} where free_cmt_seq=#{seq}")
	int freeCommentDeleteOk(String content, int seq);
}
