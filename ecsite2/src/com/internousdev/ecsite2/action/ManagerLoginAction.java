package com.internousdev.ecsite2.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.ManagerLoginDAO;
import com.internousdev.ecsite2.dto.ManagerLoginDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ManagerLoginAction extends ActionSupport implements SessionAware{
	private String loginUserId;
	private String loginPassword;
	public Map<String,Object> session;
	private ManagerLoginDAO mlDAO = new ManagerLoginDAO();
	private ManagerLoginDTO mlDTO = new ManagerLoginDTO();

	public String execute(){
		String result = ERROR;
		mlDTO = mlDAO.getLoginManagerInfo(loginUserId, loginPassword);
		session.put("loginManager", mlDTO);

		if(((ManagerLoginDTO) session.get("loginManager")).getLoginFlg()){
			result = SUCCESS;

			return result;
		}
		return result;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
