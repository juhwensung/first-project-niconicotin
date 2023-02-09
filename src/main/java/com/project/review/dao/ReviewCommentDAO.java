package com.project.review.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.review.mapper.ReviewCommentMapperInter;

@Repository
@MapperScan("com.project.review.mapper")
public class ReviewCommentDAO {
	
	@Autowired
	private ReviewCommentMapperInter reviewCommentMapperInter;
}
