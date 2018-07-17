package com.internousdev.kagiya.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.internousdev.kagiya.dto.MCategoryDTO;
import com.internousdev.kagiya.util.DBConnector;

public class MCategoryDAO {
	//カテゴリー情報表示 ヘッダーで使用
	public List<MCategoryDTO> getMCategoryList() {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<MCategoryDTO> mMCategoryDTOList = new ArrayList<MCategoryDTO>();
		String sql = "select * from m_category";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				MCategoryDTO mCategoryDTO = new MCategoryDTO();
				mCategoryDTO.setId(resultSet.getInt("id"));
				mCategoryDTO.setCategoryId(resultSet.getInt("category_id"));
				mCategoryDTO.setCategoryName(resultSet.getString("category_name"));
				mCategoryDTO.setCategortDescription(resultSet.getString("category_description"));
				mCategoryDTO.setInsertDate(resultSet.getDate("insert_date"));
				mCategoryDTO.setUpdateDate(resultSet.getDate("update_date"));
				mMCategoryDTOList.add(mCategoryDTO);
			}
			Iterator<MCategoryDTO> iterator = mMCategoryDTOList.iterator();
			if(!(iterator.hasNext())) {
				mMCategoryDTOList = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try{
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return mMCategoryDTOList;
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
}
