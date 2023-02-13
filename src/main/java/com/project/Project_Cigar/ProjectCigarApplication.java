package com.project.Project_Cigar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.project.member.*", "com.project.cigar.*", "com.project.review.*", "com.project.gongji.*", "com.project.requestCigar.*", "com.project.free_board.*"})
public class ProjectCigarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCigarApplication.class, args);
	}

}
