/********************************
 *	프로젝트 : korean-public-api
 *	패키지   : com.kyj.api.korean.pub
 *	작성일   : 2020. 4. 7.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kyj.api.korean.pub.models.commons.Result;
import com.kyj.api.korean.pub.utils.ValueUtil;

/**
 * 
 * 아파트 실거래 자료 api <br/>
 * 
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class RTMSOBJSvcServiceImpl extends AbstractService<Void> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RTMSOBJSvcServiceImpl.class);

	private String lawdCd;
	private int numOfRows = 10;;
	private int pageNo = 1;
	private String dealYmd;

	@Override
	public Void request(Result r) throws Exception {
		if (r.isError())
			return null;

		String dataString = r.getDataString();
		LOGGER.debug(dataString);
		return null;
	}

	@Override
	public Map<String, Object> getParameter() {

		var param = new HashMap<String, Object>();
		param.put("numOfRows", numOfRows);
		param.put("lawdCd", lawdCd);
		param.put("pageNo", pageNo);

		String dateString = "";
		var formatter = new SimpleDateFormat("yyyyMM");
		if (this.dealYmd == null || this.dealYmd.trim().length() == 0) {
			dateString = formatter.format(new Date());
		} else {
			try {
				Date parse = formatter.parse(this.dealYmd);
			} catch (ParseException e) {
				LOGGER.error(ValueUtil.toString(e));
				dateString = formatter.format(new Date());
			}
			dateString = formatter.format(this.dealYmd);
		}
		param.put("dealYmd", dateString);

		return param;
	}

	/**
	 * @return the lawdCd
	 */
	public String getLawdCd() {
		return lawdCd;
	}

	/**
	 * @param lawdCd
	 *            the lawdCd to set
	 */
	public void setLawdCd(String lawdCd) {
		this.lawdCd = lawdCd;
	}

	/**
	 * @return the numOfRows
	 */
	public int getNumOfRows() {
		return numOfRows;
	}

	/**
	 * @param numOfRows
	 *            the numOfRows to set
	 */
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

}
