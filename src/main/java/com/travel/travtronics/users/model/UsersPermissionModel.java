package com.travel.travtronics.users.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user_permission")
public class UsersPermissionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "permission_key")
	private String key;

	@Column(name = "permission_name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "group_id")
	private Integer groupId;

	@Column(name = "group_name")
	private String groupName;	

	@Column(name = "created_by")
	private Integer createdBy;

	@CreationTimestamp
	@Column(name = "date_created")
	private Date dateCreated;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@UpdateTimestamp
	@Column(name = "date_updated")
	private Date dateUpdated;

	@Column(name = "suspendedon")
	private Date suspendedOn;

	@Column(name = "is_system")
	private Integer isSystem;
	
	@Column(name = "application_id")
	private String application;
	
	@Column(name = "page_id")
	private String page;
	
	@Column(name = "organization_id")
	private Long organizationId;
	
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	
	
}
