package com.min.www.dto.member;

public class MemberDto {
	String Mnumber;
	String id;
	String password;
	String email;
	
	
	public MemberDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberDto(String Mnumber,String id, String password, String email) {
		// TODO Auto-generated constructor stub
		Mnumber = this.Mnumber;
		id = this.id;
		password = this.password;
		email = this.email;
	}

	public String getMnumber() {
		return Mnumber;
	}

	public void setMnumber(String mnumber) {
		Mnumber = mnumber;
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
	
	
	
	

}
