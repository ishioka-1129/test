package com.internousdev.sampleweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.sampleweb.dto.ManagerLoginDTO;
import com.internousdev.sampleweb.util.DBConnector;


public class ManagerLoginDAO {

	public ManagerLoginDTO getLoginManagerInfo(String loginManagerId,String loginPassword){

		DBConnector dbConnector = new DBConnector();
		Connection conn = dbConnector.getConnection();
		ManagerLoginDTO mlDTO = new ManagerLoginDTO();
		String sql = "SELECT * FROM manager_info where user_id = ? AND password = ?";

		try{
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, loginManagerId);
			ps.setString(2, loginPassword);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				mlDTO.setLoginId(rs.getString("user_id"));
				mlDTO.setPassword(rs.getString("password"));
				mlDTO.setUserName(rs.getString("family_name"));

				if(!(rs.getString("user_id").equals(null))){
					mlDTO.setLoginFlg(true);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return mlDTO;
	}

}
