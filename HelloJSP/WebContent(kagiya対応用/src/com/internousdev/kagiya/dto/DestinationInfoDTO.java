package com.internousdev.kagiya.dto;

import java.util.Date;

public class DestinationInfoDTO {

	private int id;
	private String userId;//ユーザーID
	private String familyName;//姓
	private String firstName;//名
	private String familyNameKana;//姓ふりがな
	private String firstNameKana;//名ふりがな
	private String email;//メールアドレス
	private String telNumber;//電話番号
	private String userAddress;//住所
	private Date registDate;//登録日時
	private Date updateDate;//更新日時

	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}
	public String getFamilyName(){
		return familyName;
	}
	public void setFamilyName(String familyName){
		this.familyName=familyName;
	}
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	public String getFamilyNameKana(){
		return familyNameKana;
	}
	public void setFamilyNameKana(String familyNameKana){
		this.familyNameKana=familyNameKana;
	}
	public String getFirstNameKana(){
		return firstNameKana;
	}
	public void setFirstNameKana(String firstNameKana){
		this.firstNameKana=firstNameKana;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getTelNumber(){
		return telNumber;
	}
	public void setTelNumber(String telNumber){
		this.telNumber=telNumber;
	}
	public String getUserAddress(){
		return userAddress;
	}
	public void setUserAddress(String userAddress){
		this.userAddress=userAddress;
	}
	public Date getRegistDate(){
		return registDate;
	}
	public void setRegistDate(Date registDate){
		this.registDate=registDate;
	}
	public Date getUpdateDate(){
		return updateDate;
	}
	public void setUpdateDate(Date updateDate){
		this.updateDate=updateDate;
	}
}
