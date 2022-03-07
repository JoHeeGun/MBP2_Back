package kr.com.jo.domain;

public class UserVO {

	private String id;
	private String password;
	private String email;
	private int deleted;
	private String join_at;
	
	
	private int startNo;
	private int endNo;
	private String schVal;
	
	
	

	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	public String getSchVal() {
		return schVal;
	}
	public void setSchVal(String schVal) {
		this.schVal = schVal;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getJoin_at() {
		return join_at;
	}
	public void setJoin_at(String join_at) {
		this.join_at = join_at;
	}
	
	

}
