package com.test.entity;

/**
 * ��ǰ���һ��������Է�װuser_message���е�һ������
 * 
 * @author �����˵�
 *
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private Integer userOn;
	private String userName;
	private String userPassword;
	private String userSex;
	private int userAge;
	private String userTel;
	private String userLoc;
	private String userRole;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserOn() {
		return userOn;
	}

	public void setUserOn(Integer userOn) {
		this.userOn = userOn;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserLoc() {
		return userLoc;
	}

	public void setUserLoc(String userLoc) {
		this.userLoc = userLoc;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
