package com.travel.travtronics.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.dto.PermissionRequestModel;
import com.travel.travtronics.service.RolePermissionService;
import com.travel.travtronics.users.model.UserRoleModel;
import com.travel.travtronics.users.model.UsersPermissionGroupModel;

@RestController
@RequestMapping("/rolePermissions")
public class RolePermissionsController {

	@Autowired
	RolePermissionService rolePermissionsService;

//	@GetMapping("/getRoles")
//	public ResponseEntity<?> getRoles() {
//
//		return rolePermissionsService.getRoles();
//	}

	/*
	 * user permission
	 */

	/*-----------permission-groups---------------*/
	@PostMapping(value = "/add-permission-groups")
	public ResponseEntity<?> createPermissionGroup(@RequestBody List<UsersPermissionGroupModel> models) {
		return rolePermissionsService.createPermissionGroup(models);
	}

	@GetMapping(value = "/get-permission-group")
	public ResponseEntity<?> getPermissionGroup(@RequestParam Integer groupId) {
		return rolePermissionsService.getPermissionGroup(groupId);
	}

	@PutMapping(value = "/modify-permission-groups")
	public ResponseEntity<?> modifyPermissionGroup(@RequestBody UsersPermissionGroupModel model) {
		return rolePermissionsService.modifyPermissionGroup(model);
	}

	@GetMapping(value = "/get-permission-groups")
	public ResponseEntity<?> getPermissionGroupList(@RequestParam Long organizationId) {
		return rolePermissionsService.getPermissionGroupList(organizationId);
	}

	@GetMapping(value = "/search-group")
	public ResponseEntity<?> searchGroup(@RequestParam String groupName) {
		return rolePermissionsService.searchGroup(groupName);
	}

	/*-----------user-role---------------*/

	@PostMapping(value = "/add-user-role")
	public ResponseEntity<?> createUserRole(@RequestBody List<UserRoleModel> models) {
		return rolePermissionsService.createUserRole(models);
	}

	@GetMapping(value = "/get-user-role")
	public ResponseEntity<?> getUserRole(@RequestParam Long roleId) {
		return rolePermissionsService.getUserRole(roleId);
	}

	@GetMapping(value = "/get-user-roles")
	public ResponseEntity<?> getUserRoles(@RequestParam Long organizationId) {
		return rolePermissionsService.getUserRoles(organizationId);
	}

	@PutMapping(value = "/modify-user-role")
	public ResponseEntity<?> modifyUserRole(@RequestBody UserRoleModel model) {
		return rolePermissionsService.modifyUserRole(model);
	}

	@GetMapping(value = "/search-rolekeys")
	public ResponseEntity<?> searchRoleKeys(@RequestParam String key) {
		return rolePermissionsService.searchRoleKeys(key);
	}

	@PostMapping(value = "/add-permission")
	public ResponseEntity<?> createPermissions(@RequestBody List<PermissionRequestModel> model) {
		return rolePermissionsService.createPermissions(model);
	}

	@GetMapping(value = "/get-permission")
	public ResponseEntity<?> getPermission(@RequestParam Integer permissionId) {
		return rolePermissionsService.getPermission(permissionId);
	}

	@PutMapping(value = "/modify-permission")
	public ResponseEntity<?> modifyPermission(@RequestBody PermissionRequestModel model) {
		return rolePermissionsService.modifyPermission(model);
	}

	@GetMapping(value = "/get-permissions")
	public ResponseEntity<?> getPermissionsList(@RequestParam Long organizationId) {
		return rolePermissionsService.getPermissionList(organizationId);
	}

	@GetMapping(value = "/search-permission")
	public ResponseEntity<?> searchPermission(@RequestParam String key) {
		return rolePermissionsService.searchPermission(key);
	}

	@GetMapping(value = "/get-page-permissions")
	public ResponseEntity<?> getPagePermissions(@RequestParam String roleKey, @RequestParam String pageKey,
			@RequestParam(required = false) String key) {
		return rolePermissionsService.getPagePermissions(roleKey, pageKey, key);
	}

	@PostMapping(value = "/get-role-page-permissions")
	public ResponseEntity<?> getRolePagePermissions(@RequestBody Set<String> pages, @RequestParam String roleKey) {
		return rolePermissionsService.getRolePagePermissionsInfo(pages, roleKey);
	}

	@PostMapping(value = "/modify-page-permission-relation")
	public ResponseEntity<?> modifyPagePermissionRelation(@RequestBody Set<Integer> permissions,
			@RequestParam Integer roleId, @RequestParam Integer pageId) {
		return rolePermissionsService.modifyPagePermissionRelation(permissions, roleId, pageId);
	}

	@GetMapping(value = "/get-role-permissions")
	public ResponseEntity<?> getRolePermissions(@RequestParam String roleKey) {
		return rolePermissionsService.getRolePermissionsInfo(roleKey);
	}
	
	@PostMapping(value = "/user-role-relation")
	public ResponseEntity<?> createUserRoleRelation(@RequestParam Integer roleId, @RequestParam Integer permissionId) {
		return rolePermissionsService.createUserRoleRelation(roleId,permissionId);
	}
}
