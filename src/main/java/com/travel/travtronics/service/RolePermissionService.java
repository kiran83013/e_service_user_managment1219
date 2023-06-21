package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travtronics.dto.ApiConverter;
import com.travel.travtronics.dto.PermissionPageView;
import com.travel.travtronics.dto.PermissionRequestModel;
import com.travel.travtronics.users.model.UserRoleModel;
import com.travel.travtronics.users.model.UsersPermissionGroupModel;
import com.travel.travtronics.users.model.UsersPermissionModel;
import com.travel.travtronics.users.model.UsersRolePermissionRelation;
import com.travel.travtronics.users.repository.UsersPermissionGroupReposiory;
import com.travel.travtronics.users.repository.UsersPermissionModelRepository;
import com.travel.travtronics.users.repository.UsersRolePermissionRelationRepository;
import com.travel.travtronics.users.repository.UsersRoleRepository;

@Service
public class RolePermissionService {

	@Autowired
	UsersPermissionGroupReposiory permissionGroupRepository;

	@Autowired
	UsersRoleRepository beUserRoleRepository;

	@Autowired
	UsersPermissionModelRepository permissionModelRepository;

	@Autowired
	UsersRolePermissionRelationRepository rolePermissionRepository;

//	@Autowired
//	PermissionEntityRepository permissionEntityRepository;

