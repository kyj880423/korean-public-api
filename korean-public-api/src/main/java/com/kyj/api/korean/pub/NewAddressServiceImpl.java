/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub
 *	작성일   : 2020. 4. 3.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyj.api.korean.pub.models.newaddress.CmmMsgHeader;
import com.kyj.api.korean.pub.models.newaddress.NewAddressListResponse;
import com.kyj.api.korean.pub.utils.RequestUtil;
import com.kyj.api.korean.pub.utils.ValueUtil;

/**
 * 새주소 5자리 우편번호 조회 서비스 <br/>
 * 
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class NewAddressServiceImpl extends AbstractService<NewAddressListResponse> implements Iterator<NewAddressListResponse> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewAddressServiceImpl.class);

	private NewAddressListResponse response;

	@Override
	public NewAddressListResponse request() throws Exception {

		String url = evaluate();

		this.response = RequestUtil.request200(new URL(url), new BiFunction<InputStream, Charset, NewAddressListResponse>() {
			@Override
			public NewAddressListResponse apply(InputStream t, Charset u) {
				try {
					String json = ValueUtil.toString(t, u);
					LOGGER.debug("JSON Result : {}", json);
					return to(json);
				} catch (Exception e) {
					NewAddressListResponse newAddressListResponse = new NewAddressListResponse();
					CmmMsgHeader cmmMsgHeader = new CmmMsgHeader();
					cmmMsgHeader.setTotalCount(0);
					cmmMsgHeader.setSuccessYN("N");
					cmmMsgHeader.setErrMsg(ValueUtil.toString(e));
					newAddressListResponse.setCmmMsgHeader(cmmMsgHeader);
					return newAddressListResponse;
				}
			}
		}, false);

		this.currentPage = this.response.getCmmMsgHeader().getCurrentPage();
		return response;

	}

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @param json
	 * @return
	 * @throws Exception
	 */
	NewAddressListResponse to(String json) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode readTree = mapper.readTree(json);
		JsonNode jsonNode = readTree.get("NewAddressListResponse");
		return mapper.readValue(jsonNode.toString(), NewAddressListResponse.class);
	}

	/**
	 * 
	 * 1. seachSe = dong 인 경우 : 요청된 지번 주소에 대해 일치하는 도로명 주소와 지번주소를 표기 1. seachSe =
	 * road 인 경우 : 요청된 도로명 주소에 대해 일치하는 도로명 주소와 지번주소를 표기
	 * 
	 * @author KYJ (callakrsos@naver.com)
	 *
	 */
	enum SearchSe {
		dong("dong"), road("road");

		private String name;

		SearchSe(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

	int countPerPage = 10;
	int currentPage = 1;
	SearchSe searchSe = SearchSe.dong;
	String srchwrd = "주월동 408-1";

	public void setSrcWrd(String keyword) {
		this.srchwrd = keyword;
	}

	@Override
	public Map<String, Object> getParameter() {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("countPerPage", countPerPage);
		hashMap.put("currentPage", currentPage);
		hashMap.put("serviceKey", getApiKey());
		hashMap.put("searchSe", searchSe.getName());
		try {
			hashMap.put("srchwrd", URLEncoder.encode(srchwrd, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return hashMap;
	}

	@Override
	public boolean hasNext() {

		if (this.response == null)
			return true;

		return this.response.getCmmMsgHeader().getCurrentPage() < currentPage;
	}

	@Override
	public NewAddressListResponse next() {
		try {
			return this.request();
		} catch (Exception e) {
			e.printStackTrace();
			this.response = null;
		}
		return this.response;

	}

}
