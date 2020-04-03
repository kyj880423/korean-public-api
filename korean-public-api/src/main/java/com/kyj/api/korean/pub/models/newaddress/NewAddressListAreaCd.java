/********************************
 *	프로젝트 : gargoyle-commons
 *	패키지   : com.kyj.fx.commons.utils
 *	작성일   : 2020. 4. 2.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.api.korean.pub.models.newaddress;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class NewAddressListAreaCd {

	private int zipNo;
	private String lnmAdres;
	private String rnAdres;

	public int getZipNo() {
		return zipNo;
	}

	public void setZipNo(int zipNo) {
		this.zipNo = zipNo;
	}

	public String getLnmAdres() {
		return lnmAdres;
	}

	public void setLnmAdres(String lnmAdres) {
		this.lnmAdres = lnmAdres;
	}

	public String getRnAdres() {
		return rnAdres;
	}

	public void setRnAdres(String rnAdres) {
		this.rnAdres = rnAdres;
	}

	@Override
	public String toString() {
		return "NewAddressListAreaCd [zipNo=" + zipNo + ", lnmAdres=" + lnmAdres + ", rnAdres=" + rnAdres + "]";
	}

}
