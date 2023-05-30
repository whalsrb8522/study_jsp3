package domain;

public class MemberVO {
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String regdate;
	private String lastlogin;
	private boolean auth;
	
	public MemberVO() {}
	
	public MemberVO(String id, String password, String name, String email, String phone) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public MemberVO(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public MemberVO(String id) {
		this.id = id;
	}

	// Getter
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public String getRegdate() {
		return regdate;
	}
	public String getLastlogin() {
		return lastlogin;
	}
	public boolean isAuth() {
		return auth;
	}
	
	// Setter
	public void setId(String id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
}
