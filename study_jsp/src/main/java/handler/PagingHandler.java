package handler;

import domain.PagingVO;

public class PagingHandler {
	private int startPage; 
	private int endPage;
	private boolean prev, next;
	
	private int totalCount;
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		this.endPage = (int) Math.ceil(pgvo.getPageNo() / (pgvo.getQty() * 1.0)) * pgvo.getQty();
		this.startPage = this.endPage - 9;
		
		int realEndPage = (int)(Math.ceil((totalCount * 1.0) / pgvo.getQty()));  //13page
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;  //존재여부 
		this.next = this.endPage < realEndPage;  
	}

	// Getter
	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	// Setter
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}
	
}
