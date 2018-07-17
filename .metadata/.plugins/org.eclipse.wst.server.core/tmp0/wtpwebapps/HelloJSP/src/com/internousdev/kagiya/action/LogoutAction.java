package com.internousdev.kagiya.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	public String execute(){
		//全てのsessionのキーと値を削除
		session.clear();
		//session(logined)に0(未ログイン状態)をいれる
		session.put("logined", 0);

		return SUCCESS;

	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}
