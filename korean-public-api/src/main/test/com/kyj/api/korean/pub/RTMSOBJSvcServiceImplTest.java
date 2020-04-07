/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub
 *	작성일   : 2020. 4. 7.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class RTMSOBJSvcServiceImplTest {

	@Test
	public void test() throws Exception {
		var s = new RTMSOBJSvcServiceImpl();
		s.setLawdCd("11110");
		s.setPageNo(1);
		
		Void request = s.request();
	}

}
