package com.travel.travtronics.dto;

public class RoleDto {

	private Integer id;

	private String roleKey;

	private String roleName;

	private Long pageId;

	private String pageKey;

	private String pagePath;

	private Long applicationId;

	private String url;

	private String applicationName;

	private Long menuId;
	
	

	public RoleDto() {
	}

	public RoleDto(Integer id, String roleKey, String roleName, Long pageId, String pageKey, String pagePath,
			Long applicationId, String url, String applicationName, Long menuId) {
		this.id = id;
		this.roleKey = roleKey;
		this.roleName = roleName;
		this.pageId = pageId;
		this.pageKey = pageKey;
		this.pagePath = pagePath;
		this.applicationId = applicationId;
		this.url = url;
		this.applicationName = applicationName;
		this.menuId = menuId;
	
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public String getPageKey() {
		return pageKey;
	}

	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	
	
}
