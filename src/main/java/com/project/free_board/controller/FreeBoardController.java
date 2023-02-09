package com.project.free_board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.free_board.dao.FreeBoardDAO;

@Controller
public class FreeBoardController {
	
	@Autowired
	private FreeBoardDAO dao;
}
