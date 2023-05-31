package domain;

public class PagingVO {
	private int pageNo;
	private int qty;

	private String type;
	private String keyword;

	public PagingVO() {
		this(1, 10);
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}

	public int getPageStart() {
		return (this.pageNo-1)*10;  //시작 limit 번지 구하기
	}
	
	public String[] getTypeToArray() {
		return this.type.split("");
	}

	// Getter
	public int getPageNo() {
		return pageNo;
	}

	public int getQty() {
		return qty;
	}

	public String getType() {
		return type;
	}

	public String getKeyword() {
		return keyword;
	}

	// Setter
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
