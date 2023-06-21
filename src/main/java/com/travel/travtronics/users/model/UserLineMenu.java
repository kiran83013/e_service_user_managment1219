package com.travel.travtronics.users.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_menu_lines")
public class UserLineMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "header_id")
	private Integer headerId;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "link", referencedColumnName = "id")
	private PagesModel link;

	@Column(name = "level")
	private String level;

	@Column(name = "menu_order")
	private Integer menuOrder;
	
	@Column(name = "organization_id")
	private Long organizationId;

	/*
	 * if unknown parent simply ignore
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private UserLineMenu parentId;

	@ManyToOne
	@JoinColumn(name = "application_id", referencedColumnName = "id")
	private ApplicationsModel applicationId;

	@Column(name = "icon")
	private String icon;

	@Column(name = "badage")
	private String badage;

	@Column(name = "badage_class")
	private String badageClass;

	@Column(name = "external_link")
	private Boolean externalLink;

	@Column(name = "new_tab")
	private Boolean newTab;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "is_collapsable")
	private Boolean isCollapsable;
	
	

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Integer headerId) {
		this.headerId = headerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PagesModel getLink() {
		return link;
	}

	public void setLink(PagesModel link) {
		this.link = link;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public UserLineMenu getParentId() {
		return parentId;
	}

	public void setParentId(UserLineMenu parentId) {
		this.parentId = parentId;
	}

	public ApplicationsModel getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(ApplicationsModel applicationId) {
		this.applicationId = applicationId;
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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getIsCollapsable() {
		return isCollapsable;
	}

	public void setIsCollapsable(Boolean isCollapsable) {
		this.isCollapsable = isCollapsable;
	}

}
