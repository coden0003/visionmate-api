package se.visionmate.api.v1.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.visionmate.api.v1.model.User;
import se.visionmate.api.v1.session.LoggedUser;

@Service
public class AuthService {
	
	public static final String LOGGED_USR_ATTR = "loggedUser";
	public static final String ROLE_NAME_ADMIN = "ADMIN";
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private UserService userSrv;
	
	

	public boolean logIn(String userLogin, String userPassword) {
		if (userSrv.isPasswordOk(userLogin, userPassword)
				// if forgot password was used
				|| (userSrv.listUser(userLogin).isPresent() && userSrv.listUser(userLogin).get().getPassword().isEmpty())) {
			httpSession.removeAttribute(LOGGED_USR_ATTR);
			LoggedUser loggedUser = new LoggedUser(userSrv.listUser(userLogin).get());
			httpSession.setAttribute(LOGGED_USR_ATTR, loggedUser);
			return true;
		}
		return false;
	}

	public boolean logOut(String userLogin) {
		try {
			httpSession.removeAttribute(LOGGED_USR_ATTR);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean resetPassword(String userLogin, String newPassword) {
		if (userSrv.listUser(userLogin).isPresent()) {
			User prevUser = userSrv.listUser(userLogin).get();
			userSrv.updateUser(userLogin, new User(userLogin, newPassword, prevUser.getRole()));
		}
		try {
			return userSrv.listUser(userLogin).get().getPassword().equals(newPassword);
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean forgotPassword(String userLogin) {
		// this is an API, and no info about how "Forgot functionality" should be
		// so this method sets password to "", and when logging in next time, if password is "",
		// the logging password is persisted to the User
		if (userSrv.listUser(userLogin).isPresent()) {
			User userMaybe = userSrv.listUser(userLogin).get();
			userSrv.updateUser(userLogin, new User(userLogin, "", userMaybe.getRole()));
		}
		try {
			return userSrv.listUser(userLogin).get().getPassword() == null;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isUserLogged() {
		try {
			return ((LoggedUser) httpSession.getAttribute(LOGGED_USR_ATTR)).getLoggedUser() != null;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isAdmin(String userLogin) {
		if (userSrv.listUser(userLogin).isPresent()) {
			try {
				return userSrv.listUser(userLogin).get().getRole().getName().equals(ROLE_NAME_ADMIN);
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	
	public boolean isAdminLogged() {
		if (httpSession.getAttribute(LOGGED_USR_ATTR) != null) {
			try {
				String loggedUserLogin = ((LoggedUser) httpSession.getAttribute(LOGGED_USR_ATTR)).getLoggedUser().getLogin();
				return userSrv.listUser(loggedUserLogin).get().getRole().getName().equals(ROLE_NAME_ADMIN);
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	 
}
