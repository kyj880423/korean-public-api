/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub.model.arvInformation
 *	작성일   : 2020. 4. 3.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub.models.arvInformation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class ArvInformationDVO {

	@JsonProperty("header")
	private HeaderDVO header;
	@JsonProperty("body")
	private BodyDVO body;

	public HeaderDVO getHeader() {
		return header;
	}

	public void setHeader(HeaderDVO header) {
		this.header = header;
	}

	public BodyDVO getBody() {
		return body;
	}

	public void setBody(BodyDVO body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "ArvInformationDVO [header=" + header + ", body=" + body + "]";
	}

}
