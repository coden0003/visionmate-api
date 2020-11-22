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

import se.visionmate.api.v1.model.Role;
import se.visionmate.api.v1.service.AuthService;
import se.visionmate.api.v1.service.MsgService;
import se.visionmate.api.v1.service.RoleService;
import se.visionmate.api.v1.session.ErrorMessage;
import se.visionmate.api.v1.session.SuccessMessage;


@RestController
@RequestMapping("/api/v1")
public class RoleController {

	
	@Autowired
	private RoleService roleSrv;
	
	@Autowired
	private AuthService authSrv;
	
	@Autowired
	private MsgService msgSrv;
	
	
	@GetMapping("/role")
	public Object listRoles() {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		if (!authSrv.isAdminLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_ADMIN);
		}
		return roleSrv.listRoles();
	}
	
	@GetMapping("/role/{roleName}")
	public Object listRole(@PathVariable String roleName) {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		if (!authSrv.isAdminLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_ADMIN);
		}
		return roleSrv.listRole(roleName);
	}
	
	@PostMapping("/role")
	public Object createRole(@RequestBody Role role) {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		if (!authSrv.isAdminLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_ADMIN);
		}
		if (roleSrv.createRole(role)) {
			return msgSrv.returnSuccess(SuccessMessage.ROLE_CREATED);
		} else {
			return msgSrv.returnError(ErrorMessage.ROLE_NOT_CREATED);
		}
	}
	
	@PutMapping("/role/{roleName}")
	public Object updateRole(@PathVariable String roleName, @RequestBody Role role) {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		if (!authSrv.isAdminLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_ADMIN);
		}
		if (roleSrv.updateRole(roleName, role)) {
			return msgSrv.returnSuccess(SuccessMessage.ROLE_UPDATED);
		} else {
			return msgSrv.returnError(ErrorMessage.ROLE_NOT_UPDATED);
		}
	}
	
	@DeleteMapping("/role/{roleName}")
	public Object deleteRole(@PathVariable String roleName) {
		if (!authSrv.isUserLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_LOGGED);
		}
		if (!authSrv.isAdminLogged()) {
			return msgSrv.returnError(ErrorMessage.USER_NOT_ADMIN);
		}
		if (roleSrv.deleteRole(roleName)) {
			return msgSrv.returnSuccess(SuccessMessage.ROLE_DELETED);
		} else {
			return msgSrv.returnError(ErrorMessage.ROLE_NOT_DELETED);
		}
	}
	
}
