package com.project.member.to;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberTO {
	private int member_seq;
	private String email;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String nickname;
	private Date smoke_years;
	private String prefer_cigar;
	private Date sign_date;
	private Date birthday;
}
