package com.itbank.model;

import java.sql.Date;

//	create table member (
//	    idx		number		default member_seq.nextval primary key,
//	    userid		varchar2(100)	unique not null,
//	    userpw		varchar2(255)	not null,
//	    registDate	date		default sysdate,	
//	    birthDate	date		not null,
//	    nickname	varchar2(100)	unique not null,
//	    email		varchar2(100)	not null,
//	    gender		varchar2(10)	check (gender in ('남성', '여성'))
//	);

public class MemberDTO {

	private int idx;
	private String userid;
	private String userpw;
	private Date registDate;
	private Date birthDate;
	private String nickname;
	private String email;
	private String gender;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
