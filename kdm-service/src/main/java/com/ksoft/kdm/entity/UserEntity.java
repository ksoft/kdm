package com.ksoft.kdm.entity;

import com.ksoft.kdm.common.BaseEntity;
import com.ksoft.kdm.enums.UserSexEnum;

import javax.persistence.Column;
import javax.persistence.Table;


@Table(name = "users")
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	//@Column(name = "user_name")
	private String userName;
	@Column(name = "password")
	private String passWord;
	@Column(name = "user_sex")
	private UserSexEnum userSex= UserSexEnum.MAN;
	@Column(name = "nick_name")
	private String nickName;

	public UserEntity() {
		super();
	}

	public UserEntity(String userName, String passWord, UserSexEnum userSex) {
		super();
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public UserSexEnum getUserSex() {
		return userSex;
	}

	public void setUserSex(UserSexEnum userSex) {
		this.userSex = userSex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}