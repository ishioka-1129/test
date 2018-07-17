// 担当:石岡

package com.internousdev.kagiya.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.ProductInfoDAO;
import com.internousdev.kagiya.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDeleteConfirmAction extends ActionSupport implements SessionAware{

	private List<String> deleteName;
	public Map<String, Object> session;
	private String errorMessage;
	ProductInfoDTO productInfoDTO = new ProductInfoDTO();
	ArrayList<ProductInfoDTO> productDeleteList = new ArrayList<>();
	ProductInfoDAO productInfoDAO = new ProductInfoDAO();

	public String execute() {
		String result = ERROR;

		//何も商品を選んでない場合一覧ページに戻る
		if(deleteName == null) {
			errorMessage = "削除する商品を選択してください";
			return result;
		}else{

			//商品を選択した場合確認面に表示するために情報を取得する
			session.put("deleteName", deleteName);
			session.put("productDeleteListSize", deleteName.size());

			for(int i=0; i < deleteName.size(); i++) {
				String productName = deleteName.get(i);
				productInfoDTO = productInfoDAO.getSelectProductInfoName(productName);
				productDeleteList.add(productInfoDTO);
				session.put("productDeleteList", productDeleteList);
			}
			result = SUCCESS;
		}
		return result;
	}

	public List<String> getDeleteName() {
		return deleteName;
	}

	public void setDeleteName(List<String> deleteName) {
		this.deleteName = deleteName;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ProductInfoDTO getProductInfoDTO() {
		return productInfoDTO;
	}

	public void setProductInfoDTO(ProductInfoDTO productInfoDTO) {
		this.productInfoDTO = productInfoDTO;
	}

	public ArrayList<ProductInfoDTO> getProductDeleteList() {
		return productDeleteList;
	}

	public void setProductDeleteList(ArrayList<ProductInfoDTO> productDeleteList) {
		this.productDeleteList = productDeleteList;
	}

	public ProductInfoDAO getProductInfoDAO() {
		return productInfoDAO;
	}

	public void setProductInfoDAO(ProductInfoDAO productInfoDAO) {
		this.productInfoDAO = productInfoDAO;
	}



}
