// 担当: 石岡

package com.internousdev.kagiya.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.MCategoryDAO;
import com.internousdev.kagiya.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAddAction extends ActionSupport implements SessionAware{


	private Map<String, Object> session;

	private List<MCategoryDTO> adminCategoryDTOList;

	//商品追加画面で商品カテゴリーを選択できるようにカテゴリーIDを取得
	public String execute(){

		MCategoryDAO mCategoryDAO = new MCategoryDAO();
		adminCategoryDTOList = mCategoryDAO.getAdminCategoryList();
		session.put("adminCategoryDTOList", adminCategoryDTOList);
		return SUCCESS;
	}



	public Map<String, Object> getSession() {
		return session;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public List<MCategoryDTO> getAdminCategoryDTOList() {
		return adminCategoryDTOList;
	}


	public void setAdminCategoryDTOList(List<MCategoryDTO> adminCategoryDTOList) {
		this.adminCategoryDTOList = adminCategoryDTOList;
	}




}
