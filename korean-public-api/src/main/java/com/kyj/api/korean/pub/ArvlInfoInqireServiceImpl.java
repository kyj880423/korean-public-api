/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub
 *	작성일   : 2020. 4. 3.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyj.api.korean.pub.models.arvInformation.ArvInformationDVO;
import com.kyj.api.korean.pub.models.arvInformation.HeaderDVO;
import com.kyj.api.korean.pub.models.newaddress.NewAddressListResponse;
import com.kyj.api.korean.pub.utils.RequestUtil;
import com.kyj.api.korean.pub.utils.ValueUtil;

/**
 * 도착정보 조회 서비스 <br/>
 * 
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class ArvlInfoInqireServiceImpl extends AbstractService<ArvInformationDVO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArvlInfoInqireServiceImpl.class);

	private ArvInformationDVO response;

	@Override
	public ArvInformationDVO request() throws Exception {

		String url = evaluate();

		this.response = RequestUtil.request200(new URL(url), new BiFunction<InputStream, Charset, ArvInformationDVO>() {
			@Override
			public ArvInformationDVO apply(InputStream t, Charset u) {
				try {
					String json = ValueUtil.toString(t, u);
					LOGGER.debug("JSON Result : {}", json);
					return to(json);
				} catch (Exception e) {
					ArvInformationDVO d = new ArvInformationDVO();
					HeaderDVO h = new HeaderDVO();
					h.setResultCode("");
					h.setResultMsg(ValueUtil.toString(e));
					d.setHeader(h);
					return d;
				}
			}
		}, false);

		// this.currentPage = this.response.getCmmMsgHeader().getCurrentPage();
		return response;

	}

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @param json
	 * @return
	 * @throws Exception
	 */
	ArvInformationDVO to(String json) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode readTree = mapper.readTree(json);
		JsonNode jsonNode = readTree.get("response");
		return mapper.readValue(jsonNode.toString(), ArvInformationDVO.class);
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

	@Override
	public Map<String, Object> getParameter() {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("serviceKey", getApiKey());
		return hashMap;
	}

}
