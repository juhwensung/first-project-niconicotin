package com.project.free_board.to;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeBoardTO {
	
	private int free_seq;
	private int free_writer_seq;
	private String free_subject;
	private String free_writer;
	private Date free_reg_date;
	private String free_content;
	private int free_hit;
	private int free_cmt_count;
	private Date free_smoke_years;
}
