package com.travel.travtronics.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Line {

	private String name;

	private Integer link;

	private String path;

	private Long organizationId;

	private Integer applicationId;
	private String url;

	private String icon;

	private String badage;

	private String badageClass;

	private Boolean externalLink;

	private Boolean newTab;

	@JsonProperty("group")
	private Group group;

	public Line() {

		this.group = new Group();
	}

	private Integer menuOrder;

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	private Boolean collapse;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLink() {
		return link;
	}

	public void setLink(Integer link) {
		this.link = link;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getBadage() {
		return badage;
	}

	public void setBadage(String badage) {
		this.badage = badage;
	}

	public String getBadageClass() {
		return badageClass;
	}

	public void setBadageClass(String badageClass) {
		this.badageClass = badageClass;
	}

	public Boolean getExternalLink() {
		return externalLink;
	}

	public void setExternalLink(Boolean externalLink) {
		this.externalLink = externalLink;
	}

	public Boolean getNewTab() {
		return newTab;
	}

	public void setNewTab(Boolean newTab) {
		this.newTab = newTab;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Boolean getCollapse() {
		return collapse;
	}

	public void setCollapse(Boolean collapse) {
		this.collapse = collapse;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	
}
