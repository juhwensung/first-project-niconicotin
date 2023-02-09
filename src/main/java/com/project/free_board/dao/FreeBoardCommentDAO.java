package com.project.free_board.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.free_board.mapper.FreeBoardCommentMapperInter;

@Repository
@MapperScan("com.project.free_board.mapper")
public class FreeBoardCommentDAO {
	
	@Autowired
	private FreeBoardCommentMapperInter freeBoardCommentMapperInter;
}
