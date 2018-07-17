package com.internousdev.kagiya.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.CartInfoDAO;
import com.internousdev.kagiya.dao.DestinationInfoDAO;
import com.internousdev.kagiya.dao.MCategoryDAO;
import com.internousdev.kagiya.dao.UserInfoDAO;
import com.internousdev.kagiya.dto.DestinationInfoDTO;
import com.internousdev.kagiya.dto.MCategoryDTO;
import com.internousdev.kagiya.dto.UserInfoDTO;
import com.internousdev.kagiya.util.InputChecker;
//import com.internousdev.sampleweb.dao.DestinationInfoDAO;
//import com.internousdev.sampleweb.dto.DestinationInfoDTO;
//import com.internousdev.sampleweb.dao.MCategoryDAO;
//import com.internousdev.sampleweb.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	private String categoryId;
	private String user_id;
	private String password;
	private boolean savedLoginId;

	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();

	private List<String> loginIdErrorMessageList = new ArrayList<String>();
	private List<String> passwordErrorMessageList = new ArrayList<String>();

	private Map<String, Object> session;


	public String execute(){
		String result = ERROR;


		// ログインIDを保存するシステム
		if(savedLoginId == true){
			session.put("savedLoginId", true);
			session.put("userId", user_id);
		}else{
			session.put("savedLoginId", false);
			session.remove("userId", user_id);
		}

		//入力された文字を判定し、エラーメッセージを返すシステム
		InputChecker ic = new InputChecker();
		session.put("loginIdErrorMessageList", "");
		session.put("passwordErrorMessageList", "");

		loginIdErrorMessageList = ic.doCheck("ログインID", user_id, 1, 8, true, false, false, true, false, false, false);
		passwordErrorMessageList = ic.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false);

		session.put("loginIdErrorMessageList", loginIdErrorMessageList);
		session.put("passwordErrorMessageList", passwordErrorMessageList);


		if(!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDao = new MCategoryDAO();
			mCategoryDtoList = mCategoryDao.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}


		UserInfoDAO userInfoDAO = new UserInfoDAO();
		UserInfoDTO userInfoDTO = new UserInfoDTO();

		userInfoDTO = userInfoDAO.getUserInfo(user_id, password);

		System.out.println(user_id);
		System.out.println(password);

		session.put("loginUser", userInfoDTO);

		if(((UserInfoDTO) session.get("loginUser")).getLogined() == 1){
			result = SUCCESS;
			session.put("loginId", userInfoDTO.getUser_id());
			session.put("logined", 1);

			int count =0;
			CartInfoDAO cartInfoDAO = new CartInfoDAO();
			count = cartInfoDAO.linkToLoginId(String.valueOf(session.get("tempUserId")),user_id);
			if(count > 0) {
				DestinationInfoDAO destinationInfoDao = new DestinationInfoDAO();

				try {
					List<DestinationInfoDTO> destinationInfoDtoList = new ArrayList<DestinationInfoDTO>();
					destinationInfoDtoList = destinationInfoDao.getDestinationInfo(user_id);
					Iterator<DestinationInfoDTO> iterator = destinationInfoDtoList.iterator();

					if(!(iterator.hasNext())) {
						destinationInfoDtoList = null;
					}
					session.put("destinationInfoDtoList", destinationInfoDtoList);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				result = "settlement";
				}else {
					result = SUCCESS;
					}
			session.put("logined", 1);
			}
		return result;
		}




			//statusを判別して管理者か否かを判断する
//			if(((UserInfoDTO) session.get("loginUser")).getStatus() == 1){
//				result = "admin";
//			}




	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getUser_Id(){
		return user_id;
	}
	public void setUser_id(String user_id){
		this.user_id = user_id;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public boolean isSavedLoginId() {
		return savedLoginId;
	}
	public void setSavedLoginId(boolean savedLoginId) {
		this.savedLoginId = savedLoginId;
	}

	public List<String> getLoginIdErrorMessageList() {
		return loginIdErrorMessageList;
	}
	public void setLoginIdErrorMessageList(List<String> loginIdErrorMessageList) {
		this.loginIdErrorMessageList = loginIdErrorMessageList;
	}

	public List<String> getPasswordErrorMessageList() {
		return passwordErrorMessageList;
	}
	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList) {
		this.passwordErrorMessageList = passwordErrorMessageList;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}


}