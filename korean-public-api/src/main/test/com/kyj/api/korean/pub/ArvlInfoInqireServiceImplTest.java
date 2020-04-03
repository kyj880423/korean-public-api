/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub
 *	작성일   : 2020. 4. 3.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub;

import static org.junit.Assert.*;

import org.junit.Test;

import com.kyj.api.korean.pub.models.arvInformation.ArvInformationDVO;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class ArvlInfoInqireServiceImplTest {

	@Test
	public void test() throws Exception {
		ArvlInfoInqireServiceImpl s = new ArvlInfoInqireServiceImpl();
		ArvInformationDVO request = s.request();
		System.out.println(request);
	}

}
