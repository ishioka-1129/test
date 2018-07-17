package com.internousdev.kagiya.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.MCategoryDAO;
import com.internousdev.kagiya.dao.UserInfoDAO;
import com.internousdev.kagiya.dto.MCategoryDTO;
import com.internousdev.kagiya.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private UserInfoDAO userInfoDAO = new UserInfoDAO();
	private ArrayList<UserInfoDTO> userInfoList = new ArrayList<UserInfoDTO>();
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();

	public String execute() {

		//mCategoryDtoListをセッションに挿入
		if(!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDao = new MCategoryDAO();
			mCategoryDtoList = mCategoryDao.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}

		//loginIdがなければエラーを返す
		if(!session.containsKey("loginId")) {
			return ERROR;
		}

		//loginIdからユーザーのデータを取ってきてリストに挿入
		userInfoList = userInfoDAO.getUserInfo(session.get("loginId").toString());

		//ユーザーデータが空ならエラーを返す
		if(userInfoList.isEmpty()) {
			return ERROR;
		}

		return SUCCESS;
	}

	@Override
	public void setSession(Map<String,Object> session) {
		this.session = session;
	}
	public ArrayList<UserInfoDTO> getUserInfoList() {
		return userInfoList;
	}
}
