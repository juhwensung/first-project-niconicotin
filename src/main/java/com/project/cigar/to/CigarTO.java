package com.project.cigar.to;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CigarTO {
	
	private int cigar_seq;
	private int cigar_writer_seq;
	private String cigar_brand;
	private String cigar_name;
	private double cigar_tar;
	private double cigar_nicotine;
	private String cigar_file_name;
	private int cigar_file_size;
	private String cigar_hash_tag;
	private String cigar_content;
	private double cigar_total_grade;
	private Date cigar_reg_date;
	private int cigar_hit;
}
/*
insert into cigar_list values 
(0, 1, '말보루', '화이트 후레쉬',  6.0, 0.5, '말보루.jpg', 60, '#해쉬#태그', '내용', 9.9,
		now(), 0); 
*/