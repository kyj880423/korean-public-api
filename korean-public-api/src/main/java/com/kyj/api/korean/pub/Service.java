/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub
 *	작성일   : 2020. 4. 3.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public interface Service<T> {

	/**
	 * API키가 저장된 파일명. <br/>
	 * @최초생성일 2020. 4. 3.
	 */
	public static final String API_KEY_FILE_NAME = "korean-public-api-key.properties";
	
	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @return
	 * @throws Exception
	 */
	public T request() throws Exception;

	/**
	 * set open api key <br/>
	 * 
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @param key
	 */
	public void setApiKey(String key);

}
