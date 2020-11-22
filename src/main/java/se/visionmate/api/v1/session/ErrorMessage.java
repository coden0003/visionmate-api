package se.visionmate.api.v1.session;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {
	
	
	USER_NOT_LOGGED("Session is closed, please log in", HttpStatus.METHOD_NOT_ALLOWED), 
	USER_NOT_ADMIN("User has no access to this method", HttpStatus.METHOD_NOT_ALLOWED), 
	USER_NOT_FOUND("User not found", HttpStatus.BAD_REQUEST),
	ROLE_NOT_FOUND("Role with this name was not found", HttpStatus.BAD_REQUEST),
	USER_NOT_CREATED("User was not created", HttpStatus.INTERNAL_SERVER_ERROR),
	USER_NOT_UPDATED("User was not updated", HttpStatus.INTERNAL_SERVER_ERROR),
	USER_NOT_DELETED("User was not deleted", HttpStatus.INTERNAL_SERVER_ERROR),
	USER_PASS_NOT_RESET("User password was not resetted", HttpStatus.INTERNAL_SERVER_ERROR),
	USER_PASS_NOT_FORGT("Error forgotting user password", HttpStatus.INTERNAL_SERVER_ERROR),
	USER_LOGIN_ERR("Error logging user in, please check password", HttpStatus.BAD_REQUEST),
	USER_LOGOUT_ERR("Error logging user out, please check if user was logged in", HttpStatus.BAD_REQUEST),
	ROLE_NOT_CREATED("Role was not created", HttpStatus.INTERNAL_SERVER_ERROR),
	ROLE_NOT_UPDATED("Role was not updated", HttpStatus.INTERNAL_SERVER_ERROR),
	ROLE_NOT_DELETED("Role was not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
 
	
    private String msg;
    private HttpStatus code;
 
    ErrorMessage(String aMsg, HttpStatus aCode) {
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