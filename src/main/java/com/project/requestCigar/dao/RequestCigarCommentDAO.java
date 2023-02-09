package com.project.requestCigar.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.requestCigar.mapper.RequestCigarCommentMapperInter;

@Repository
@MapperScan("com.project.requestCigar.mapper")
public class RequestCigarCommentDAO {
	
	@Autowired
	private RequestCigarCommentMapperInter requestCigarCommentMapperInter;
}
