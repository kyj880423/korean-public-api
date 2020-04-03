package com.kyj.api.korean.pub;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyj.api.korean.pub.models.newaddress.NewAddressListResponse;

/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : 
 *	작성일   : 2020. 4. 3.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class APITest {

	private static final Logger LOGGER = LoggerFactory.getLogger(APITest.class);
	Properties properties;

	@Before
	public void init() throws IOException {
		properties = new Properties();
		properties.load(new FileInputStream(new File("korean-public-api-key.properties")));
	}

	@Test
	public void retriveNewAdressAreaCdServiceTest() throws MalformedURLException, Exception {

		NewAddressServiceImpl service = new NewAddressServiceImpl();
		NewAddressListResponse r = service.request();
		System.out.println(r);

		LOGGER.debug("Header :  {} ", r.getCmmMsgHeader());
		LOGGER.debug("## Body ##");
		r.getNewAddressListAreaCd().forEach(a -> {
			LOGGER.debug(a.toString());
		});

	}

	@Test
	public void retriveNewAdressAreaCdServiceIteratorTest() throws MalformedURLException, Exception {

		NewAddressServiceImpl service = new NewAddressServiceImpl();
//		service.request();
		while (service.hasNext()) {
			NewAddressListResponse r = service.next();
			System.out.println(r);
			LOGGER.debug("Header :  {} ", r.getCmmMsgHeader());
			LOGGER.debug("## Body ##");
			r.getNewAddressListAreaCd().forEach(a -> {
				LOGGER.debug(a.toString());
			});
		}

	}

	NewAddressListResponse to(String json) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode readTree = mapper.readTree(json);
		JsonNode jsonNode = readTree.get("NewAddressListResponse");
		return mapper.readValue(jsonNode.toString(), NewAddressListResponse.class);
	}

	@Test
	public void toObjectTest() throws Exception {
		String json = "{\"NewAddressListResponse\":{\"cmmMsgHeader\":{\"requestMsgId\":\"\",\"responseMsgId\":\"\",\"responseTime\":\"20200403:08075570\",\"successYN\":\"Y\",\"returnCode\":\"00\",\"errMsg\":\"\",\"totalCount\":2,\"countPerPage\":10,\"totalPage\":1,\"currentPage\":1},\"newAddressListAreaCd\":[{\"zipNo\":61725,\"lnmAdres\":\"광주광역시 남구 서문대로 745 (주월동, 빅스포)\",\"rnAdres\":\"광주광역시 남구 주월동 408-1 빅스포\"},{\"zipNo\":61725,\"lnmAdres\":\"광주광역시 남구 서문대로749번길 3 (주월동, 클럽메드엄지통닭)\",\"rnAdres\":\"광주광역시 남구 주월동 408-1 클럽메드엄지통닭\"}]}}";
		NewAddressListResponse newAddressListResponse = to(json);
		System.out.println(newAddressListResponse);
	}

}
