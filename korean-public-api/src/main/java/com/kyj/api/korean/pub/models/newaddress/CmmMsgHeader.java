/********************************
 *	프로젝트 : gargoyle-commons
 *	패키지   : com.kyj.fx.commons.utils
 *	작성일   : 2020. 4. 2.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub.models.newaddress;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class CmmMsgHeader {

	private String cmmMsgHeader;
	private String responseMsgId;
	private String requestMsgId;
	private String responseTime;
	private String successYN;
	private String returnCode;
	private String errMsg;
	private int totalCount;
	private int countPerPage;
	private int totalPage;
	private int currentPage;

	public String getCmmMsgHeader() {
		return cmmMsgHeader;
	}

	public void setCmmMsgHeader(String cmmMsgHeader) {
		this.cmmMsgHeader = cmmMsgHeader;
	}

	public String getRequestMsgId() {
		return requestMsgId;
	}

	public void setRequestMsgId(String requestMsgId) {
		this.requestMsgId = requestMsgId;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getSuccessYN() {
		return successYN;
	}

	public void setSuccessYN(String successYN) {
		this.successYN = successYN;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getResponseMsgId() {
		return responseMsgId;
	}

	public void setResponseMsgId(String responseMsgId) {
		this.responseMsgId = responseMsgId;
	}

	@Override
	public String toString() {
		return "CmmMsgHeader [cmmMsgHeader=" + cmmMsgHeader + ", requestMsgId=" + requestMsgId + ", responseTime=" + responseTime
				+ ", successYN=" + successYN + ", returnCode=" + returnCode + ", errMsg=" + errMsg + ", totalCount=" + totalCount
				+ ", countPerPage=" + countPerPage + ", totalPage=" + totalPage + ", currentPage=" + currentPage + "]";
	}

}
