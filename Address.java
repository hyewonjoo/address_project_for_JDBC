package com.test.java.mvc;


// DTO, VO
// - 멤버 변수
// - Getter/Setter
// - toString() > Debug
public class Address {

	private String seq;
	private String name;
	private String age;
	private String gender;
	private String tel;
	private String address;
	private String regdate;



	// 매개변수가 없는 기본 생성자
	public Address() {
		this.seq = seq;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.tel = tel;
		this.address = address;
		this.regdate = regdate;
	}



	// 매개변수가 있는 생성자
	public Address(String seq, String name, String age, String gender, String tel, String address,
			String regdate) {

		this.seq = seq;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.tel = tel;
		this.address = address;
		this.regdate = regdate;



	}



	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}



}
