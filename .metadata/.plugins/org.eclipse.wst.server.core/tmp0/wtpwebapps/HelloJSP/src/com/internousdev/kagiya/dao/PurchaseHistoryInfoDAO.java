package com.internousdev.kagiya.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.kagiya.dto.PurchaseHistoryInfoDTO;
import com.internousdev.kagiya.util.DBConnector;

public class PurchaseHistoryInfoDAO {

	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfo(String loginId) {
		List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList = new ArrayList<PurchaseHistoryInfoDTO>();
		String sql = "SELECT"
				+ " phi.id as id," /* ID */
				+ " phi.user_id," /* ユーザーID */
				+ " phi.product_count," /* 個数 */
				+ " pi.product_id," /* 商品ID */
				+ " pi.product_name," /*商品名*/
				+ " pi.product_name_kana," /* 商品名かな */
				+ " pi.product_description," /* 商品詳細 */
				+ " pi.category_id," /* カテゴリID */
				+ " pi.price," /* 価格 */
				+ " pi.image_file_name," /* 画像ファイルパス */
				+ " pi.image_file_path," /* 画像ファイル名 */
				+ " pi.release_company," /* 発売会社名 */
				+ " pi.release_date," /* 発売年月日 */
				+ " phi.price," /* 値段 */
				+ " phi.regist_date," /* 登録日 */
				+ " phi.update_date," /* 更新日 */
				+ " di.family_name," /* 姓 */
				+ " di.first_name," /* 姓 */
				+ " di.family_name_kana," /* 姓かな */
				+ " di.first_name_kana," /* 名かな */
				+ " di.email as email," /* メールアドレス */
				+ " di.tel_number," /* 電話番号 */
				+ " di.user_address"

				+ " FROM purchase_history_info phi"

				+ " LEFT JOIN product_info pi"

				+ " ON phi.product_id = pi.product_id"

				+ " LEFT JOIN destination_info di"

				+ " ON phi.destination_id = di.id"	//購入履歴テーブルをメインに外部結合

				+ " WHERE phi.user_id=?"

				+ " ORDER BY regist_date DESC";		//更新日時降順に並び替え

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);

			ResultSet resultSet = preparedStatement.executeQuery();		//SQL実行

			while (resultSet.next()) {
				PurchaseHistoryInfoDTO dto = new PurchaseHistoryInfoDTO();
				dto.setId(resultSet.getInt("id"));
				dto.setUserId(resultSet.getString("user_id"));
				dto.setProductId(resultSet.getInt("product_id"));
				dto.setProductCount(resultSet.getInt("product_count"));
				dto.setPrice(resultSet.getInt("price"));
				dto.setRegistDate(resultSet.getDate("regist_date"));
				dto.setUpdateDate(resultSet.getDate("update_date"));
				dto.setProductName(resultSet.getString("product_name"));
				dto.setProductNameKana(resultSet.getString("product_name_kana"));
				dto.setProductDescription(resultSet.getString("product_description"));
				dto.setCategoryId(resultSet.getInt("category_id"));
				dto.setImageFileName(resultSet.getString("image_file_name"));
				dto.setImageFilePath(resultSet.getString("image_file_path"));
				dto.setReleaseCompany(resultSet.getString("release_company"));
				dto.setReleaseDate(resultSet.getDate("release_date"));
				dto.setFamilyName(resultSet.getString("family_name"));
				dto.setFirstName(resultSet.getString("first_name"));
				dto.setFamilyNameKana(resultSet.getString("family_name_kana"));
				dto.setFirstNameKana(resultSet.getString("first_name_kana"));
				dto.setEmail(resultSet.getString("email"));
				dto.setTelNumber(resultSet.getString("tel_number"));
				dto.setUserAddress(resultSet.getString("user_address"));

				purchaseHistoryInfoDTOList.add(dto);		//抽出結果をpurchaseHistoryInfoDTOListに追加
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return purchaseHistoryInfoDTOList;
	}

	public int deleteAll(String loginId) {
		String sql = "delete from purchase_history_info where user_id=?";	//抽出条件に合うレコードを削除
		int count = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			count = preparedStatement.executeUpdate();		//SQL実行

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int regist(String loginId, int productId, int productCount, int destinationId, int price) {

		DBConnector dbConnector = new DBConnector();

		Connection connection = dbConnector.getConnection();

		String sql = "insert into purchase_history_info(user_id, product_id, product_count, price, "
				+ "destination_id, regist_date, update_date) values (?, ?, ?, ?, ?, now(), '0000-01-01')";

		int count = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, productCount);
			preparedStatement.setInt(4, price);
			preparedStatement.setInt(5, destinationId);

			count = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}



}
