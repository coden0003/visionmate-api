package se.visionmate.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.visionmate.api.v1.model.User;
import se.visionmate.api.v1.service.AuthService;
import se.visionmate.api.v1.service.MsgService;
import se.visionmate.api.v1.service.UserService;
import se.visionmate.api.v1.session.ErrorMessage;
import se.visionmate.api.v1.session.SuccessMessage;


@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	
	@Autowired
	private UserService userSrv;
	
	@Autowired
	private AuthService authSrv;
	
	@Autowired
	private MsgService msgSrv;
	
	
	@GetMapping("/users")
	public Object listUsers() {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		return userSrv.listUsers();
	}
	
	@GetMapping("/users/{userLogin}")
	public Object listUser(@PathVariable String userLogin) {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		return userSrv.listUser(userLogin);
	}
	
	@PostMapping("/users")
	public Object createUser(@RequestBody User user) {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		if (!authSrv.isAdminLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_ADMIN);
		}
		if (userSrv.createUser(user)) {
			return msgSrv.returnSuccess(SuccessMessage.USER_CREATED);
		} else {
			return msgSrv.returnError(ErrorMessage.USER_NOT_CREATED);
		}
	}
	
	@PostMapping("/users/{userLogin}")
	public Object createUser(@PathVariable String userLogin, @RequestBody String userPassword, @RequestBody String roleName) {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		if (!authSrv.isAdminLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_ADMIN);
		}
		if (userSrv.createUser(userLogin, userPassword, roleName)) {
			return msgSrv.returnSuccess(SuccessMessage.USER_CREATED);
		} else {
			return msgSrv.returnError(ErrorMessage.USER_NOT_CREATED);
		}
	}
	
	@PutMapping("/users/{userLogin}")
	public Object updateUser(@PathVariable String userLogin, @RequestBody User user) {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		if (!authSrv.isAdminLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_ADMIN);
		}
		if (userSrv.updateUser(userLogin, user)) {
			return msgSrv.returnSuccess(SuccessMessage.USER_UPDATED);
		} else {
			return msgSrv.returnError(ErrorMessage.USER_NOT_UPDATED);
		}
	}
	
	@DeleteMapping("/users/{userLogin}")
	public Object deleteUser(@PathVariable String userLogin) {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		if (!authSrv.isAdminLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_ADMIN);
		}
		if (userSrv.deleteUser(userLogin)) {
			return msgSrv.returnSuccess(SuccessMessage.USER_DELETED);
		} else {
			return msgSrv.returnError(ErrorMessage.USER_NOT_DELETED);
		}
	}

}
