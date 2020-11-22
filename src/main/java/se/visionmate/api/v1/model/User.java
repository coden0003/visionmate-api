package se.visionmate.api.v1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "login")
	private String login;
	@Column(name = "password")
//	private transient String password = null;
	private String password = null;
	@OneToOne
	@JoinColumn(name = "role_name")
	private Role role;
	
	
	public User() {
	}

	
	public User(String aLogin, String aPassword, Role aRole) {
		login = aLogin;
		password = aPassword;
		role = aRole;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", role=" + role + "]";
	}

}
