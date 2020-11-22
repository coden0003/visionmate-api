package se.visionmate.api.v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.visionmate.api.v1.model.Role;
import se.visionmate.api.v1.model.RoleRepository;
import se.visionmate.api.v1.model.User;
import se.visionmate.api.v1.model.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	public List<User> listUsers() {
		List<User> users = new ArrayList<>();
		userRepo.findAll()
			.forEach(users::add);
		return users;
	}
	
	public Optional<User> listUser(String userLogin) {
		return userRepo.findById(userLogin);
	}

	public boolean createUser(User user) {
		userRepo.save(user);
		if (userRepo.findById(user.getLogin()).isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean createUser(String userLogin, String userPassword, String roleName) {
		if (roleRepo.existsById(roleName)) {
			Role theRole = roleRepo.findById(roleName).get();
			User newUser = new User(userLogin, userPassword, theRole);
			userRepo.save(newUser);
		}
		if (userRepo.findById(userLogin).isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean updateUser(String userLogin, User user) {
		if (userRepo.findById(userLogin).isPresent()) {
			userRepo.save(user);
		}
		if (userRepo.findById(userLogin).isPresent()
				&& userRepo.findById(userLogin).get().getPassword().equals(user.getPassword())) {
			return true;
		}
		return false;
	}
	
	public boolean deleteUser(String userLogin) {
		userRepo.deleteById(userLogin);
		if (userRepo.findById(userLogin).isPresent()) {
			return false;
		}
		return true;
	}

	public boolean isAdmin(String userLogin) {
		try {
			return userRepo.findById(userLogin).get()
					.getRole().getName().equals("ADMIN");
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isPasswordOk(String userLogin, String userPassword) {
		if (userRepo.findById(userLogin).isPresent()) {
			try {
				return userRepo.findById(userLogin).get().getPassword().equals(userPassword);
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	
	
}
