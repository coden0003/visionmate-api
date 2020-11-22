package se.visionmate.api.v1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import se.visionmate.api.v1.model.Permission;
import se.visionmate.api.v1.model.PermissionEnum;
import se.visionmate.api.v1.model.Role;

@SpringBootTest
public class RoleServiceTest {
	
	
	@Autowired
	private RoleService roleSrv;
	

	
	private Role initData() {
		
		List<Permission> testPerms = new ArrayList<Permission>();
		testPerms.add(new Permission(PermissionEnum.LIST));
		
		Role testRole = new Role("testRole", testPerms);
		roleSrv.createRole(testRole);
		
		return roleSrv.listRole(testRole.getName()).get();
	}
	
	
	@Test
	public void testCreateRole() {
		
		Role createdRole = initData();
		
		assertEquals(true, roleSrv.listRole(createdRole.getName()).isPresent());
		assertEquals(false, roleSrv.listRole(createdRole.getName()).isEmpty());
	}
	
	@Test
	public void testUpdateRole() {
		
		Role createdRole = initData();
		
		List<Permission> updatedPerms = new ArrayList<Permission>();
		updatedPerms.add(new Permission(PermissionEnum.LIST));
		updatedPerms.add(new Permission(PermissionEnum.CREATE));
		
		Role updatedRole = new Role(createdRole.getName(), updatedPerms);
		
		roleSrv.updateRole(createdRole.getName(), updatedRole);
		
//		assertSame(updatedRole, roleSrv.listRole(createdRole.getName()).get());
		assertNotSame(createdRole, roleSrv.listRole(createdRole.getName()).get());
	}
	
	@Test
	public void testDeleteRole() {
		
		Role createdRole = initData();
		
		roleSrv.deleteRole(createdRole.getName());
		
		assertEquals(true, roleSrv.listRole(createdRole.getName()).isEmpty());
		assertNotEquals(true, roleSrv.listRole(createdRole.getName()).isPresent());
	}
	
}
