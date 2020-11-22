package se.visionmate.api.v1.session;

import org.springframework.http.HttpStatus;

public enum SuccessMessage {
	
	
	USER_LOGGED_IN("User logged in", HttpStatus.OK),
	USER_LOGGED_OUT("User logged out", HttpStatus.OK),
	FORGOT_PASSWORD("User password setted to null, on next login password will be settled for this account", HttpStatus.OK),
	RESET_PASSWORD("User password changed", HttpStatus.OK),
	USER_CREATED("User created", HttpStatus.CREATED), 
	USER_UPDATED("User updated", HttpStatus.OK),
	USER_DELETED("User deleted", HttpStatus.OK),
	ROLE_CREATED("Role created", HttpStatus.CREATED),
	ROLE_UPDATED("Role updated", HttpStatus.OK),
	ROLE_DELETED("Role deleted", HttpStatus.OK);
 
	
    private String msg;
    private HttpStatus code;
 
    SuccessMessage(String aMsg, HttpStatus aCode) {
        this.msg = aMsg;
        this.code = aCode;
    }
 
    public String getMsg() {
        return msg;
    }
    
    public HttpStatus getCode() {
    	return code;
    }
}