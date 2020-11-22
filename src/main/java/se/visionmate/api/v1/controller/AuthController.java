package se.visionmate.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.visionmate.api.v1.service.AuthService;
import se.visionmate.api.v1.service.MsgService;
import se.visionmate.api.v1.session.ErrorMessage;
import se.visionmate.api.v1.session.SuccessMessage;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

	
	@Autowired
	private AuthService authSrv;
	
	@Autowired
	private MsgService msgSrv;
	
	
	@PostMapping("/auth/login/{userLogin}")
	public Object logIn(@PathVariable String userLogin, @RequestBody String userPassword) {
		if (authSrv.logIn(userLogin, userPassword)) {
			return msgSrv.returnSuccess(SuccessMessage.USER_LOGGED_IN);
		} else {
			return msgSrv.returnError(ErrorMessage.USER_LOGIN_ERR);
		}
	}

	@PostMapping("/auth/logout/{userLogin}")
	public Object logOut(@PathVariable String userLogin) {
		if (authSrv.logOut(userLogin)) {
			return msgSrv.returnSuccess(SuccessMessage.USER_LOGGED_OUT); 
		} else {
			return msgSrv.returnError(ErrorMessage.USER_LOGOUT_ERR);
		}
	}
	
	@PostMapping("/auth/reset/{userLogin}")
	public Object resetPassword(@PathVariable String userLogin, @RequestBody String newPassword) {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		if (!authSrv.isAdminLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_ADMIN);
		}
		if (authSrv.resetPassword(userLogin, newPassword)) {
			return msgSrv.returnSuccess(SuccessMessage.RESET_PASSWORD);
		} else {
			return msgSrv.returnError(ErrorMessage.USER_PASS_NOT_RESET);
		}
	}
	
	@PostMapping("/auth/forgot/{userLogin}")
	public Object forgotPassword(@PathVariable String userLogin) {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		if (authSrv.forgotPassword(userLogin)) {
			return msgSrv.returnSuccess(SuccessMessage.FORGOT_PASSWORD);
		} else {
			return msgSrv.returnError(ErrorMessage.USER_PASS_NOT_FORGT);
		}
	}
	
}
