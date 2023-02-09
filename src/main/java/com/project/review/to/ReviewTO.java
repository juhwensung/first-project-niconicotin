package com.project.review.to;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewTO {
	
	private int review_seq;
	private int review_writer_seq;
	private int review_cigar_seq;
	private String review_subject;
	private String review_writer;
	private Date review_reg_date;
	private String review_content;
	private int review_hit;
	private int review_cmt_count;
	private int review_grade;
	private int review_like;
	private int review_dislike;
	private String review_file_name;
	private int review_file_size;
	private Date review_smoke_years;
}
