package com.travel.travtronics.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role_permission")
public class UsersRolePermissionRelation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auto_id")
	private Integer id;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "permission_id")
	private Integer permissionId;

	@Column(name = "is_active")
	private Integer isActive;
	
	
	

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	
}
