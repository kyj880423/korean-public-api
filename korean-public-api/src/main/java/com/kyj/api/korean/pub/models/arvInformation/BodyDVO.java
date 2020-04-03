/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub.model.arvInformation
 *	작성일   : 2020. 4. 3.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub.models.arvInformation;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class BodyDVO {

	private ItemsDVO items;

	public ItemsDVO getItems() {
		return items;
	}

	public void setItems(ItemsDVO items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "BodyDVO [items=" + items + "]";
	}

}
