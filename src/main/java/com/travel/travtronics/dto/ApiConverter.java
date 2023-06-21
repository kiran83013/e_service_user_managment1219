package com.travel.travtronics.dto;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import com.travel.travtronics.users.model.User;
import com.travel.travtronics.users.model.UsersPermissionModel;


public class ApiConverter {

	public static UsersPermissionModel mapPermissonRequestToModel(PermissionRequestModel request) {
		UsersPermissionModel permissionModel = new UsersPermissionModel();
		if (request.getId() != null && request.getId() != 0)
			permissionModel.setId(request.getId());
		permissionModel.setCreatedBy(request.getCreatedBy());
		permissionModel.setKey(request.getKey());
		permissionModel.setDescription(request.getDescription());
		permissionModel.setName(request.getName());
		permissionModel.setGroupId(request.getGroupId());
		permissionModel.setGroupName(request.getGroupName());
		permissionModel.setIsSystem(request.getIsSystem());
		permissionModel.setSuspendedOn(request.getSuspendedOn());
		permissionModel.setDateCreated(new Date());
		permissionModel.setApplication(request.getApplication());
		permissionModel.setPage(request.getPage());
		permissionModel.setOrganizationId(request.getOrganizationId());
		return permissionModel;
	}

	public static PermissionRequestModel mapPModelToRequest(UsersPermissionModel request, Set<String> roles) {

		PermissionRequestModel permissionModel = new PermissionRequestModel();
		permissionModel.setId(request.getId());
		permissionModel.setCreatedBy(request.getCreatedBy());
		permissionModel.setKey(request.getKey());
		permissionModel.setDescription(request.getDescription());
		permissionModel.setName(request.getName());
		permissionModel.setGroupId(request.getGroupId());
		permissionModel.setGroupName(request.getGroupName());
		permissionModel.setIsSystem(request.getIsSystem());
		permissionModel.setOrganizationId(request.getOrganizationId());
		permissionModel.setSuspendedOn(request.getSuspendedOn() != null ? request.getSuspendedOn() : null);
		permissionModel.setDateCreated(request.getDateCreated() != null ? request.getDateCreated() : null);
		permissionModel.setDateUpdated(request.getDateUpdated() != null ? request.getDateUpdated() : null);
		permissionModel.setCreatedBy(request.getCreatedBy() != null ? request.getCreatedBy() : 0);
		permissionModel.setUpdatedBy(request.getUpdatedBy() != null ? request.getUpdatedBy() : 0);
		permissionModel.setApplication(request.getApplication());
		permissionModel.setPage(request.getPage());
		if (roles != null && !roles.isEmpty())
			permissionModel.setRoles(roles);
		else
			permissionModel.setRoles(Collections.emptySet());
		return permissionModel;

	}
	
	public static UserDto convertDtoToModel(User model) {
		UserDto dto = new UserDto();
		dto.setUserId(model.getUserId());
		dto.setUserName(model.getUserName());
		dto.setIamId(model.getIamId());
		dto.setEmail(model.getEmail());
		dto.setPhoneNumber(model.getPhoneNumber());
		dto.setRoleId(model.getRoleId().toString());
		dto.setUserName(model.getUserName());
		dto.setUpdatedDate(model.getUpdatedDate());
		dto.setPassword(model.getPassword());
		dto.setOrganizationId(model.getOrganizationId());
		dto.setCreatedDate(model.getCreatedDate());
		dto.setEmail(model.getEmail());
		dto.setPhoneNumber(model.getPhoneNumber());
		dto.setStartDate(model.getStartDate());
		dto.setRefId(model.getRefId());
		dto.setRefTypeId(model.getRefTypeId());
		dto.setRefTypeName(model.getRefTypeName());
		dto.setFirstName(model.getFirstName());
		dto.setLastName(model.getLastName());
		dto.setDepartment(model.getDepartment());
		dto.setSupervisor(model.getSupervisor());
		dto.setBizId(model.getBizId());
		dto.setBusinessUnit(model.getBusinessUnit());
		dto.setContactId(model.getContactId());
		dto.setCostCenter(model.getCostCenter());
		dto.setSocialId(model.getSocialId());
		dto.setSocialImageURL(model.getSocialImageURL());
		dto.setSocialType(model.getSocialType());
		return dto;
	}
}
