/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub.utils
 *	작성일   : 2020. 4. 3.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class RequestUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @param <T>
	 * @param url
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static <T> T request200(URL url, BiFunction<InputStream, Charset, T> response) throws Exception {
		return request200(url, null, response, false);
	}

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @param <T>
	 * @param url
	 * @param response
	 * @param autoClose
	 * @return
	 * @throws Exception
	 */
	public static <T> T request200(URL url, BiFunction<InputStream, Charset, T> response, boolean autoClose) throws Exception {
		return request200(url, null, response, autoClose);
	}

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @param <T>
	 * @param url
	 * @param out
	 * @param response
	 * @param autoClose
	 * @return
	 * @throws Exception
	 */
	public static <T> T request200(URL url, byte[] out, BiFunction<InputStream, Charset, T> response, boolean autoClose) throws Exception {

		URLConnection openConnection = url.openConnection();
		HttpURLConnection conn = (HttpURLConnection) openConnection;
		InputStream is = null;
		T result = null;
		try {

			conn.setDefaultUseCaches(true);
			conn.setUseCaches(true);

			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:12.0) Gecko/20100101 Firefox/12.0");
			conn.setRequestProperty("Accept-Encoding", "UTF-8");
			// conn.setRequestProperty("Connection", "keep-alive");

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Accept-Encoding", "UTF-8");
			conn.setRequestProperty("Accept-Language", "KR");

			conn.setConnectTimeout(6000);
			conn.setReadTimeout(6000);

			if (out != null) {
				conn.setDoOutput(true);
				OutputStream stream = conn.getOutputStream();
				stream.write(out);
			}

			conn.connect();

			is = conn.getInputStream();

			String contentType = conn.getContentType();
			String contentEncoding = conn.getContentEncoding();
			// int contentLength = conn.getContentLength();
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			try {
				Optional<String> findAny = headerFields.keySet().stream().filter(f -> f != null).filter(str -> {

					if ("Accept-Encoding".equals(str))
						return true;

					return str.toLowerCase().indexOf("charset") >= 0;
				}).findAny();

				if (findAny.isPresent()) {
					String wow = findAny.get();
					List<String> list = headerFields.get(wow);
					wow = list.get(0);
					if (Charset.isSupported(wow)) {
						contentEncoding = wow;
					}
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

			// String charset = "UTF-8";
			if (ValueUtil.isEmpty(contentEncoding)) {
				if (contentType != null) {
					Optional<String> map = Stream.of(contentType.split(";")).filter(txt -> txt.toLowerCase().contains("charset"))
							.findFirst().map(v -> {
								return v.substring(v.indexOf("=") + 1);
							});
					if (map.isPresent())
						contentEncoding = map.get();
					else {
						String headerField = conn.getHeaderField("Accept-Charset");
						if (ValueUtil.isNotEmpty(headerField))
							contentEncoding = headerField;
					}
				}
			}

			if (ValueUtil.isEmpty(contentEncoding)) {
				contentEncoding = "UTF-8";
				LOGGER.debug("override default encoding 'UTF-8'");
			}

			LOGGER.debug("code : [{}] [{}] URL : {} ,  ", conn.getResponseCode(), contentEncoding, url.toString());

			if (200 == conn.getResponseCode()) {
				result = response.apply(is, Charset.forName(contentEncoding));
			}

		} finally {

			if (autoClose) {
				if (is != null)
					is.close();

				if (conn != null)
					conn.disconnect();
			}

		}
		return result;

	}

}
