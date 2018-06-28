package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.ecsite2.dto.ManagerLoginDTO;
import com.internousdev.ecsite2.util.DBConnector;

public class ManagerLoginDAO {
	private DBConnector dbConnector = new DBConnector();
	private Connection conn = dbConnector.getConnection();
	private ManagerLoginDTO dto = new ManagerLoginDTO();

	public ManagerLoginDTO getLoginManagerInfo(String loginUserId, String loginPassword){

		String sql = "SELECT * FROM login_manager where manager_id = ? AND password = ?";

		try{
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, loginUserId);
			ps.setString(2, loginPassword);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				dto.setLoginId(rs.getString("manager_id"));
				dto.setLoginPassword(rs.getString("password"));
				dto.setUserName(rs.getString("user_name"));

				if(!(rs.getString("manager_id").equals(null))){
					dto.setLoginFlg(true);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}

	public ManagerLoginDTO getManagerLoginDTO(){
		return dto;
	}
}
