//一時的にメモ帳として活用　後に削除

package com.internousdev.kagiya.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.internousdev.kagiya.dto.MCategoryDTO;
import com.internousdev.kagiya.dto.ProductInfoDTO;
import com.internousdev.kagiya.util.DBConnector;

public class ProductAddDAO {

	//商品追加機能用
		public int productAddInfo(int productId,String productName,String productNameKana,String productDescription,int categoryId,int price,String imageFilePath,String release_company) throws SQLException{
			DBConnector dbConnector = new DBConnector();
			int count=0;
			Connection connection = dbConnector.getConnection();
			String sql = "INSERT INTO product_info(product_id,product_name,product_name_kana,product_description,category_id,price,image_file_path,release_date,release_company,regist_date) VALUES(?,?,?,?,?,?,?,now(),?,now())";

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, productId);
				preparedStatement.setString(2, productName);
				preparedStatement.setString(3, productNameKana);
				preparedStatement.setString(4, productDescription);
				preparedStatement.setInt(5, categoryId);
				preparedStatement.setInt(6, price);
				preparedStatement.setString(7, imageFilePath);
				preparedStatement.setString(9, release_company);
				count = preparedStatement.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				connection.close();
			}
			return count;
		}

		//ID重複チェック用
		public ArrayList<ProductInfoDTO> productIdCheck(){

			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			ArrayList<ProductInfoDTO> productInfo = new ArrayList<ProductInfoDTO>();

			String sql = "SELECT product_id FROM product_info";

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();

				while(resultSet.next()){
					ProductInfoDTO productInfoDTO = new ProductInfoDTO();

					productInfoDTO.setProductId(resultSet.getInt("product_id"));
					productInfo.add(productInfoDTO);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			}
			return productInfo;
		}

		//商品削除用
		public ProductInfoDTO getSelectProductInfoname(String productName) {
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			ProductInfoDTO productInfoDTO = new ProductInfoDTO();

			String sql = "SELECT id, product_id, product_name, product_name_kana, product_description, categoly_id, price, image_file_path, release_date, release_company, status FROM product_info WHERE product_name = ?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productName);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCatgoryId(resultSet.getInt("categoly_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
			}else{
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return productInfoDTO;
	}

		//商品削除用
		public void productDeleteInfo(String deleteName) throws SQLException{
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();

			String sql = "DELETE FROM product_info WHERE product_name = ?";

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, deleteName);
				preparedStatement.executeUpdate();
			} catch (SQLException e){
				e.printStackTrace();
			}finally{
				connection.close();
			}
		}


		//商品追加カテゴリー表示用 追加先 MCategoryDAO
		public List<MCategoryDTO> getAdminCategoryList(){
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			List<MCategoryDTO> adminCategoryDTOList = new ArrayList<MCategoryDTO>();

			String sql = "SELECT * FROM m_category WHERE id > 1";
			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();

				while(resultSet.next()) {
					MCategoryDTO adminCategoryDTO = new MCategoryDTO();
					adminCategoryDTO.setId(resultSet.getInt("id"));
					adminCategoryDTO.setCategoryId(resultSet.getInt("category_id"));
					adminCategoryDTO.setCategoryName(resultSet.getString("category_name"));
					adminCategoryDTO.setCategortDescription(resultSet.getString("category_description"));
					adminCategoryDTO.setInsertDate(resultSet.getDate("insert_date"));
					adminCategoryDTO.setUpdateDate(resultSet.getDate("update_date"));
					adminCategoryDTOList.add(adminCategoryDTO);
				}
				Iterator<MCategoryDTO> iterator = adminCategoryDTOList.iterator();
				if(!(iterator.hasNext())){
					adminCategoryDTOList = null;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return adminCategoryDTOList;
		}


		/*管理者商品ID*/
		public static String productIdChk(String productId) {
			String result = null;

			if(productId.isEmpty()) {
				result = "【商品IDを入力してください】";
			}
			if(productId.length()<1 || productId.length()>4) {
				result = "【商品IDは1文字以上4文字以下で入力してください】";
			}
			if(!productId.matches("^[0-9]+$")) {
				result = "【商品IDは半角数字で入力してください】";
			}
			return result;
		}

		/*管理者商品名*/
		public static String productNameChk(String productName) {
			String result = null;

			if(productName.isEmpty()) {
				result = "【商品名を入力してください】";
			}
			if(productName.length()<1 || productName.length()>50) {
				result ="【商品名は1文字以上50文字以下で入力してください】";
			}
			return result;
		}

		/*管理者商品名かな*/
		public static String productNameKanaChk(String productNameKana) {
			String result = null;

			if(productNameKana.isEmpty()) {
				result = "【商品名の仮名を入力してください】";
			}
			if(productNameKana.length()<1 || productNameKana.length()>50) {
				result ="【商品名仮名は1文字以上50文字以下で入力してください】";
			}
			if(!productNameKana.matches("^[ぁ-ゞー0-9]+$")) {
				result = "【商品名の仮名をはひらがなで入力してください】";
			}
			return result;
		}

		/*管理者公開年*/
		public static String releaseCompanyChk(String releaseCompany) {
			String result = null;

			if(releaseCompany.isEmpty()) {
				return result = "【発売会社を入力してください】";
			}
			if(releaseCompany.length()<1 || releaseCompany.length()>50) {
				result ="【発売会社は1文字以上50文字以下で入力してください】";
			}

			return result;
		}

		/*管理者商品価格*/
		public static String priceChk(String price) {
			String result = null;

			if(price.isEmpty()) {
				return result = "【商品価格を入力してください】";
			}
			if(!price.matches("^[0-9]+$")) {
				return result = "【商品価格は数字で入力してください】";
			}
			if(Integer.parseInt(price)<100 || Integer.parseInt(price)>1000000) {
				result = "【商品価格は100円以上100万円以内で入力してください】";
			}
			return result;
		}

		/*管理者商品詳細*/
		public static String productDescriptionChk(String productDescription) {
			String result = null;

			if(productDescription.isEmpty()) {
				result = "【商品詳細を入力してください】";
			}
			if(productDescription.length()<1 || productDescription.length()>250) {
				result = "【商品詳細は1文字以上250文字以下で入力してください】";
			}
			return result;
		}

		/*管理者画像ファイル*/
		public static String imageFilePathChk(String imageFilePath) {
			String result = null;

			if(imageFilePath.equals(null) || imageFilePath.equals("")) {
				result = "【画像ファイルを選んでください】";
			}

			return result;
		}


}
