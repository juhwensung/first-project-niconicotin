package com.project.review.to;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCommentTO {
	
	private int review_cmt_seq;
	private int review_pseq;
	private int review_cmt_writer_seq;
	private int review_grp;
	private int review_grps;
	private int review_grpl;
	private String review_cmt_writer;
	private String review_cmt_content;
	private Date review_cmt_reg_date;
}
