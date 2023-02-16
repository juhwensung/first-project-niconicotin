package com.project.gongji.to;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GongjiTO {

	private int gongji_seq;
	private int gongji_writer_seq;
	private String gongji_subject;
	private String gongji_writer;
	private Date gongji_reg_date;
	private String gongji_content;
	private int gongji_hit;
	private int gongji_cmt_count;
	private Date gongji_smoke_years;
}
