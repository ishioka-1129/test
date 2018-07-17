package com.internousdev.kagiya.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.MCategoryDAO;
import com.internousdev.kagiya.dto.MCategoryDTO;
import com.internousdev.kagiya.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();

	public String execute() {

		//ログインも仮ユーザーも設定されていなければ仮ユーザーを設定
		if(!(session.containsKey("loginId")) && !(session.containsKey("tempUserId"))) {
			CommonUtility commonUtility = new CommonUtility();
			session.put("tempUserId", commonUtility.getRamdomValue());
		}


		//セッションのloginedに値がなければログオフ状態にしなおす
		if(!session.containsKey("logined")) {
			session.put("logined", 0);
		}

		//mCategoryDtoListをセッションに挿入
		if(!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			mCategoryDtoList = mCategoryDAO.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}

		return SUCCESS;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
