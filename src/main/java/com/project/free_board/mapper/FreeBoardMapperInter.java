package com.project.free_board.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.free_board.to.FreeBoardTO;

@Mapper
public interface FreeBoardMapperInter {
	
	@Select("select free_seq, free_writer_seq, free_subject, free_writer, free_reg_date, free_content, free_hit, free_cmt_count, "
			+ "free_file_size, free_file_name, free_smoke_years, free_public "
			+ "from free_board order by free_seq desc")
	ArrayList<FreeBoardTO> freeList();
	
	@Update("update free_board set free_hit = free_hit+1 where free_seq=#{free_seq}")
	int freeViewHit(FreeBoardTO to);
	
	@Select("select free_seq, free_writer_seq, free_subject, free_writer, free_reg_date, free_content, free_hit, free_cmt_count, "
			+ "free_file_size, free_file_name, free_smoke_years, free_public "
			+ "from free_board where free_seq=#{free_seq}")
	FreeBoardTO freeView(FreeBoardTO to);
	
	@Insert("insert into free_board values"
			+ "(0, #{free_writer_seq}, #{free_subject}, #{free_writer}, now(), #{free_content}, 0, 0, #{free_file_name},"
			+ " #{free_file_size}, now(), #{free_public})")
	int freeWrite_Ok(FreeBoardTO to);
	
	@Select("select free_seq, free_writer_seq, free_subject, free_writer, free_reg_date, free_content, free_hit, free_cmt_count, "
			+ "free_file_size, free_file_name, free_smoke_years, free_public "
			+ "from free_board where free_seq=#{free_seq}")
	FreeBoardTO freeModify(FreeBoardTO to);
	
	@Update("update free_board set "
			+ "free_subject=#{free_subject}, free_content=#{free_content}, free_file_name=#{free_file_name}, free_file_size=#{free_file_size}, free_public=#{free_public} "
			+ "where free_seq=#{free_seq}")
	int freeModify_Ok(FreeBoardTO to);
	
	@Update("update free_board set "
			+ "free_subject=#{free_subject}, free_content=#{free_content}"
			+ "where free_seq=#{free_seq}")
	int freeModify_Ok_NoImage(FreeBoardTO to);
	
	@Select("select free_seq, free_writer_seq, free_subject, free_writer, free_reg_date, free_content, free_hit, free_cmt_count, free_file_size, free_file_name, free_smoke_years "
			+ "from free_board where free_seq=#{free_seq}")
	FreeBoardTO freeDelete(FreeBoardTO to);
	
	@Delete("delete from free_board where free_seq=#{free_seq} and free_writer_seq=#{free_writer_seq}")
	int freeDelete_Ok(FreeBoardTO to);
	
	@Delete("delete from free_comment where free_pseq=#{free_seq}")
	int freeDeleteAllComment(FreeBoardTO to);
}