	public ResponseEntity<?> createPermissionGroup(List<UsersPermissionGroupModel> models) {

		for (UsersPermissionGroupModel model : models) {

			if (permissionGroupRepository.existsByGroupName(model.getGroupName())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\":\"duplicate group received\"}");
			}
			permissionGroupRepository.save(model);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"permission-groups-created\"}");
	}

	public ResponseEntity<?> getPermissionGroup(Integer groupId) {
		UsersPermissionGroupModel groupValidation = permissionGroupRepository.findByGroupId(groupId);
		if (Objects.isNull(groupValidation))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"permission-group-not-found\"}");
		else
			return ResponseEntity.status(HttpStatus.OK).body(groupValidation);

	}

	public ResponseEntity<?> modifyPermissionGroup(UsersPermissionGroupModel model) {
		UsersPermissionGroupModel groupValidation = permissionGroupRepository.findByGroupId(model.getGroupId());
		if (Objects.isNull(groupValidation))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"permission-group-not-found\"}");
		else {
			permissionGroupRepository.save(model);
			return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"permission-group-modified\"}");
		}
	}

	public ResponseEntity<?> getPermissionGroupList(Long organizationId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(permissionGroupRepository.findByOrganizationId(organizationId));
	}

	public ResponseEntity<?> searchGroup(String groupName) {

		Set<UsersPermissionGroupModel> group = permissionGroupRepository.findByGroupNameContainsIgnoreCase(groupName);
		return ResponseEntity.status(HttpStatus.OK).body(group);
	}

	/*
	 * user role apis
	 */

	/*
	 * public ResponseEntity<?> createBeUserRole(List<UserRoleModel> models) {
	 * 
	 * models.stream().forEach(model -> { beUserRoleRepository.save(model); });
	 * 
	 * return
	 * ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"roles-created\"}");
	 * }
	 */

	public ResponseEntity<?> createUserRole(List<UserRoleModel> models) {
		List<String> errorMessages = new ArrayList<>();

		for (UserRoleModel model : models) {
			// Check if Key is empty or already exists
			if (StringUtils.isBlank(model.getKey())) {
				errorMessages.add("Role Key cannot be empty.");
			} else if (beUserRoleRepository.findByKey(model.getKey()).isPresent()) {
				errorMessages.add("RoleKey '" + model.getKey() + "' already exists.");
			}

			// Check if Name is empty or already exists
			if (StringUtils.isBlank(model.getName())) {
				errorMessages.add("Role Name cannot be empty.");
			} else if (beUserRoleRepository.findByName(model.getName()).isPresent()) {
				errorMessages.add("RoleName '" + model.getName() + "' already exists.");
			}

			// Check if both Key and Name already exist
			if (beUserRoleRepository.findByKeyAndName(model.getKey(), model.getName()).isPresent()) {
				errorMessages.add("RoleKey'" + model.getKey() + ", " + "and RoleName" + " " + model.getName()
						+ "' already exist.");
			}

			// If there are no errors, save the UserRole
			if (errorMessages.isEmpty()) {
				beUserRoleRepository.save(model);
			}
		}

		// Return success message if no errors, else return error messages
		if (errorMessages.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"roles-created\"}");
		} else {
			String errorMessage = String.join(" ", errorMessages);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"" + errorMessage + "\"}");
		}
	}

	public ResponseEntity<?> getUserRole(Long roleId) {
		Optional<UserRoleModel> roleValidation = beUserRoleRepository.findByRoleId(roleId);
		if (!roleValidation.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"role-not-found\"}");
		else
			return ResponseEntity.status(HttpStatus.OK).body(roleValidation);
	}

	public ResponseEntity<?> getUserRoles(Long organizationId) {
		return ResponseEntity.status(HttpStatus.OK).body(beUserRoleRepository.findByOrganizationId(organizationId));
	}

	public ResponseEntity<?> modifyUserRole(UserRoleModel model) {
		Optional<UserRoleModel> roleValidation = beUserRoleRepository.findByRoleId(model.getId());
		if (!roleValidation.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"role-not-found\"}");
		else {
			beUserRoleRepository.save(model);
			return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"role-modified\"}");
		}
	}

	public ResponseEntity<?> searchRoleKeys(String key) {
		Set<UserRoleModel> keyData = beUserRoleRepository.findByKeyIgnoreCaseEquals(key);

		return ResponseEntity.status(HttpStatus.OK).body(keyData);
	}

	/*
	 * Permission apis
	 */

	public ResponseEntity<?> createPermissions(List<PermissionRequestModel> models) {
		for (PermissionRequestModel model : models) {
			if (permissionModelRepository.existsByKey(model.getKey())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\":\"duplicate key exists\"}");
			}

			UsersPermissionModel savedPermission = permissionModelRepository
					.save(ApiConverter.mapPermissonRequestToModel(model));

			if (Objects.nonNull(model.getRoles()) && !model.getRoles().isEmpty()) {
				model.getRoles().forEach(r -> {

					Optional<UserRoleModel> roleInfo = beUserRoleRepository.findByNameIgnoreCase(r);
					if (roleInfo.isPresent())
						createPermissionRoleRelation(savedPermission.getId(), roleInfo.get().getId().intValue());
				});
			}
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"permission-created\"}");
	}

	public ResponseEntity<?> createPermissionRoleRelation(Integer permissionId, Integer roleId) {
		UsersRolePermissionRelation relation = new UsersRolePermissionRelation();
		relation.setPermissionId(permissionId);
		relation.setRoleId(roleId);
		relation.setIsActive(0);
		rolePermissionRepository.save(relation);
		return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"role-permission-relation-created\"}");
	}

	public ResponseEntity<?> getPermission(Integer permissionId) {

		Optional<UsersPermissionModel> permissionValidation = permissionModelRepository
				.findByPermissionId(permissionId);
		if (!permissionValidation.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"permission-not-found\"}");
		else {
			PermissionRequestModel response = ApiConverter.mapPModelToRequest(permissionValidation.get(),
					extractRoles(permissionValidation.get().getId()));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	public Set<String> extractRoles(Integer permissionId) {

		return rolePermissionRepository.findByPermissionId(permissionId).stream().map(rel -> {

			Optional<UserRoleModel> role = beUserRoleRepository.findByRoleId(rel.getRoleId().longValue());
			if (role.isPresent())
				return role.get().getName();
			else
				return "";

		}).filter(r -> !r.isEmpty()).collect(Collectors.toSet());

	}

	public ResponseEntity<?> modifyPermission(PermissionRequestModel model) {
		Optional<UsersPermissionModel> permissionValidation = permissionModelRepository
				.findByPermissionId(model.getId());
		if (!permissionValidation.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"permission-not-found\"}");
		else {
			UsersPermissionModel savedPermission = permissionModelRepository
					.save(ApiConverter.mapPermissonRequestToModel(model));
			if (Objects.nonNull(model.getRoles()) && !model.getRoles().isEmpty()) {

				Set<UsersRolePermissionRelation> relations = rolePermissionRepository
						.findByPermissionId(savedPermission.getId());

				deactivatePermissionRoleRelation(relations);
				model.getRoles().forEach(r -> {

					Optional<UserRoleModel> roleInfo = beUserRoleRepository.findByNameIgnoreCase(r);
					if (roleInfo.isPresent())
						createPermissionRoleRelation(savedPermission.getId(), roleInfo.get().getId().intValue());
				});
			}
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"permission-modified\"}");
		}

	}

	public void deactivatePermissionRoleRelation(Set<UsersRolePermissionRelation> relations) {
		relations.stream().peek(rel -> rel.setIsActive(1)).forEach(rolePermissionRepository::save);
	}

	public ResponseEntity<?> getPermissionList(Long organizationId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(permissionModelRepository.findByOrganizationId(organizationId));
	}

	public ResponseEntity<?> searchPermission(String key) {

		Set<UsersPermissionModel> keyData = permissionModelRepository.findByKeyIgnoreCaseEquals(key);

		return ResponseEntity.status(HttpStatus.OK).body(keyData);
	}

	public ResponseEntity<?> getPagePermissions(String roleKey, String pageKey, String key) {
		Set<String> permissionKeys = new LinkedHashSet<>();
		if (Objects.isNull(key) || key.isBlank()) {
			permissionKeys = permissionModelRepository.getPermisionsByPageAndRole(roleKey, pageKey);
		} else {
			permissionKeys = permissionModelRepository.getPermisionsByPageAndRoleAndKey(roleKey, pageKey, key);
		}

		return ResponseEntity.status(HttpStatus.OK).body(permissionKeys);
	}

	public ResponseEntity<?> getRolePagePermissionsInfo(Set<String> pages, String roleKey) {
		List<PermissionPageView> pagesInfo = rolePermissionRepository.findByRoleIdAndPageId(pages, roleKey);
		if (pagesInfo.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"NOT_FOUND\"}");
		else
			return ResponseEntity.status(HttpStatus.OK).body(pagesInfo);
	}

	public ResponseEntity<?> modifyPagePermissionRelation(Set<Integer> permissions, Integer roleId, Integer pageId) {

		Set<UsersRolePermissionRelation> relations = rolePermissionRepository
				.findByRoleIdAndPageIdAndPermissionId(roleId, pageId);
		/*
		 * roleBackRelations
		 */
		deactivatePermissionRoleRelation(relations);

		if (!permissions.isEmpty()) {
			permissions.stream().map(permission -> {
				UsersRolePermissionRelation rel = new UsersRolePermissionRelation();
				rel.setPermissionId(permission);
				rel.setRoleId(roleId);
				rel.setIsActive(0);
				return rel;
			}).forEach(rolePermissionRepository::save);
		}

		return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"OK\"}");
	}

	public ResponseEntity<?> getRolePermissionsInfo(String roleKey) {
		List<PermissionPageView> PermissionInfo = rolePermissionRepository.findPermissionByRoleId(roleKey);

		if (PermissionInfo.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"permissions-not-found\"}");
		else {

			return ResponseEntity.status(HttpStatus.OK).body(PermissionInfo);
		}

	}
//	rolePermissionRepository
	public ResponseEntity<?> createUserRoleRelation(Integer roleId, Integer permissionId) {
		UsersRolePermissionRelation relation = new UsersRolePermissionRelation();
		relation.setRoleId(roleId);
		relation.setPermissionId(permissionId);
		rolePermissionRepository.save(relation);
		return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"role-user-relation-created\"}");
	}

}
