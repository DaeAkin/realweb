package com.min.www.dto.member;

public class MemberDto {
	String Mnumber;
	String id;
	String nickname;
	String password;
	String email;
	String imageurl;
	
	
	
	public MemberDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberDto(String Mnumber,String id,String nickname, String password, String email, String imageurl) {
		// TODO Auto-generated constructor stub
		Mnumber = this.Mnumber;
		nickname = this.nickname;
		id = this.id;
		password = this.password;
		email = this.email;
		imageurl = this.imageurl;
	}
	
	
	

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
