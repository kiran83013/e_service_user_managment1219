package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.dto.UserSearchRequestDto;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.UserManagementService;
import com.travel.travtronics.users.model.User;
import com.travel.travtronics.util.SortType;

@RestController
@RequestMapping("/userManagement")
public class UserManagmentController {

	@Autowired
	UserManagementService userManagementService;

	/*
	 * @PostMapping("/createUser") public ResponseEntity<?> createUser(@RequestBody
	 * User model, @RequestParam(required = false) String userRole) throws Exception
	 * { return userManagementService.createUser(model, userRole); }
	 */

	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User model) throws Exception {
		return userManagementService.updateUser(model);
	}

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<?> getUser(@PathVariable Long userId) throws Exception {
		return userManagementService.getUser(userId);
	}

	@GetMapping("/get-customer-users")
	public ResponseEntity<?> getUsersByCustomer(@RequestParam Long customerId, @RequestParam Long organizationId) {
		return userManagementService.getUsersByCustomer(customerId, organizationId);
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getUsers(@RequestParam Long organizationId) {
		return userManagementService.getUsers(organizationId);
	}

	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody User model) {
		return userManagementService.createUser(model);
	}

	@GetMapping(value = "/list-get-user-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(required = false) String userName,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "userId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return userManagementService.getPagenationByOrganization(organizationId, userName, pageNo, pageSize, sortBy,
				sortType);
	}

	@GetMapping(value = "/get-user-by-name", produces = "application/json")
	public ResponseEntity<?> getUserByName(@RequestParam String name) {
		return userManagementService.getUserByName(name);
	}

	@PostMapping(value = "/search-user")
	public ResponseEntity<?> searchUser(@RequestBody UserSearchRequestDto model) {
		return userManagementService.searchUser(model);
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestParam(required = false) String email) throws Exception {
		return userManagementService.forgotPassword(email);
	}

}
