//担当: 石岡

package com.internousdev.kagiya.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.ProductInfoDAO;
import com.internousdev.kagiya.dto.ProductInfoDTO;
import com.internousdev.kagiya.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAddConfirmAction extends ActionSupport implements SessionAware{

	private String productId;
	private String productName;
	private String productNameKana;
	private String productDescription;
	private String categoryId;
	private String price;
	private String imageFilePath;
	private String releaseCompany;
	private String image;

	public Map<String, Object> session;

	public String productIdCheckError;
	public String productIdError;
	public String productNameError;
	public String productNameKanaError;
	public String productDescriptionError;
	public String priceError;
	public String imageFilePathError;
	public String releaseCompanyError;

	public String execute() throws SQLException{
		String result = ERROR;

		imageFilePath = image;

		//入力された内容が正常かどうか判定する
		productIdError = InputChecker.productIdChk(productId);
		productNameError = InputChecker.productNameChk(productName);
		productNameKanaError = InputChecker.productNameKanaChk(productNameKana);
		productDescriptionError = InputChecker.productDescriptionChk(productDescription);
		priceError = InputChecker.priceChk(price);
		imageFilePathError = InputChecker.imageFilePathChk(image);
		releaseCompanyError = InputChecker.releaseCompanyChk(releaseCompany);


		//重複IDがないか確認する
		ArrayList<ProductInfoDTO> productInfo = new ArrayList<ProductInfoDTO>();
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();

		productInfo = productInfoDAO.productIdCheck();

		for(int i=0; i<productInfo.size(); i++){
			String productIdCheck = String.valueOf(productInfo.get(i).getProductId());

			if(productIdCheck.equals(productId)){
				productIdCheckError = "既に同じ商品IDが存在します";
			}
		}

		//異常があった場合エラーを返す
		if(productIdError != null || productIdCheckError != null || productNameError != null || productNameKanaError != null || productDescriptionError != null || priceError != null || imageFilePathError != null || releaseCompanyError != null){
		return ERROR;
		}


		//入力された値を保存する
		session.put("productId", productId);
		session.put("productName", productName);
		session.put("productNameKana", productNameKana);
		session.put("productDescription", productDescription);
		session.put("categoryId", categoryId);
		session.put("price", price);
		session.put("imageFilePath", imageFilePath);
		session.put("releaseCompany", releaseCompany);

		result = SUCCESS;
		return result;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImageFilePath() {
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	public String getReleaseCompany() {
		return releaseCompany;
	}
	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}

	//imageを取得します
	public String getImage() {
		return image;
	}

	//imageを設定します
	public void setImage(String image) {
		this.image = image;
	}
	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	//productIdCheckErrorを取得します
	public String getProductIdCheckError() {
		return productIdCheckError;
	}

	//productIdCheckErrorを設定します
	public void setProductIdCheckError(String productIdCheckError) {
		this.productIdCheckError = productIdCheckError;
	}


	public String getProductIdError() {
		return productIdError;
	}
	public void setProductIdError(String productIdError) {
		this.productIdError = productIdError;
	}
	public String getProductNameError() {
		return productNameError;
	}
	public void setProductNameError(String productNameError) {
		this.productNameError = productNameError;
	}
	public String getProductNameKanaError() {
		return productNameKanaError;
	}
	public void setProductNameKanaError(String productNameKanaError) {
		this.productNameKanaError = productNameKanaError;
	}
	public String getProductDescriptionError() {
		return productDescriptionError;
	}
	public void setProductDescriptionError(String productDescriptionError) {
		this.productDescriptionError = productDescriptionError;
	}
	public String getPriceError() {
		return priceError;
	}
	public void setPriceError(String priceError) {
		this.priceError = priceError;
	}
	public String getImageFilePathError() {
		return imageFilePathError;
	}
	public void setImageFilePathError(String imageFilePathError) {
		this.imageFilePathError = imageFilePathError;
	}
	public String getReleaseCompanyError() {
		return releaseCompanyError;
	}
	public void setReleaseCompanyError(String releaseCompanyError) {
		this.releaseCompanyError = releaseCompanyError;
	}



}
