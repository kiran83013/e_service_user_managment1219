package com.travel.travtronics.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.travel.travtronics.exception.NotFoundException;
import com.travel.travtronics.request.AceessTokenRequestModel;
import com.travel.travtronics.request.LoginRequestModel;
import com.travel.travtronics.service.AuthenticationService;
import com.travel.travtronics.user.entity.GetToken;
import com.travel.travtronics.user.entity.KeycloakUserModel;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationService authenticationService;

	@ApiIgnore
	@PostMapping(value = "/createUser")
	public @ResponseBody ResponseEntity<?> signUp(@RequestBody KeycloakUserModel userModel) {

		return authenticationService.createUser(userModel);

	}

	@ApiIgnore
	@PostMapping(value = "/obtainToken", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<?> obtainToken(@RequestBody GetToken model) {

		return authenticationService.obtainToken(model);

	}

	@PostMapping(value = "/userLogin")
	public @ResponseBody ResponseEntity<?> login(@RequestBody LoginRequestModel model)
			throws NotFoundException, JsonParseException, JsonMappingException, IOException {

		return authenticationService.login(model);

	}

	@PostMapping(value = "/updatePassword", produces = "application/json")
	public @ResponseBody ResponseEntity<?> updatePassword(@RequestParam String id, @RequestParam String password) {

		return authenticationService.updatePassword(id, password);

	}

	@PostMapping(value = "/verifyToken")
	public ResponseEntity<?> verifyAccessToken(@RequestBody AceessTokenRequestModel model) {

		return authenticationService.verifyAccessToken(model);

	}

	@PostMapping(value = "/refresh-token", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<?> refreshToken(@RequestBody AceessTokenRequestModel model) {
		return authenticationService.refreshToken(model);
	}

	@ApiIgnore
	@PutMapping(value = "/update-user-claim")
	public ResponseEntity<?> updateUserClaim(@RequestParam Long userId, @RequestParam String role)
			throws NotFoundException {

		return authenticationService.updateUserClaim(userId, role);

	}
	
	@PostMapping(value = "/user-logout/{userId}")
	public ResponseEntity<?> userLogout(@PathVariable Long userId) {
		return authenticationService.userLogout(userId);
	}

}
