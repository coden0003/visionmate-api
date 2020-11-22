package se.visionmate.api.v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.visionmate.api.v1.model.Role;
import se.visionmate.api.v1.model.RoleRepository;

@Service
public class RoleService {
	
	
	@Autowired
	private RoleRepository roleRepo;

	
	public List<Role> listRoles() {
		List<Role> roles = new ArrayList<>();
		roleRepo.findAll()
			.forEach(roles::add);
		return roles;
	}
	
	public Optional<Role> listRole(String roleName) {
		return roleRepo.findById(roleName);
	}

	public boolean createRole(Role role) {
		roleRepo.save(role);
		if (roleRepo.findById(role.getName()).isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean updateRole(String roleName, Role role) {
		if (roleRepo.findById(roleName).isPresent()) {
			roleRepo.save(role);
		}
		if (roleRepo.findById(roleName).isPresent()
				&& roleRepo.findById(roleName).get().getName().equals(role.getName())) {
			return true;
		}
		return false;
	}
	
	public boolean deleteRole(String roleName) {
		roleRepo.deleteById(roleName);
		if (roleRepo.findById(roleName).isPresent()) {
			return false;
		}
		return true;
	}

	
	
}
