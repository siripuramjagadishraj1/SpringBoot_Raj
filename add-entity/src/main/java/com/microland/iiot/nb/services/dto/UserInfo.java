package com.microland.iiot.nb.services.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserInfo{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userNo;
	
	@Column
	private String userName;
	
	//Constructors
	public UserInfo(Integer userNo, String userName) { super(); this.userNo = userNo; this.userName = userName;}
	public UserInfo() {}
	//Getter, Setter
	public Integer getUserNo() {return userNo;}
	public void setUserNo(Integer userNo) {this.userNo = userNo;}
	public String getUserName() {return userName;}
	public void setUserName(String userName) {this.userName = userName;}
	@Override
	public String toString() {return "{UserNo: "+userNo+" UserName: "+userName+"}";}
}
