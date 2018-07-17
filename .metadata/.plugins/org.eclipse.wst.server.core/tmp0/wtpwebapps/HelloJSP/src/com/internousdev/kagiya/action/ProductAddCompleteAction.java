//担当: 石岡

package com.internousdev.kagiya.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.ProductInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAddCompleteAction extends ActionSupport implements SessionAware {

	public Map<String, Object> session;
	public int count;

	//商品データが登録されたらSUCCESSを返す
	public String execute() throws SQLException {
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();

		String result = ERROR;

		int count = 0;

		count += productInfoDAO.productAddInfo(
				Integer.parseInt(session.get("productId").toString()),
				session.get("productName").toString(),
				session.get("productNameKana").toString(),
				session.get("productDescription").toString(),
				Integer.parseInt(session.get("categoryId").toString()),
				Integer.parseInt(session.get("price").toString()),
				session.get("imageFilePath").toString(),
				session.get("releaseCompany").toString());

		if (count >= 0) {
			result = SUCCESS;
		}

		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
