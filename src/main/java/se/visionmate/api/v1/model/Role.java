package se.visionmate.api.v1.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "name")
	private String name;
	@OneToMany(fetch = FetchType.EAGER)
	@Column(name = "permissions")
	private List<Permission> perms;
	
	
	public Role() {
	}

	
	public Role(String aName, List<Permission> aPerms) {
		name = aName;
		perms = aPerms;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Permission> getPerms() {
		return perms;
	}
	public void setPerms(List<Permission> perms) {
		this.perms = perms;
	}


	@Override
	public String toString() {
		return "Role [name=" + name + ", perms=" + perms + "]";
	}

}
