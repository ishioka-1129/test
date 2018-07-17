package com.internousdev.kagiya.util;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils
;
public class InputChecker {



	/**
	 * このプログラムは渡された値を正規表現か検証するものです。
	 * 使い方はdoCheckに9つの引数を渡します。後半部分には当てはまる形式にtrueを入れます。
	 * emailを判別したいのであれば英字、数字、記号を含むため下記のようになります。
	 * 例(質問の内容,値,最小文字数,最大文字数,true,false,false,true,true)となります。
	 * 結果はList形式で渡されることに気を付けてください。
	 *
	 */
	public List<String> doCheck(String propertyName,String value,int minLength,int maxLength,boolean availableAlphabeticCharacters,boolean availableKanji,boolean availableHiragana,boolean availableHalfWidthDigit,boolean availableHalfWidthSymbols,boolean availableKatakana,boolean availableFullWidthSymbols){

		//検証した結果を入れるList
				List<String> stringList = new ArrayList<String>();
				List<String> characterTypeList = new ArrayList<String>();

				//入力欄が空かどうかを検証します
				if(StringUtils.isEmpty(value)){
					stringList.add(propertyName + "を入力してください。");
				}

				//入力欄が最小文字数以上、最大文字数以下かどうかを検証します
				if(value.length() < minLength || value.length() > maxLength){
					stringList.add(propertyName + "は" + minLength + "文字以上" + maxLength + "文字以下で入力してください。");
				}


				///////////入力された文字のタイプから何を判別するか決めます//////////
				String regularExpression = "";
				String errorExpression = "";


				if(availableAlphabeticCharacters || availableKanji || availableHiragana || availableHalfWidthDigit || availableHalfWidthSymbols||availableKatakana||availableFullWidthSymbols){
					regularExpression = "[^";
				}
				if(!(availableAlphabeticCharacters) || !(availableKanji) || !(availableHiragana) || !(availableHalfWidthDigit) || !(availableHalfWidthSymbols)|| !(availableKatakana)|| !(availableFullWidthSymbols)){
					errorExpression = "[^";
				}

				if(availableAlphabeticCharacters){
					regularExpression +="a-zA-Z";
						characterTypeList.add("半角英字");
				}else{
					errorExpression += "a-zA-Z";
				}

				if(availableKanji){
					regularExpression +="一-鉞";
					characterTypeList.add("漢字");
				}else{
					errorExpression +="一-鉞";
				}

				if(availableHiragana){
					regularExpression +="ぁ-ん";
					characterTypeList.add("ひらがな");
				}else{
					errorExpression +="ぁ-ん";
				}

				if(availableHalfWidthDigit){
					regularExpression +="0-9";
					characterTypeList.add("半角数字");
				}else{
					errorExpression+="0-9";
				}

				if(availableHalfWidthSymbols){
					regularExpression +="@.,;:!#$%&'*+-/=?^_`{|}~";
					characterTypeList.add("半角記号");
				}else{
					errorExpression +="@.,;:!#$%&'*+-/=?^_`{|}~";
				}

				if(availableKatakana){
					regularExpression +="ァ-ヺ";
					characterTypeList.add("カタカナ");
				}else{
					errorExpression +="ァ-ヺ";
				}

				if(availableFullWidthSymbols){
					regularExpression +="＠．，；：！＃＄％＆’＊＋―／＝？＾＿｀｛｜｝～";
					characterTypeList.add("全角記号");
				}else{
					errorExpression +="＠．，；：！＃＄％＆’＊＋―／＝？＾＿｀｛｜｝～";
				}

				if(!StringUtils.isEmpty(regularExpression)){
					regularExpression +="]+";
				}
				if(!StringUtils.isEmpty(errorExpression)){
					errorExpression +="]+";
				}
				////////////////////////////ここまで///////////////////////////


				//判別した項目に応じてエラーメッセージを返します
				String characterType = "";
				for(int i = 0;i < characterTypeList.size();i++){
					if(i == 0){
						characterType += characterTypeList.get(i).toString();
					}else{
						characterType += "、" + characterTypeList.get(i).toString();
					}
				}
				if(errorExpression.equals("")){
					if(value.matches(regularExpression)){
						stringList.add(propertyName + "は" + characterType + "で入力してください。");
					}
				}else{
					if(value.matches(regularExpression)||(!value.matches(errorExpression)&&!value.equals(""))){
						stringList.add(propertyName + "は" + characterType + "で入力してください。");
					}
				}

				return stringList;
			}


	//一度目のパスワードと二度目のパスワードが同じかを検証します。
	public List<String> doPasswordCheck(String password,String reConfirmationPassword){
		List<String> stringList = new ArrayList<String>();
		if(!(password.equals(reConfirmationPassword))){
			stringList.add("入力されたパスワードが異なります。");
		}
		return stringList;
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
