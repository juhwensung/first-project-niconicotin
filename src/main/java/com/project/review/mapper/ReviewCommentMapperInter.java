package com.project.review.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.review.to.ReviewCommentTO;

@Mapper
public interface ReviewCommentMapperInter {
	
	@Select("select review_cmt_seq, review_pseq, review_cmt_writer_seq, review_grp, review_grps, review_grpl, "
			+ "review_cmt_writer, review_cmt_content, review_cmt_reg_date "
			+ "from review_comment where review_pseq=#{review_pseq} order by review_grp desc, review_grps asc")
	ArrayList<ReviewCommentTO> reviewCommentList(ReviewCommentTO to);
	
	@Select("select max(review_grp) as review_grp, review_pseq from review_comment where review_pseq=#{review_pseq}")
	ReviewCommentTO reviewParentCommentWrite(ReviewCommentTO to);
	
	@Select("select review_cmt_seq, review_pseq, review_grp, review_grps, review_grpl, review_cmt_writer "
			+ "from review_comment where review_cmt_seq=#{review_cmt_seq}")
	ReviewCommentTO reviewCommentWrite(ReviewCommentTO to);
	
	@Select("select review_grp, review_grps from review_comment where review_cmt_seq=#{parentSeq}")
	ReviewCommentTO reviewCommentPseqGrp(int parentSeq);
	
	@Update("update review_comment set review_grps=review_grps+1 where review_grp=#{review_grp} and review_grps>#{review_grps}")
	void reviewCommentParentsUP(ReviewCommentTO pTO);
	
	@Insert("insert into review_comment values (0, #{review_pseq}, #{review_cmt_writer_seq}, #{review_grp}, "
			+ "#{review_grps}, #{review_grpl}, #{review_cmt_writer}, #{review_cmt_content}, now())")
	int reviewParentCommentWriteOk(ReviewCommentTO to);
	
	
	@Insert("insert into review_comment values (0, #{review_pseq}, #{review_cmt_writer_seq}, #{review_grp}, "
			+ "#{review_grps}+1, #{review_grpl}+1, #{review_cmt_writer}, #{review_cmt_content}, now())")
	int reviewCommentWriteOk(ReviewCommentTO to);
	
	@Select("select review_cmt_seq, review_pseq, review_cmt_writer_seq, review_grp, review_grps, review_grpl, "
			+ "review_cmt_writer, review_cmt_content, review_cmt_reg_date "
			+ "from review_comment where review_cmt_seq=#{review_cmt_seq}")
	ReviewCommentTO reviewCommentModify(ReviewCommentTO to);
	
	@Update("update review_comment set review_cmt_seq=#{review_cmt_seq}, review_pseq=#{review_pseq}, review_cmt_writer_seq=#{review_cmt_writer_seq}, "
			+ "review_grp=#{review_grp}, review_grps=#{review_grps}, review_grpl=#{review_grpl}, "
			+ "review_cmt_writer=#{review_cmt_writer}, review_cmt_content=#{review_cmt_content}, review_cmt_reg_date=#{review_cmt_reg_date} "
			+ "where review_cmt_seq=#{review_cmt_seq}")
	int reviewCommentModifyOk(ReviewCommentTO to);
	
	@Select("select review_cmt_seq, review_pseq, review_cmt_writer_seq, review_grp, review_grps, review_grpl, "
			+ "review_cmt_writer, review_cmt_content, review_cmt_reg_date "
			+ "from review_comment where review_cmt_seq=#{review_cmt_seq}")
	ReviewCommentTO reviewCommentDelete(ReviewCommentTO to);
	
	@Update("update review_comment set review_cmt_content=#{content} where review_cmt_seq=#{seq}")
	int reviewCommentDeleteOk(String content, int seq);
}
