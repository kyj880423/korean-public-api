/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub.models.arvInformation
 *	작성일   : 2020. 4. 3.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub.models.arvInformation;

import java.util.List;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class ItemDVO {
	List<CityCodeDVO> items;

	public List<CityCodeDVO> getItems() {
		return items;
	}

	public void setItems(List<CityCodeDVO> items) {
		this.items = items;
	}

}
