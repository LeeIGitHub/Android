package lee.afk.http;

public class HttpResult {
	/**
	 * Http请求是否成功
	 */
	private boolean success;

	/**
	 * Http的返回
	 */
	private String result;

	/**
	 * 
	 * 请求的错误代码
	 * 
	 * 注：与下面的responseCode不一样，该code指后台返回的errorCode,并非Http请求错误code
	 * 
	 */
	private int errorCode;

	/**
	 * 请求错误的信息提示
	 * 
	 * 转为给用户看的报错信息，例如：errorCode = 0x001(密码输入错误)、errorCode = 404(网页不存在)...
	 */
	private String errorMessage;

	/**
	 * 其他报错信息
	 * 
	 * 给开发者看的log
	 */
	private String exceptionMessage;

	/**
	 * 
	 * 该code是由httpConnection.getResponseCode取得
	 * 
	 * 例如：404、200
	 */
	private int responseCode;

	/**
	 * 用来区别该请求调用的是那个接口
	 */
	private int type;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	@Override
	public String toString() {
		return "HttpResult [success=" + success + ", result=" + result + ", errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + ", exceptionMessage=" + exceptionMessage + ", responseCode=" + responseCode + ", type="
				+ type + "]";
	}
	
}
