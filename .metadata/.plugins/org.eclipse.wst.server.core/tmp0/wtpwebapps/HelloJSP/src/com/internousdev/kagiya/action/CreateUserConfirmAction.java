package com.internousdev.kagiya.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserConfirmAction extends ActionSupport implements SessionAware{

	public Map<String,Object> session;
	private String family_name;
	private String first_name;
	private String family_name_kana;
	private String first_name_kana;
	private String sex;
	private static final String MALE = "男性";
	private static final String FEMALE = "女性";
	private String defaultSexValue = MALE;
	private String email;
	private String user_id;
	private String password;

//ErrorMessageは1つの入力項目に複数あるためListを作成
	private List<String> family_nameErrorMessageList = new ArrayList<String>();
	private List<String> first_nameErrorMessageList = new ArrayList<String>();
	private List<String> family_name_kanaErrorMessageList = new ArrayList<String>();
	private List<String> first_name_kanaErrorMessageList = new ArrayList<String>();
	private List<String> sexList = new ArrayList<String>();
	private List<String> emailErrorMessageList = new ArrayList<String>();
	private List<String> user_idErrorMessageList = new ArrayList<String>();
	private List<String> passwordErrorMessageList = new ArrayList<String>();

	public String execute(){

		String result = ERROR;

//InputCheckerをインスタンス化し入力された値をsessionに入れる
		InputChecker inputChecker = new InputChecker();

		session.put("family_name", family_name);
		session.put("first_name",first_name);
		session.put("family_name_kana",family_name_kana);
		session.put("first_name_kana",first_name_kana);
		session.put("sex",sex);
		session.put("email",email);
		session.put("user_id",user_id);
		session.put("password",password);

//InputCheckerのdoCheckにそれぞれのErrorMessageListの値を渡す
		family_nameErrorMessageList = inputChecker.doCheck("姓",family_name,1,16,true,true,true,false,false,false,false);
		first_nameErrorMessageList = inputChecker.doCheck("名",first_name,1,16,true,true,true,false,false,false,false);
		family_name_kanaErrorMessageList = inputChecker.doCheck("姓ふりがな",family_name_kana,1,16,false,false,true,false,false,false,false);
		first_name_kanaErrorMessageList = inputChecker.doCheck("名ふりがな",first_name_kana,1,16,false,false,true,false,false,false,false);
		emailErrorMessageList = inputChecker.doCheck("メールアドレス",email,14,32,true,false,false,true,true,false,false);
		user_idErrorMessageList = inputChecker.doCheck("ログインID",user_id,1,8,true,false,false,true,false,false,false);
		passwordErrorMessageList = inputChecker.doCheck("パスワード",password,1,16,true,false,false,true,false,false,false);

//ErrorMessageが１つもないか確認。なければresultをSUCCESSにする
		if(family_nameErrorMessageList.size() == 0
		&& first_nameErrorMessageList.size() == 0
		&& family_name_kanaErrorMessageList.size() == 0
		&& first_name_kanaErrorMessageList.size() == 0
		&& emailErrorMessageList.size() == 0
		&& user_idErrorMessageList.size() == 0
		&& passwordErrorMessageList.size() == 0){
			result = SUCCESS;

//もしエラーがある場合は該当するErrorMessageをsessionに入れresultをERRORにする
		}else{
			session.put("family_nameErrorMessageList",family_nameErrorMessageList);
			session.put("first_nameErrorMessageList",first_nameErrorMessageList);
			session.put("family_name_kanaErrorMessageList",family_name_kanaErrorMessageList);
			session.put("first_name_kanaErrorMessageList",first_name_kanaErrorMessageList);
			session.put("emailErrorMessageList",emailErrorMessageList);
			session.put("user_idErrorMessageList",user_idErrorMessageList);
			session.put("passwordErrorMessageList",passwordErrorMessageList);
			result = ERROR;
		}

//性別リストに性別の変数を入れresultで結果を返す
		sexList.add(MALE);
		sexList.add(FEMALE);
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

	public Map<String,Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}

	public String getDefaultSexValue() {
		return defaultSexValue;
	}

	public void setDefaultSexValue(String defaultSexValue) {
		this.defaultSexValue = defaultSexValue;
	}

	public List<String> getFamily_nameErrorMessageList(){
		return family_nameErrorMessageList;
	}

	public void setFamily_nameErrorMessageList(List<String> family_nameErrorMessageList){
		this.family_nameErrorMessageList = family_nameErrorMessageList;
	}

	public List<String> getFirst_nameErrorMessageList(){
		return first_nameErrorMessageList;
	}

	public void setFirst_nameErrorMessageList(List<String> first_nameErrorMessageList){
		this. first_nameErrorMessageList= first_nameErrorMessageList;
	}

	public List<String> getFamily_name_kanaErrorMessageList(){
		return family_name_kanaErrorMessageList;
	}

	public void setFamily_name_kanaErrorMessageList(List<String> family_name_kanaErrorMessageList){
		this.family_name_kanaErrorMessageList = family_name_kanaErrorMessageList;
	}

	public List<String> getFirst_name_kanaErrorMessageList(){
		return first_name_kanaErrorMessageList;
	}

	public void setFirst_name_kanaErrorMessageList(List<String> first_name_kanaErrorMessageList){
		this.first_name_kanaErrorMessageList = first_name_kanaErrorMessageList;
	}

	public List<String> getSexList (){
		return sexList;
	}

	public void setSexList(List<String> sexList){
		this.sexList = sexList;
	}

	public List<String> getEmailErrorMessageList(){
		return emailErrorMessageList;
	}

	public void setEmailErrorMessageList(List<String> emailErrorMessageList){
		this.emailErrorMessageList = emailErrorMessageList;
	}

	public  List<String> getUser_idErrorMessageList(){
		return user_idErrorMessageList;
	}
	public void setUser_idErrorMessageList(List<String> user_idErrorMessageList){
		this.user_idErrorMessageList = user_idErrorMessageList;
	}

	public  List<String> getPasswordErrorMessageList(){
		return passwordErrorMessageList;
	}
	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList){
		this.passwordErrorMessageList = passwordErrorMessageList;
	}

}
