package com.project.requestCigar.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.requestCigar.to.RequestCigarTO;

@Mapper
public interface RequestCigarMapperInter {

	@Select("select request_seq, request_writer_seq, request_subject, request_writer, request_reg_date, request_content, request_hit, request_cmt_count, "
			+ "request_cigar_brand, request_cigar_name, request_tar, request_nicotine, request_file_name, request_file_size, request_smoke_years "
			+ "from cigar_request order by request_seq desc")
	ArrayList<RequestCigarTO> requestList();
	
	@Update("update cigar_request set request_hit = request_hit+1 where request_seq = #{request_seq}")
	int requestView_hit(RequestCigarTO to);
	
	@Select("select request_seq, request_writer_seq, request_subject, request_writer, request_reg_date, request_content, request_hit, request_cmt_count, "
			+ "request_cigar_brand, request_cigar_name, request_tar, request_nicotine, request_file_name, request_file_size, request_smoke_years "
			+ "from cigar_request where request_seq = #{request_seq}")
	RequestCigarTO requestView(RequestCigarTO to);
	
	@Insert("insert into cigar_request values (0, #{request_writer_seq}, #{request_subject}, #{request_writer}, now(), #{request_content}, 0, 0, "
			+ "#{request_cigar_brand}, #{request_cigar_name}, #{request_tar}, #{request_nicotine}, #{request_file_name}, #{request_file_size}, #{request_smoke_years})")
	int requestWirte_ok(RequestCigarTO to);

	@Select("select request_seq, request_writer_seq, request_subject, request_writer, request_reg_date, request_content, request_hit, request_cmt_count, "
			+ "request_cigar_brand, request_cigar_name, request_tar, request_nicotine, request_file_name, request_file_size, request_smoke_years "
			+ "from cigar_request where request_seq = #{request_seq}")
	RequestCigarTO requestModify(RequestCigarTO to);
	
	@Update("update cigar_request set request_subject=#{request_subject}, request_content=#{request_content}, request_cigar_brand=#{request_cigar_brand}, "
			+ "request_cigar_name=#{request_cigar_name}, request_tar=#{request_tar}, request_nicotine=#{request_nicotine}, request_file_name=#{request_file_name}, request_file_size=#{request_file_size} "
			+ "where request_seq=#{request_seq}")
	int requestModify_ok(RequestCigarTO to);
	
	@Select("select request_seq, request_writer_seq, request_subject, request_writer, request_reg_date, request_content, request_hit, request_cmt_count, "
			+ "request_cigar_brand, request_cigar_name, request_tar, request_nicotine, request_file_name, request_file_size, request_smoke_years "
			+ "from cigar_request where request_seq = #{request_seq}")
	RequestCigarTO requestDelete(RequestCigarTO to);
	
	@Delete("delete from cigar_request where request_seq=#{request_seq} and request_writer_seq=#{request_writer_seq}")
	int requestDelete_ok(RequestCigarTO to);
	
}
