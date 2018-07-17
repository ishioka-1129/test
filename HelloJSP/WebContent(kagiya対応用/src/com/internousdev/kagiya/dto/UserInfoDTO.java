package com.internousdev.kagiya.dto;

import java.sql.Date;

public class UserInfoDTO {

	private int id;
	private String user_id;
	private String password;
	private String family_name;
	private String first_name;
	private String family_name_kana;
	private String first_name_kana;
	private int sex;
	private String email;
	private int status;
	private int logined;
	private Date regist_date;
	private Date update_date;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getFamily_name_kana() {
		return family_name_kana;
	}
	public void setFamily_name_kana(String family_name_kana) {
		this.family_name_kana = family_name_kana;
	}
	public String getFirst_name_kana() {
		return first_name_kana;
	}
	public void setFirst_name_kana(String first_name_kana) {
		this.first_name_kana = first_name_kana;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLogined() {
		return logined;
	}
	public void setLogined(int logined) {
		this.logined = logined;
	}
	public Date getRegist_date() {
		return regist_date;
	}
	public void setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

}
