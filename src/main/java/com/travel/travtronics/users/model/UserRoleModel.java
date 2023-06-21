package com.travel.travtronics.users.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travel.travtronics.dto.RoleDto;
import com.travel.travtronics.util.QueryConst;

@Entity
@Table(name = "user_role")

@SqlResultSetMapping(name = "findByRoleInfoId", classes = @ConstructorResult(targetClass = RoleDto.class, columns = {
		@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "role_key", type = String.class),
		@ColumnResult(name = "role_name", type = String.class), @ColumnResult(name = "page_id", type = Long.class),
		@ColumnResult(name = "page_key", type = String.class), @ColumnResult(name = "page_path", type = String.class),
		@ColumnResult(name = "application_id", type = Long.class), @ColumnResult(name = "url", type = String.class),
		@ColumnResult(name = "application_name", type = String.class),
		@ColumnResult(name = "menu_id", type = Long.class) }))

@NamedNativeQuery(name = "UserRoleModel.findByRoleInfoId", resultClass = RoleDto.class, resultSetMapping = "findByRoleInfoId", query = QueryConst.ROLE_QUERY)
public class UserRoleModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role_key")
	private String key;

	@Column(name = "role_name")
	private String name;

	private String description;

	@Column(name = "suspendedon")
	private Date suspendedOn;

	@Column(name = "is_system")
	private Integer isSystem;

	@Column(name = "menu_id")
	private Integer menuId;
	
	@Column(name = "landing_page_id")
	@JsonProperty("landingPageId")
	private Integer pageId;

	@Column(name = "organization_id")
	private Long organizationId;
	
	@Column(name = "dynamic_menu_id")
	private Integer dynamicMenuId;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getDynamicMenuId() {
		return dynamicMenuId;
	}

	public void setDynamicMenuId(Integer dynamicMenuId) {
		this.dynamicMenuId = dynamicMenuId;
	}

	
}