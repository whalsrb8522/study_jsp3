package domain;

public class BoardVO {
	private int bno;
	private String title;
	private String writer;
	private String regdate;
	private String content;
	private int readcount;
	
	public BoardVO() {}
	
	public BoardVO(int bno, String title, String writer, String regdate, String content, int readcount) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.regdate = regdate;
		this.content = content;
		this.readcount = readcount;
	}
	
	public BoardVO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	
	public BoardVO(int bno, String title, String content) {
		this.bno = bno;
		this.title = title;
		this.content = content;
	}

	public BoardVO(int bno) {
		this.bno = bno;
	}

	// Getter
	public int getBno() {
		return bno;
	}
	public String getTitle() {
		return title;
	}
	public String getWriter() {
		return writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public String getContent() {
		return content;
	}
	public int getReadcount() {
		return readcount;
	}
	
	// Setter
	public void setBno(int bno) {
		this.bno = bno;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
}
