package com.internousdev.kagiya.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.MCategoryDAO;
import com.internousdev.kagiya.dao.ProductInfoDAO;
import com.internousdev.kagiya.dto.MCategoryDTO;
import com.internousdev.kagiya.dto.PaginationDTO;
import com.internousdev.kagiya.dto.ProductInfoDTO;
import com.internousdev.kagiya.util.InputChecker;
import com.internousdev.kagiya.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class SearchItemAction extends ActionSupport implements SessionAware {

	private String keywords;
	private int categoryId;
	private String pageNo;
	private Map<String, Object> session;
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
	private List<String> keywordsErrorMessageList = new ArrayList<String>();
	private List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
	ProductInfoDAO productInfoDAO = new ProductInfoDAO();

	public String execute() throws SQLException {
		String result = ERROR;
		InputChecker inputChecker = new InputChecker();

		//keywordsがnullの時、空文字を代入
		if(keywords == null) {
			keywords = "";
		}

		//半角記号だけNG
		keywordsErrorMessageList = inputChecker.doCheck("検索ワード", keywords, 0, 16, true, true, true, true, false, true, true);

		session.put("keywordsErrorMessageList", keywordsErrorMessageList);

		//categoryIdが0or1の時、すべてのカテゴリーからキーワード検索
		if (categoryId == 1 || categoryId == 0) {
			result = SUCCESS;
			productInfoDTOList = productInfoDAO.getProductListAllByKeywords(keywords.replaceAll("　", " ").split(" "));
		} else {//categoryIdとキーワードから検索
			result = SUCCESS;
			productInfoDTOList = productInfoDAO.getProductInfoListByKeywords(keywords.replaceAll("　", " ").split(" "), categoryId);
		}

		//検索結果にデータが入っているか確認。なければnullを代入
		Iterator<ProductInfoDTO> iterator = productInfoDTOList.iterator();
		if(!(iterator.hasNext())) {
			productInfoDTOList = null;
		}

		//mCategoryDtoListをセッションに挿入
		if(!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDao = new MCategoryDAO();
			mCategoryDtoList = mCategoryDao.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}

		if(!(productInfoDTOList == null)) {//検索結果がnullでなければ
			Pagination pagination = new Pagination();
			PaginationDTO paginationDTO = new PaginationDTO();
			if(pageNo == null) {//現在のページが１ページ目の時のページデータ
				paginationDTO = pagination.initialize(productInfoDTOList, 9);
			} else {//現在のページが2ページ目以降の時のページデータ
				paginationDTO = pagination.getPage(productInfoDTOList, 9, pageNo);
			}//ページデータをセッションに挿入
			session.put("totalPageSize", paginationDTO.getTotalPageSize());
			session.put("currentPageNo", paginationDTO.getCurrentPageNo());
			session.put("totalRecordSize", paginationDTO.getTotalPageSize());
			session.put("startRecordNo", paginationDTO.getStartRecordNo());
			session.put("endRecordSize", paginationDTO.getEndRecordNo());
			session.put("pageNumberList", paginationDTO.getPageNumberList());
			session.put("productInfoDTOList", paginationDTO.getCurrentProductInfoPage());
			session.put("hasNextPage", paginationDTO.hasNextPage());
			session.put("hasPreviousPage", paginationDTO.hasPreviousPage());
			session.put("nextPageNo", paginationDTO.getNextPageNo());
			session.put("previousPageNo", paginationDTO.getPreviousPageNo());
		} else {//検索結果がnullならセッションにnullを挿入
			session.put("productInfoDTOList", null);
		}

		return result;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<String> getKeywordsErrorMessageList() {
		return keywordsErrorMessageList;
	}

	public List<ProductInfoDTO> getProductInfoDTOList() {
		return productInfoDTOList;
	}

}
