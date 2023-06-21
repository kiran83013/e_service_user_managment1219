package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.PagesService;
import com.travel.travtronics.users.model.PagesModel;

@RestController
@RequestMapping("/page")
public class PagesController {

	@Autowired
	PagesService pageService;

	@PostMapping(value = "/create-page", consumes = "application/json", produces = "application/json")
	public APIResponse create(@RequestBody PagesModel model) {
		return pageService.create(model);
	}

	@PutMapping(value = "/update-page", consumes = "application/json", produces = "application/json")
	public APIResponse update(@RequestBody PagesModel model) {
		return pageService.update(model);
	}

	@GetMapping(value = "/get-pageid", produces = "application/json")
	public APIResponse getById(@RequestParam Long pageId) {
		return pageService.getById(pageId);
	}

	@GetMapping(value = "/get-pagelistbyappilicationId", produces = "application/json")
	public APIResponse pagelist(@RequestParam Long applicationId) {
		return pageService.pagelist(applicationId);
	}

	@GetMapping(value = "/get-pagekey", produces = "application/json")
	public APIResponse pagekey(@RequestParam String pageKey) {
		return pageService.pagekey(pageKey);
	}

	@GetMapping(value = "/get-pagelistbyorgid", produces = "application/json")
	public APIResponse list(@RequestParam Long organizationId) {
		return pageService.list(organizationId);
	}

	@GetMapping(value = "/search-page")
	public APIResponse searchPage(@RequestParam String pageName) {
		return pageService.searchPage(pageName);
	}

	@GetMapping(value = "get-menu-pages")
	public APIResponse getMenuPages(@RequestParam Integer roleId) {

		return pageService.getMenuPages(roleId);
	}

}
