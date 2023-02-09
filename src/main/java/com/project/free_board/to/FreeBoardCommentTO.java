package com.project.free_board.to;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeBoardCommentTO {

	private int free_cmt_seq;
	private int free_pseq;
	private int free_cmt_writer_seq;
	private int free_grp;
	private int free_grps;
	private int free_grpl;
	private String free_cmt_writer;
	private String free_cmt_content;
	private Date free_cmt_reg_date;
}
