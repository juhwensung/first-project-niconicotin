package com.project.gongji.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.gongji.to.GongjiTO;

@Mapper
public interface GongjiMapperInter {
	
	@Select("select gongji_seq, gongji_writer_seq, gongji_subject, gongji_writer, gongji_reg_date, gongji_content, gongji_hit, gongji_cmt_count, "
			+ "gongji_file_name, gongji_file_size, gongji_smoke_years, gongji_public "
			+ "from gongji_board order by gongji_seq desc")
	ArrayList<GongjiTO> gongjiList();
	
	@Update("update gongji_board set gongji_hit = gongji_hit+1 where gongji_seq = #{gongji_seq}")
	int gongjiView_hit(GongjiTO to);
	
	@Select("select gongji_seq, gongji_writer_seq, gongji_subject, gongji_writer, gongji_reg_date, gongji_content, gongji_hit, gongji_cmt_count, "
			+ "gongji_file_name, gongji_file_size, gongji_smoke_years, gongji_public "
			+ "from gongji_board where gongji_seq=#{gongji_seq}")
	GongjiTO gongjiView(GongjiTO to);
	
	@Insert("insert into gongji_board values (0, #{gongji_writer_seq}, #{gongji_subject}, #{gongji_writer},  now(), #{gongji_content}, "
			+ "0, 0, #{gongji_file_name}, #{gongji_file_size}, #{gongji_smoke_years}, #{gongji_public})")
	int gongjiWirte_ok(GongjiTO to);

	@Select("select gongji_seq, gongji_writer_seq, gongji_subject, gongji_writer, gongji_reg_date, gongji_content, gongji_hit, gongji_cmt_count, "
			+ "gongji_file_name, gongji_file_size, gongji_smoke_years, gongji_public "
			+ "from gongji_board where gongji_seq=#{gongji_seq}")
	GongjiTO gongjiModify(GongjiTO to);
	
	@Update("update gongji_board set gongji_subject=#{gongji_subject}, gongji_content=#{gongji_content}, "
			+ "gongji_file_name=#{gongji_file_name}, gongji_file_size=#{gongji_file_size}, gongji_public=#{gongji_public} "
			+ "where gongji_seq=#{gongji_seq}")
	int gongjiModify_ok(GongjiTO to);
	
	@Update("update gongji_board set "
			+ "gongji_subject=#{gongji_subject}, gongji_content=#{gongji_content}"
			+ "where gongji_seq=#{gongji_seq}")
	int gongjiModify_Ok_NoImage(GongjiTO to);
	
	@Select("select gongji_seq, gongji_writer_seq, gongji_subject, gongji_writer, gongji_reg_date, gongji_content, gongji_hit, gongji_cmt_count, "
			+ "gongji_file_name, gongji_file_size, gongji_smoke_years, gongji_public from gongji_board "
			+ "where gongji_seq=#{gongji_seq}")
	GongjiTO gongjiDelete(GongjiTO to);
	
	@Delete("delete from gongji_board where gongji_seq=#{gongji_seq} and gongji_writer_seq=#{gongji_writer_seq}")
	int gongjiDelete_ok(GongjiTO to);
	
	@Select("select gongji_seq, gongji_writer_seq, gongji_subject, gongji_writer, gongji_reg_date, gongji_content, gongji_hit, gongji_cmt_count, "
			+ "gongji_file_name, gongji_file_size, gongji_smoke_years, gongji_public from gongji_board "
			+ " where gongji_subject like CONCAT('%', #{gongji_subject}, '%')"
			+ " or gongji_content like CONCAT('%', #{gongji_content}, '%')"
			+ " or gongji_writer like CONCAT('%', #{gongji_writer}, '%')")
	ArrayList<GongjiTO> GongjiBoardSearch(GongjiTO to);
}
