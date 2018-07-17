package com.internousdev.kagiya.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.MCategoryDAO;
import com.internousdev.kagiya.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class StartAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();

	public String execute(){

		//mCategoryDtoListをセッションに挿入
		if(!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDao = new MCategoryDAO();
			mCategoryDtoList = mCategoryDao.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}

		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
