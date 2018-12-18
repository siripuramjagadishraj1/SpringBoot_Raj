package com.application.mybatis;

import java.io.Serializable;

public class Student implements Serializable{
	
	private int sid;
	
	private String sname;
	
	private String email;
	
	public Student() {}
	
	public Student(String sname, String email) {
		super();
		this.sname = sname;
		this.email = email;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "[SID: "+this.sid+", SNAME: "+this.sname+", EMAIL: "+this.email+"]";
	}
}
