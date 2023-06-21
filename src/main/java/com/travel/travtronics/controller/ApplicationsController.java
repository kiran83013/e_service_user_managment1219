package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.ApplicationsService;
import com.travel.travtronics.users.model.ApplicationsModel;

@RestController
public class ApplicationsController {

	@Autowired
	ApplicationsService applicationService;

	@PostMapping(value = "/create-application", consumes = "application/json", produces = "application/json")
	public APIResponse create(@RequestBody ApplicationsModel application) {
		return applicationService.create(application);
	}

	@PutMapping(value = "/update-application", consumes = "application/json", produces = "application/json")
	public APIResponse update(@RequestBody ApplicationsModel application) {
		return applicationService.update(application);
	}

	@GetMapping(value = "/get-applicationid", produces = "application/json")
	public APIResponse getById(@RequestParam Long applicationId) {
		return applicationService.getById(applicationId);
	}

	@GetMapping(value = "/get-applicationlist", produces = "application/json")
	public APIResponse list(@RequestParam Long organizationId) {
		return applicationService.list(organizationId);
	}

	@GetMapping(value = "/search-application")
	public APIResponse searchApplication(@RequestParam String applicationName) {
		return applicationService.searchApplication(applicationName);
	}
}
