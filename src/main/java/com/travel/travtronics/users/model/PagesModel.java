package com.travel.travtronics.users.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.util.Status;

@Entity
@Table(name = "user_page")
public class PagesModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long pageId;
	
	@Column(name = "application_id")
	private Long applicationId;
	
	@Column(name = "organization_id")
	private Long organizationId;
	
	@Column(name = "page_name")
	private String pageName;
	
	@Column(name = "page_key")
	private String pageKey;
	
	@Column(name = "path")
	private String path	;
	
	@Column(name = "created_by", updatable = false)
	private String createdBy;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPageKey() {
		return pageKey;
	}

	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	
}
