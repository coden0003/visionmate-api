package se.visionmate.api.v1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission {

	@Id
	@Enumerated(EnumType.STRING)
	@Column(name = "name")
	private PermissionEnum perm;

	
	public Permission() {
	}

	public Permission(PermissionEnum aPerm) {
		perm = aPerm;
	}
	
	
	public PermissionEnum getPerm() {
		return perm;
	}
	public void setPerm(PermissionEnum perm) {
		this.perm = perm;
	}
	
	
	
	@Override
	public String toString() {
		return "Permission [perm=" + perm + "]";
	}
	
	
}
