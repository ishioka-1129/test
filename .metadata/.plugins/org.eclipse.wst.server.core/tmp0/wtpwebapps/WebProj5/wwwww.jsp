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
					preparedStatement.setString(8, release_company);
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

			//商品削除用 削除する商品情報取得
			public ProductInfoDTO getSelectProductInfoName(String productName) {
				DBConnector dbConnector = new DBConnector();
				Connection connection = dbConnector.getConnection();
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();

				String sql = "SELECT id, product_id, product_name, product_name_kana, product_description, category_id, price, image_file_path, release_date, release_company, status FROM product_info WHERE product_name = ?";

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
					productInfoDTO.setCatgoryId(resultSet.getInt("category_id"));
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

			//商品削除用 DBから選択した商品と同じproductnameを検索
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
			
			
			
					<!-- AdminPaginationAction -->
 		<action name="AdminPaginationAction"
 		class="com.internousdev.kagiya.action.AdminPaginationAction" method="execute">
 		<result name="delete">productDelete.jsp</result>
 		</action>
 		
 		
 		