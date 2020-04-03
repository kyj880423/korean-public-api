/********************************
 *	프로젝트 : gargoyle-commons
 *	패키지   : com.kyj.fx.commons.utils
 *	작성일   : 2020. 4. 2.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub.models.newaddress;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class NewAddressListResponse {
	
	@JsonProperty("cmmMsgHeader")
	private CmmMsgHeader cmmMsgHeader;
	@JsonProperty("newAddressListAreaCd")
	private List<NewAddressListAreaCd> newAddressListAreaCd = Collections.emptyList();

	public CmmMsgHeader getCmmMsgHeader() {
		return cmmMsgHeader;
	}

	public void setCmmMsgHeader(CmmMsgHeader cmmMsgHeader) {
		this.cmmMsgHeader = cmmMsgHeader;
	}

	public List<NewAddressListAreaCd> getNewAddressListAreaCd() {
		return newAddressListAreaCd;
	}

	public void setNewAddressListAreaCd(List<NewAddressListAreaCd> newAddressListAreaCd) {
		this.newAddressListAreaCd = newAddressListAreaCd;
	}

	@Override
	public String toString() {
		return "NewAddressListResponse [cmmMsgHeader=" + cmmMsgHeader + ", newAddressListAreaCd=" + newAddressListAreaCd + "]";
	}

}
