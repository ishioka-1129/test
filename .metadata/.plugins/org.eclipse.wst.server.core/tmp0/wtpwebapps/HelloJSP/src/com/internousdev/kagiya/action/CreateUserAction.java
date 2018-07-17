package com.internousdev.kagiya.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateUserAction extends ActionSupport implements SessionAware{

	private String family_name;
	private String first_name;
	private String family_name_kana;
	private String first_name_kana;
	private String sex;
	private List<String> sexList = new ArrayList<String>();
	private String email;
	private String user_id;
	private String password;
	private static final String MALE = "男性";
	private static final String FEMALE = "女性";
	private String defaultSexValue = MALE;
	private Map<String,Object> session;

	public String execute(){
		String result = ERROR;

//前回の値が表示されないようsessionに格納されている値を削除
		session.remove("family_nameErrorMessageList");
		session.remove("first_nameErrorMessageList");
		session.remove("family_name_kanaErrorMessageList");
		session.remove("first_name_kanaErrorMessageList");
		session.remove("emailErrorMessageList");
		session.remove("user_idErrorMessageList");
		session.remove("passwordErrorMessageList");

//sessionに値を入れる
		session.put("family_name",family_name);
		session.put("first_name",first_name);
		session.put("family_name_kana",family_name_kana);
		session.put("first_name_kana",first_name_kana);

//性別に値が何もないことを確認。なければMALEをsessionに入れ、あればsessionから文字列として取得し性別に値を入れる
		if(sex == null){
			session.put("sex",MALE);
		}else{
			session.put("sex",String.valueOf(session.get("sex")));
		}
//性別リストに値を入れ、それぞれsessionに値を入れる。resultにSUCCESSを入れresultを返す
		sexList.add(MALE);
		sexList.add(FEMALE);
		session.put("sexList",sexList);
		session.put("email",email);
		session.put("user_id",user_id);
		session.put("password",password);
		result = SUCCESS;
		return result;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<String> getSexList() {
		return sexList;
	}

	public void setSexList(List<String> sexList) {
		this.sexList = sexList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getDefaultSexValue() {
		return defaultSexValue;
	}

	public void setDefaultSexValue(String defaultSexValue) {
		this.defaultSexValue = defaultSexValue;
	}

	public Map<String,Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}
}
