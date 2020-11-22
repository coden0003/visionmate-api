package se.visionmate.api.v1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import se.visionmate.api.v1.model.Permission;
import se.visionmate.api.v1.model.PermissionEnum;
import se.visionmate.api.v1.model.Role;
import se.visionmate.api.v1.model.User;

@SpringBootTest
public class UserServiceTest {
	
	
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
	
	
	private User initData(int ver) {
		
		List<Permission> testPerms = new ArrayList<Permission>();
		testPerms.add(new Permission(PermissionEnum.LIST));
		
		Role testRole = new Role("testRole", testPerms);
		roleSrv.createRole(testRole);
		if (ver == 1) {
			User toCreate = new User(userLogin, userPassword, testRole);
			userSrv.createUser(toCreate);
		} else {
			userSrv.createUser(userLogin, userPassword, "testRole");
		}
		
		return userSrv.listUser(userLogin).get();
	}
	
	
	@Test
	public void testCreateUser() {
		
		User createdUser = initData(1);
		
		assertEquals(true, userSrv.listUser(createdUser.getLogin()).isPresent());
		assertEquals(false, userSrv.listUser(createdUser.getLogin()).isEmpty());
	}
	
	@Test
	public void testCreateUser2() {
		
		User createdUser = initData(2);
		
		assertEquals(true, userSrv.listUser(createdUser.getLogin()).isPresent());
		assertEquals(false, userSrv.listUser(createdUser.getLogin()).isEmpty());
	}
	
	@Test
	public void updateUser() {
		
		User createdUser = initData(1);
		
		User updatedUser = new User(createdUser.getLogin(), newPassword, createdUser.getRole());
		userSrv.updateUser(userLogin, updatedUser);

		assertEquals("updatedPassword", userSrv.listUser(createdUser.getLogin()).get().getPassword());
		assertNotEquals("WRONG_updatedPassword", userSrv.listUser(createdUser.getLogin()).get().getPassword());
	}
	
	@Test
	public void testDeleteUser() {
		
		initData(1);
		
		userSrv.deleteUser(userLogin);
		
		assertEquals(true, userSrv.listUser(userLogin).isEmpty());
		assertEquals(false, userSrv.listUser(userLogin).isPresent());
	}

	@Test
	public void testIsAdmin() {
		
		// Admin user
		List<Permission> testPerms = new ArrayList<Permission>();
		testPerms.add(new Permission(PermissionEnum.CREATE));
		testPerms.add(new Permission(PermissionEnum.UPDATE));
		testPerms.add(new Permission(PermissionEnum.DELETE));
		testPerms.add(new Permission(PermissionEnum.LIST));
		roleSrv.createRole(new Role("ADMIN", testPerms));
		userSrv.createUser(userLogin, userPassword, "ADMIN");
		User adminUser = userSrv.listUser(userLogin).get();
		
		assertEquals(true, authSrv.isAdmin(adminUser.getLogin()));
		
		// Not admin user
		testPerms = new ArrayList<Permission>();
		testPerms.add(new Permission(PermissionEnum.LIST));
		roleSrv.createRole(new Role("NOTADMIN", testPerms));
		userSrv.createUser(userLogin, userPassword, "NOTADMIN");
		User notAdminUser = userSrv.listUser(userLogin).get();
		
		assertEquals(false, authSrv.isAdmin(notAdminUser.getLogin()));
	}
	
	@Test
	public void testIsPasswordOk() {
		
		initData(1);
		
		assertEquals(true, userSrv.isPasswordOk(userLogin, userPassword));
		assertEquals(false, userSrv.isPasswordOk(userLogin, userPassword+"\'"));
		assertEquals(false, userSrv.isPasswordOk(userLogin, userPassword+"*"));
		assertEquals(false, userSrv.isPasswordOk(userLogin, ""));
	}
	
	
}
