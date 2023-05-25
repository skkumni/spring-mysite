package com.itbank.model;

public class Paging {

	// 요청받은 페이지에 따라 불러올 게시글의 범위를 지정하는 클래스
	
	private int page;				// 요청받은 page
	private int boardCount;			// 총 게시글의 개수, count(*)
	private int perPage = 10;		// 화면 당 출력할 게시글의 개수
	private int perSection = 10;	// 여러 페이지를 섹션으로 분류하여 출력할 페이지의 개수
	
	private int offset;				// 쿼리문에서 건너뛸 레코드의 수
	
	private int pageCount;			// 페이지 전체 개수
	private int begin;				// 쪽번호 출력 시작 값
	private int end;				// 쪽번호 출력 끝 값 
	private boolean prev;			// 이전 버튼을 출력하는 조건
	private boolean next;			// 다음 버튼을 출력하는 조건
	
	@Override
	public String toString() {
		String format = "[";
		format += "page : %s, ";
		format += "boardCount : %s, ";
		format += "perPage : %s, ";
		format += "perSection : %s, ";
		format += "offset : %s, ";
		format += "pageCount : %s, ";
		format += "begin : %s, ";
		format += "end : %s, ";
		format += "prev : %s, ";
		format += "next : %s ]";
		
		String s = String.format(format, 
					page, boardCount, perPage, perSection, offset,
					pageCount, begin, end, prev, next
				);
		return s;
	}
	
	// 생성자
	public Paging(int page, int boardCount) {
		this.page = page;
		this.boardCount = boardCount;
		
		// 총 게시글이 몇개이고, 몇번째 페이지를 요청받았는지 알아야한다
		offset = (page - 1) * perPage;
		begin = ((int)(page - 1) / perSection) * perSection + 1;
		end = begin + perSection - 1;
		
		// 정확히 나누어떨어지지 않고, 나머지가 있으면 페이지 수를 증가시켜야 한다
		boolean flag = boardCount % perPage != 0;
		pageCount = boardCount / perPage + (flag ? 1 : 0);
		
		// 마지막 번호는 전체 페이지 수를 초과할 수 없다
		end = end >= pageCount ? pageCount : end;
		
		int section = (page - 1) / perSection;
		int lastSection = (pageCount - 1) / perSection;
		prev = begin > perSection;
		next = lastSection > section;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getPerSection() {
		return perSection;
	}
	public void setPerSection(int perSection) {
		this.perSection = perSection;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	
	
	
	
	
	
	
	
	
}
