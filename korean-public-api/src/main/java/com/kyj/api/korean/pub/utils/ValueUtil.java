/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub.utils
 *	작성일   : 2020. 4. 3.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.io.output.StringBuilderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class ValueUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValueUtil.class);

	/**
	 * get Content <br/>
	 * 
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @param input
	 * @param u
	 * @return
	 * @throws IOException
	 */
	public static String toString(InputStream input, Charset u) {
		byte[] buffer = new byte[1024 * 4];
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String out = "";
		try {
			int n = -1;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
			out = output.toString(u);
		} catch (Exception e) {
			LOGGER.error(ValueUtil.toString(e));
		}
		return out;
	}

	/**
	 * 에러 메세지 상세화
	 *
	 * @param title
	 *            메세지 타이틀
	 * @param e
	 * @return
	 */
	public static String toString(String title, Throwable e) {
		if (e == null)
			return "[warnning] Exception is null";

		String errMsg = "";
		try (StringBuilderWriter sbw = new StringBuilderWriter()) {
			try (PrintWriter printWriter = new PrintWriter(sbw, true)) {
				if (title != null)
					printWriter.write("#############  ".concat(title).concat("  ##############\n"));
				e.printStackTrace(printWriter);
			}
			errMsg = sbw.toString();
		}
		return errMsg;
	}

	public static String toString(Exception e) {
		return toString(null, e);
	}

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @param contentEncoding
	 * @return
	 */
	public static boolean isEmpty(String contentEncoding) {
		return !isNotEmpty(contentEncoding);
	}

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2020. 4. 3.
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		boolean flag = true;
		if (obj != null) {
			if (obj instanceof String) {
				String valueOf = obj.toString().trim();
				flag = valueOf.length() > 0 && !valueOf.equals("") && !valueOf.equals("null");
			} else if (obj instanceof Collection) {
				Collection<?> list = (Collection<?>) obj;
				flag = !list.isEmpty();

				// flag = list.size() > 0;
			} else if (obj instanceof Map) {

				Map<?, ?> map = (Map<?, ?>) obj;
				flag = map.size() > 0;
			}
		} else {
			flag = false;
		}
		return flag;

	}

}
