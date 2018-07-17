package com.internousdev.kagiya.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.kagiya.dto.UserInfoDTO;
import com.internousdev.kagiya.util.DBConnector;

public class UserInfoDAO {

//入力された値を引数としてcreateUserに入れる
	public int createUser(String family_name,String first_name,String family_name_kana,String first_name_kana,String sex,String email,String user_id,String password){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
//これからデータを登録するためデータ登録数を管理するcountは0とする
		int count = 0;
//SQL文に値をそれぞれ入れexecuteUpdateでデータを返す。その際いくつデータを登録したかcountに入れる
		String sql = "INSERT INTO user_info(user_id,password,family_name,first_name,family_name_kana,first_name_kana,sex,email,status,logined,regist_date,update_date) values(?,?,?,?,?,?,?,?,?,?,now(),0)";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,user_id);
			preparedStatement.setString(2,password);
			preparedStatement.setString(3,family_name);
			preparedStatement.setString(4,first_name);
			preparedStatement.setString(5,family_name_kana);
			preparedStatement.setString(6,first_name_kana);
			preparedStatement.setString(7,sex);
			preparedStatement.setString(8,email);
			preparedStatement.setInt(9,0);
			preparedStatement.setInt(10,1);
			count = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}

	public UserInfoDTO getUserInfo(String user_id,String password){

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		UserInfoDTO userInfoDTO = new UserInfoDTO();

		String sql = "SELECT * FROM user_info where user_id = ? AND password = ?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,user_id);
			preparedStatement.setString(2,password);


			ResultSet resultSet = preparedStatement.executeQuery();


			if(resultSet.next()){
				userInfoDTO.setUser_id(resultSet.getString("user_id"));
				userInfoDTO.setPassword(resultSet.getString("password"));
				userInfoDTO.setStatus(resultSet.getInt("status"));

				if(!(resultSet.getString("user_id").equals(null))){
					userInfoDTO.setLogined(1);
			}
		}

		}catch(Exception e){
				e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userInfoDTO;
	}

	public ArrayList<UserInfoDTO> getUserInfo(String loginId) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		ArrayList<UserInfoDTO> userInfoDTO = new ArrayList<UserInfoDTO>();

		String sql = "SELECT * FROM user_info where user_id = ?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,loginId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				UserInfoDTO dto = new UserInfoDTO();
				dto.setFamily_name(resultSet.getString("family_name"));
				dto.setFirst_name(resultSet.getString("first_name"));
				dto.setFamily_name_kana(resultSet.getString("family_name_kana"));
				dto.setFirst_name_kana(resultSet.getString("first_name_kana"));
				dto.setSex(resultSet.getInt("sex"));
				dto.setEmail(resultSet.getString("email"));
				userInfoDTO.add(dto);
			} else {
				userInfoDTO = null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfoDTO;
	}

	//confirmでPWに**入れる
	public String concealPassword(String password) {
		int beginIndex = 0;
		int endIndex = 1;
		if(password.length() > 1) {
			endIndex = 2;
		}
		StringBuilder stringBuilder = new StringBuilder("****************");

		String concealPassword = stringBuilder.replace(beginIndex, endIndex, password.substring(beginIndex,endIndex)).toString();
		return concealPassword;
	}

	//ID&PWあってるか
	public boolean isExistsUserInfo(String loginId, String password) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		boolean result = false;
		String sql = "select count(*) as count from user_info where user_id=? and password=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				if (resultSet.getInt("count") > 0) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//PWリセット
	public int resetPassword(String loginId, String password) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		String sql = "update user_info set password=? where user_id=?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, loginId);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}



}


