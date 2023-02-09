package com.project.requestCigar.to;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCigarCommentTO {
	
	private int request_cmt_seq;
	private int request_pseq;
	private int request_cmt_writer_seq;
	private int request_grp;
	private int request_grps;
	private int request_grpl;
	private String request_cmt_writer;
	private String request_cmt_content;
	private Date request_cmt_reg_date;

}
