// 担当:石岡

package com.internousdev.kagiya.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.ProductInfoDAO;
import com.internousdev.kagiya.dto.PaginationDTO;
import com.internousdev.kagiya.dto.ProductInfoDTO;
import com.internousdev.kagiya.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDeleteAction extends ActionSupport implements SessionAware{

	private String productName;
	private String productNameKana;
	private int price;
	private String imageFilePath;
	private String imageFileName;
	private String categoryId;
	private String keywords;
	private int count;
	private String pageNo;
	public List<ProductInfoDTO> productInfoDTOList;
	public Map<String, Object> session;

	//商品一覧を表示するためにページデータを取得
	public String execute() {

		String result = SUCCESS;

		//商品情報を取得
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();

		//商品情報をsessionに格納
		productInfoDTOList = productInfoDAO.selectAll();
		session.put("productInfoDTOList", productInfoDTOList);

		//商品情報を取得できている場合、商品を１ページに９個表示させる
		if(!(productInfoDTOList == null)) {
			Pagination pagination = new Pagination();
			PaginationDTO paginationDTO = new PaginationDTO();

			paginationDTO = pagination.getPage(productInfoDTOList, 9, pageNo);

			//ページ情報を格納
			session.put("productInfoDTOList", paginationDTO.getCurrentProductInfoPage());
			session.put("totalPageSize", paginationDTO.getTotalPageSize());
			session.put("currentPageNo", paginationDTO.getCurrentPageNo());
			session.put("totalRecordSize", paginationDTO.getTotalRecordSize());
			session.put("startRecordNo", paginationDTO.getStartRecordNo());
			session.put("endRecordNo", paginationDTO.getEndRecordNo());
			session.put("previousPage", paginationDTO.hasPreviousPage());
			session.put("previousPageNo", paginationDTO.getPreviousPageNo());
			session.put("nextPage", paginationDTO.hasNextPage());
			session.put("nextPageNo", paginationDTO.getNextPageNo());
		}else{
			session.put("productInfoDTOList", null);
		}

		return result;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getProductNameKana() {
		return productNameKana;
	}



	public void setProductNameKana(String productNameKana) {
		this.productNameKana = productNameKana;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public String getImageFilePath() {
		return imageFilePath;
	}



	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}



	public String getImageFileName() {
		return imageFileName;
	}



	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}



	public String getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}



	public String getKeywords() {
		return keywords;
	}



	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}



	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public List<ProductInfoDTO> getProductInfoDTOList() {
		return productInfoDTOList;
	}

	public void setProductInfoDTOList(List<ProductInfoDTO> productInfoDTOList) {
		this.productInfoDTOList = productInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}