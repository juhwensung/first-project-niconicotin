package com.project.requestCigar.to;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCigarTO {
	
	private int request_seq;
	private int request_writer_seq;
	private String request_subject;
	private String request_writer;
	private Date request_reg_date;
	private String request_content;
	private int request_hit;
	private int request_cmt_count;
	private String request_cigar_brand;
	private String request_cigar_name;
	private double request_tar;
	private double request_nicotine;
	private String request_file_name;
	private int request_file_size;
	private Date request_smoke_years;
}
