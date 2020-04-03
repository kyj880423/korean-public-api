/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub
 *	작성일   : 2020. 4. 3.
 *		작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.kyj.api.korean.pub.utils.ValueUtil;

/**
 * 
 * 공공 데이터를 처리하기 위한 추상 클래스 정의 <br/>
 * 
 * @author KYJ (callakrsos@naver.com)
 *
 */
public abstract class AbstractService<T> implements Service<T> {

	public AbstractService() {
		loadApiKey();
	}

	/**
	 * api key 값을 리턴한다. api는 프로젝트 korea-public-api-key 파일에 구현해야하며 <br/>
	 * 
	 * 키는 구현된 클래스이름 + .key로 설정되야한다. <br/>
	 * 
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 */
	protected void loadApiKey() {
		String classSimpleName = getClass().getSimpleName();
		var p = new Properties();
		try {
			FileInputStream in = new FileInputStream(new File(API_KEY_FILE_NAME));
			String c = ValueUtil.toString(in, StandardCharsets.UTF_8);
			p.load(new ByteArrayInputStream(c.getBytes(StandardCharsets.UTF_8)));
			String format = String.format("%s.key", classSimpleName);

			if (null == p.get(format))
				throw new RuntimeException(" key name or value is empty. :: " + format);

			this.apiKey = p.get(format).toString();

			if (ValueUtil.isEmpty(this.apiKey))
				throw new RuntimeException(" key name or value is empty. :: " + format);

		} catch (IOException e) {
			throw new RuntimeException("API Key load Failed. ", e);
		}
	}

	/**
	 * open api key. <br/>
	 * 
	 * @최초생성일 2020. 4. 3.
	 */
	private String apiKey;

	public abstract T request() throws Exception;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kyj.api.korean.pub.Service#setApiKey(java.lang.String)
	 */
	public void setApiKey(String key) {
		this.apiKey = key;
	}

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @return
	 */
	protected String getApiKey() {
		return this.apiKey;
	}

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @return
	 */
	protected File getSeviceApiDir() {
		String classSimpleName = this.getClass().getSimpleName();
		File file = new File("services", classSimpleName + ".vm");
		return file;
	}

	/**
	 * url template을 리턴한다 해당 템플릿은 프로젝트 service-template 하위에 구현하며 <br/>
	 * 파일 명명규칙은 아래와 같다. <br/>
	 * 클래스이름 + .vm <br/>
	 * 
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @param f
	 *            템플릿 파일
	 * @return 템플릿 컨텐츠
	 * @throws IOException
	 */
	protected String readTemplate(File f) throws IOException {
		return Files.readString(f.toPath());
	}

	/**
	 * API 서비스로 보낼 URL 파라미터로 <br/>
	 * API 서비스의 파라미터값을 리턴하는 코드를 작성해야한다 <br/>
	 * 
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @return
	 */
	public abstract Map<String, Object> getParameter();

	/**
	 * URL 주소를 관련 파라미터와 매핑된 결과를 리턴한다. <br/>
	 * Velocity 문법 규칙에 따른 내용이어야 한다. <br/>
	 * 
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @return
	 * @throws IOException
	 */
	protected String evaluate() throws IOException {

		File file = getSeviceApiDir();
		if (!file.exists())
			throw new FileNotFoundException("File Not Found : " + file.getName());

		String template = readTemplate(file);

		VelocityContext velocityContext = new VelocityContext(getParameter());
		StringWriter writer = new StringWriter();
		Velocity.evaluate(velocityContext, writer, this.getClass().getName(), template);

		return writer.toString();
	}

}
