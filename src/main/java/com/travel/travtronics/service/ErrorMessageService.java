package com.travel.travtronics.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.travel.travtronics.users.model.User;

public class ErrorMessageService {

	public static ResponseEntity<String> clientErrorException(HttpClientErrorException ex) {
		return new ResponseEntity<String>(ex.getResponseBodyAsString(), ex.getStatusCode());
	}

	public static ResponseEntity<String> duplicateUserPhonenUmber(String phoneNumber) {
		return new ResponseEntity<String>(
				"{\"message\":\"user with phone number " + phoneNumber + " already registered\"}", HttpStatus.CONFLICT);
	}

	public static ResponseEntity<String> duplicateEmail(String email) {

		return new ResponseEntity<String>("{\"message\":\"User with email " + email + " already registered\"}",
				HttpStatus.CONFLICT);
	}

	public static ResponseEntity<String> socialAccountNotRegistered(String email, String socialType) {
		return new ResponseEntity<String>("{\"message\":\"Register with " + socialType + " to continue\"}",
				HttpStatus.CONFLICT);
	}

	public static ResponseEntity<String> userIdNotFound(Long userId) {
		return new ResponseEntity<String>("{\"message\":\"user with id " + userId + " not found\"}",
				HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<String> userDeleted(Long userId) {
		return new ResponseEntity<String>("{\"message\":\"user with id " + userId + " deleted successfully\"}",
				HttpStatus.OK);
	}

	public static ResponseEntity<String> roleNotFound(String roleName) {
		return new ResponseEntity<String>("{\"message\":\"Role " + roleName + " is not found\"}", HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<String> permissionNotFound(String permissionName) {
		return new ResponseEntity<String>("{\"message\":\"Permission " + permissionName + " is not found\"}",
				HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<String> roleIdNotFound(Long roleId) {
		return new ResponseEntity<String>("{\"message\":\"Role Id " + roleId + " is not found\"}",
				HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<String> userRoleRelationNotFound(Long userId, Long roleId) {
		return new ResponseEntity<String>(
				"{\"message\":\"Relation between user ID " + userId + " and role ID " + roleId + " is not found\"}",
				HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<String> rolePermissionRelationNotFound(Long roleId, Long permissionId) {
		return new ResponseEntity<String>("{\"message\":\"Relation between role ID " + roleId + " and permission ID "
				+ permissionId + " is not found\"}", HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<?> duplicateRoleDetails(String roleName, String roleKey) {
		return new ResponseEntity<String>(
				"{\"message\":\"Role with roleName " + roleName + " and rolekey " + roleKey + " already exists\"}",
				HttpStatus.CONFLICT);
	}

	public static ResponseEntity<?> RoleIdNotFound(Long roleId) {
		return new ResponseEntity<String>("{\"message\":\"Role id" + roleId + " is not found\"}", HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<?> duplicatePermissionDetails(String permissionName, String permissionKey) {
		return new ResponseEntity<String>("{\"message\":\"Permission with PermissionName " + permissionName
				+ " and rolekey " + permissionKey + " already exists\"}", HttpStatus.CONFLICT);
	}

	public static ResponseEntity<?> PermissionIdNotFound(Long permissionId) {
		return new ResponseEntity<String>("{\"message\":\"Permission id" + permissionId + " is not found\"}",
				HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<?> UserEmailNotFound(String email) {
		return new ResponseEntity<String>("{\"message\":\"User with this " + email + " is not valid\"}",
				HttpStatus.CONFLICT);
	}

	public static ResponseEntity<?> userErrors(User model) {

		List<String> errors = Arrays.asList("User with email " + model.getEmail() + " already registered",
				"User with userName  " + model.getUserName() + "  already exists",
				"User name does not conatin any spaces");

		Map<String, Object> error = new HashMap<>();
		error.put("error", errors);
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

}
