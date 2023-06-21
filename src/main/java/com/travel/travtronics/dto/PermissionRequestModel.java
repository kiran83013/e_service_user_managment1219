package com.travel.travtronics.dto;

import java.util.Date;
import java.util.Set;

public class PermissionRequestModel {

	private Integer id;

	private String key;

	private String name;

	private String description;

	private Integer groupId;

	private String groupName;

	private Integer createdBy;

	private Date dateCreated;

	private Integer updatedBy;

	private Date dateUpdated;

	private Date suspendedOn;

	private Integer isSystem;
	
	private Set<String> roles;
	
	private String application;
	
	private String page;
	
	private Long organizationId;

	

	

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Date getSuspendedOn() {
		return suspendedOn;
	}

	public void setSuspendedOn(Date suspendedOn) {
		this.suspendedOn = suspendedOn;
	}

	public Integer getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	
	
	

}
