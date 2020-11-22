package se.visionmate.api.v1.session;

import org.springframework.http.HttpStatus;

public class CallResponse {

	
	private boolean success;
	private String message;
	private HttpStatus code;
	
	
	public CallResponse() {
	}

	
	public CallResponse(Object msg) {
		if (msg instanceof ErrorMessage) {
			success = false;
			message = ((ErrorMessage) msg).getMsg();
			code = ((ErrorMessage) msg).getCode();
		} else if (msg instanceof SuccessMessage) {
			success = true;
			message = ((SuccessMessage) msg).getMsg();
			code = ((SuccessMessage) msg).getCode();
		}
	}


	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getCode() {
		return code;
	}
	public void setMessage(HttpStatus code) {
		this.code = code;
	}


	@Override
	public String toString() {
		return "CallResponse [success=" + success + ", message=" + message + ", code=" + code + "]";
	}

}
