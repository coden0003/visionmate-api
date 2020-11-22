package se.visionmate.api.v1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import se.visionmate.api.v1.model.Permission;
import se.visionmate.api.v1.model.PermissionEnum;
import se.visionmate.api.v1.model.Role;
import se.visionmate.api.v1.model.User;
import se.visionmate.api.v1.session.LoggedUser;

@SpringBootTest
public class AuthServiceTest {
	
	public static final String LOGGED_USR_ATTR = "loggedUser";
	public static final String ROLE_NAME_ADMIN = "ADMIN";
	
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private UserService userSrv;
	@Autowired
	private RoleService roleSrv;
	@Autowired
	private AuthService authSrv;
	
	
	// TEST DATA
	private final static String userLogin = "testLogin";
	private final static String userPassword = "testPass";
	private final static String newPassword = "updatedPassword";
	
	
	private User initData() {
		
		List<Permission> testPerms = new ArrayList<Permission>();
		testPerms.add(new Permission(PermissionEnum.LIST));
		
		roleSrv.createRole(new Role("testRole", testPerms));
		userSrv.createUser(userLogin, userPassword, "testRole");
		
		return userSrv.listUser(userLogin).get();
	}
	
	
	@Test
	public void testLogIn() {

		initData();
		
		assertEquals(Boolean.TRUE, authSrv.logIn(userLogin, userPassword));
		
	}

	@Test
	public void testLogOut() {
		
		initData();
		
		authSrv.logIn(userLogin, userPassword);
		authSrv.logOut(userLogin);
		
		assertEquals(null, httpSession.getAttribute(LOGGED_USR_ATTR));
	}
	
	@Test
	public void resetPassword() {
		
		User testUser = initData();
		testUser.setPassword(newPassword);
		
		userSrv.updateUser(userLogin, testUser);
		
		assertEquals(newPassword, userSrv.listUser(userLogin).get().getPassword());
	}
	
	@Test
	public void testForgotPassword() {
		
		initData();
		
		authSrv.forgotPassword(userLogin);
		
		assertEquals("", userSrv.listUser(userLogin).get().getPassword());
	}
	
	@Test
	public void testIsUserLogged() {
		
		initData();
		
		authSrv.logIn(userLogin, userPassword);
		String loggedUserLogin = ((LoggedUser) httpSession.getAttribute(LOGGED_USR_ATTR)).getLoggedUser().getLogin();
		
		assertEquals(loggedUserLogin, userLogin);
	}
	
	 
}
