package com.internousdev.kagiya.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.PurchaseHistoryInfoDAO;
import com.opensymphony.xwork2.ActionSupport;


public class DeletePurchaseHistoryAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;

	public String execute(){
		String result = ERROR;
		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();

		int count = purchaseHistoryInfoDAO.deleteAll(session.get("loginId").toString());		//メソッド呼び出し

		//テスト用
//		String loginId = "guest1";
//		int count = purchaseHistoryInfoDAO.deleteAll(loginId);

		if(count > 0){		//削除件数が１件以上あった場合
			result = SUCCESS;
			session.remove("purchaseHistoryInfoDTOList");
			return result;
		}
		return result;
	}

	public Map<String, Object> getSession(){
		return session;
	}
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

}
