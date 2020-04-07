/**
 * 
 */
package com.kyj.api.korean.pub.models.commons;

/**
 * @author calla
 *
 */
public class Result {

	private String dataString;
	private String dataType;
	private boolean isError;
	private String errMeg;
	
	public String getDataString() {
		return dataString;
	}
	public void setDataString(String dataString) {
		this.dataString = dataString;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public String getErrMeg() {
		return errMeg;
	}
	public void setErrMeg(String errMeg) {
		this.errMeg = errMeg;
	}
	
}
