package domain;

public class CommentVO {
	private int cno;
	private int bno;
	private String writer;	// Default : "익명"
	private String content;
	private String regdate;	// Default : Now()
	
	// 기본 생성자
	public CommentVO() {}
	
	// 등록
	public CommentVO(int bno, String writer, String content) {
		this.bno = bno;
		this.writer = writer;
		this.content = content;
	}

	// 수정
	public CommentVO(int cno, String content) {
		this.cno = cno;
		this.content = content;
	}

	// Getter
	public int getCno() {
		return cno;
	}
	public int getBno() {
		return bno;
	}
	public String getWriter() {
		return writer;
	}
	public String getContent() {
		return content;
	}
	public String getRegdate() {
		return regdate;
	}
	
	// Setter
	public void setCno(int cno) {
		this.cno = cno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", bno=" + bno + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + "]";
	}
}
