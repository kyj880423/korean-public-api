/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub.model.arvInformation
 *	작성일   : 2020. 4. 3.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub.models.arvInformation;

import java.util.List;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class ItemsDVO {

	private List<CityCodeDVO> item;

	public List<CityCodeDVO> getItem() {
		return item;
	}

	public void setItem(List<CityCodeDVO> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "ItemsDVO [item=" + item + "]";
	}

}
