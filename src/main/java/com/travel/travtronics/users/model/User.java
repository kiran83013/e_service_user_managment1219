package com.travel.travtronics.users.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel.travtronics.dto.UserSearchResponse;

@Entity
@Table(name = "user_info")

@SqlResultSetMapping(name = "find_by_search_params", classes = @ConstructorResult(targetClass = UserSearchResponse.class, columns = {
@ColumnResult(name = "userId", type = Long.class),
@ColumnResult(name = "iamId", type = String.class), @ColumnResult(name = "refId", type = Long.class),
@ColumnResult(name = "refTypeId", type = Long.class), @ColumnResult(name = "refTypeName", type = String.class),
@ColumnResult(name = "firstName", type = String.class), @ColumnResult(name = "lastName", type = String.class),
@ColumnResult(name = "userName", type = String.class),@ColumnResult(name = "email", type = String.class),
@ColumnResult(name = "phoneNumber", type = String.class), @ColumnResult(name = "department", type = String.class),
@ColumnResult(name = "departmentName", type = String.class), @ColumnResult(name = "supervisor", type = String.class),
@ColumnResult(name = "startDate", type = Date.class),@ColumnResult(name = "createdDate", type = Date.class),
@ColumnResult(name = "updatedDate", type = Date.class),@ColumnResult(name = "location", type = String.class), 
@ColumnResult(name = "locationName", type = String.class),@ColumnResult(name = "organizationId", type = Long.class),
@ColumnResult(name = "organizationName", type = String.class),@ColumnResult(name = "businessUnit", type = String.class),
@ColumnResult(name = "businessUnitName", type = String.class), @ColumnResult(name = "costCenter", type = String.class),
@ColumnResult(name = "costCenterName", type = String.class), @ColumnResult(name = "socialId", type = String.class), @ColumnResult(name = "socialType", type = String.class),
@ColumnResult(name = "socialImageURL", type = String.class),@ColumnResult(name = "contactId", type = Integer.class),
@ColumnResult(name = "bizId", type = Integer.class),@ColumnResult(name = "wtId", type = Long.class),
@ColumnResult(name = "wtProfile", type = String.class),@ColumnResult(name = "wtFlag", type = Boolean.class),
@ColumnResult(name = "roleId", type = Long.class),@ColumnResult(name = "roleName", type = String.class)}))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@JsonFilter("userFilter")
	@Transient
	private String password;

	@JsonIgnore
	@Column(name = "iam_id", updatable = false)
	private String iamId;

	@Column(name = "ref_id")
	private Long refId;

	@Column(name = "ref_type_id")
	private Long refTypeId;

	@Column(name = "ref_type_name")
	private String refTypeName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "user_name", unique = true, updatable = false)
	private String userName;

	@Column(name = "email", unique = true, updatable = false)
	private String email;

	@Column(name = "phone_number", unique = true, updatable = false)
	private String phoneNumber;

	@Column(name = "department")
	private String department;

	@Column(name = "supervisor")
	private String supervisor;

	@Column(name = "start_date")
	private Date startDate;

	@CreatedDate
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	private String location;

	@Column(name = "organization")
	private Long organizationId;

	@Column(name = "business_unit")
	private String businessUnit;

	@Column(name = "cost_center")
	private String costCenter;

	@Column(name = "social_id")
	private String socialId;
	@Column(name = "social_type")
	private String socialType;
	@Column(name = "social_image_url")
	private String socialImageURL;

	@Column(name = "contact_id")
	private Integer contactId;

	@Column(name = "biz_id")
	private Integer bizId;

	@Column(name = "wt_id")
	private Long wtId;

	@Column(name = "wt_profile")
	private String wtProfile;

	@Column(name = "wt_flag")
	private Boolean wtFlag;

	@Column(name = "role_id", nullable = false)
	private Long roleId;

	public Long getWtId() {
		return wtId;
	}

	public void setWtId(Long wtId) {
		this.wtId = wtId;
	}

	public String getWtProfile() {
		return wtProfile;
	}

	public void setWtProfile(String wtProfile) {
		this.wtProfile = wtProfile;
	}

	public Boolean getWtFlag() {
		return wtFlag;
	}

	public void setWtFlag(Boolean wtFlag) {
		this.wtFlag = wtFlag;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getSocialType() {
		return socialType;
	}

	public void setSocialType(String socialType) {
		this.socialType = socialType;
	}

	public String getSocialImageURL() {
		return socialImageURL;
	}

	public void setSocialImageURL(String socialImageURL) {
		this.socialImageURL = socialImageURL;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIamId() {
		return iamId;
	}

	public void setIamId(String iamId) {
		this.iamId = iamId;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public Long getRefTypeId() {
		return refTypeId;
	}

	public void setRefTypeId(Long refTypeId) {
		this.refTypeId = refTypeId;
	}

	public String getRefTypeName() {
		return refTypeName;
	}

	public void setRefTypeName(String refTypeName) {
		this.refTypeName = refTypeName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

}
