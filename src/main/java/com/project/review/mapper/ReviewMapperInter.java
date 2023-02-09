package com.project.review.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.review.to.ReviewTO;

@Mapper
public interface ReviewMapperInter {
	
	@Select("select review_seq, review_writer_seq, review_cigar_seq, review_subject, review_writer, review_reg_date, review_content, "
			+ "review_hit, review_cmt_count, review_grade, review_like, review_dislike, review_file_name, review_file_size, review_smoke_years "
			+ "from review_board order by review_seq desc")
	ArrayList<ReviewTO> reviewList();
	
	@Update("update review_board set review_hit = review_hit+1 where review_seq = #{review_seq}")
	int reviewView_hit(ReviewTO to);
	
	@Select("select review_seq, review_writer_seq, review_subject, review_writer, review_reg_date, review_content, review_hit, review_cmt_count, "
			+ "review_grade, review_like, review_dislike, review_file_name, review_file_size, review_smoke_years "
			+ "from review_board where review_seq = #{review_seq}")
	ReviewTO reviewView(ReviewTO to);
	
	@Insert("insert into review_board values (0, #{review_writer_seq}, #{review_cigar_seq}, #{review_subject}, #{review_writer}, now(), "
			+ "#{review_content}, 0, 0, #{review_grade}, 0, 0, #{review_file_name}, #{review_file_size}, now())")
	int reviewWirte_ok(ReviewTO to);
	
	@Select("select round(AVG(review_grade), 2) as avgGrade from review_board where review_cigar_seq=#{review_cigar_seq}")
	double reviewAvgGrade(ReviewTO to);
	
	@Update("update cigar_list set cigar_total_grade = #{avg} where cigar_seq = #{seq}")
	int reviewCigarAvgGrade(double avg, int seq);
	
	@Select("select review_seq, review_writer_seq, review_cigar_seq, review_subject, review_writer, review_reg_date, review_content, review_hit, review_cmt_count, "
			+ "review_grade, review_like, review_dislike, review_file_name, review_file_size, review_smoke_years "
			+ "from review_board where review_seq = #{review_seq}")
	ReviewTO reviewModify(ReviewTO to);
	
	@Update("update review_board set review_subject=#{review_subject}, review_content=#{review_content}, review_grade=#{review_grade}, "
			+ "review_file_name=#{review_file_name}, review_file_size=#{review_file_size} "
			+ "where review_seq=#{review_seq}")
	int reviewModify_ok(ReviewTO to);
	
	@Select("select review_seq, review_writer_seq, review_cigar_seq, review_subject, review_writer, review_reg_date, review_content, review_hit, review_cmt_count, "
			+ "review_grade, review_like, review_dislike, review_file_name, review_file_size, review_smoke_years "
			+ "from review_board where review_seq = #{review_seq}")
	ReviewTO reviewDelete(ReviewTO to);
	
	@Delete("delete from review_board where review_seq=#{review_seq}")
	int reviewDelete_ok(ReviewTO to);
}
