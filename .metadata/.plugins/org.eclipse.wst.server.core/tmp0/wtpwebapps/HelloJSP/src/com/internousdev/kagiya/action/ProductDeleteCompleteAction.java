//担当:石岡

package com.internousdev.kagiya.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.ProductInfoDAO;
import com.internousdev.kagiya.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDeleteCompleteAction extends ActionSupport implements SessionAware{
	public Map<String, Object> session;

	public String execute() throws SQLException {

		//@SuppressWarningsでコンパイル警告を抑制
		@SuppressWarnings("unchecked")
		List<String> deleteName = (List<String>) session.get("deleteName");

		for(int i=0; i<deleteName.size(); i++) {

			//選択した商品と同じproductNameの物を削除
			@SuppressWarnings("unchecked")
			List<ProductInfoDTO> list = (List<ProductInfoDTO>) session.get("productDeleteList");
			String productName = list.get(i).getProductName();

			ProductInfoDAO productInfoDAO = new ProductInfoDAO();
			productInfoDAO.productDeleteInfo(productName);
		}
		//session内の情報を削除しSUCCESSを返す
		session.remove("productInfoDTOList");
		String result = SUCCESS;
		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
