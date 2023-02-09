package com.project.gongji.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.gongji.to.GongjiCommentTO;

@Mapper
public interface GongjiCommentMapperInter {

	@Select("select gongji_cmt_seq, gongji_pseq, gongji_cmt_writer_seq, gongji_grp, gongji_grps, gongji_grpl, "
			+ "gongji_cmt_writer, gongji_cmt_content, gongji_cmt_reg_date "
			+ "from gongji_comment where gongji_pseq=#{gongji_pseq} order by gongji_grp desc, gongji_grps asc")
	ArrayList<GongjiCommentTO> gongjiCommentList(GongjiCommentTO to);
	
	@Select("select max(gongji_grp) as gongji_grp, gongji_pseq from gongji_comment where gongji_pseq=#{gongji_pseq}")
	GongjiCommentTO gongjiParentCommentWrite(GongjiCommentTO to);
	
	@Select("select gongji_cmt_seq, gongji_pseq, gongji_grp, gongji_grps, gongji_grpl, gongji_cmt_writer "
			+ "from gongji_comment where gongji_cmt_seq=#{gongji_cmt_seq}")
	GongjiCommentTO gongjiCommentWrite(GongjiCommentTO to);
	
	@Select("select gongji_grp, gongji_grps from gongji_comment where gongji_cmt_seq=#{parentSeq}")
	GongjiCommentTO gongjiCommentPseqGrp(int parentSeq);
	
	@Update("update gongji_comment set gongji_grps=gongji_grps+1 where gongji_grp=#{gongji_grp} and gongji_grps>#{gongji_grps}")
	void gongjiCommentParentsUP(GongjiCommentTO pTO);
	
	@Insert("insert into gongji_comment values (0, #{gongji_pseq}, #{gongji_cmt_writer_seq}, #{gongji_grp}, "
			+ "#{gongji_grps}, #{gongji_grpl}, #{gongji_cmt_writer}, #{gongji_cmt_content}, now())")
	int gongjiParentCommentWriteOk(GongjiCommentTO to);
	
	
	@Insert("insert into gongji_comment values (0, #{gongji_pseq}, #{gongji_cmt_writer_seq}, #{gongji_grp}, "
			+ "#{gongji_grps}+1, #{gongji_grpl}+1, #{gongji_cmt_writer}, #{gongji_cmt_content}, now())")
	int gongjiCommentWriteOk(GongjiCommentTO to);
	
	@Select("select gongji_cmt_seq, gongji_pseq, gongji_cmt_writer_seq, gongji_grp, gongji_grps, gongji_grpl, "
			+ "gongji_cmt_writer, gongji_cmt_content, gongji_cmt_reg_date "
			+ "from gongji_comment where gongji_cmt_seq=#{gongji_cmt_seq}")
	GongjiCommentTO gongjiCommentModify(GongjiCommentTO to);
	
	@Update("update gongji_comment set gongji_cmt_seq=#{gongji_cmt_seq}, gongji_pseq=#{gongji_pseq}, gongji_cmt_writer_seq=#{gongji_cmt_writer_seq}, "
			+ "gongji_grp=#{gongji_grp}, gongji_grps=#{gongji_grps}, gongji_grpl=#{gongji_grpl}, "
			+ "gongji_cmt_writer=#{gongji_cmt_writer}, gongji_cmt_content=#{gongji_cmt_content}, gongji_cmt_reg_date=#{gongji_cmt_reg_date} "
			+ "where gongji_cmt_seq=#{gongji_cmt_seq}")
	int gongjiCommentModifyOk(GongjiCommentTO to);
	
	@Select("select gongji_cmt_seq, gongji_pseq, gongji_cmt_writer_seq, gongji_grp, gongji_grps, gongji_grpl, "
			+ "gongji_cmt_writer, gongji_cmt_content, gongji_cmt_reg_date "
			+ "from gongji_comment where gongji_cmt_seq=#{gongji_cmt_seq}")
	GongjiCommentTO gongjiCommentDelete(GongjiCommentTO to);
	
	@Update("update gongji_comment set gongji_cmt_content=#{content} where gongji_cmt_seq=#{seq}")
	int gongjiCommentDeleteOk(String content, int seq);
}

