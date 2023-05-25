package com.itbank.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

//	idx			number			default board_seq.nextval primary key,
//	writer		varchar2(100)	not null,
//	title		varchar2(500)	not null,
//	content		varchar2(2000)	not null,
//	fileName	varchar2(255)	,
//	writeDate	date			default sysdate,
//	viewCount	number			default 0,
//	allowReply	char(1)			check(allowReply in ('y', 'n')),

public class BoardDTO {
	
	private int idx;
	private String writer;
	private String title;
	private String content;
	private String fileName;
	private MultipartFile uploadFile;
	private Date writeDate;
	private int viewCount;
	private String allowReply;
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getAllowReply() {
		return allowReply;
	}
	public void setAllowReply(String allowReply) {
		this.allowReply = allowReply;
	}
	
	
	

}
