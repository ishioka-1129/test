package com.internousdev.kagiya.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.kagiya.dto.DestinationInfoDTO;
import com.internousdev.kagiya.util.DBConnector;

public class DestinationInfoDAO {

	public int insert(String userId,String familyName,String firstName,String familyNameKana,String firstNameKana,
						String email,String telNumber,String userAddress){
		DBConnector dbConnector=new DBConnector();
		Connection con=dbConnector.getConnection();

		int count=0;

		String sql="INSERT INTO destination_info(user_id, family_name, first_name, family_name_kana, first_name_kana,"
				+ " email, tel_number, user_address, regist_date, update_date)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, now(), '0000-01-01')";
		//ユーザーID、姓、名、姓ふりがな、名ふりがな、メールアドレス、電話番号、住所、登録日時、更新日時をDBに挿入。

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, familyName);
			ps.setString(3, firstName);
			ps.setString(4, familyNameKana);
			ps.setString(5, firstNameKana);
			ps.setString(6, email);
			ps.setString(7, telNumber);
			ps.setString(8, userAddress);
			count=ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		try{
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}

	public List<DestinationInfoDTO>getDestinationInfo(String loginId)throws SQLException{
		DBConnector dbConnector=new DBConnector();
		Connection con=dbConnector.getConnection();
		List<DestinationInfoDTO>destinationDTOList=new ArrayList<DestinationInfoDTO>();

		String sql="SELECT id, family_name, first_name, family_name_kana, first_name_kana, user_address, tel_number,"
				+ " email FROM destination_info WHERE user_id=?";

		try{
			con=dbConnector.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, loginId);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				DestinationInfoDTO destinationDTO=new DestinationInfoDTO();
				destinationDTO.setId(rs.getInt("id"));
				destinationDTO.setFamilyName(rs.getString("family_name"));
				destinationDTO.setFirstName(rs.getString("first_name"));
				destinationDTO.setFamilyNameKana(rs.getString("family_name_kana"));
				destinationDTO.setFirstNameKana(rs.getString("first_name_kana"));
				destinationDTO.setUserAddress(rs.getString("user_address"));
				destinationDTO.setEmail(rs.getString("email"));
				destinationDTO.setTelNumber(rs.getString("tel_number"));
				destinationDTOList.add(destinationDTO);
			}//結果をDestinationDTOに追加。
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			con.close();
		}
		return destinationDTOList;
	}
}
