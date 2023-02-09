package com.project.gongji.to;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GongjiCommentTO {
	
	private int gongji_cmt_seq;
	private int gongji_pseq;
	private int gongji_cmt_writer_seq;
	private int gongji_grp;
	private int gongji_grps;
	private int gongji_grpl;
	private String gongji_cmt_writer;
	private String gongji_cmt_content;
	private Date gongji_cmt_reg_date;
}
