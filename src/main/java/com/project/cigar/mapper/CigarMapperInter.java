package com.project.cigar.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.cigar.to.CigarTO;

@Mapper
public interface CigarMapperInter {

	@Select("select cigar_seq, cigar_writer_seq, cigar_brand, cigar_name, cigar_tar, cigar_nicotine, "
			+ "cigar_file_name, cigar_file_size, cigar_hash_tag, cigar_content, cigar_total_grade cigar_reg_date, cigar_hit "
			+ "from cigar_list order by cigar_seq desc")
	ArrayList<CigarTO> cigarList();
	
	@Update("update cigar_list set cigar_hit = cigar_hit+1 where cigar_seq = #{cigar_seq}")
	int cigarView_hit(CigarTO to);
	
	@Select("select cigar_seq, cigar_writer_seq, cigar_brand, cigar_name, cigar_tar, cigar_nicotine, "
			+ "cigar_file_name, cigar_file_size, cigar_hash_tag, cigar_content, cigar_total_grade cigar_reg_date, cigar_hit "
			+ "from cigar_list where cigar_seq = #{cigar_seq}")
	CigarTO cigarView(CigarTO to);
	
	@Insert("insert into cigar_list values "
			+ "(0, #{cigar_writer_seq}, #{cigar_brand}, #{cigar_name},  #{cigar_tar}, #{cigar_nicotine}, "
			+ "#{cigar_file_name}, #{cigar_file_size}, #{cigar_hash_tag}, #{cigar_content}, #{cigar_total_grade}, now(), 0)")
	int cigarWirte_ok(CigarTO to);
	
	@Select("select cigar_seq, cigar_writer_seq, cigar_brand, cigar_name, cigar_tar, cigar_nicotine, "
			+ "cigar_file_name, cigar_file_size, cigar_hash_tag, cigar_content, cigar_total_grade cigar_reg_date, cigar_hit "
			+ "from cigar_list where cigar_seq = #{cigar_seq}")
	CigarTO cigarModify(CigarTO to);
	
	@Update("update cigar_list set "
			+ "cigar_brand=#{cigar_brand}, cigar_name=#{cigar_name}, cigar_tar=#{cigar_tar}, cigar_nicotine=#{cigar_nicotine}, "
			+ "cigar_file_name=#{cigar_file_name}, cigar_file_size=#{cigar_file_size}, cigar_hash_tag=#{cigar_hash_tag}, cigar_content=#{cigar_content} where cigar_seq=#{cigar_seq}")
	int cigarModify_ok(CigarTO to);
	
	@Select("select cigar_seq, cigar_writer_seq, cigar_brand, cigar_name, cigar_tar, cigar_nicotine, "
			+ "cigar_file_name, cigar_file_size, cigar_hash_tag, cigar_content, cigar_total_grade cigar_reg_date, cigar_hit "
			+ "from cigar_list where cigar_seq = #{cigar_seq}")
	CigarTO cigarDelete(CigarTO to);
	
	@Delete("delete from cigar_list where cigar_seq=#{cigar_seq} and cigar_writer_seq=#{cigar_writer_seq}")
	int cigarDelete_ok(CigarTO to);
	
}
