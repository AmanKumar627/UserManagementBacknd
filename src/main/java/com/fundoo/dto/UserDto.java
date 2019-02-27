package com.fundoo.dto;

public class UserDto {
 private String userName;
 private String password;
 private int id;
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
@Override
public String toString() {
	return "UserDto [userName=" + userName + ", password=" + password + ", id=" + id + "]";
}

}
