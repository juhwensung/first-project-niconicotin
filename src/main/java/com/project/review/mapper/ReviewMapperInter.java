package com.project.review.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.review.to.ReviewDislikeCheckTO;
import com.project.review.to.ReviewLikeCheckTO;
import com.project.review.to.ReviewTO;

@Mapper
public interface ReviewMapperInter {
	
	@Select("select review_seq, review_writer_seq, review_cigar_seq, review_subject, review_writer, review_reg_date, review_content, "
			+ "review_hit, review_cmt_count, review_grade, review_like, review_dislike, review_smoke_years "
			+ "from review_board order by review_seq desc")
	ArrayList<ReviewTO> reviewList();
	
	@Update("update review_board set review_hit = review_hit+1 where review_seq = #{review_seq}")
	int reviewView_hit(ReviewTO to);
	
	@Select("select review_seq, review_writer_seq, review_cigar_seq, review_subject, review_writer, review_reg_date, review_content, review_hit, review_cmt_count, "
			+ "review_grade, review_like, review_dislike, review_smoke_years "
			+ "from review_board where review_seq = #{review_seq}")
	ReviewTO reviewView(ReviewTO to);
	
	@Insert("insert into review_board values (0, #{review_writer_seq}, #{review_cigar_seq}, #{review_subject}, #{review_writer}, now(), "
			+ "#{review_content}, 0, 0, #{review_grade}, 0, 0, #{review_smoke_years})")
	int reviewWirte_ok(ReviewTO to);
	
	@Select("select round(AVG(review_grade), 2) as avgGrade from review_board where review_cigar_seq=#{review_cigar_seq}")
	double reviewAvgGrade(ReviewTO to);
	
	@Update("update cigar_list set cigar_total_grade = #{avg} where cigar_seq = #{seq}")
	int reviewCigarAvgGrade(double avg, int seq);
	
	@Select("select review_seq, review_writer_seq, review_cigar_seq, review_subject, review_writer, review_reg_date, review_content, review_hit, review_cmt_count, "
			+ "review_grade, review_like, review_dislike, review_smoke_years "
			+ "from review_board where review_seq = #{review_seq}")
	ReviewTO reviewModify(ReviewTO to);
	
	@Update("update review_board set review_subject=#{review_subject}, review_content=#{review_content}, review_grade=#{review_grade} "
			+ "where review_seq=#{review_seq}")
	int reviewModify_ok(ReviewTO to);
	
	@Select("select review_seq, review_writer_seq, review_cigar_seq, review_subject, review_writer, review_reg_date, review_content, review_hit, review_cmt_count, "
			+ "review_grade, review_like, review_dislike, review_smoke_years "
			+ "from review_board where review_seq = #{review_seq}")
	ReviewTO reviewDelete(ReviewTO to);
	
	@Delete("delete from review_board where review_seq=#{review_seq} and review_writer_seq=#{review_writer_seq}")
	int reviewDelete_ok(ReviewTO to);
	
	@Update("update review_board set review_like = review_like+1 where review_seq = #{review_seq}")
	int reviewLike_Up(ReviewTO to);
	
	@Update("update review_board set review_like = review_like-1 where review_seq = #{review_seq}")
	int reviewLike_Down(ReviewTO to);
	
	@Update("update review_board set review_dislike = review_dislike+1 where review_seq = #{review_seq}")
	int reviewDislike_Up(ReviewTO to);
	
	@Update("update review_board set review_dislike = review_dislike-1 where review_seq = #{review_seq}")
	int reviewDislike_Down(ReviewTO to);
	
	@Select("select count(like_users_seq) like_users_seq, count(like_board_seq) like_board_seq from like_check_table where like_users_seq=#{like_users_seq}  and like_board_seq=#{like_board_seq};")
	ReviewLikeCheckTO reviewLike_Check(ReviewLikeCheckTO likeTO);
	
	@Select("select count(dislike_users_seq) dislike_users_seq, count(dislike_board_seq) dislike_board_seq from dislike_check_table where dislike_users_seq=#{dislike_users_seq} and dislike_board_seq=#{dislike_board_seq};")
	ReviewDislikeCheckTO reviewDislike_Check(ReviewDislikeCheckTO dislikeTO);
	
	@Insert("insert into like_check_table values (#{memberSeq}, #{pseq})")
	int reviewLike_Insert(int memberSeq , int pseq);
	
	@Insert("insert into dislike_check_table values (#{memberSeq}, #{pseq})")
	int reviewDislike_Insert(int memberSeq , int pseq);
	
	@Delete("delete from like_check_table where #{memberSeq} and #{pseq}")
	int reviewLike_Delete(int memberSeq , int pseq);
	
	@Delete("delete from dislike_check_table where #{memberSeq} and #{pseq}")
	int reviewDislike_Delete(int memberSeq , int pseq);
	
	@Select("select review_seq, review_writer_seq, review_cigar_seq, review_subject, review_writer, review_reg_date, review_content, review_hit, review_cmt_count,"
			+ "review_hit, review_cmt_count, review_grade, review_like, review_dislike, review_smoke_years "
			+ " where reveiw_subject like CONCAT('%', #{review_subject}, '%')"
			+ " or reveiw_content like CONCAT('%', #{review_content}, '%')"
			+ " or reveiw_writer like CONCAT('%', #{review_writer}, '%')")
	ArrayList<ReviewTO> ReviewBoardSearch(ReviewTO to);
}
