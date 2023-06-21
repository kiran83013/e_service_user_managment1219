package com.travel.travtronics.dto;

import java.util.Date;

public class UserSearchResponse {

	private Long userId;

	private String iamId;

	private Long refId;

	private Long refTypeId;

	private String refTypeName;

	private String firstName;

	private String lastName;

	private String userName;

	private String email;

	private String phoneNumber;

	private String department;

	private String departmentName;

	private String supervisor;

	private Date startDate;

	private Date createdDate;

	private Date updatedDate;

	private String location;

	private String locationName;

	private Long organizationId;

	private String organizationName;

	private String businessUnit;

	private String businessUnitName;

	private String costCenter;

	private String costCenterName;

	private String socialId;

	private String socialType;

	private String socialImageURL;

	private Integer contactId;

	private Integer bizId;

	private Long wtId;

	private String wtProfile;

	private Boolean wtFlag;

	private Long roleId;

	private String roleName;

	public UserSearchResponse(Long userId, String iamId, Long refId, Long refTypeId, String refTypeName,
			String firstName, String lastName, String userName, String email, String phoneNumber, String department,
			String departmentName, String supervisor, Date startDate, Date createdDate, Date updatedDate,
			String location, String locationName, Long organizationId, String organizationName, String businessUnit,
			String businessUnitName, String costCenter, String costCenterName, String socialId, String socialType,
			String socialImageURL, Integer contactId, Integer bizId, Long wtId, String wtProfile, Boolean wtFlag,
			Long roleId, String roleName) {
		super();
		this.userId = userId;
		this.iamId = iamId;
		this.refId = refId;
		this.refTypeId = refTypeId;
		this.refTypeName = refTypeName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.department = department;
		this.departmentName = departmentName;
		this.supervisor = supervisor;
		this.startDate = startDate;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.location = location;
		this.locationName = locationName;
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.businessUnit = businessUnit;
		this.businessUnitName = businessUnitName;
		this.costCenter = costCenter;
		this.costCenterName = costCenterName;
		this.socialId = socialId;
		this.socialType = socialType;
		this.socialImageURL = socialImageURL;
		this.contactId = contactId;
		this.bizId = bizId;
		this.wtId = wtId;
		this.wtProfile = wtProfile;
		this.wtFlag = wtFlag;
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
